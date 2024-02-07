package com.quiz.mjv.service;

import com.quiz.mjv.dto.QuestionDTO;
import com.quiz.mjv.entity.Question;
import com.quiz.mjv.mapper.QuestionMapper;
import com.quiz.mjv.repository.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class QuestionService {
    @Autowired
    private final QuestionRepository questionRepository;
    @Autowired
    private final QuestionMapper questionMapper;

    public QuestionDTO createdQuestion (QuestionDTO questionDTO){
        return  questionMapper.toDTO(questionRepository.save(questionMapper.toEntity(questionDTO)));
    }

}
