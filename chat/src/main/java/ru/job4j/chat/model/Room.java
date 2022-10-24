package ru.job4j.chat.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
@Data
@RequiredArgsConstructor
@Entity
@Table(name = "room")
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    @NotNull(message = "Name must be not null")
    private String name;
    @Column(name = "username")
    private String user;
    private String message;
}
