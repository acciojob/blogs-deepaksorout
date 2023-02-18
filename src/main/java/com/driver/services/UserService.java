package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User createUser(String username, String password){
        User newuser=new User();

        //setting all attributes of user
        newuser.setUsername(username);
        newuser.setPassword(password);

        //saving user to userRepository
        userRepository.save(newuser);

        return newuser;
    }


    public void deleteUser(int userId){
        userRepository.deleteById(userId);
    }

    public User updateUser(Integer id, String password){
        User curruser=userRepository.findById(id).get();
        curruser.setPassword(password);

        userRepository.save(curruser);

        return curruser;
    }
}















