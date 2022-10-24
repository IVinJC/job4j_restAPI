package ru.job4j.chat.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.chat.dto.RoleDTO;
import ru.job4j.chat.model.Role;
import ru.job4j.chat.repository.RoleRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public List<RoleDTO> getRoles() {
        return roleRepository.findAll().stream()
                .map(role -> RoleDTO.builder()
                        .name(role.getName())
                        .person(role.getPerson())
                        .build())
                .collect(Collectors.toList());
    }

    public RoleDTO findById(int id) {
        return roleRepository.findById(id)
                .map(role -> RoleDTO.builder()
                        .name(role.getName())
                        .person(role.getPerson())
                        .build())
                .orElseThrow(() -> new NoSuchElementException("No such role " + id));
    }

    public Role create(RoleDTO roleDTO) {
        Role role = new Role(roleDTO.getName(), roleDTO.getPerson());
        return roleRepository.save(role);
    }

    public Role update(RoleDTO roleDTO, int id) {
        Role role = new Role(roleDTO.getName(), roleDTO.getPerson());
        role.setId(id);
        return roleRepository.save(role);
    }

    public void delete(int id) {
        Role role = new Role();
        role.setId(id);
        roleRepository.delete(role);
    }
}
