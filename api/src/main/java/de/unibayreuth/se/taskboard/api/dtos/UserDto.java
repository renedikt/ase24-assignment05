package de.unibayreuth.se.taskboard.api.dtos;

//TODO(✅): Add DTO for users.


import jakarta.validation.constraints.NotNull;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO that represents a user.
 */
public record UserDto(
        @Nullable
        UUID uuid,
        @Nullable
        LocalDateTime createdAt,
        @NotNull
        String name
) { }
