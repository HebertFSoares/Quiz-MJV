package com.quiz.mjv.jwt;

import com.quiz.mjv.dto.UserDTO;
import com.quiz.mjv.entity.Users;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class JwtUserDetails extends User {

    private Users users;

    public JwtUserDetails(Users users) {
        super(users.getEmail(), users.getPassword(), AuthorityUtils.createAuthorityList(users.getRole().name()));
        this.users = users;
    }

    public Long getId() {
        return this.users.getId();
    }

    public String getRole() {
        return this.users.getRole().name();
    }
}
