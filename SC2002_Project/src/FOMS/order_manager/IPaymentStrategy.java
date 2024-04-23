package FOMS.order_manager;


public interface IPaymentStrategy {
    void pay(double amount);
    String getPaymentMethod();
}
