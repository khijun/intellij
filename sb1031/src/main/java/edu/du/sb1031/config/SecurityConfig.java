package edu.du.sb1031.config;

import edu.du.sb1031.dto.AuthInfo;
import edu.du.sb1031.entity.Member;
import edu.du.sb1031.service.MemberService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
//@EnableWebSecurity(debug = true)
@Log4j2
public class SecurityConfig{
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    @Lazy// 순환참조 회피
    private MemberService memberService;


    @Bean
    public UserDetailsService userDetailsService() {
        return username ->{
            log.info("사용자: " + username);
            return toUserDetails(memberService.findByUsername(username));
        };
    }

    public UserDetails toUserDetails(Member member){
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(member.getRole()));
        System.out.println("현재 유저의 권한: " + member.getRole());
        return User.builder()
                .username(member.getUsername())
                .password(member.getPassword())
                .authorities(authorities)
                .build();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler(){
        return (HttpServletRequest request, HttpServletResponse response, Authentication authentication)->{
            // 로그인한 사용자의 이름을 가져옵니다.
            String username = authentication.getName();
            System.out.println("로그인 성공");

            // 여기서 'username'을 사용하여 Member 객체를 조회하고, 'authInfo'를 세션에 저장합니다.
            // 예를 들어, MemberService에서 'username'을 사용하여 멤버를 조회한다고 가정합니다.
            Member member = memberService.findByUsername(username); // 회원 정보 조회

            // 세션에 authInfo 추가
            request.getSession().setAttribute("authInfo", new AuthInfo(member.getId()));

            // 로그인 후 /home 페이지로 리디렉션
            response.sendRedirect("/");
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/layout/**", "/search**/**").permitAll()  // public 경로는 인증 없이 접근 가능
                .antMatchers("/js/**", "/css/**", "/images/**").permitAll()
                .antMatchers("/login/**","/login", "/register/**").permitAll()
                .antMatchers("/item/add").hasAnyAuthority("ROLE_MANAGER", "ROLE_ADMIN")
                .antMatchers("/item/*").permitAll()
                .anyRequest().authenticated()         // 다른 모든 요청은 인증 필요
                .and()
                .formLogin()
                .loginPage("/login")
                .successHandler(authenticationSuccessHandler())// 기본 로그인 폼 사용
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .permitAll();
        return http.build();
    }
}
