package myproject.shoppingmall.web.form;

import lombok.Data;
import myproject.shoppingmall.domain.member.constant.Address;
import myproject.shoppingmall.domain.member.entity.Member;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class JoinForm {

    @NotEmpty(message = "아이디는 필수입니다.")
    @Size(min = 1, max = 10, message = "아이디는 1~10자로 입력해주세요.")
    private String loginId;

    @NotEmpty(message = "패스워드는 필수입니다.")
    @Size(min = 1, max = 20, message = "비밀번호는 1~20자로 입력해주세요.")
    private String password;

    @NotEmpty(message = "이름은 필수입니다.")
    @Size(min = 1, max = 15, message = "이름은 1~15자로 입력해주세요.")
    private String name;

    @NotEmpty(message = "이메일은 필수입니다.")
    @Size(min = 1, max = 30, message = "이메일은 1~30자로 입력해주세요.")
    private String email;

    @NotEmpty(message = "주소는 필수입니다.")
    @Size(min = 1, max = 10)
    private String city;

    @NotEmpty(message = "주소는 필수입니다.")
    @Size(min = 1, max = 10)
    private String street;

    @NotEmpty(message = "주소는 필수입니다.")
    @Size(min = 1, max = 10)
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

    public void setPassword(String password) {
        this.password = password;
    }
}
