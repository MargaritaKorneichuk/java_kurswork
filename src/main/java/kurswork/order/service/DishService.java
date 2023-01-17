package kurswork.order.service;

import kurswork.order.dto.DishDto;
import kurswork.order.entity.Dish;

import java.util.List;

public interface DishService {
    Dish saveDish(DishDto dishDto);
    Dish findByName(String name);
    List<DishDto> findAllDish();
}
