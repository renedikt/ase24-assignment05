package de.unibayreuth.se.taskboard.api.controller;

import de.unibayreuth.se.taskboard.api.dtos.UserDto;
import de.unibayreuth.se.taskboard.api.mapper.UserDtoMapper;
import de.unibayreuth.se.taskboard.business.exceptions.MalformedRequestException;
import de.unibayreuth.se.taskboard.business.exceptions.TaskNotFoundException;
import de.unibayreuth.se.taskboard.business.exceptions.UserNotFoundException;
import de.unibayreuth.se.taskboard.business.ports.UserService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@OpenAPIDefinition(
        info = @Info(
                title = "TaskBoard",
                version = "0.0.1"
        )
)
@Tag(name = "Users")
@Controller
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
        private final UserService userService;
        private final UserDtoMapper userDtoMapper;

        // TODO(✅): Add GET /api/users endpoint to retrieve all users.
        // TODO(✅): Add GET /api/users/{id} endpoint to retrieve a user by ID.
        // TODO(✅): Add POST /api/users endpoint to create a new user based on a provided user DTO.

        @Operation(
                summary = "Get all users.",
                responses = {
                        @ApiResponse(
                                responseCode = "200",
                                content = @Content(
                                        mediaType = "application/json",
                                        schema = @Schema(type = "array", implementation = UserDto.class)
                                ),
                                description = "All users as a JSON array."
                        )
                }
        )
        @GetMapping
        public ResponseEntity<List<UserDto>> getAllUsers() {
                return ResponseEntity.ok(
                        userService.getAll().stream()
                                .map(userDtoMapper::fromBusiness)
                                .toList()
                );
        }

        @Operation(
                summary = "Get user by ID.",
                responses = {
                        @ApiResponse(
                                responseCode = "200",
                                content = @Content(
                                        mediaType = "application/json",
                                        schema = @Schema(implementation = UserDto.class)
                                ),
                                description = "The user with the provided ID as a JSON object."
                        ),
                        @ApiResponse(
                                responseCode = "400",
                                description = "User with the provided ID could not be found."
                        )
                }
        )
        @GetMapping("/{id}")
        public ResponseEntity<UserDto> getById(@PathVariable UUID id) {
                try {
                        return ResponseEntity.ok(
                                userDtoMapper.fromBusiness(userService.getById(id))
                        );
                } catch (TaskNotFoundException e) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
                }
        }

        @Operation(
                summary = "Creates a new user.",
                responses = {
                        @ApiResponse(
                                responseCode = "200",
                                content = @Content(
                                        mediaType = "application/json",
                                        schema = @Schema(implementation = UserDto.class)
                                ),
                                description = "The new user as a JSON object."
                        ),
                        @ApiResponse(
                                responseCode = "400",
                                description = "" //TODO
                        )
                }
        )
        @PostMapping
        public ResponseEntity<UserDto> create(@RequestBody @Valid UserDto userDto) {
                try {
                        return ResponseEntity.ok(
                                userDtoMapper.fromBusiness(
                                        userService.create(
                                                userDtoMapper.toBusiness(userDto)
                                        )
                                )
                        );
                } catch (MalformedRequestException e) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
                }
        }
}
