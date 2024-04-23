package FOMS.order_manager;


public interface PaymentStrategy {
    void pay(double amount);
    String getPaymentMethod();
}
