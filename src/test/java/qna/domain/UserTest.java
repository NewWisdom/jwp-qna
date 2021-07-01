package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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

    @Test
    @DisplayName("질문자와 로그인 사용자가 다를 경우 예외가 발생한다.")
    void checkPermissionToDelete(){
        assertThatThrownBy(() ->
                JAVAJIGI.checkPermissionToDelete(SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}
