package qna.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qna.domain.*;
import qna.dto.AnswerDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnswerService {
    private AnswerRepository answerRepository;
    private QuestionRepository questionRepository;
    UserRepository userRepository;
    @Autowired
    public AnswerService(AnswerRepository answerRepository, QuestionRepository questionRepository, UserRepository userRepository){
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
    }

    public Answer save(AnswerDTO answerDTO){
        Answer answer = answerDTO.toEntity();
        answer.setQuestion(questionRepository.findQuestionById(answerDTO.getQuestionId()));
        answer.setWriter(userRepository.findUserById(answerDTO.getUserId()));
        return answerRepository.save(answer);
    }

    public List<AnswerDTO> findByQuestionId(Long questionId){
        Question question = questionRepository.findQuestionById(questionId);
        List<Answer> answerList = answerRepository.findByQuestionAndDeletedFalse(question);
        return answerList.stream()
                .map(m->m.toDTO())
                .collect(Collectors.toList());
    }
}
