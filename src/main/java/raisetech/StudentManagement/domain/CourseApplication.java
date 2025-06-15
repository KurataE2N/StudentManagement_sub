package raisetech.StudentManagement.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CourseApplication {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;                    // 申込ID


  private String studentId;            // 受講生ID
  private String courseId;             // コースID

  @Enumerated(EnumType.STRING)
  private ApplicationStatus status;    // 申込ステータス

  // getter / setter

}
