package ru.job4j.chat.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
@Entity
@Table (name = "role")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    @NotNull(message = "Name must be not null")
    private String name;
    @NonNull
    @ManyToOne ()
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;
}
