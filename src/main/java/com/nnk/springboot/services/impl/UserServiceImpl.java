package com.nnk.springboot.services.impl;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.IUserService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    Logger logger;

    @Override
    public User getSpecificUserById(Integer id) {
        logger.info("User " + id + " find");
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid User Id: " + id));
    }

    @Override
    public List<User> getAllUser() {
        logger.info("User List find");
        return userRepository.findAll();
    }

    public User findByUsername(String username) {
        logger.info("User " + username + " find");
        return userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("Invalid User Username: " + username));
    }

    @Override
    @Transactional
    public Boolean createUser(User user) {
        if(userRepository.existsByUsername(user.getUsername())) {
            logger.error("User cannot be created / The username isn't available");
            return false;
        } else {
            User newUser = new User(user.getUsername(), user.getPassword(), user.getFullname(), user.getRole());
            newUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            logger.info("User created");
            userRepository.save(newUser);
            return true;
        }
    }

    @Override
    @Transactional
    public void updateUser(Integer id, User user) {
        User userToUpdate = getSpecificUserById(id);
        userToUpdate.setFullname(user.getFullname());
        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userToUpdate.setRole(user.getRole());
        logger.info("User " + id + " updated");
        userRepository.save(userToUpdate);
    }

    @Override
    @Transactional
    public void deleteUser(Integer id) {
        logger.info("User " + id + " deleted");
        userRepository.deleteById(id);
    }
}
