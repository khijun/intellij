package com.example.demo.survey;

import java.util.Arrays;
import java.util.List;

import com.example.demo.member.entity.Member;
import com.example.demo.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/survey")
public class SurveyController {

	@Autowired
	SurveyService surveyService;
    @Autowired
    private MemberService memberService;

	@GetMapping("/surveyForm")
	public String form(Model model) {
		List<Question> questions = createQuestions();
		for (Question question : questions) {
			System.out.println(question);
		}
		model.addAttribute("questions", questions);
		return "survey/surveyForm";
	}

	private List<Question> createQuestions() {
		Question q1 = new Question("당신의 역할은 무엇입니까?",
				Arrays.asList("서버", "프론트", "풀스택"));
		Question q2 = new Question("많이 사용하는 개발도구는 무엇입니까?",
				Arrays.asList("이클립스", "인텔리J", "서브라임"));
		Question q3 = new Question("하고 싶은 말을 적어주세요.");
		return Arrays.asList(q1, q2, q3);
	}

	@GetMapping("/survey")
	public String survey() {
		return surveyService.isExist()?
				"redirect:/survey/surveyView"
				:"redirect:/survey/surveyForm";
	}

	@GetMapping("/surveyView")
	public String surveyDetail(){
		//모델 전다,ㄹ해줘야함ㄴ
		return "survey/surveyDetail";
	}

	@PostMapping("/surveySubmit")
	public String submit(@ModelAttribute("ansData") AnsweredData data) {


//		Member member = memberService.getMember();
//		System.out.println(member);
//		data.setMember(member);
		System.out.println(data);
		surveyService.save(data);
		System.out.println(data);
		return "redirect:/survey/surveyView";
	}

}
