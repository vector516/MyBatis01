package com.itheima.mapper;

import com.itheima.domain.User;

import java.util.List;

public interface UserMapper {
    public User queryUserById(int id);
    public List<User> queryUserByUsername(String username);
    public void saveUser(User user);


}
