package myproject.shoppingmall.domain.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginForm {

    @NotEmpty(message = "아이디를 입력해주세요")
    private String loginId;

    @NotEmpty(message = "비밀번호를 입력해주세요")
    private String password;

}
