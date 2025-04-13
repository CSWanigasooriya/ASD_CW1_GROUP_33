public class SMSNotification implements NotificationStrategy {
    private static SMSNotification instance;

    private SMSNotification() {}

    public static synchronized SMSNotification getInstance(){
        if(instance == null){
            instance = new SMSNotification();
        }
        return instance;
    }
    @Override
    public void sendNotification(Customer customer, String message) {
        System.out.println("Sending SMS to " + customer.getName() + ": " + message);
    }
}
