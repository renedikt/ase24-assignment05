package de.unibayreuth.se.taskboard.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO that represents a user.
 */
@Data
public class UserDto {
        @Nullable
        private UUID id;
        @Nullable
        private LocalDateTime createdAt;
        @NotNull
        @NotBlank
        @Size(max = 255, message = "Name can be at most 255 characters long.")
        private String name;
}
