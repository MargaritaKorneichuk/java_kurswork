package kurswork.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kurswork.order.entity.Dish;

public interface DishRepository extends JpaRepository<Dish,Long> {
    Dish findByName(String name);
}
