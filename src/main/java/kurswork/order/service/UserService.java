package kurswork.order.service;

import java.util.List;

import kurswork.order.dto.UserDto;
import kurswork.order.entity.User;

public interface UserService {

	 User saveUser(UserDto userDto);

	    User findByEmail(String email);

	    List<UserDto> findAllUsers();
}
