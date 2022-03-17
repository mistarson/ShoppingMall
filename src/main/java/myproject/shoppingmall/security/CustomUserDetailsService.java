package myproject.shoppingmall.security;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import myproject.shoppingmall.domain.Member;
import myproject.shoppingmall.repository.MemberRepository;
import myproject.shoppingmall.service.MemberService;
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username = " + username);

        Member findMember = memberRepository.findByLoginId(username).get();

        if (findMember == null) {
            throw new UsernameNotFoundException("입력한 ID는 없는 ID입니다.");
        }

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(findMember.getRole()));

        return new AccountContext(findMember, roles);

    }
}
