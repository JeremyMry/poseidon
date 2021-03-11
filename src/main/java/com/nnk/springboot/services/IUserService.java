package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;

import java.util.List;

public interface IUserService {

    public User getSpecificUserById(Integer id);

    public List<User> getAllUser();

    public Boolean createUser(User user);

    public void updateUser(Integer id, User user);

    public void deleteUser(Integer id);
}
