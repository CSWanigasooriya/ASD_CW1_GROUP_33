import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        BookingManager bookingManager = new BookingManager(new CallBookingStrategy(), new SMSNotification(), new AvailabilityService(new MockTableRepository()));
        Table requestedTable = new Table();
        requestedTable.setTableId("1");
        requestedTable.setCapacity(12);
        bookingManager.createBooking(new Customer("Chamath", "Colombo", "123123"), requestedTable, LocalDateTime.now(), 2);
    }
}