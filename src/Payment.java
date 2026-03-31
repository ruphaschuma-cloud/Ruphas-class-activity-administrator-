public class Payment {
    private String paymentID;
    private double amount;
    private String method;

    public Payment(String paymentID, double amount, String method) {
        this.paymentID = paymentID;
        this.amount = amount;
        this.method = method;
    }

    public double processPayment(double amount) {
        System.out.println("Processing payment of " + amount + " via " + method);
        return amount;
    }

    public void generateReceipt(double amount) {
        System.out.println("Receipt generated for Payment ID: " + paymentID +
                           " | Amount: " + amount +
                           " | Method: " + method);
    }

    public String getPaymentID() { return paymentID; }
    public double getAmount() { return amount; }
    public String getMethod() { return method; }
}
