package edu.du.sb1202_test_lms.controller;

import edu.du.sb1202_test_lms.dto.MainPageDto;
import edu.du.sb1202_test_lms.service.MainPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainPageController {

    private final MainPageService mainPageService;

    @GetMapping("/")
    public String index(){
        return "redirect:/main";
    }

    @GetMapping("/main")
    public String showMainPage(Model model) {
        MainPageDto mainPageData = mainPageService.getMainPageData();

        // model에 DTO 객체를 바로 담아서 뷰로 전달
        model.addAttribute("mainPageData", mainPageData);

        return "main-page";  // 메인 페이지 뷰
    }
}


