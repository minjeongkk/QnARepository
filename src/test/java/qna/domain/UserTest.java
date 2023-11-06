package qna.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {
    public static final User JAVAJIGI = new User(1L, "javajigi", "password", "name", "javajigi@slipp.net");
    public static final User SANJIGI = new User(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");

    @Test
    void test_JAVAJIGI_user(){
        assertThat(JAVAJIGI.getId()).isEqualTo(1L);
    }

    @Test
    void test_SANJIGI_user(){
        assertThat(SANJIGI.getId()).isEqualTo(2L);
    }
}
