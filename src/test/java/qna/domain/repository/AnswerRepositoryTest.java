package qna.domain.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import qna.domain.Answer;
import qna.domain.Question;
import qna.domain.User;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static qna.domain.AnswerTest.A1;
import static qna.domain.QuestionTest.Q1;
import static qna.domain.UserTest.JAVAJIGI;

/**
 * @Id
 * @GeneratedValue(strategy = GenerationType.IDENTITY)
 * private Long id;
 * @ManyToOne private User writer;
 * @OneToOne private Question question;
 * private String contents;
 * private boolean deleted = false;
 */

@DataJpaTest
class AnswerRepositoryTest {
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private QuestionRepository questionRepository;

    @Test
    void save() {
        User user = userRepository.save(JAVAJIGI);
        Question question = questionRepository.save(Q1);
        Answer saveAnswer = answerRepository.save(A1);

        assertAll(
                () -> assertThat(saveAnswer.getId()).isNotNull(),
                () -> assertThat(saveAnswer.getWriter()).isEqualTo(user),
                () -> assertThat(saveAnswer.getQuestion()).isEqualTo(question),
                () -> assertThat(saveAnswer.getContents()).isEqualTo(A1.getContents())
        );
    }
}