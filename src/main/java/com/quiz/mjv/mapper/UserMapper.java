package com.quiz.mjv.mapper;

import com.quiz.mjv.dto.UserDTO;
import com.quiz.mjv.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public UserMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }
    public UserDTO toDTO(User user){
        return modelMapper.map(user, UserDTO.class);
    }
    public User toEntity(UserDTO userDTO){
        return modelMapper.map(userDTO, User.class);
    }
}
