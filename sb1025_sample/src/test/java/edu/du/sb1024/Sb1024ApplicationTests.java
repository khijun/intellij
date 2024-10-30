package edu.du.sb1024;

import edu.du.sb1024.fileuploadboard.board.dto.BoardDto;
import edu.du.sb1024.fileuploadboard.entity.Board;
import edu.du.sb1024.fileuploadboard.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@SpringBootTest
class Sb1024ApplicationTests {

    @Autowired
    BoardRepository boardRepository;

    @Test
    void contextLoads() {
        Pageable pageable = PageRequest.of(0, 10);
        System.out.println(pageable);
        List<Board> page = boardRepository.findAllByOrderByBoardIdxDesc(pageable);
        System.out.println("페이지: " + page);
        page.forEach(System.out::println);
    }

    @Test
    void testPage2(){
        Pageable pageable = PageRequest.of(0, 10);
        List<Board> page = boardRepository.findAllByOrderByBoardIdxDesc(pageable);
        System.out.println(page.getFirst());

    }

    @Test
    void testPage3(){
        Pageable pageable = PageRequest.of(1, 10);
        List<Board> list = boardRepository.findAll();
        final int start = (int) pageable.getOffset();
        System.out.println(start);
        final int end = Math.min((start + pageable.getPageSize()), list.size());
        System.out.println(end);
        final Page<Board> page = new PageImpl<>(list.subList(start, end), pageable, list.size());
    }

}
