package myproject.shoppingmall.service;

import myproject.shoppingmall.domain.Member;
import myproject.shoppingmall.domain.form.JoinForm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    void 회원전체조회() {
        JoinForm joinForm = new JoinForm();
        joinForm.setName("KIM");
        joinForm.setLoginId("KIM");
        joinForm.setPassword("123");


        JoinForm joinForm1 = new JoinForm();
        joinForm1.setName("LEE");
        joinForm1.setLoginId("LEE");
        joinForm1.setPassword("123");

        memberService.join(joinForm);
        memberService.join(joinForm1);

        List<Member> members = memberService.findMembers();

        assertThat(members.size()).isEqualTo(2);

    }

    @Test
    void 없는회원조회() throws Exception {

        assertThrows(Exception.class, () -> memberService.findByLoginId("e12e"));


    }

    @Test
    void 회원가입() throws Exception {
        //given
        JoinForm joinForm = new JoinForm();
        joinForm.setName("KIM");
        joinForm.setLoginId("123");
        joinForm.setPassword("123");
        Member member = memberService.DtoToEntity(joinForm);

        //when
        memberService.join(joinForm);

        Member findMember = memberService.findByLoginId(member.getLoginId());

        //then
        assertThat(member.getLoginId()).isEqualTo(findMember.getLoginId());
    }

    @Test
    void 중복_회원_예외() {
        //given
        JoinForm joinForm = new JoinForm();
        joinForm.setName("KIM");
        joinForm.setLoginId("123");
        joinForm.setPassword("123");


        JoinForm joinForm1 = new JoinForm();
        joinForm1.setName("LEE");
        joinForm1.setLoginId("123");
        joinForm1.setPassword("123");

        //when
        memberService.join(joinForm);

        //then
        assertThrows(IllegalStateException.class, ()->memberService.join(joinForm1));

    }

}