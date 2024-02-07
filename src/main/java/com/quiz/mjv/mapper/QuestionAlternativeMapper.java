package com.quiz.mjv.mapper;

import com.quiz.mjv.dto.QuestionAlternativeDTO;
import com.quiz.mjv.entity.QuestionAlternative;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuestionAlternativeMapper {
    private final ModelMapper modelMapper;

    public QuestionAlternativeMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public QuestionAlternativeDTO toDTO (QuestionAlternative questionAlternative){
        return modelMapper.map(questionAlternative, QuestionAlternativeDTO.class);
    }

    public QuestionAlternative toEntity(QuestionAlternativeDTO questionAlternativeDTO){
        return modelMapper.map(questionAlternativeDTO, QuestionAlternative.class);
    }

}
