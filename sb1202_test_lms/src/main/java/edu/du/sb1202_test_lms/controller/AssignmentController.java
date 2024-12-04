package edu.du.sb1202_test_lms.controller;

import edu.du.sb1202_test_lms.service.AssignmentService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/assignments")
public class AssignmentController {

    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @GetMapping("/{assignmentId}/submit")
    public String showSubmitForm(@PathVariable Long assignmentId, Model model) {
        model.addAttribute("assignment", assignmentService.getAssignmentById(assignmentId));
        return "submit-assignment";
    }

    @PostMapping("/{assignmentId}/submit")
    public String submitAssignment(@PathVariable Long assignmentId,
                                   @RequestParam String content,
                                   @RequestParam("file") MultipartFile file,
                                   @AuthenticationPrincipal UserDetails user) {
        assignmentService.submitAssignment(assignmentId, user.getUsername(), content, file);
        return "redirect:/my-courses";
    }
}

