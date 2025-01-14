package de.unibayreuth.se.taskboard.business.ports;

import de.unibayreuth.se.taskboard.business.domain.User;
import de.unibayreuth.se.taskboard.business.exceptions.MalformedRequestException;
import de.unibayreuth.se.taskboard.business.exceptions.UserNotFoundException;
import jakarta.validation.constraints.NotNull;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.UUID;

public interface UserService {
    void clear();

    @NonNull
    List<User> getAll();

    @NonNull
    User getById(@NotNull UUID id) throws UserNotFoundException;

    @NonNull
    User create(@NotNull User user)throws MalformedRequestException;
}
