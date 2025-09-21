package raisetech.StudentManagement.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import raisetech.StudentManagement.domain.ApplicationStatus;
import raisetech.StudentManagement.domain.CourseApplication;
import raisetech.StudentManagement.service.CourseApplicationService;

@RestController
public class CourseApplicationController {

  private final CourseApplicationService applicationService;

  public CourseApplicationController(CourseApplicationService applicationService) {
    this.applicationService = applicationService;
  }

  // 申込処理（studentId と courseId を受け取って Service に渡す）
  @PostMapping("/course/apply")
  public void applyToCourse(@RequestParam String studentId, @RequestParam String courseId) {
    applicationService.applyCourse(studentId, courseId);
  }

  // ステータス更新
  @PutMapping("/course/{id}/status")
  public void updateStatus(@PathVariable String id, @RequestParam ApplicationStatus status) {
    applicationService.updateApplicationStatus(id, status);
  }

  // 受講生ごとの申込一覧
  @GetMapping("/course/student/{studentId}")
  public List<CourseApplication> getApplicationsByStudent(@PathVariable String studentId) {
    return applicationService.getApplicationsByStudentId(studentId);
  }
}
