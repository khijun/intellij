package edu.du.sb1202_test_lms;

import edu.du.sb1202_test_lms.service.DummyDataService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Sb1202TestLmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(Sb1202TestLmsApplication.class, args);
    }

    @Bean
    public CommandLineRunner dataLoader(DummyDataService dummyDataService) {
        return args -> dummyDataService.generateDummyData();
    }
}

