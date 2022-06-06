package myproject.shoppingmall.domain.member.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import myproject.shoppingmall.domain.audit.BaseEntity;
import myproject.shoppingmall.domain.member.constant.Address;
import myproject.shoppingmall.domain.member.constant.Role;
import myproject.shoppingmall.domain.order.entity.Order;
import myproject.shoppingmall.domain.post.entity.Post;

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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public Member(String loginId, String password, String name, String email, Address address, Role role) {

        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.address = address;
        this.role = role;
    }

    public static Member createMember(Member member) {
        return Member.builder()
                .loginId(member.getLoginId())
                .password(member.getPassword())
                .name(member.getName())
                .email(member.getEmail())
                .address(member.getAddress())
                .role(member.getRole())
                .build();
    }

    public void updateMember(String name, Address address) {
        this.name = name;
        this.address = address;
    }
}
