package FOMS.process_manager;

import FOMS.order_manager.Order;

public interface IOrderFilter {
    boolean matches(Order order);
}
