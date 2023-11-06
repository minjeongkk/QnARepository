package qna.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteHistoryTest {
    public static final DeleteHistory D1 = new DeleteHistory(ContentType.QUESTION, 1L, UserTest.JAVAJIGI, LocalDateTime.now());
    public static final DeleteHistory D2 = new DeleteHistory(ContentType.ANSWER, 1L, UserTest.SANJIGI, LocalDateTime.now());

    @Test
    void test_D1_type(){
        assertThat(D1.getContentType()).isEqualTo(ContentType.QUESTION);
    }

    @Test
    void test_D2_type(){
        assertThat(D2.getContentType()).isEqualTo(ContentType.ANSWER);
    }

    @Test
    void test_D1_question(){
        assertThat(D1.getDeletedById().getId()).isEqualTo(1L);
    }

    @Test
    void test_D2_question(){
        assertThat(D2.getDeletedById().getId()).isEqualTo(2L);
    }
}
