package myproject.shoppingmall.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import myproject.shoppingmall.dto.MemberDto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter @Setter @NoArgsConstructor
public class UpdateMemberForm {

    @NotEmpty(message = "아이디는 필수입니다.")
    private String loginId;

    @NotEmpty(message = "이름은 필수입니다.")
    @Size(min = 1, max = 15)
    private String name;

    @NotEmpty(message = "이메일은 필수입니다.")
    private String email;

    @NotEmpty(message = "주소는 필수입니다.")
    private String city;

    @NotEmpty(message = "주소는 필수입니다.")
    private String street;

    @NotEmpty(message = "주소는 필수입니다.")
    private String zipcode;

    public UpdateMemberForm(MemberDto memberDto) {
        this.loginId = memberDto.getLoginId();
        this.name = memberDto.getName();
        this.email = memberDto.getEmail();
        this.city = memberDto.getCity();
        this.street = memberDto.getStreet();
        this.zipcode = memberDto.getZipcode();
    }
}
