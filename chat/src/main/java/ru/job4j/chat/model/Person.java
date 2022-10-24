package ru.job4j.chat.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "person")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    @NotNull(message = "Name must be not null")
    private String name;
    @NonNull
    @NotNull(message = "Password must be not null")
    private String password;
    @NonNull
    @ManyToOne()
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private Room room;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Person{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", room=").append(room);
        sb.append('}');
        return sb.toString();
    }
}
