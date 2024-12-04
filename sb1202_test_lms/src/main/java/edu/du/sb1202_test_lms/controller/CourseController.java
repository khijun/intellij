package edu.du.sb1202_test_lms.controller;

import edu.du.sb1202_test_lms.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public String listCourses(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        return "courses";
    }

    @GetMapping("/enroll")
    public String enrollCourse(@RequestParam Long courseId, @AuthenticationPrincipal UserDetails user) {
        courseService.enrollCourse(courseId, user.getUsername());
        return "redirect:/my-courses";
    }

    @GetMapping("/my-courses")
    public String listMyCourses(Model model, @AuthenticationPrincipal UserDetails user) {
        model.addAttribute("myCourses", courseService.getCoursesByUser(user.getUsername()));
        return "my-courses";
    }
}

