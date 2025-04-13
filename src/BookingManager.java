import java.time.LocalDateTime;

public class BookingManager {
    private final BookingStrategy bookingStrategy;
    private final NotificationStrategy notificationStrategy;
    private final AvailabilityService availabilityService;

    public BookingManager(BookingStrategy bookingStrategy, NotificationStrategy notificationStrategy, AvailabilityService availabilityService) {
        this.bookingStrategy = bookingStrategy;
        this.notificationStrategy = notificationStrategy;
        this.availabilityService = availabilityService;
    }

    public void createBooking(Customer customer, Table table, LocalDateTime dateTime, Integer guests) {
        String tableId = table.getTableId();

        boolean reserved = availabilityService.checkAndReserve(tableId, dateTime, guests);
        if (!reserved) {
            System.out.println("Table not available for booking at " + dateTime);
            return;
        }

        Booking booking = bookingStrategy.createBooking(customer, table, dateTime, guests);

        String message = "At " + dateTime + " on Table " + table.getTableNumber();
        notificationStrategy.sendNotification(customer, message);

        System.out.println("Booking created successfully: " + booking);
    }
}
