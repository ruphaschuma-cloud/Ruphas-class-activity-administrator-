// Interface: Payment
public interface Payment {
    double processPayment(double amount);
    void generateReceipt(double amount);
}