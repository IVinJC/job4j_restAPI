package ru.job4j.chat.dto;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class MessageDTO {
    private int id;
    @NotNull(message = "Message must be not null")
    private String text;

    public MessageDTO(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MessageDTO messageDTO = (MessageDTO) o;
        return Objects.equals(text, messageDTO.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MessageDTO{");
        sb.append("text='").append(text).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
