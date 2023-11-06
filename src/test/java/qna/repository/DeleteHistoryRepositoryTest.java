package qna.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import qna.domain.ContentType;
import qna.domain.DeleteHistory;
import qna.domain.DeleteHistoryRepository;
import qna.domain.User;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
public class DeleteHistoryRepositoryTest {
    @Autowired
    private DeleteHistoryRepository deleteHistoryRepository;

    @Test
    void save() {
        ContentType contentType = ContentType.ANSWER;
        Long contentId = 1L;
        User user = new User();
        LocalDateTime createDate = LocalDateTime.now();

        DeleteHistory expected = new DeleteHistory(contentType, contentId, user, createDate);
        DeleteHistory actual = deleteHistoryRepository.save(expected);

        assertAll(
                () -> assertThat(actual).isNotNull(),
                () -> assertThat(actual).isEqualTo(expected)
        );
    }

    @Test
    void checkContents() {
        ContentType contentType = ContentType.ANSWER;
        Long contentId = 1L;
        User user = new User();
        LocalDateTime createDate = LocalDateTime.now();

        DeleteHistory expected = new DeleteHistory(contentType, contentId, user, createDate);
        DeleteHistory actual = deleteHistoryRepository.save(expected);

        assertThat(actual.getContentType()).isEqualTo(contentType);
    }
}
