package edu.du.sb1029_board_validation;

import edu.du.sb1029_board_validation.fileuploadboard.entity.Board;
import edu.du.sb1029_board_validation.fileuploadboard.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.stream.IntStream;

@SpringBootApplication
//        (exclude = SecurityAutoConfiguration.class)
public class Sb1029BoardValidationApplication {

    @Autowired
    private BoardRepository boardRepository;

    public static void main(String[] args) {
        SpringApplication.run(Sb1029BoardValidationApplication.class, args);
    }

    @PostConstruct
    public void init() {
        IntStream.rangeClosed(1, 100).forEach(i -> {

            Board board = Board.builder()
                    .title("제목" + i)
                    .createdDatetime(LocalDateTime.now().toString().substring(0, 10))
                    .contents("내용" + i)
                    .deletedYn("N")
                    .hitCnt(0)
                    .build();
            boardRepository.save(board);
        });
    }

}
