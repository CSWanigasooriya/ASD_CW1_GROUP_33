import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Table {
    private String tableId;
    private Integer tableNumber;
    private Integer capacity;
    private String location;
    private Map<LocalDateTime, Booking> bookings = new HashMap<>();

    public Table(String tableId, Integer tableNumber, int capacity, String location) {
        this.tableId = tableId;
        this.tableNumber = tableNumber;
        this.capacity = capacity;
        this.location = location;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public Integer getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(Integer tableNumber) {
        this.tableNumber = tableNumber;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Map<LocalDateTime, Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Map<LocalDateTime, Booking> bookings) {
        this.bookings = bookings;
    }

    public boolean hasReservation(LocalDateTime dateTime) {
        if (dateTime == null) {
            return false;
        }
        return bookings.containsKey(dateTime);
    }

    @Override
    public String toString() {
        return "Table{" + "tableId='" + tableId + '\'' + ", tableNumber='" + tableNumber + '\'' + ", capacity=" + capacity + ", location='" + location + '\'' + ", bookings=" + bookings + '}';
    }


}
