package edu.du.sb1030.service;

import edu.du.sb1030.dao.MemberDao;
import edu.du.sb1030.dto.Member;
import edu.du.sb1030.dto.RegisterRequest;
import edu.du.sb1030.exception.DuplicateMemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MemberRegisterService {
	@Autowired
	private MemberDao memberDao;

	public Long regist(RegisterRequest req) {
		Member member = memberDao.selectByEmail(req.getEmail());
		if (member != null) {
			throw new DuplicateMemberException("dup email " + req.getEmail());
		}
		Member newMember = new Member(
				req.getEmail(), req.getPassword(), req.getName(), 
				LocalDateTime.now());
		memberDao.insert(newMember);
		return newMember.getId();
	}
}
