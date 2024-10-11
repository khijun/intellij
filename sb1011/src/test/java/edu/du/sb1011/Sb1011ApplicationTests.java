package edu.du.sb1011;

import edu.du.sb1011.dto.BoardDto;
import edu.du.sb1011.dto.MemberDto;
import edu.du.sb1011.mapper.MemberMapper;
import edu.du.sb1011.service.BoardService;
import edu.du.sb1011.service.MemberService;
import edu.du.sb1011.service.MemberServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootTest
class Sb1011ApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    MemberService ms;

    @Autowired
    BoardService bs;

    @Test
    void memberDaoTest(){
        ms.getAll().forEach(System.out::println);
        MemberDto dto = new MemberDto(-1, "name", "id", "name", "");
        ms.addOne(dto);
        int recentId = ms.getMaxIdx();
        System.out.println("New Member: " + ms.getOneByIdx(recentId));
        MemberDto updateDto = new MemberDto(recentId, "updatedName", "id", "name", "");
        ms.setOne(updateDto);
        System.out.println("Update Member: " + ms.getOneByIdx(recentId));
//        ms.delOne(recentId);
        System.out.println("Delete Member: ");
        System.out.println(ms.getOneByIdx(recentId));
    }

    @Test
    void loginFailExceptionTest() throws Exception{
        MemberDto dto = new MemberDto(-1, "name", "khijun00", "wrongPassword", "");
        ms.login(null, dto);
    }

    @Test
    void loginSuccessTest() throws Exception{
        MemberDto dto = new MemberDto(-1, "name", "khijun00", "1234", "");
        System.out.println("Input Member: " + dto);
        MemberDto loginMember = ms.login(null, dto);
        System.out.println("Login Member: " + loginMember);
    }

    @Test
    void boardInsertTest() throws Exception{
        BoardDto board = new BoardDto();
        board.setContents("contents");
        board.setTitle("title");
        board.setCreatorId(1);
        bs.insertBoard(board);
        System.out.println(board);
    }
}
