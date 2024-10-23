package edu.du.sb1021;

import edu.du.sb1021.repository.MemberRepository;
import edu.du.sb1021.repository.RoleRepository;
import edu.du.sb1021.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Sb1021ApplicationTests {

    @Autowired
    MemberRepository mr;

    @Autowired
    RoleRepository rr;

    @Autowired
    MemberService ms;


    @Test
    void contextLoads() {
    }

    @Test
    void test(){
//        mrr.findAll().forEach(System.out::println);
        mr.findAll().forEach(System.out::println);
        rr.findAll().forEach(System.out::println);
    }

}
