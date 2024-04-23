package FOMS.process_manager;

import FOMS.order_manager.*;

public class IDOrderFilter implements IOrderFilter {
    private String idToFilter;

    public IDOrderFilter(String idToFilter) {
        this.idToFilter = idToFilter;
    }

    @Override
    public boolean matches(Order order) {
        return order.getOrderId().equals(idToFilter);
    }
}
