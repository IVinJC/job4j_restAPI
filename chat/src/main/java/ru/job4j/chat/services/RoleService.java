package ru.job4j.chat.services;

import ru.job4j.chat.model.Role;
import ru.job4j.chat.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    public Optional<Role> findById(int id) {
        return roleRepository.findById(id);
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
