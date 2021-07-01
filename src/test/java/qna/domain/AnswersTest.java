package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.exception.CannotDeleteException;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static qna.domain.AnswerTest.A1;
import static qna.domain.AnswerTest.A2;
import static qna.domain.UserTest.JAVAJIGI;

class AnswersTest {
    @Test
    @DisplayName("대답을 추가한다.")
    void add() {
        Answers answers = new Answers();
        answers.add(A1);

        assertThat(answers.getAnswers()).containsExactly(A1);
    }

    @Test
    @DisplayName("질문에 다른 사용자의 답글이 있으면 예외가 발생한다.")
    void delete_fail() {
        Answers answers = new Answers(Arrays.asList(A1, A2));

        assertThatThrownBy(() ->
                answers.delete(JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("답의 상태를 삭제로 만들고 삭제 이력을 반환한다.")
    void delete_success() {
        Answers answers = new Answers(Arrays.asList(A1, A1));
        List<DeleteHistory> deleteHistories = answers.delete(JAVAJIGI);

        assertThat(deleteHistories).containsExactly(
                new DeleteHistory(ContentType.ANSWER, A1.getId(), A1.getWriter()),
                new DeleteHistory(ContentType.ANSWER, A1.getId(), A1.getWriter()));
    }
}