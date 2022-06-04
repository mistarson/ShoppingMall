package myproject.shoppingmall.web.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import myproject.shoppingmall.domain.member.entity.Member;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter @Setter @NoArgsConstructor
public class UpdateMemberForm {

    @NotEmpty(message = "아이디는 필수입니다.")
    private String loginId;

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

    public UpdateMemberForm(Member member) {
        this.loginId = member.getLoginId();
        this.name = member.getName();
        this.email = member.getEmail();
        this.city = member.getAddress().getCity();
        this.street = member.getAddress().getStreet();
        this.zipcode = member.getAddress().getZipcode();
    }
}
