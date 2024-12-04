package edu.du.sb1202_test_lms.service;

import edu.du.sb1202_test_lms.entity.Assignment;
import edu.du.sb1202_test_lms.entity.Submission;
import edu.du.sb1202_test_lms.repository.AssignmentRepository;
import edu.du.sb1202_test_lms.repository.SubmissionRepository;
import edu.du.sb1202_test_lms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final SubmissionRepository submissionRepository;
    private final UserRepository userRepository;

    public Assignment getAssignmentById(Long assignmentId) {
        return assignmentRepository.findById(assignmentId).orElseThrow();
    }

    public void submitAssignment(Long assignmentId, String username, String content, MultipartFile file) {
        Submission submission = new Submission();
        submission.setAssignment(assignmentRepository.findById(assignmentId).orElseThrow());
        submission.setUser(userRepository.findByEmail(username).orElseThrow());
        submission.setContent(content);
        // 파일 처리 로직 추가
        submission.setScore(null); // 아직 채점되지 않음
        submissionRepository.save(submission);
    }

}

