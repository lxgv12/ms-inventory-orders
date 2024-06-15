package com.unir.ms_inventory_orders.data;

import org.springframework.data.jpa.repository.JpaRepository;
import com.unir.ms_inventory_orders.model.db.Order;

public interface OrderJpaRepository extends JpaRepository<Order, Long> {

}
