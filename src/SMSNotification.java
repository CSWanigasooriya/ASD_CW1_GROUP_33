public class SMSNotification implements NotificationStrategy {
    @Override
    public void sendNotification(Customer customer, String message) {
        System.out.println("Sending SMS to " + customer.getName() + ": " + message);
    }
}
