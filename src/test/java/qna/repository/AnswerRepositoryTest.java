package qna.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import qna.domain.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
public class AnswerRepositoryTest {
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    void save(){
        Answer expected = new Answer();
        Answer actual = answerRepository.save(expected);
        assertAll(
                () -> assertThat(actual.getId()).isNotNull(),
                () -> assertThat(actual.getWriterId()).isEqualTo(expected.getWriterId())
        );
    }

    @Test
    void findByUserId(){
        User user = new User("KIM","1234", "k", "a@naver.com");
        userRepository.save(user);
        Question question = new Question("hello", "hi");
        Answer expected = new Answer(user,question, "good");
        assertThat(expected.getWriterId()).isEqualTo(user.getId());
    }
}
