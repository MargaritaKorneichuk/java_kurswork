package kurswork.order.service;

import kurswork.order.dto.OrderDto;
import kurswork.order.entity.Order;
import kurswork.order.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService{
    private OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository){
        this.orderRepository=orderRepository;
    }
    @Override
    public Order saveOrder(OrderDto orderDto) {
        Order order=new Order();
        order.setName(orderDto.getName());
        order.setStatus("Adopted");
        order.setComposition(orderDto.getComposition());
        return orderRepository.save(order);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<OrderDto> findAllOrders() {
        List<Order> orders=orderRepository.findAll();
        return orders.stream().map((order)->convertEntityToDto(order)).collect(Collectors.toList());
    }

    private OrderDto convertEntityToDto(Order order){
        OrderDto orderDto = new OrderDto();
        orderDto.setName(order.getName());
        orderDto.setComposition(order.getComposition());
        orderDto.setId(order.getId());
        orderDto.setStatus(order.getStatus());
        return orderDto;
    }
}
