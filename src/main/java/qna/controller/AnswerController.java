package qna.controller;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import qna.domain.Answer;
import qna.domain.AnswerRepository;
import qna.domain.Answers;
import qna.dto.AnswerDTO;
import qna.service.AnswerService;
import qna.service.QnaService;
import qna.service.UserService;

import java.util.List;

@RestController
public class AnswerController {
    private AnswerService answerService;
    private QnaService qnaService;
    private UserService userService;

    @Autowired
    public AnswerController(AnswerService answerService, QnaService qnaService, UserService userService) {
        this.answerService = answerService;
        this.qnaService = qnaService;
        this.userService = userService;
    }

    @RequestMapping(value = "/saveAnswer", method = RequestMethod.POST)
    public ResponseEntity<Long> saveAnswer(@RequestBody AnswerDTO answerDTO){
        Answer answer = answerService.save(answerDTO);
        return new ResponseEntity<>(answer.getId(), HttpStatus.OK);
    }

    @RequestMapping(value = "/findAnswers/{questionId}", method = RequestMethod.GET)
    public ResponseEntity<List<AnswerDTO>> findAnswers(@PathVariable Long questionId){
        List<AnswerDTO> answerList = answerService.findByQuestionId(questionId);
        return new ResponseEntity<>(answerList, HttpStatus.OK);
    }
}
