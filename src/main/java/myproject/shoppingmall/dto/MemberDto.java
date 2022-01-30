package myproject.shoppingmall.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import myproject.shoppingmall.domain.Address;
import myproject.shoppingmall.domain.Member;

import javax.persistence.Embedded;
import javax.validation.constraints.NotEmpty;

@Getter
public class MemberDto {

    @NotEmpty(message = "아이디를 입력해주세요.")
    private String loginId;

    @NotEmpty(message = "비밀번호를 입력해주세요.")
    private String password;

    @NotEmpty(message = "이름을 입력해주세요.")
    private String name;

    @NotEmpty(message = "이메일을 입력해주세요.")
    private String email;

    private String city;
    private String street;
    private String zipcode;

    //
    public MemberDto(Member member) {
        this.loginId = member.getLoginId();
        this.password = member.getPassword();
        this.name = member.getName();
        this.email = member.getEmail();
        this.city = member.getAddress().getCity();
        this.street = member.getAddress().getStreet();
        this.zipcode = member.getAddress().getZipcode();
    }

    public Member toEntity() {

        return Member.builder()
                .loginId(this.getLoginId())
                .password(this.getPassword())
                .name(this.getName())
                .email(this.getEmail())
                .address(new Address(this.getCity(), this.getStreet(), this.getZipcode()))
                .build();

    }
}
