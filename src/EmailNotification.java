public class EmailNotification implements NotificationStrategy {
    private static EmailNotification instance;

    private EmailNotification() {}

    public static synchronized EmailNotification getInstance(){
        if(instance == null){
            instance = new EmailNotification();
        }
        return instance;
    }
    @Override
    public void sendNotification(Customer customer, String message) {
        System.out.println("Sending Email to " + customer.getName() + ": " + message);
    }
}
