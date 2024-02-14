package com.jforg.institutionproject.service;

import java.util.Optional;

import com.jforg.institutionproject.entiy.User;
import com.jforg.institutionproject.exception.EntityNotFoundException;
import com.jforg.institutionproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        return unwrapUser(user, id);
    }

    @Override
    public User getUser(String userName) {
        Optional<User> user = userRepository.findByUsername(userName);
        return unwrapUser(user, 404L);
    }
    @Override
    public Optional<User> getUserByUsername(String userName) {
        Optional<User> user = userRepository.findByUsername(userName);
        return user;
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    static User unwrapUser(Optional<User> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id, User.class);
    }

}