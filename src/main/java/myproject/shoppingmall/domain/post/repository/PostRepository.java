package myproject.shoppingmall.domain.post.repository;

import myproject.shoppingmall.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
