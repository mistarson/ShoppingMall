package myproject.shoppingmall.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import myproject.shoppingmall.domain.Address;

import javax.persistence.Embedded;

@Data
@NoArgsConstructor
public class MemberDto {

    private String loginId;

    private String password;

    private String name;

    private String city;
    private String street;
    private String zipcode;

}
