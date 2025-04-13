public class EmailNotification implements NotificationStrategy {

    @Override
    public void sendNotification(Customer customer, String message) {
        System.out.println("Sending Email to " + customer.getName() + ": " + message);
    }
}
