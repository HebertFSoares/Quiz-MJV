package com.quiz.mjv.mapper;

import com.quiz.mjv.dto.QuestionAlternativeDTO;
import com.quiz.mjv.dto.QuestionDTO;
import com.quiz.mjv.entity.Question;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuestionMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public QuestionMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public QuestionDTO toDTO(Question question){
        QuestionDTO questionDTO = modelMapper.map(question, QuestionDTO.class);

        if (question.getQuestionAlternatives() != null) {
            List<QuestionAlternativeDTO> alternativeDTOs = question.getQuestionAlternatives()
                    .stream()
                    .map(alternative -> modelMapper.map(alternative, QuestionAlternativeDTO.class))
                    .collect(Collectors.toList());

            ArrayList<QuestionAlternativeDTO> alternativeDTOArrayList = new ArrayList<>(alternativeDTOs);
            questionDTO.setQuestionAlternativeDTOS(alternativeDTOArrayList);
        } else {
            questionDTO.setQuestionAlternativeDTOS(new ArrayList<>());
        }

        return questionDTO;
    }



    public Question toEntity(QuestionDTO questionDTO){
        return modelMapper.map(questionDTO, Question.class);
    }
}
