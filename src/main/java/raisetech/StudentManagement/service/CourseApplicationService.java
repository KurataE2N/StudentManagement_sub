package raisetech.StudentManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raisetech.StudentManagement.domain.ApplicationStatus;
import raisetech.StudentManagement.domain.CourseApplication;
import raisetech.StudentManagement.repository.CourseApplicationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CourseApplicationService {

  private final CourseApplicationRepository repository;

  @Autowired
  public CourseApplicationService(CourseApplicationRepository repository) {
    this.repository = repository;
  }

  // 申込処理（仮申込ステータスで登録）
  public void applyCourse(String studentId, String courseId) {
    // 同じstudentId & courseIdの申込がすでに存在していないかチェック
    Optional<CourseApplication> existing = repository.findByStudentIdAndCourseId(studentId, courseId);
    if (existing.isPresent()) {
      throw new IllegalStateException("すでにこのコースに申し込んでいます。");
    }

    CourseApplication application = new CourseApplication();
    application.setStudentId(studentId);
    application.setCourseId(courseId);
    application.setStatus(ApplicationStatus.TEMPORARY); // 仮申込

    repository.save(application);
  }

  // ステータス更新
  public void updateApplicationStatus(String applicationId, ApplicationStatus status) {
    CourseApplication application = repository.findById(applicationId)
        .orElseThrow(() -> new IllegalArgumentException("申込IDが存在しません: " + applicationId));
    application.setStatus(status);
    repository.save(application);
  }

  // 受講生ごとの申込一覧
  public List<CourseApplication> getApplicationsByStudentId(String studentId) {
    return repository.findAllByStudentId(studentId);
  }
}
