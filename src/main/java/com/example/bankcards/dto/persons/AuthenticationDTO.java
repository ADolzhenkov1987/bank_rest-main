package com.example.bankcards.dto.persons;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AuthenticationDTO {
    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 128, message = "Имя должно быть от 2 до 128 символов длиной")
    private String name;

    private String password;
}