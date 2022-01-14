package myproject.shoppingmall.domain.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class JoinForm {

    @NotEmpty(message = "이름은 필수입니다.")
    private String name;

    @NotEmpty(message = "아이디는 필수입니다.")
    private String loginId;

    @NotEmpty(message = "패스워드는 필수입니다.")
    private String password;

    private String city;
    private String street;
    private String zipcode;

}