package kurswork.order.controller;

import jakarta.validation.Valid;
import kurswork.order.entity.Role;
import kurswork.order.repository.RoleRepository;
import kurswork.order.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import kurswork.order.dto.UserDto;
import kurswork.order.entity.User;
import kurswork.order.service.UserService;

import java.util.Arrays;
import java.util.List;

@Controller
public class AuthController {

    private UserService userService;

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    public AuthController(UserService userService,RoleRepository roleRepository,UserRepository userRepository) {
        this.userService = userService;
        this.roleRepository=roleRepository;
        this.userRepository=userRepository;
    }

    @GetMapping("index")
    public String home(){
        return "index";
    }

    @GetMapping("home")
    public String home1(){
        return "home";
    }


    @GetMapping("admin")
    public String admin(){
        return "admin";
    }

    @GetMapping("")
    public String start(){
        return "index";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    // handler method to handle user registration request
    @GetMapping("register")
    public String showRegistrationForm(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }
    @GetMapping("admin/users")
    public String listRegisteredUsers(Model model){
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        List<String> roles= Arrays.asList("ADMIN","GUEST","WAITER","COOK");
        model.addAttribute("roles",roles);
        return "users";
    }

    /*@GetMapping("admin/users/{user}")
    public String editUser(@PathVariable User user,Model model){
        Role r= user.getRoles().get(0);
        user.setRole_name(r.getName().substring(5));
        model.addAttribute("user",user);
        List<String> roles= Arrays.asList("ADMIN","GUEST","WAITER","COOK");
        model.addAttribute("roles",roles);
        return "edit_user";
    }*/

    @PostMapping("/admin/users/{user}")
    public String userEdit(@PathVariable User user,@RequestParam String role_name){
        user.getRoles().clear();
        user.getRoles().add(roleRepository.findByName("ROLE_"+role_name));
        userRepository.save(user);
        return "redirect:/admin/users";
    }

    // handler method to handle register user form submit request
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto user,
                               BindingResult result,
                               Model model){
        User existing = userService.findByEmail(user.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
        }
        userService.saveUser(user);
        return "redirect:/login";
    }

}
