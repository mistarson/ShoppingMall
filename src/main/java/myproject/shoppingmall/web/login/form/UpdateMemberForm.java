package myproject.shoppingmall.web.login.form;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import myproject.shoppingmall.domain.member.constant.Address;
import myproject.shoppingmall.domain.member.entity.Member;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter @Setter
@Builder
public class UpdateMemberForm {

    @NotBlank(message = "아이디는 필수입니다.")
    private String loginId;

    @NotBlank(message = "이름은 필수입니다.")
    @Size(min = 1, max = 15, message = "이름은 1~15자로 입력해주세요.")
    private String memberName;

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

    public static UpdateMemberForm from(Member member) {

        Address address = member.getAddress();

        return UpdateMemberForm.builder()
                .loginId(member.getLoginId())
                .memberName(member.getMemberName())
                .email(member.getEmail())
                .city(address.getCity())
                .street(address.getStreet())
                .zipcode(address.getZipcode())
                .build();
    }

    public Member toEntity() {

        Address address = Address.from(city, street, zipcode);
        return Member.builder()
                .loginId(loginId)
                .name(memberName)
                .email(email)
                .address(address)
                .build();
    }

}
