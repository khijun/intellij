package edu.du.sb1011.service;

import edu.du.sb1011.dto.MemberDto;
import edu.du.sb1011.exception.LoginFailedException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface MemberService {
    List<MemberDto> getAll();

    MemberDto login(HttpSession session, MemberDto inputDto) throws LoginFailedException;

    int delOne(int memberIdx);
    int addOne(MemberDto memberDto);
    int setOne(MemberDto memberDto);
    MemberDto getOneByIdx(int memberIdx);
    int getMaxIdx();
}
