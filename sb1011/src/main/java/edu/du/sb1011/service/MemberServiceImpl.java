package edu.du.sb1011.service;

import edu.du.sb1011.dto.MemberDto;
import edu.du.sb1011.exception.IdNotFoundException;
import edu.du.sb1011.exception.LoginFailedException;
import edu.du.sb1011.exception.PasswordWrongException;
import edu.du.sb1011.mapper.MemberMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberMapper mm;

    @Override
    public List<MemberDto> getAll() {
        return mm.getAll();
    }

    @Override
    public MemberDto login(MemberDto inputDto) throws LoginFailedException {
        try {
            System.out.println(inputDto);
            MemberDto dto = mm.getOneById(inputDto.getId());//  입력한 아이디로 부터 갖고온 계정 정보
            System.out.println(dto);
            if(dto == null)//   입력한 아이디에 해당하는 계정이 없을 시
                throw new IdNotFoundException();
            String password = inputDto.getPassword();
            if(!(dto.getPassword().equals(inputDto.getPassword())))//   입력한 비번과 입력한 아이디의 계정의 비번이 비일치시
                throw new PasswordWrongException();
            return dto;
        }catch (IdNotFoundException | PasswordWrongException e){
            e.printStackTrace();
            throw new LoginFailedException();
        }
    }

    @Override
    public int delOne(int memberIdx) {
        return mm.delOne(memberIdx);
    }

    @Override
    public int addOne(MemberDto memberDto) {
        return mm.addOne(memberDto);
    }

    @Override
    public int setOne(MemberDto memberDto) {
        return mm.setOne(memberDto);
    }

    @Override
    public MemberDto getOneByIdx(int memberIdx) {
        return mm.getOneByIdx(memberIdx);
    }

    @Override
    public int getMaxIdx() {
        return mm.getMaxIdx();
    }
}

