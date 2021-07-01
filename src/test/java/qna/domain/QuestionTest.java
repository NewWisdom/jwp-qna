package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static qna.domain.AnswerTest.A1;
import static qna.domain.AnswerTest.A2;
import static qna.domain.DeleteHistoryTest.D1;
import static qna.domain.DeleteHistoryTest.D2;
import static qna.domain.UserTest.JAVAJIGI;

public class QuestionTest {
    public static Question Q1 = new Question("title1", "contents1").writeBy(JAVAJIGI);
    public static Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @BeforeEach
    void setUp() {
        Q1 = new Question("title1", "contents1").writeBy(JAVAJIGI);
        Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);
        D1 = new DeleteHistory(ContentType.QUESTION, Q1.getId(), JAVAJIGI);
        D2 = new DeleteHistory(ContentType.ANSWER, Q1.getId(), JAVAJIGI);
    }

    @Test
    @DisplayName("질문 삭제 성공")
    void delete_success_1() throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = Q1.delete(JAVAJIGI);

        assertAll(
                () -> assertThat(Q1.isDeleted()).isTrue(),
                () -> assertThat(deleteHistories).containsExactly(D1)
        );
    }

    @Test
    @DisplayName("다른 사람이 쓴 글일 경우 예외가 발생한다.")
    void delete_fail_1() {
        assertThatThrownBy(() ->
                Q1.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("질문에 같은 사용자의 답글만 있으면 성공한다.")
    void delete_success_2() throws CannotDeleteException {
        Q1.addAnswer(A1);

        List<DeleteHistory> deleteHistories = Q1.delete(JAVAJIGI);

        assertAll(
                () -> assertThat(Q1.isDeleted()).isTrue(),
                () -> assertThat(deleteHistories).contains(D1, D2)
        );
        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("질문에 다른 사용자의 답글이 있으면 예외가 발생한다.")
    void delete_fail_2() {
        Q1.addAnswer(A2);

        assertThatThrownBy(() ->
                Q1.delete(JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}
