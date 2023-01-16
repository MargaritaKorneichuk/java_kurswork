package kurswork.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kurswork.order.entity.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {
	Role findByName(String name);
}
