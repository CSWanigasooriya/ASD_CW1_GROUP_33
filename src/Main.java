import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        BookingManager bookingManager = new BookingManager(CallBookingStrategy.getInstance(), SMSNotification.getInstance(), new AvailabilityService(new MockTableRepository()));
        Table requestedTable = new Table("1", 1, 12, "Garden");
        bookingManager.createBooking(new Customer("Chamath", "Colombo", "123123"), requestedTable, LocalDateTime.now(), 2);
    }
}