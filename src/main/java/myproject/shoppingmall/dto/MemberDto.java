package myproject.shoppingmall.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import myproject.shoppingmall.domain.Address;

import javax.persistence.Embedded;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
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

}
