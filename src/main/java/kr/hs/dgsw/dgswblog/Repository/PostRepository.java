package kr.hs.dgsw.dgswblog.Repository;

import kr.hs.dgsw.dgswblog.Domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository
    extends JpaRepository<Post, Long> {

    List<Post> findAllByOrderByCreatedDesc();

    Optional<Post> findTopByUserIdOrderByIdDesc(Long userId);
}
