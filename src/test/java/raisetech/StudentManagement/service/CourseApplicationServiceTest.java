package raisetech.StudentManagement.service;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import raisetech.StudentManagement.domain.CourseApplication;
import raisetech.StudentManagement.domain.ApplicationStatus;
import raisetech.StudentManagement.repository.CourseApplicationRepository;

/**
 * CourseApplicationService のユニットテスト。
 * リポジトリをモック化して、サービスロジックの検証。
 */
public class CourseApplicationServiceTest {

  @Mock
  private CourseApplicationRepository repository;

  @InjectMocks
  private CourseApplicationService service;

  public CourseApplicationServiceTest() {
    MockitoAnnotations.openMocks(this); // モック初期化
  }

  /**
   * [applyCourseのテスト]
   * 有効な studentId と courseId を渡したときに、CourseApplication が生成され、リポジトリに保存されること。
   */

  @Test
  public void testApplyCourse() {
    String studentId = "student001";
    String courseId = "course001";

    service.applyCourse(studentId, courseId);

    // repository.save() が1回呼ばれていることを検証
    verify(repository, times(1)).save(any(CourseApplication.class));
  }

  /**
   * 【updateApplicationStatusの正常系テスト】
   * 指定した applicationId に対応する CourseApplication が存在し、そのステータスが更新されて保存されること。
   */
  @Test
  public void testUpdateApplicationStatus() {
    String applicationId = "app001";
    CourseApplication app = new CourseApplication();
    app.setId(applicationId);

    when(repository.findById(applicationId)).thenReturn(java.util.Optional.of(app));

    service.updateApplicationStatus(applicationId, ApplicationStatus.CONFIRMED);

    verify(repository, times(1)).save(app);
  }

  /**
   * 【updateApplicationStatusの異常系テスト】
   * 指定した applicationId が存在しない場合に、例外がスローされること。
   */
  @Test
  public void testUpdateApplicationStatus_ThrowsExceptionWhenNotFound() {
    String applicationId = "notExistId";

    // モックで findById() が空を返すよう設定
    when(repository.findById(applicationId)).thenReturn(java.util.Optional.empty());

    // 例外がスローされることを検証
    try {
      service.updateApplicationStatus(applicationId, ApplicationStatus.CONFIRMED);
      assert false : "例外が発生しませんでした";
    } catch (IllegalArgumentException e) {
      assert e.getMessage().contains(applicationId);
    }
  }
}