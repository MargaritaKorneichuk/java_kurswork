package kurswork.order.service;

import kurswork.order.dto.OrderDto;
import kurswork.order.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order saveOrder(OrderDto orderDto);
    Optional<Order> findById(Long id);
    List<OrderDto> findAllOrders();
}
