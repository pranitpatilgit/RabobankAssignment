package nl.rabobank.service;

import nl.rabobank.dto.User;

public interface UserService {

    User getUserByName(String name);
    User createUser(User user);
}
