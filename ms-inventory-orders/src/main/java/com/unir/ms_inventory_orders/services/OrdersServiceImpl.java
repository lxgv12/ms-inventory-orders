package com.unir.ms_inventory_orders.services;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unir.ms_inventory_orders.data.OrderJpaRepository;
import com.unir.ms_inventory_orders.facade.BooksFacade;
import com.unir.ms_inventory_orders.model.Book;
import com.unir.ms_inventory_orders.model.db.Order;
import com.unir.ms_inventory_orders.model.request.OrderRequest;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired 
    private OrderJpaRepository repository;

    @Autowired //Inyeccion por campo (field injection). Es la menos recomendada.
    private BooksFacade booksFacade;

    @Override
    public Order getOrder(String id) {
        return repository.findById(Long.valueOf(id)).orElse(null);
    }

    @Override
    public List<Order> getOrders() {
        List<Order> orders = repository.findAll();
        return orders.isEmpty() ? null : orders;
    }

    @Override
    public Order createOrder(OrderRequest request) {
        List<Book> books = request.getBooks().stream().map(booksFacade::getBook).filter(Objects::nonNull).toList();

        if (books.size() != request.getBooks().size()) {
            return null;
        } else {
            Order order = Order.builder()
                    .books(books.stream().map(Book::getId).collect(Collectors.toList()))
                    .build();
            repository.save(order);
            return order;
        }
    }
}
