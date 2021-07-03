package qna;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import qna.domain.Question;
import qna.domain.repository.QuestionRepository;
import qna.domain.repository.UserRepository;
import qna.service.QnaService;

import static qna.domain.AnswerTest.A1;
import static qna.domain.QuestionTest.Q1;
import static qna.domain.UserTest.JAVAJIGI;

@SpringBootTest
class ApplicationTest {
    @Autowired
    private QnaService qnaService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private QuestionRepository questionRepository;

    @Test
    void contextLoads() {
        userRepository.save(JAVAJIGI);
        Question save = questionRepository.save(Q1);
        qnaService.deleteQuestion(JAVAJIGI, save.getId());
    }
}
