import java.time.LocalDateTime;

public class Booking {
    private final String bookingId;
    private final Customer customer;
    private final Table table;
    private final LocalDateTime dateTime;
    private final Integer guestCount;
    private final BookingType bookingType;

    public Booking(String bookingId, Customer customer, Table table, LocalDateTime dateTime, Integer guestCount, BookingType bookingType) {
        this.bookingId = bookingId;
        this.customer = customer;
        this.table = table;
        this.dateTime = dateTime;
        this.guestCount = guestCount;
        this.bookingType = bookingType;
    }

    @Override
    public String toString() {
        return "Booking{" + "bookingId='" + bookingId + '\'' + ", customer=" + customer + ", table=" + table + ", dateTime=" + dateTime + ", guestCount=" + guestCount + ", bookingType=" + bookingType + '}';
    }
}
