package edu.du.sb1021_hd_db;

import edu.du.sb1021_hd_db.entity.Member;
import edu.du.sb1021_hd_db.repository.MemberRepository;
import edu.du.sb1021_hd_db.spring.MemberDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@SpringBootTest
class Sb1021HdDbApplicationTests {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberDao memberDao;

    @Test
    void contextLoads() {
    }

    @Test
    void inputTest(){
        Member member = Member.builder()
                .name("홍길동")
                .email("hong@korea")
                .password("1234")
                .regdate(LocalDateTime.now())
                .build();
        System.out.println(memberRepository.save(member));

        Member members = memberDao.selectByEmail("hong@korea");
        System.out.println(members);
    }

    @Test
    void daoInputTest(){
        Member member = Member.builder()
                .name("홍길동")
                .email("hong@korea")
                .password("1234")
                .regdate(LocalDateTime.now())
                .build();
    }

}
