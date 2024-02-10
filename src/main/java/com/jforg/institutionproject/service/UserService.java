package com.jforg.institutionproject.service;

import com.jforg.institutionproject.entiy.User;

public interface UserService {
    User getUser(Long id);
    User getUser(String username);

    User saveUser(User user);
}