package com.quiz.mjv.service;

import com.quiz.mjv.dto.QuestionDTO;
import com.quiz.mjv.entity.Question;
import com.quiz.mjv.mapper.QuestionMapper;
import com.quiz.mjv.repository.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

<<<<<<< Updated upstream
=======
import java.util.List;
import java.util.Random;

>>>>>>> Stashed changes

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

<<<<<<< Updated upstream
=======
    public QuestionDTO getRandomQuestion() {
        List<Question> allQuestions = questionRepository.findAll();
        int totalQuestions = allQuestions.size();

        if (totalQuestions == 0) {
            return null;
        }

        // Gera um índice aleatório
        Random random = new Random();
        int randomIndex = random.nextInt(totalQuestions);

        // Retorna a pergunta aleatória
        Question randomQuestion = allQuestions.get(randomIndex);
        return questionMapper.toDTO(randomQuestion);
    }
>>>>>>> Stashed changes
}
