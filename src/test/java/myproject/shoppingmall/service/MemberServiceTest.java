//package myproject.shoppingmall.service;
//
//import myproject.shoppingmall.domain.member.entity.Member;
//import myproject.shoppingmall.web.form.JoinForm;
//import myproject.shoppingmall.domain.member.repository.MemberRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@Transactional
//class MemberServiceTest {
//
//    @Autowired
//    MemberService memberService;
//
//    @Autowired
//    MemberRepository memberRepository;
//
//    @Autowired
//    EntityManager em;
//
//    @Test
//    void 회원전체조회() {
//        JoinForm joinForm = new JoinForm();
//        joinForm.setName("KIM");
//        joinForm.setLoginId("KIM");
//        joinForm.setPassword("123");
//
//
//        JoinForm joinForm1 = new JoinForm();
//        joinForm1.setName("LEE");
//        joinForm1.setLoginId("LEE");
//        joinForm1.setPassword("123");
//
//        memberService.join(joinForm);
//        memberService.join(joinForm1);
//
//        List<Member> members = memberService.findMembers();
//
//        assertThat(members.size()).isEqualTo(2);
//
//    }
//
//    @Test
//    void 없는회원조회() throws Exception {
//
//        assertThrows(Exception.class, () -> memberService.findByLoginId("e12e"));
//
//
//    }
//
//    @Test
//    void 회원가입() throws Exception {
//        //given
//        JoinForm joinForm = new JoinForm();
//        joinForm.setName("KIM");
//        joinForm.setLoginId("123");
//        joinForm.setPassword("123");
//        Member member = joinForm.joinFormToEntity();
//
//        //when
//        memberService.join(joinForm);
//
//        Member findMember = memberService.findByLoginId(member.getLoginId());
//
//        //then
//        assertThat(member.getLoginId()).isEqualTo(findMember.getLoginId());
//    }
//
//    @Test
//    void 중복_회원_예외() {
//        //given
//        JoinForm joinForm = new JoinForm();
//        joinForm.setName("KIM");
//        joinForm.setLoginId("123");
//        joinForm.setPassword("123");
//
//
//        JoinForm joinForm1 = new JoinForm();
//        joinForm1.setName("LEE");
//        joinForm1.setLoginId("123");
//        joinForm1.setPassword("123");
//
//        //when
//        memberService.join(joinForm);
//
//        //then
//        assertThrows(IllegalStateException.class, ()->memberService.join(joinForm1));
//
//    }
//
////    @Test
////    void EntityToDto() throws Exception{
////        Member member = new Member();
////        member.setLoginId("123");
////        member.setPassword("123");
////        member.setName("KIM");
////
////        MemberDto memberDto = memberService.EntityToDto();
////
////        assertThat(member.getLoginId()).isEqualTo(memberDto.getLoginId());
////        assertThat(member.getPassword()).isEqualTo(memberDto.getPassword());
////        assertThat(member.getName()).isEqualTo(memberDto.getName());
////    }
////
////    @Test
////    void 멤버업데이트() throws Exception {
////
////        Member member = new Member();
////        member.setLoginId("123");
////        member.setPassword("123");
////        member.setName("KIM");
////
////        memberRepository.save(member);
////
////        em.flush();
////        em.clear();
////
////        MemberDto memberDto = new MemberDto();
////        memberDto.setLoginId("456");
////        memberDto.setPassword("456");
////        memberDto.setName("SON");
////        memberService.updateMember(member.getId(), memberDto);
////
////        assertThat(memberDto.getLoginId()).isEqualTo("456");
////        assertThat(memberDto.getPassword()).isEqualTo("456");
////        assertThat(memberDto.getName()).isEqualTo("SON");
////
////    }
//
//}