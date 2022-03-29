package myproject.shoppingmall.form;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
public class UpdateMemberForm {

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

    public UpdateMemberForm(String name, String email, String city, String street, String zipcode) {
        this.name = name;
        this.email = email;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
