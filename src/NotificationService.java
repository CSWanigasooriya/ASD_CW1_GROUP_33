public class NotificationService {
    private NotificationStrategy strategy;
    public NotificationService(NotificationStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(NotificationStrategy strategy) {
        this.strategy = strategy;
    }

    public void notify(Customer customer, String message) {
        this.strategy.sendNotification(customer, message);
    }
}
