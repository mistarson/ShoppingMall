package myproject.shoppingmall.repository;

import myproject.shoppingmall.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;

    @Test
    public void loginTest() {
        //given
        Member member = Member.builder()
                .loginId("thsckdgus0")
                .name("thsckd")
                .password("123123")
                .build();

        memberRepository.save(member);

        em.flush();
        em.clear();

        //when
        Member findMember = memberRepository.findByLoginId("thsckdgus0").get();

        //then
        assertThat(member.getLoginId()).isEqualTo(findMember.getLoginId());
    }

}