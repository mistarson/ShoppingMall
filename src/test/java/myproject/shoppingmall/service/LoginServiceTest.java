package myproject.shoppingmall.service;

import myproject.shoppingmall.domain.Member;
import myproject.shoppingmall.domain.form.LoginForm;
import myproject.shoppingmall.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class LoginServiceTest {

    @Autowired
    LoginService loginService;

    @Autowired
    MemberRepository memberRepository;

//    @Test
//    void loginTest() throws Exception {
//
//        Member member = new Member();
//        member.setLoginId("thsckdgus0");
//        member.setName("thsckd");
//        member.setPassword("123123");
//        memberRepository.save(member);
//
//        LoginForm loginForm = new LoginForm();
//        loginForm.setLoginId(member.getLoginId());
//        loginForm.setPassword(member.getPassword());
//
//        LoginForm loginForm2 = new LoginForm();
//        loginForm2.setLoginId("123");
//        loginForm2.setPassword("123");
//
//        Member loginMember = loginService.login(loginForm);
//        Member loginMember2 = loginService.login(loginForm2);
//
//        assertThat(member.getLoginId()).isEqualTo(loginMember.getLoginId());
//        assertThat(loginMember2).isNull();
//
//
//    }

}