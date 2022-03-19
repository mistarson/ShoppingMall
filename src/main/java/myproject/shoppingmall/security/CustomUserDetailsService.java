package myproject.shoppingmall.security;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import myproject.shoppingmall.domain.Member;
import myproject.shoppingmall.repository.MemberRepository;
import myproject.shoppingmall.service.MemberService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, BadCredentialsException {
        System.out.println("username = " + username);

        if (memberRepository.findByLoginId(username).isEmpty()) {
            throw new BadCredentialsException("BadCredentialsException: 아이디나 비밀번호가 일치하지 않습니다.");
        }

        Member findMember = memberRepository.findByLoginId(username).get();

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(findMember.getRole()));

        return new AccountContext(findMember, roles);

    }
}
