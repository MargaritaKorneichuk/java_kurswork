package kurswork.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kurswork.order.entity.Order;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
