package kr.hs.dgsw.dgswblog.Repository;

import kr.hs.dgsw.dgswblog.Domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository
    extends JpaRepository<Post, Long> {

    List<Post> findAllByOrderByCreatedDesc();
}
