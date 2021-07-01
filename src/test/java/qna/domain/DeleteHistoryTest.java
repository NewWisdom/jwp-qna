package qna.domain;

import org.junit.jupiter.api.BeforeEach;

import static qna.domain.QuestionTest.Q1;
import static qna.domain.UserTest.JAVAJIGI;

class DeleteHistoryTest {
    public static DeleteHistory D1 = new DeleteHistory(ContentType.QUESTION, Q1.getId(), JAVAJIGI);
    public static DeleteHistory D2 = new DeleteHistory(ContentType.ANSWER, Q1.getId(), JAVAJIGI);

    @BeforeEach
    void setUp() {
        D1 = new DeleteHistory(ContentType.QUESTION, Q1.getId(), JAVAJIGI);
        D2 = new DeleteHistory(ContentType.ANSWER, Q1.getId(), JAVAJIGI);
    }
}