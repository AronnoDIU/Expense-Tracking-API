package com.aronno.expensetracking_api.service;

import com.aronno.expensetracking_api.entity.User;
import com.aronno.expensetracking_api.exceptions.ResourceNotFoundException;
import com.aronno.expensetracking_api.exceptions.UserAlreadyExistsException;
import com.aronno.expensetracking_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
    }

    @Override
    public User createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException("User already exists with email: " + user.getEmail());
        }
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User user) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User updatedUser = existingUser.get();
            updatedUser.setFirstName(user.getFirstName());
            updatedUser.setLastName(user.getLastName());
            updatedUser.setEmail(user.getEmail());
            updatedUser.setPassword(user.getPassword());
            updatedUser.setDateOfBirth(user.getDateOfBirth());
            updatedUser.setRole(user.getRole());
            updatedUser.setPhoneNumber(user.getPhoneNumber());
            updatedUser.setAddress(user.getAddress());
            return userRepository.save(updatedUser);
        } else {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
    }

    @Override
    public void deleteUser(Long id) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            userRepository.delete(existingUser.get());
        } else {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
    }

    @Override
    public User getUserByEmail(String email) {
        SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByEmail(email);
    }
}
