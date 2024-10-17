package edu.du.sb1015_2;

import edu.du.sb1015_2.entity.MyData;
import edu.du.sb1015_2.repository.MyDataRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Sb10152ApplicationTests {

    @Autowired
    MyDataRepository myDataRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void getList(){
        myDataRepository.findAll().forEach(System.out::println);
    }

    @Test
    void dmlTest(){
        getList();
        String mail = "test@mail.com";
        MyData myData = MyData.builder().age(20).mail(mail).name("읾").memo("테스트계정임").build();
        myDataRepository.save(myData);
        getList();
        Long l = myDataRepository.findByMail(mail).get().getId();
        myDataRepository.deleteById(l);
        getList();
    }

    @Test
    void getOne(){
        System.out.println(myDataRepository.findById(1L));
    }


}
