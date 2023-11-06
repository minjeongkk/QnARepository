package qna.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    void test_Q1(){
        assertThat(Q1.getTitle()).isEqualTo("title1");
    }

    @Test
    void test_Q2(){
        assertThat(Q2.getTitle()).isEqualTo("title2");
    }

    @Test
    void test_Q1_user(){
        assertThat(Q1.getWriter().getId()).isEqualTo(1L);
    }

    @Test
    void test_Q2_user(){
        assertThat(Q2.getWriter().getId()).isEqualTo(2L);
    }
}
