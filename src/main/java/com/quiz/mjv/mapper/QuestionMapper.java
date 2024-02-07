package com.quiz.mjv.mapper;

import com.quiz.mjv.dto.QuestionDTO;
import com.quiz.mjv.entity.Question;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuestionMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public QuestionMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public QuestionDTO toDTO(Question question){
        return modelMapper.map(question, QuestionDTO.class);
    }

    public Question toEntity(QuestionDTO questionDTO){
        return modelMapper.map(questionDTO, Question.class);
    }
}
