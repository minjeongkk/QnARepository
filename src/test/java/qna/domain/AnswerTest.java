package qna.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void test_A1_user(){
        assertThat(A1.getWriter().getId()).isEqualTo(1L);
    }

    @Test
    void test_A2_user(){
        assertThat(A2.getWriter().getId()).isEqualTo(2L);
    }

    @Test
    void test_A1_question(){
        assertThat(A1.getQuestion().getTitle()).isEqualTo("title1");
    }

    @Test
    void test_A2_question(){
        assertThat(A2.getQuestion().getTitle()).isEqualTo("title1");
    }
}
