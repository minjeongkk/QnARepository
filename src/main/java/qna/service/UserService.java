package qna.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qna.domain.User;
import qna.domain.UserRepository;
import qna.dto.UserDTO;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long signUp(UserDTO userDTO) {
        User user = userRepository.save(userDTO.toEntity());
        return user.getId();
    }

    public User findById(Long id) {
        return Optional.ofNullable(userRepository.findUserById(id))
                .orElseThrow(NoSuchElementException::new);
    }

    public UserDTO findByUserId(String userId) {
        Optional<User> user = Optional.ofNullable(userRepository.findByUserId(userId))
                .orElseThrow(NoSuchElementException::new);
        return user.get().toDTO();
    }
}
