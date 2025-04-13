import java.time.LocalDateTime;

public class Booking {
    private String bookingId;
    private Customer customer;
    private Table table;
    private LocalDateTime dateTime;
    private Integer guestCount;

    public Booking(String bookingId, Customer customer, Table table, LocalDateTime dateTime, Integer guestCount) {
        this.bookingId = bookingId;
        this.customer = customer;
        this.table = table;
        this.dateTime = dateTime;
        this.guestCount = guestCount;
    }

    public Booking() {

    }

    @Override
    public String toString() {
        return "Booking{" + "bookingId='" + bookingId + '\'' + ", customer=" + customer + ", table=" + table + ", dateTime=" + dateTime + ", guestCount=" + guestCount + '}';
    }
}
