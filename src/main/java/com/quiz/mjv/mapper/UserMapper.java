package com.quiz.mjv.mapper;

import com.quiz.mjv.dto.UserDTO;
import com.quiz.mjv.entity.Users;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public UserMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public static Users toUsuario(UserDTO createDto) {
        return new ModelMapper().map(createDto, Users.class);
    }

    public UserDTO toDto(Users users) {
        String role = users.getRole().name().substring("ROLE_".length());
        PropertyMap<Users, UserDTO> props = new PropertyMap<Users, UserDTO>() {
            @Override
            protected void configure() {
                map().setRole(Users.Role.valueOf(role));
            }
        };
        modelMapper.addMappings(props);
        return modelMapper.map(users, UserDTO.class);
    }

    public Users toEntity(UserDTO userDTO){
        return modelMapper.map(userDTO, Users.class);
    }
}
