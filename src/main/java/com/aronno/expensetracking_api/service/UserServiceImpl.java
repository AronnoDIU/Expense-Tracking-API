package com.aronno.expensetracking_api.service;

import com.aronno.expensetracking_api.entity.User;
import com.aronno.expensetracking_api.exceptions.ResourceNotFoundException;
import com.aronno.expensetracking_api.exceptions.UserAlreadyExistsException;
import com.aronno.expensetracking_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User getUser() {
        return getLoggedInUser();
    }

    @Override
    public User createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException("User already exists with email: " + user.getEmail());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        User loggedInUser = getLoggedInUser();

        updateFieldIfNotNull(user.getFirstName(), loggedInUser::setFirstName);
        updateFieldIfNotNull(user.getLastName(), loggedInUser::setLastName);
        updateFieldIfNotNull(user.getEmail(), loggedInUser::setEmail);
        updateFieldIfNotNull(user.getPassword(), password -> loggedInUser.setPassword(passwordEncoder.encode(password)));
        updateFieldIfNotNull(user.getDateOfBirth(), loggedInUser::setDateOfBirth);
        updateFieldIfNotNull(user.getRole(), loggedInUser::setRole);
        updateFieldIfNotNull(user.getPhoneNumber(), loggedInUser::setPhoneNumber);
        updateFieldIfNotNull(user.getAddress(), loggedInUser::setAddress);

        return userRepository.save(loggedInUser);
    }

    @Override
    public void deleteUser() {
        User user = getLoggedInUser();
        userRepository.delete(user);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
    }

    /**
     * Updates a field of the user if the new value is not null.
     *
     * @param newValue the new value to set
     * @param setter   the setter method to call
     * @param <T>      the type of the field
     */
    private <T> void updateFieldIfNotNull(T newValue, java.util.function.Consumer<T> setter) {
        if (newValue != null) {
            setter.accept(newValue);
        }
    }
}
