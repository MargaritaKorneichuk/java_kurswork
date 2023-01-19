package kurswork.order.controller;
import jakarta.validation.Valid;
import kurswork.order.dto.DishDto;
import kurswork.order.dto.OrderDto;
import kurswork.order.entity.*;
import kurswork.order.repository.DishRepository;
import kurswork.order.repository.OrderRepository;
import kurswork.order.service.DishService;
import kurswork.order.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class DishController {
    private DishRepository dishRepository;
    private DishService dishService;

    private OrderRepository orderRepository;
    private OrderService orderService;
    public DishController(DishService dishService,DishRepository dishRepository, OrderRepository orderRepository,OrderService orderService){
        this.dishService=dishService;
        this.dishRepository=dishRepository;
        this.orderRepository=orderRepository;
        this.orderService=orderService;
    }
    @GetMapping("/admin/menu")
    public String menuShow(Model model){
        List<DishDto> menu=dishService.findAllDish();
        model.addAttribute("menu",menu);
        return "menu";
    }

    @GetMapping("/admin/menu/{dish}")
    public String deleteDish(@PathVariable Dish dish){
        dishRepository.deleteById(dish.getId());
        return "redirect:/admin/menu";
    }

    @GetMapping("/admin/menu/new")
    public String newDish(Model model){
        DishDto dish=new DishDto();
        model.addAttribute("dish",dish);
        return "new_dish";
    }
    @GetMapping("/order")
    public String menu(Model model){
        List<DishDto> menu=dishService.findAllDish();
        model.addAttribute("menu",menu);
        return "order";
    }

    @GetMapping("/orders")
    public String orders(Model model){
        List<OrderDto> orders=orderService.findAllOrders();
        model.addAttribute("orders",orders);
        List<String> statuses= Stream.of(Status.values()).map(Enum::name).collect(Collectors.toList());
        model.addAttribute("statuses",statuses);
        return "orders";
    }

    @PostMapping("/admin/menu/new/save")
    public String newDishSave(@Valid @ModelAttribute("dish") DishDto dish,
                               BindingResult result,
                               Model model){
        Dish existing = dishService.findByName(dish.getName());
        if (existing != null) {
            result.rejectValue("name", null, "There is already a dish with that name");
        }
        dishService.saveDish(dish);
        return "redirect:/admin/menu";
    }

    @PostMapping("/orders/{order}")
    public String userEdit(@PathVariable Order order,@RequestParam String status){
        if (!Objects.equals(status, "Ready")){
            order.setStatus(status);
            orderRepository.save(order);
            return "redirect:/orders";
        }
        orderRepository.delete(order);
        return "redirect:/orders";
    }

    @PostMapping("/order/save")
    public String orderSave(@RequestParam String[] check){
        for(String dish_name:check){
            Dish dish=dishRepository.findByName(dish_name);
            OrderDto order=new OrderDto();
            order.setComposition(dish.getComposition());
            order.setName(dish.getName());
            orderService.saveOrder(order);
        }
        return "redirect:/home";
    }

}
