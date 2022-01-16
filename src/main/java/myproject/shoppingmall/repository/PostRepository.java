package myproject.shoppingmall.repository;

import myproject.shoppingmall.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
