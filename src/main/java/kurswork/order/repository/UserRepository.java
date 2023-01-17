package kurswork.order.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import kurswork.order.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {
	User findByEmail(String email);
}
