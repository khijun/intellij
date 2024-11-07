package edu.du.sb1030.service;

import edu.du.sb1030.dao.MemberDao;
import edu.du.sb1030.dto.AuthInfo;
import edu.du.sb1030.dto.Member;
import edu.du.sb1030.exception.WrongIdPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
	@Autowired
	private MemberDao memberDao;
	
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	public AuthInfo authenicate(String email, String password) {
		Member member = memberDao.selectByEmail(email);
		if(member == null) {
			throw new WrongIdPasswordException();
		}
		if(!member.matchPassword(password))
			throw new WrongIdPasswordException();
		return new AuthInfo(member.getId(), member.getEmail(), member.getPassword());
	}
}
