package qna.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import qna.domain.AnswerRepository;
import qna.domain.QuestionRepository;
import qna.domain.User;
import qna.domain.UserRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void save() {
        User expected = new User();
        User actual = userRepository.save(expected);
        assertAll(
                () -> assertThat(actual.getId()).isNotNull(),
                () -> assertThat(actual.getName()).isEqualTo(expected.getName())
        );
    }

    @Test
    void findByUserId() {
        User expected = new User("KIM","1234", "k", "a@naver.com");
        userRepository.save(expected);
        Optional<User> actual = userRepository.findByUserId(expected.getUserId());
        assertThat(actual.get().getName()).isEqualTo("k");
    }
}
