package kurswork.order.service;

import kurswork.order.dto.DishDto;
import kurswork.order.dto.UserDto;
import kurswork.order.entity.Dish;
import kurswork.order.entity.User;
import kurswork.order.repository.DishRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DishServiceImpl implements DishService{
    private DishRepository dishRepository;

    public DishServiceImpl(DishRepository dishRepository){
        this.dishRepository=dishRepository;
    }
    @Override
    public Dish saveDish(DishDto dishDto) {
        Dish dish=new Dish();
        dish.setName(dishDto.getName());
        dish.setCost(dishDto.getCost());
        dish.setComposition(dishDto.getComposition());
        return dishRepository.save(dish);
    }

    @Override
    public Dish findByName(String name) {
        return dishRepository.findByName(name);
    }

    @Override
    public List<DishDto> findAllDish() {
        List<Dish> dishes=dishRepository.findAll();
        return dishes.stream().map((dish)->convertEntityToDto(dish)).collect(Collectors.toList());
    }

    private DishDto convertEntityToDto(Dish dish){
        DishDto dishDto = new DishDto();
        dishDto.setName(dish.getName());
        dishDto.setCost(dish.getCost());
        dishDto.setComposition(dish.getComposition());
        dishDto.setId(dish.getId());
        return dishDto;
    }
}
