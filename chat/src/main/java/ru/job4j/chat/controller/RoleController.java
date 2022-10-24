package ru.job4j.chat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.chat.dto.RoleDTO;
import ru.job4j.chat.model.Role;
import ru.job4j.chat.services.RoleService;

import java.util.List;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping("/")
    public List<RoleDTO> findAll() {
        return roleService.getRoles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> findById(@PathVariable int id) {
        return new ResponseEntity<>(roleService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Role> create(@RequestBody RoleDTO roleDTO) {
        if (roleDTO.getName() == null) {
            throw new IllegalArgumentException("Define username");
        }
        return new ResponseEntity<>(
                this.roleService.create(roleDTO),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody RoleDTO roleDTO) {
        if (roleDTO.getName() == null) {
            throw new IllegalArgumentException("Define username");
        }
        this.roleService.create(roleDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        this.roleService.delete(id);
        return ResponseEntity.ok().build();
    }
}
