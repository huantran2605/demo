package com.huan.demo.service;

import com.huan.demo.entity.User;
import com.huan.demo.model.request.UserRequest;
import com.huan.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(UserRequest userRequest) {
        User newUser = new User();
        BeanUtils.copyProperties(userRequest, newUser);
        return userRepository.save(newUser);
    }

    public User getUserById(String id) {
        return userRepository.findById(id).orElseThrow();
    }

    public User updateUser (String id, User user) {
        user.setId(id);
        return userRepository.save(user);
    }

    public void deleteUser (String id) {
        userRepository.deleteById(id);
    }

    public User findByName(String name) {
        return userRepository.findByName(name);
    }
}