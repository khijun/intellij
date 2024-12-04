package edu.du.sb1031.service;

import edu.du.sb1031.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final MemberService memberService;

	public Member getCurrentMember() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication == null||!authentication.isAuthenticated()) return null;
		return memberService.findByUsername(((UserDetails)authentication.getPrincipal()).getUsername());
	}

}
