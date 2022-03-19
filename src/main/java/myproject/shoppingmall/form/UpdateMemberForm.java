package myproject.shoppingmall.form;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
public class UpdateMemberForm {

    @NotEmpty(message = "이름은 필수입니다.")
    private String name;

    @NotEmpty(message = "이메일은 필수입니다.")
    private String email;

    @NotEmpty(message = "주소는 필수입니다.")
    private String city;

    @NotEmpty(message = "주소는 필수입니다.")
    private String street;

    @NotEmpty(message = "주소는 필수입니다.")
    private String zipcode;

    public UpdateMemberForm(String name, String email, String city, String street, String zipcode) {
        this.name = name;
        this.email = email;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
