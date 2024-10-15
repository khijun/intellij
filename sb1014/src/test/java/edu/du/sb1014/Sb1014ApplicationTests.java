package edu.du.sb1014;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
class Sb1014ApplicationTests {

    @Autowired
    MemoRepository mr;

    @Test
    void insertMemo() {
        System.out.println(mr.getClass().getName());
        IntStream.range(0,10).forEach(i->{
//            System.out.println(i);
            Memo memo = Memo.builder().memoText("Sample"+i).build();
//            System.out.println(memo);
            mr.save(memo);
        });
    }

    @Test
    void selectMemo() {
        long mno = 10L;
        Optional<Memo> memo = mr.findById(mno);
        System.out.println(memo.get());
    }

    @Test
    void findAllMemo() {
        List<Memo> memos = mr.findAll();
        memos.forEach(System.out::println);
    }

    @Test
    void updateMemo() {
        Memo memo = Memo.builder().id(1L).memoText("Updated memo").build();
        mr.save(memo);
    }

    @Test
    void deleteMemo(){
        long mno = 10L;
        mr.deleteById(mno);
    }

    @Test
    void queryMethod(){
        List<Memo> memos = mr.findByIdBetweenOrderByIdDesc(2L, 7L);
        memos.forEach(System.out::println);
    }

    @Test
    void queryMethod2(){
        List<Memo> memos = mr.findByMemoTextContaining("sam");
        memos.forEach(System.out::println);
    }

    @Test
    void queryAnnotation(){
        List<Memo> memos = mr.getListDesc();
        memos.forEach(System.out::println);
    }

    @Test
    void queryAnnotation2(){
        System.out.printf("Memo count: %d\n", mr.getCount());
    }

}
