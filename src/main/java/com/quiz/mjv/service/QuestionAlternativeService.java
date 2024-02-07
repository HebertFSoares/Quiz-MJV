package com.quiz.mjv.service;

import com.quiz.mjv.dto.QuestionAlternativeDTO;
import com.quiz.mjv.entity.QuestionAlternative;
import com.quiz.mjv.mapper.QuestionAlternativeMapper;
import com.quiz.mjv.repository.QuestionAlternativeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
@Service
@AllArgsConstructor
public class QuestionAlternativeService {
    @Autowired
    private final QuestionAlternativeRepository questionAlternativeRepository;

    @Autowired
    private final QuestionAlternativeMapper questionAlternativeMapper;

    public QuestionAlternativeDTO createQuestionAlternative (QuestionAlternative questionAlternative){
        try {
            QuestionAlternative savedQuestionAlternative = questionAlternativeRepository.save(questionAlternative);
            return  questionAlternativeMapper.toDTO(savedQuestionAlternative);
        } catch (DataAccessException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, error("save"), e);
        }
    }

    private String error(String error){
        return "QuestionAlternativeService" + error;
    }

}

