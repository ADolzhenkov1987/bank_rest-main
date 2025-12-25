package com.example.bankcards.dto.persons;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PersonDTO {
    @NotBlank
    @Size(min = 2, max = 128)
    @Column(name = "name", nullable = false)
    private String name;

    @Size(min = 8, max = 16)
    @Column(name = "password", nullable = false)
    private String password;
}
