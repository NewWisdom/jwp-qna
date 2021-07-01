package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static qna.domain.DeleteHistoryTest.D1;

public class UserTest {
    public static User JAVAJIGI = new User(1L, "javajigi", "password", "name", "javajigi@slipp.net");
    public static User SANJIGI = new User(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");

    @BeforeEach
    void setUp() {
        JAVAJIGI = new User(1L, "javajigi", "password", "name", "javajigi@slipp.net");
        SANJIGI = new User(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");
    }

    @Test
    @DisplayName("삭제 히스토리를 추가한다.")
    void addDeletedHistory() {
        JAVAJIGI.addDeletedHistory(D1);

        assertThat(JAVAJIGI.getDeleteHistories()).containsExactly(D1);
    }
}
