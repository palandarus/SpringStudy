package com.geekbrains.spring.lesson8.repositories;

import com.geekbrains.spring.lesson8.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
