package com.quiz.mjv.jwt;

import com.quiz.mjv.entity.Users;
import com.quiz.mjv.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService usuarioService;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users users = usuarioService.buscarPorUsername(email);
        return new JwtUserDetails(users);
    }

    public JwtToken getTokenAuthenticated(String email) {
        Users.Role role = usuarioService.buscarRolePorUsername(email);
        return JwtUtils.createToken(email, role.name().substring("ROLE_".length()));
    }
}
