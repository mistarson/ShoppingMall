package myproject.shoppingmall.domain.post.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import myproject.shoppingmall.domain.audit.BaseEntity;
import myproject.shoppingmall.domain.member.entity.Member;
import org.springframework.util.Assert;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, targetEntity = Member.class)
    @JoinColumn(name = "member_id", updatable = false)
    private Member member;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Builder
    public Post(String title, Member member, String content) {
        Assert.hasText(title, "글 제목은 비어있을 수 없습니다.");
        Assert.hasText(member.getLoginId(), "회원은 비어있을 수 없습니다.");
        Assert.hasText(content, "글 내용은 비어있을 수 없습니다.");

        this.title = title;
        this.member = member;
        this.content = content;
    }
}
