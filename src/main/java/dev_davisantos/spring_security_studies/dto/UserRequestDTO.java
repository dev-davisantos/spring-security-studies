package dev_davisantos.spring_security_studies.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record UserRequestDTO(
        @NotBlank String name,
        @NotBlank String username,
        @NotBlank String password,
        @NotNull List<String> roles
) {
}
