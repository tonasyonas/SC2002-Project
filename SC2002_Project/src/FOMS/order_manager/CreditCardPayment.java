package FOMS.order_manager;

public class CreditCardPayment implements IPaymentStrategy {
    @Override
    public void pay(double amount) {
        // Simulate credit card payment
        System.out.println("Simulating a credit card payment for $" + amount);
    }

    @Override
    public String getPaymentMethod() {
        return "Credit Card";
    }
}
