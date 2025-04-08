package com.aronno.expensetracking_api.service;

import com.aronno.expensetracking_api.entity.User;
import com.aronno.expensetracking_api.exceptions.ResourceNotFoundException;
import com.aronno.expensetracking_api.exceptions.UserAlreadyExistsException;
import com.aronno.expensetracking_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
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
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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
            updatedUser.setPassword(passwordEncoder.encode(user.getPassword()));
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
        return userRepository.findByEmail(email);
    }
}
