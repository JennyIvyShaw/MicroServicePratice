package com.cst8277.ums.controllers;

import com.cst8277.ums.dao.UserRepository;
import com.cst8277.ums.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(method= RequestMethod.GET, path="/users")
    public List<User> findAllUsers(){
        return userRepository.findAllUsers();
    }

    @RequestMapping(method= RequestMethod.GET, path="/users/user/{userId}")
    public Optional<User> findUserById(@PathVariable("userId") UUID userId){
        return userRepository.findUserById(userId);
    }

    @RequestMapping(method = RequestMethod.POST, path="/users/user")
    public User createUser(@RequestBody User user){
        return  userRepository.createUser(user);
    }

    @RequestMapping(method = RequestMethod.DELETE, path="/users/user/{userId}")
    public String deleteUserById (@PathVariable("userId") UUID userId){
        return userRepository.deleteUserById(userId);
    }


}
