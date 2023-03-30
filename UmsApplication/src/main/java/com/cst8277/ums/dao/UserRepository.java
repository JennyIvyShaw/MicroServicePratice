package com.cst8277.ums.dao;

import com.cst8277.ums.dto.Role;
import com.cst8277.ums.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepository implements UmsRepository{

    private static final String GET_ALL_USERS = "SELECT * FROM ums.users;";
    private static final String GET_ALL_ROLES = "SELECT * FROM ums.roles;";
    private static final String GET_USER_BY_ID = "SELECT * FROM ums.users WHERE id = (UUID_TO_BIN(?));";
    private static final String CREATE_USER = "INSERT INTO ums.users (id, name, email, password, created, last_visit_id) VALUES (UUID_TO_BIN(?),?,?,?,?, UUID_TO_BIN(?));";
    private static final String DELETE_USER =  "DELETE FROM ums.users WHERE id = (UUID_TO_BIN(?));";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<User> findAllUsers() {
        return jdbcTemplate.query(GET_ALL_USERS, (rs, rowNum) -> {
            return new User(BytesConverter.bytesToUuid(rs.getBytes("id")), rs.getString("name" ), rs.getString("email"), rs.getString("password"), rs.getInt("created"), BytesConverter.bytesToUuid(rs.getBytes("last_visit_id")));
        });
    }

    @Override
    public List<Role> findAllRoles() {
        return jdbcTemplate.query(GET_ALL_ROLES, (rs, rowNum) -> {
            return new Role(BytesConverter.bytesToUuid(rs.getBytes("id")), rs.getString("name"),rs.getString("description"));
        });
    }

    @Override
    public Optional <User> findUserById(UUID id) {

        User finalUser = new User();
        jdbcTemplate.queryForObject(GET_USER_BY_ID, new Object[]{id.toString()}, (rs, rowNum) -> {
            finalUser.setId(BytesConverter.bytesToUuid(rs.getBytes("id")));
            finalUser.setName(rs.getString("name" ));
            finalUser.setEmail(rs.getString("email"));
            finalUser.setPassword(rs.getString("password"));
            finalUser.setCreated(rs.getInt("created"));
            finalUser.setLast_visit_id(BytesConverter.bytesToUuid(rs.getBytes("last_visit_id")));
            return finalUser;
        });

        return Optional.ofNullable(finalUser);
    }

    @Override
    public User createUser(User user) {
        jdbcTemplate.update(CREATE_USER, user.getId().toString(), user.getName(), user.getEmail(), user.getPassword(), user.getCreated(), user.getLast_visit_id().toString());
        return user;
    }

    @Override
    public String deleteUserById(UUID userId) {
        jdbcTemplate.update(DELETE_USER, userId.toString());
        return "User " +  userId.toString() + " has been deleted";
    }
}
