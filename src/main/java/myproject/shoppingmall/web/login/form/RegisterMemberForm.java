package myproject.shoppingmall.web.login.form;

import lombok.Data;
import myproject.shoppingmall.domain.member.constant.Address;
import myproject.shoppingmall.domain.member.constant.Role;
import myproject.shoppingmall.domain.member.entity.Member;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class RegisterMemberForm {

    @NotBlank(message = "아이디는 필수입니다.")
    @Size(min = 1, max = 10, message = "아이디는 1~10자로 입력해주세요.")
    private String loginId;

    @NotBlank(message = "패스워드는 필수입니다.")
    @Size(min = 1, max = 20, message = "비밀번호는 1~20자로 입력해주세요.")
    private String password;

    @NotBlank(message = "이름은 필수입니다.")
    @Size(min = 1, max = 15, message = "이름은 1~15자로 입력해주세요.")
    private String name;

    @NotBlank(message = "이메일은 필수입니다.")
    @Size(min = 1, max = 30, message = "이메일은 1~30자로 입력해주세요.")
    private String email;

    @NotBlank(message = "주소는 필수입니다.")
    @Size(min = 1, max = 10)
    private String city;

    @NotBlank(message = "주소는 필수입니다.")
    @Size(min = 1, max = 10)
    private String street;

    @NotBlank(message = "주소는 필수입니다.")
    @Size(min = 1, max = 10)
    private String zipcode;

    public Member toEntity(PasswordEncoder passwordEncoder) {

        return Member.builder()
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .name(name)
                .email(email)
                .address(new Address(city, street, zipcode))
                .role(Role.USER)
                .build();

    }

    public void setPassword(String password) {
        this.password = password;
    }
}
