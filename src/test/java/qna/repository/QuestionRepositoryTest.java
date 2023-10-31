package qna.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import qna.domain.Question;
import qna.domain.QuestionRepository;
import qna.domain.User;
import qna.domain.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
public class QuestionRepositoryTest {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    void save(){
        Question expected = new Question();
        Question actual = questionRepository.save(expected);
        assertAll(
                () -> assertThat(actual.getId()).isNotNull(),
                () -> assertThat(actual.getWriterId()).isEqualTo(expected.getWriterId())
        );
    }

    @Test
    void findByUserId(){
        User expected = new User("KIM","1234", "k", "a@naver.com");
        userRepository.save(expected);
        Question question = new Question("hello", "hi");
        question.writeBy(expected);
        assertThat(question.getWriterId()).isEqualTo(expected.getId());
    }
}
