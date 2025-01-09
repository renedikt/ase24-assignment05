package de.unibayreuth.se.taskboard.business.impl;

import de.unibayreuth.se.taskboard.business.domain.User;
import de.unibayreuth.se.taskboard.business.exceptions.MalformedRequestException;
import de.unibayreuth.se.taskboard.business.exceptions.UserNotFoundException;
import de.unibayreuth.se.taskboard.business.ports.UserService;

import java.util.List;
import java.util.UUID;

public class UserServiceImpl implements UserService {

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getById(UUID id) throws UserNotFoundException {
        return null;
    }

    @Override
    public User create(User user) throws MalformedRequestException {
        return null;
    }
}
