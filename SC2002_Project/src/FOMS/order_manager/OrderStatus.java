package FOMS.order_manager;

public enum OrderStatus {
    NEW("New"), 
    READY_TO_PICKUP("Ready to Pickup"), 
    COMPLETED("Completed"), 
    CANCELLED("Cancelled");

    private final String status;

    OrderStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.status;
    }
}
