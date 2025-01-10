package de.unibayreuth.se.taskboard.api.dtos;

//TODO(✅): Add DTO for users.


import jakarta.validation.constraints.NotNull;
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
        private String name;
}
