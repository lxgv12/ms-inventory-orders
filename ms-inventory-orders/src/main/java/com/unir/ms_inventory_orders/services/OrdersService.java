package com.unir.ms_inventory_orders.services;

import java.util.List;
import com.unir.ms_inventory_orders.model.db.Order;
import com.unir.ms_inventory_orders.model.request.OrderRequest;

public interface OrdersService {
    Order getOrder(String id);
	List<Order> getOrders();
    Order createOrder(OrderRequest request);
}
