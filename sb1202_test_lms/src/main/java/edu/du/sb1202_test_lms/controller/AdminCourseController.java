package edu.du.sb1202_test_lms.controller;

import edu.du.sb1202_test_lms.dto.CreateCourseDto;
import edu.du.sb1202_test_lms.service.CourseService;
import edu.du.sb1202_test_lms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/courses")
@RequiredArgsConstructor
public class AdminCourseController {

    private final CourseService courseService;
    private final UserService userService;

    @GetMapping("/new")
    public String showCreateCourseForm(Model model) {
        model.addAttribute("instructors", userService.getAllInstructors());
        model.addAttribute("courseDto", new CreateCourseDto());
        return "create-course";
    }

    @PostMapping
    public String createCourse(@ModelAttribute CreateCourseDto courseDto) {
        courseService.createCourse(courseDto);
        return "redirect:/admin/courses";
    }

    @GetMapping
    public String listCourses(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        return "courses-list";
    }
}

