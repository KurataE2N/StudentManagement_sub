package raisetech.StudentManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import raisetech.StudentManagement.domain.CourseApplication;

import java.util.List;
import java.util.Optional;

public interface CourseApplicationRepository extends JpaRepository<CourseApplication, String> {
  List<CourseApplication> findAllByStudentId(String studentId);
  Optional<CourseApplication> findByStudentIdAndCourseId(String studentId, String courseId);
}
