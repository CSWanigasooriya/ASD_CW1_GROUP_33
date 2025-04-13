import java.time.LocalDateTime;

public class WalkInBookingStrategy implements BookingStrategy {

    @Override
    public Booking createBooking(Customer customer, Table table, LocalDateTime date, Integer guests) {
        Booking booking = new Booking(BookingIdGenerator.generateBookingId(), customer, table, date, guests, BookingType.WALK_IN);
        System.out.println(booking);
        return booking;
    }
}
