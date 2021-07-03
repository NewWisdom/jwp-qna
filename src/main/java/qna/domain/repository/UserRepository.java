package qna.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import qna.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
