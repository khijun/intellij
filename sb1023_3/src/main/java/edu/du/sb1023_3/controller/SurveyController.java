package edu.du.sb1023_3.controller;

import java.util.Arrays;
import java.util.List;

import edu.du.sb1023_3.entity.AnsweredData;
import edu.du.sb1023_3.entity.Question;
import edu.du.sb1023_3.entity.Response;
import edu.du.sb1023_3.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/survey")
public class SurveyController {

    @Autowired
    SurveyService ss;

    @GetMapping
    public String form(Model model) {
        List<Question> questions = createQuestions();
        model.addAttribute("questions", questions);
        return "/survey/surveyForm";
    }

    private List<Question> createQuestions() {
        Question q1 = new Question("당신의 역할은 무엇입니까?",
                Arrays.asList("서버", "프론트", "풀스택"));
        Question q2 = new Question("많이 사용하는 개발도구는 무엇입니까?",
                Arrays.asList("이클립스", "인텔리J", "서브라임"));
        Question q3 = new Question("하고 싶은 말을 적어주세요.");
        return Arrays.asList(q1, q2, q3);
    }

    @PostMapping
    public String submit(HttpServletRequest request, @ModelAttribute("ansData") AnsweredData ansData) {
        ss.save(ansData);
        return "/survey/submitted";
    }

}
