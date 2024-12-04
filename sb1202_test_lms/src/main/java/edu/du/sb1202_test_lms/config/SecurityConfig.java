package edu.du.sb1202_test_lms.config;


import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
//@EnableWebSecurity(debug = true)
@Log4j2
public class SecurityConfig {
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        return username ->{
//            log.info("사용자: " + username);
//            return toUserDetails(memberService.findByUsername(username));
//        };
//    }

//    public UserDetails toUserDetails(Member member){
//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority(member.getRole()));
//        return User.builder()
//                .username(member.getUsername())
//                .password(member.getPassword())
//                .authorities(authorities)
//                .build();
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
//                .antMatchers("/", "/layout/**", "/search**/**").permitAll()  // public 경로는 인증 없이 접근 가능
//                .antMatchers("/js/**", "/css/**", "/images/**").permitAll()
//                .antMatchers("/login/**","/login", "/register/**", "/logout").permitAll()
//                .antMatchers("/item/add", "/item/stockIn").hasAnyAuthority("ROLE_MANAGER", "ROLE_ADMIN")
//                .antMatchers("/item/review").hasAnyRole("USER")
//                .antMatchers("/item/*").permitAll()
//                .antMatchers("/cart/deleteItem/**").hasAnyRole("USER", "ADMIN")
////                .antMatchers("C:/uploads/**").permitAll() // 이미지파일
//                .antMatchers("/files/**").permitAll() // 이미지파일
//                .anyRequest().authenticated()         // 다른 모든 요청은 인증 필요
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .and()
//                .logout()
//                .logoutUrl("/logout")
                .anyRequest()
                .permitAll();
        http.csrf().disable();
        return http.build();
    }
}
