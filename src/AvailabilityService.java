import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AvailabilityService {

    private final TableRepository tableRepository;
    private final Map<String, Lock> lockRegistry = new ConcurrentHashMap<>();

    public AvailabilityService(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    public boolean checkAvailability(String tableId, LocalDateTime dateTime, int guests) {
        Table table = tableRepository.findById(tableId);
        if (table == null || dateTime == null) return false;
        return table.getCapacity() >= guests && !table.hasReservation(dateTime);
    }

    public void reserve(String tableId, LocalDateTime dateTime) {
        Table table = tableRepository.findById(tableId);
        if (table == null || dateTime == null) return;

        Booking dummyBooking = new Booking(); // Populate as needed
        table.getBookings().put(dateTime, dummyBooking);
    }

    public boolean checkAndReserve(String tableId, LocalDateTime dateTime, int guests) {
        Lock lock = lockRegistry.computeIfAbsent(tableId, _ -> new ReentrantLock());

        lock.lock();
        try {
            Table table = tableRepository.findById(tableId);
            if (table == null) return false;

            if (checkAvailability(tableId, dateTime, guests)) {
                reserve(tableId, dateTime);
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }
}