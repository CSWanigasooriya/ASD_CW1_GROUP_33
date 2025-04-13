import java.time.LocalDateTime;
import java.util.Date;

public class BookingManager {
    private BookingStrategy bookingStrategy;
    private NotificationStrategy notificationStrategy;
    private final AvailabilityService availabilityService;

    public BookingManager(BookingStrategy bookingStrategy,
                          NotificationStrategy notificationStrategy,
                          AvailabilityService availabilityService) {
        this.bookingStrategy = bookingStrategy;
        this.notificationStrategy = notificationStrategy;
        this.availabilityService = availabilityService;
    }

    public void setBookingStrategy(BookingStrategy bookingStrategy) {
        this.bookingStrategy = bookingStrategy;
    }

    public void createBooking(Customer customer, Table table, LocalDateTime dateTime, Integer guests) {
        String tableId = table.getTableId();

        boolean reserved = availabilityService.checkAndReserve(tableId, dateTime, guests);
        if (!reserved) {
            System.out.println("Table not available for booking at " + dateTime);
            return;
        }

        Booking booking = bookingStrategy.createBooking(customer, table, dateTime, guests);

        String message = "Booking confirmed for " + customer.getName() +
                " at " + dateTime + " on Table " + table.getTableNumber();
        notificationStrategy.sendNotification(customer, message);

        System.out.println("Booking created successfully: " + booking);
    }
}
