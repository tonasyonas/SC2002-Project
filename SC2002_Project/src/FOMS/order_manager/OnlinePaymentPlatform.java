package FOMS.order_manager;

public class OnlinePaymentPlatform implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        // Simulate online payment platform
        System.out.println("Simulating an online payment platform transaction for $" + amount);
    }

    @Override
    public String getPaymentMethod() {
        return "Online Payment Platform";
    }
}
