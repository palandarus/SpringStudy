package com.geekbrains.spring.lesson8.services;


import com.geekbrains.spring.lesson8.entities.Role;
import com.geekbrains.spring.lesson8.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
