package edu.du.sb1014_2;

import edu.du.sb1014_2.entity.Board;
import edu.du.sb1014_2.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Sb10142ApplicationTests {

    @Autowired
    BoardRepository boardRepository;

    @Test
    void contextLoads() {
        System.out.println(boardRepository.findById(1));
    }

    @Test
    void brTest(){
//        boardRepository.selectBoardList().forEach(System.out::println);
    }

}
