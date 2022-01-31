package myproject.shoppingmall.domain;

import lombok.*;
import myproject.shoppingmall.aop.BaseEntity;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "member_id", unique = true, nullable = false)
    private Long id;

    @Column(length = 15, nullable = false)
    private String loginId;

    @Column(length = 20, nullable = false)
    private String password;

    @Column(length = 15, nullable = false)
    private String name;

    @Column(length = 50, nullable = false)
    private String email;

    @OneToMany(mappedBy = "member", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Post> postList = new ArrayList<>();

    @Embedded
    private Address address;

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
    }

    public void updateMember(Member member) {

        this.loginId = member.getLoginId();
        this.password = member.getPassword();
        this.name = member.getName();
        this.address = member.address;

    }
}
