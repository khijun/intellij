package edu.du.sb1021.config;

import edu.du.sb1021.entity.Member;
import edu.du.sb1021.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
@Log4j2
public class SecurityConfig {

    final MemberRepository mr;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username ->{
            log.info("사용자: " + username);
            return toUserDetails(mr.findByUsername(username).get());
        };
    }

    public UserDetails toUserDetails(Member member){
        return User.builder()
                .username(member.getUsername())
                .password(member.getPassword())
                .roles("USER")
                .build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .antMatchers("/sample/join**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .anyRequest().authenticated();
        http.formLogin();
        http.csrf().disable();
        http.logout();
        http.exceptionHandling().accessDeniedPage("/sample/accessDenied");
        http.csrf()
                .ignoringAntMatchers("/h2-console/**")
                .and().headers().frameOptions().sameOrigin();
        http.csrf()
                .ignoringAntMatchers("/sample/join");
        return http.build();
    }
}
