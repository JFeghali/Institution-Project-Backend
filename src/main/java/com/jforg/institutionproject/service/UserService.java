package com.jforg.institutionproject.service;

import com.jforg.institutionproject.entity.User;

import java.util.Optional;

public interface UserService {
    User getUser(Long id);
    User getUser(String username);
    Optional<User> getUserByUsername(String username);
    User saveUser(User user);
}