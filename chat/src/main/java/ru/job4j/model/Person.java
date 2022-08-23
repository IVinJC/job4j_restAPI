package ru.job4j.model;

import javax.persistence.*;
import java.util.List;
@Entity
@Table (name = "person")
public class Person {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany (mappedBy = "person")
    private List<Role> roles;

    public Person(int id, String name, List<Role> role) {
        this.id = id;
        this.name = name;
        this.roles = role;
    }

    public Person() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Role> getRole() {
        return roles;
    }

    public void setRole(List<Role> role) {
        this.roles = role;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Person{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", role=").append(roles);
        sb.append('}');
        return sb.toString();
    }
}
