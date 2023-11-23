package qna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import qna.domain.User;
import qna.dto.UserDTO;
import qna.service.UserService;

@RestController
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public ResponseEntity<Long> registerMember(@RequestBody UserDTO userDTO) {
        Long id = userService.signUp(userDTO);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @RequestMapping(value = "/findMember/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> findMember(@PathVariable Long id) {
        User user = userService.findById(id);
        return new ResponseEntity<>(user.toDTO(), HttpStatus.OK);
    }
}
