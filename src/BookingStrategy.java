import java.time.LocalDateTime;

public interface BookingStrategy {
    Booking createBooking(Customer customer, Table table, LocalDateTime date, Integer guests);
}
