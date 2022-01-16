package myproject.shoppingmall.domain;

import lombok.*;
import myproject.shoppingmall.dto.MemberDto;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
//일관 성 유 지 를 위 해 protected를 사용 하여 무 분 별 한 생 성을 제한 해야 함
    //Setter 또한 막아 서 일 관 성 을 유지 하게 해 야 함
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
    public Member(String loginId, String password, String name, String email,Address address) {
        Assert.hasText(loginId, "로그인아이디는 비어있으면 안됨");
        Assert.hasText(password, "password는 비어있으면 안됨");
        Assert.hasText(name, "name은 비어있으면 안됨");
        Assert.hasText(email, "email은 비어있으면 안됨");

        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.address = address;
        this.email = email;
    }

    public void updateMember(MemberDto memberDto) {

        this.loginId = memberDto.getLoginId();
        this.password = memberDto.getPassword();
        this.name = memberDto.getName();
        this.address = new Address(memberDto.getCity(), memberDto.getStreet(), memberDto.getZipcode());

    }
}
