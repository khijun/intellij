package edu.du.sb1021_hd_db;

import edu.du.sb1021_hd_db.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class Sb1021HdDbApplication {

    final MemberRepository memberRepository;

    public static void main(String[] args) {
        SpringApplication.run(Sb1021HdDbApplication.class, args);

    }
}
