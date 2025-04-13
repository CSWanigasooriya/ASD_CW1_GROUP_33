import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        BookingManager bookingManager = new BookingManager(new CallBookingStrategy(), new SMSNotification(), new AvailabilityService(new MockTableRepository()));
        bookingManager.createBooking(new Customer("Chamath", "Colombo", "123123"), new Table(), LocalDateTime.now(), 12);
    }
}