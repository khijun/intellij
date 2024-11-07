package edu.du.sb1031.service;

import edu.du.sb1031.dto.AuthInfo;
import edu.du.sb1031.dto.Define;
import edu.du.sb1031.entity.Member;
import edu.du.sb1031.exception.DeletedMemberException;
import edu.du.sb1031.exception.FrozenMemberException;
import edu.du.sb1031.exception.WrongUsernamePasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

	@Autowired
	private MemberService ms;

	public AuthInfo authenticate(String username, String password) {
		Member member = ms.findByUsername(username);
		if (member == null) {
			throw new WrongUsernamePasswordException();
		}
		if (!member.getPassword().equals(password)) {
			throw new WrongUsernamePasswordException();
		}
		if(member.getRole() == Define.DELETE){
			throw new DeletedMemberException();
		}
		if(member.getRole() == Define.FREEZE){
			throw new FrozenMemberException();
		}
		return new AuthInfo(member.getId());
	}

}
