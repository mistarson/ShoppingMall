package myproject.shoppingmall.service;

import lombok.RequiredArgsConstructor;
import myproject.shoppingmall.domain.Member;
import myproject.shoppingmall.domain.form.JoinForm;
import myproject.shoppingmall.domain.form.LoginForm;
import myproject.shoppingmall.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    // null이면 로그인 실패
    public Member login(LoginForm loginForm) throws Exception {
        return memberRepository.findByLoginId(loginForm.getLoginId())
                .filter( m->m.getPassword().equals(loginForm.getPassword()))
                .orElse(null);
    }
}
