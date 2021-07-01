package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static qna.domain.AnswerTest.A1;
import static qna.domain.AnswerTest.A2;
import static qna.domain.UserTest.JAVAJIGI;

public class QuestionTest {
    public static Question Q1 = new Question("title1", "contents1").writeBy(JAVAJIGI);
    public static Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);


    @BeforeEach
    void setUp() {
        Q1 = new Question("title1", "contents1").writeBy(JAVAJIGI);
        Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);
    }

    @Test
    @DisplayName("질문 삭제 성공")
    void delete_success_1() throws CannotDeleteException {
        Q1.delete(JAVAJIGI);

        assertThat(Q1.isDeleted()).isTrue();
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

        Q1.delete(JAVAJIGI);

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
