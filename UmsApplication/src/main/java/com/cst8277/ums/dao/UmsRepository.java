package com.cst8277.ums.dao;

import com.cst8277.ums.dto.Role;
import com.cst8277.ums.dto.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UmsRepository {

    List<User> findAllUsers();

    List <Role> findAllRoles();

    Optional<User> findUserById(UUID id);

    User createUser (User user);

    String deleteUserById (UUID userId);

}
