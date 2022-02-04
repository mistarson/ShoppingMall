package myproject.shoppingmall.form;

import lombok.Data;
import myproject.shoppingmall.domain.Address;
import myproject.shoppingmall.domain.Member;

import javax.validation.constraints.NotEmpty;

@Data
public class JoinForm {

    @NotEmpty(message = "아이디는 필수입니다.")
    private String loginId;

    @NotEmpty(message = "패스워드는 필수입니다.")
    private String password;

    @NotEmpty(message = "이름은 필수입니다.")
    private String name;

    @NotEmpty(message = "이메일을 입력해주세요.")
    private String email;

    private String city;
    private String street;
    private String zipcode;

    public Member joinFormToEntity() {

        return Member.builder()
                .loginId(this.getLoginId())
                .password(this.getPassword())
                .name(this.getName())
                .email(this.getEmail())
                .address(new Address(this.getCity(), this.getStreet(), this.getZipcode()))
                .build();

    }

}
