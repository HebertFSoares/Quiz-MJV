package com.quiz.mjv.service;

import com.quiz.mjv.dto.QuestionDTO;
import com.quiz.mjv.entity.Question;
import com.quiz.mjv.mapper.QuestionMapper;
import com.quiz.mjv.repository.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    public QuestionDTO createdQuestion (QuestionDTO questionDTO) {
        return questionMapper.toDTO(questionRepository.save(questionMapper.toEntity(questionDTO)));
    }

    public QuestionDTO getRandomQuestion () {
        List<Question> allQuestions = questionRepository.findAll();
        int totalQuestions = allQuestions.size();

        if (totalQuestions == 0) {
            return null;
        }

        Random random = new Random();
        int randomIndex = random.nextInt(totalQuestions);

        Question randomQuestion = allQuestions.get(randomIndex);
        randomQuestion.getQuestionAlternatives().size();

        return questionMapper.toDTO(randomQuestion);
    }
    public List<QuestionDTO> getAllQuestions() {
        List<Question> questions = questionRepository.findAll();
        return questions.stream()
                .map(questionMapper::toDTO)
                .collect(Collectors.toList());
    }

    public QuestionDTO getQuestionById (Long questionId) {
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        if (optionalQuestion.isPresent()) {
            Question question = optionalQuestion.get();
            return questionMapper.toDTO(question);
        } else {
            return null;
        }
    }


    public QuestionDTO updateQuestion (Long questionId, QuestionDTO questionDTO) {
        Question existingQuestion = questionRepository.findById(questionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Question not found with id: " + questionId));

        existingQuestion.setQuestion(questionDTO.getQuestion());
        existingQuestion.setResponse(questionDTO.getResponse());
        existingQuestion.setTheme(questionDTO.getTheme());

        Question updatedQuestion = questionRepository.save(existingQuestion);

        return questionMapper.toDTO(updatedQuestion);
    }

    public boolean deleteQuestion (Long questionId) {
        Question existingQuestion = questionRepository.findById(questionId).orElse(null);

        if (existingQuestion == null) {
            return false;
        } else {
            questionRepository.delete(existingQuestion);
            return true;
        }
    }

}
