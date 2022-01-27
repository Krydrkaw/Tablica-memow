package pl.gw.memboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.gw.memboard.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
