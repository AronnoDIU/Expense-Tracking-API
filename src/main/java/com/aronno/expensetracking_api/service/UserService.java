package com.aronno.expensetracking_api.service;

import com.aronno.expensetracking_api.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    /**
     * Retrieves all users from the database.
     *
     * @return a list of all users
     */
    List<User> getAllUsers();

    /**
     * Retrieves a user by their ID.
     *
     * @param id the ID of the user to retrieve
     * @return the user with the specified ID
     */
    User getUserById(Long id);

    /**
     * Creates a new user in the database.
     *
     * @param user the user to create
     * @return the created user
     */
    User createUser(User user);

    /**
     * Updates an existing user in the database.
     *
     * @param id   the ID of the user to update
     * @param user the updated user information
     * @return the updated user
     */
    User updateUser(Long id, User user);

    /**
     * Deletes a user by their ID.
     *
     * @param id the ID of the user to delete
     */
    void deleteUser(Long id);

    /**
     * Retrieves a user by their email address.
     *
     * @param email the email address of the user to retrieve
     * @return the user with the specified email address
     */
    Optional<User> getUserByEmail(String email);

    /**
     * Retrieves the currently logged-in user.
     *
     * @return the currently logged-in user
     */
    User getLoggedInUser();
}
