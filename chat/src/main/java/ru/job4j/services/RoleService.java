package ru.job4j.services;

import ru.job4j.model.Role;
import org.springframework.stereotype.Service;
import ru.job4j.repository.RoleRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getRoles() {
        List<Role> roles = new ArrayList<>();
        roleRepository.findAll().forEach(roles::add);
        return roles;
    }

    public Role create(Role role) {
        return roleRepository.save(role);
    }

    public Role update(Role role, int id) {
        role.setId(id);
        return roleRepository.save(role);
    }

    public void delete(int id) {
        Role role = new Role();
        role.setId(id);
        roleRepository.delete(role);
    }
}
