package qna.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import qna.CannotDeleteException;
import qna.NotFoundException;
import qna.domain.*;
import qna.dto.QuestionDTO;
import qna.dto.UserDTO;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class QnaService {
    private static final Logger log = LoggerFactory.getLogger(QnaService.class);

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired UserRepository userRepository;
    @Autowired
    private DeleteHistoryService deleteHistoryService;

    public QnaService(QuestionRepository questionRepository, AnswerRepository answerRepository, DeleteHistoryService deleteHistoryService) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.deleteHistoryService = deleteHistoryService;
    }

    public Long save(QuestionDTO questionDTO){
        User writer = userRepository.findUserById(questionDTO.getUserId());
        Question question = questionDTO.toEntity();
        question.writeBy(writer);
        questionRepository.save(question);
        return question.getId();
    }

//    @Transactional(readOnly = true)
//    public Question findQuestionById(Long id) {
//        return questionRepository.findByIdAndDeletedFalse(id)
//                .orElseThrow(NotFoundException::new);
//    }

    @Transactional(readOnly = true)
    public Question findQuestionById(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    @Transactional
    public void deleteQuestion(User loginUser, Long questionId) throws CannotDeleteException {
        Question question = findQuestionById(questionId);

        DeleteHistories deleteHistories = question.deleteQuestion(loginUser);

        deleteHistoryService.saveAll(deleteHistories.getHistories());
    }

    @Transactional(readOnly = true)
    public List<Question> findQuestionsByUser(Long userId){
        User user = userRepository.findUserById(userId);
        return questionRepository.findByWriter(user);
    }
}