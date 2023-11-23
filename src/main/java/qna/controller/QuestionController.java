package qna.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import qna.CannotDeleteException;
import qna.domain.Question;
import qna.domain.User;
import qna.dto.QuestionDTO;
import qna.service.QnaService;
import qna.service.UserService;

import java.util.List;

@RestController
@Slf4j
public class QuestionController {
    private QnaService qnaService;
    private UserService userService;

    @Autowired
    public QuestionController(QnaService qnaService, UserService userService) {
        this.qnaService = qnaService;
        this.userService = userService;
    }

    @RequestMapping(value = "/saveQuestion", method = RequestMethod.POST)
    public ResponseEntity<Long> saveQuestion(@RequestBody QuestionDTO questionDTO) {
        log.info("질문 등록 : "+questionDTO.getTitle());
        Long id = qnaService.save(questionDTO);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @RequestMapping(value = "/findQuestion/{id}", method = RequestMethod.GET)
    public ResponseEntity<QuestionDTO> findQuestion(@PathVariable Long id) {
        QuestionDTO questionDTO = qnaService.findQuestionById(id).toDTO();
        return new ResponseEntity<>(questionDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/findQuestions/{userId}", method = RequestMethod.GET)
    public ResponseEntity<List<Question>> findQuestions(@PathVariable Long userId) {
        List<Question> questions = qnaService.findQuestionsByUser(userId);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @RequestMapping(value="/deleteQuestion/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Long> deleteQuestion(@PathVariable Long id, @RequestParam(name="userId")Long userId) throws CannotDeleteException {
        User user = userService.findById(userId);
        qnaService.deleteQuestion(user, id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
