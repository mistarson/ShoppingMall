package myproject.shoppingmall.domain.member.entity;

import lombok.*;
import myproject.shoppingmall.domain.post.entity.Post;
import myproject.shoppingmall.domain.audit.BaseEntity;
import myproject.shoppingmall.domain.member.constant.Address;
import myproject.shoppingmall.domain.order.entity.Order;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(length = 15, nullable = false)
    private String loginId;

    @Column(nullable = false)
    private String password;

    @Column(length = 15, nullable = false)
    private String name;

    @Column(length = 50, nullable = false)
    private String email;

    @OneToMany(mappedBy = "member")
    private List<Order> orderList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Post> postList = new ArrayList<>();

    @Embedded
    @Column(nullable = false)
    private Address address;

    private String role;



    @Builder
    public Member(String loginId, String password, String name, String email, Address address) {
        Assert.hasText(loginId, "로그인아이디는 비어있으면 안됨");
        Assert.hasText(password, "password는 비어있으면 안됨");
        Assert.hasText(name, "name은 비어있으면 안됨");
        Assert.hasText(email, "email은 비어있으면 안됨");

        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.address = address;
        this.role = "ROLE_USER";
    }

    public void updateMember(String name, Address address) {
        this.name = name;
        this.address = address;
    }
}