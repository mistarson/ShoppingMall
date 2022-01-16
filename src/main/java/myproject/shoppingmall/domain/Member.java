package myproject.shoppingmall.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@ToString
public class Member {
//일관 성 유 지 를 위 해 protected를 사용 하여 무 분 별 한 생 성을 제한 해야 함
    //Setter 또한 막아 서 일 관 성 을 유지 하게 해 야 함
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String loginId;

    private String password;

    private String name;

    @Embedded
    private Address address;

    public Member(String loginId, String password, String name, Address address) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.address = address;
    }
}
