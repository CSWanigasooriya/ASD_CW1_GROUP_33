import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AvailabilityService {
    private static AvailabilityService instance;

    private final TableRepository tableRepository;
    private final Map<String, Lock> lockRegistry = new ConcurrentHashMap<>();

    private AvailabilityService(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    public static synchronized AvailabilityService getInstance(TableRepository tableRepository) {
        if (instance == null) {
            instance = new AvailabilityService(tableRepository);
        }
        return instance;
    }

    public boolean checkAvailability(String tableId, LocalDateTime dateTime, int guests) {
        System.out.println("[INFO] Checking availability for tableId: " + tableId + " at " + dateTime + " for " + guests + " guests.");

        Table table = tableRepository.findById(tableId);
        if (table == null) {
            System.out.println("[WARN] Table not found for tableId: " + tableId);
            return false;
        }

        if (dateTime == null) {
            System.out.println("[WARN] Provided dateTime is null.");
            return false;
        }

        boolean isCapacitySufficient = table.getCapacity() >= guests;
        boolean isReserved = table.hasReservation(dateTime);

        System.out.println("[DEBUG] Table capacity: " + table.getCapacity() + " | Requested guests: " + guests);
        System.out.println("[DEBUG] Reservation exists at " + dateTime + "? " + isReserved);

        if (isCapacitySufficient && !isReserved) {
            System.out.println("[INFO] Table " + table.getTableNumber() + " is available.");
            return true;
        } else {
            if (!isCapacitySufficient) {
                System.out.println("[WARN] Table " + table.getTableNumber() + " does not have enough capacity.");
            }
            if (isReserved) {
                System.out.println("[WARN] Table " + table.getTableNumber() + " is already reserved at " + dateTime);
            }
            return false;
        }
    }

    public void reserve(String tableId, LocalDateTime dateTime) {
        Table table = tableRepository.findById(tableId);
        if (table == null || dateTime == null) return;

        Booking dummyBooking = new Booking();
        table.getBookings().put(dateTime, dummyBooking);
    }

    public boolean checkAndReserve(String tableId, LocalDateTime dateTime, int guests) {
        Lock lock = lockRegistry.computeIfAbsent(tableId, _ -> new ReentrantLock());

        System.out.println("[INFO] Attempting to acquire lock for tableId: " + tableId + " at " + dateTime);

        lock.lock();
        try {
            System.out.println("[INFO] Lock acquired for tableId: " + tableId);

            Table table = tableRepository.findById(tableId);
            if (table == null) {
                System.out.println("[WARN] Table not found for tableId: " + tableId);
                return false;
            }

            System.out.println("[INFO] Checking availability for Table " + table.getTableNumber() + " with capacity: " + table.getCapacity() + " | Requested guests: " + guests + " | DateTime: " + dateTime);

            if (checkAvailability(tableId, dateTime, guests)) {
                System.out.println("[INFO] Table is available. Proceeding to reserve.");
                reserve(tableId, dateTime);
                System.out.println("[SUCCESS] Table " + table.getTableNumber() + " successfully reserved at " + dateTime);
                return true;
            } else {
                System.out.println("[WARN] Table is NOT available at " + dateTime + " for " + guests + " guests.");
                return false;
            }

        } catch (Exception ex) {
            System.err.println("[ERROR] Exception during reservation: " + ex.getMessage());
            return false;
        } finally {
            lock.unlock();
            System.out.println("[INFO] Lock released for tableId: " + tableId);
        }
    }

}