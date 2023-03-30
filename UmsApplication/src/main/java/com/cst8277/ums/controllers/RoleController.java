package com.cst8277.ums.controllers;

import com.cst8277.ums.dao.UserRepository;
import com.cst8277.ums.dto.Role;
import com.cst8277.ums.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoleController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(method= RequestMethod.GET, path="/roles")
    public List<Role> findAllUsers(){
        return userRepository.findAllRoles();
    }


}
