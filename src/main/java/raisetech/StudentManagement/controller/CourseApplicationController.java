package raisetech.StudentManagement.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import raisetech.StudentManagement.domain.ApplicationStatus;
import raisetech.StudentManagement.domain.CourseApplication;
import raisetech.StudentManagement.service.StudentService.CourseApplicationService;

@RestController
public class CourseApplicationController {

  private final CourseApplicationService applicationService;

  public CourseApplicationController(CourseApplicationService applicationService) {
    this.applicationService = applicationService;
  }

  @PostMapping("/course/apply")
  public CourseApplication applyToCourse(@RequestBody CourseApplication application) {
    return applicationService.applyToCourse(application);
  }

  @PutMapping("/course/{id}/status")
  public void updateStatus(@PathVariable String id, @RequestParam ApplicationStatus status) {
    applicationService.updateApplicationStatus(id, status);
  }

  @GetMapping("/course/student/{studentId}")
  public List<CourseApplication> getApplicationsByStudent(@PathVariable String studentId) {
    return applicationService.getApplicationsByStudentId(studentId);
  }
}
