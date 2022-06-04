//package myproject.shoppingmall.repository;
//
//import myproject.shoppingmall.domain.member.constant.Address;
//import myproject.shoppingmall.domain.member.entity.Member;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@Transactional
//class MemberRepositoryTest {
//
//    @Autowired
//    MemberRepository memberRepository;
//
//    @Autowired
//    EntityManager em;
//
//    @Test
//    public void loginTest() {
//        //given
//        Member member = Member.builder()
//                .loginId("thsckdgus0")
//                .password("123123")
//                .name("thsckd")
//                .email("123@naver.com")
//                .address(new Address("city", "street", "zipcode"))
//                .build();
//
//        memberRepository.save(member);
//
//        em.flush();
//        em.clear();
//
//        //when
//        Member findMember = memberRepository.findByLoginId("thsckdgus0").get();
//
//        //then
//        assertThat(member.getLoginId()).isEqualTo(findMember.getLoginId());
//    }
//
//}