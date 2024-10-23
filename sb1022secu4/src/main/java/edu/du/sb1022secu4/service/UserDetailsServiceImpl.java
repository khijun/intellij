package edu.du.sb1022secu4.service;

import edu.du.sb1022secu4.entity.Member;
import edu.du.sb1022secu4.repository.MemberRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    private PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("==============> 사용자 : " + username);
//        UserDetails user = User.withUsername("user")
//                .password(passwordEncoder().encode("1234"))
//                .roles("USER")
//                .build();
//        Member member2 = Member.builder()
//                .id(1001L)
//                .username("홍길동")
//                .password(passwordEncoder().encode("1234"))
//                .email("hong@aaa.com")
//                .build();
        Member member = memberRepository.findByUsername(username).get();
        return toUserDetails(member);
    }

    private UserDetails toUserDetails(Member member){
        return User.builder()
                .username(member.getUsername())
                .password(member.getPassword())
//                .authorities(new SimpleGrantedAuthority(member.getRole().toString()))
                .roles("USER")
                .build();
    }
}
