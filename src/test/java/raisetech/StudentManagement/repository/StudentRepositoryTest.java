package raisetech.StudentManagement.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourse;

@MybatisTest
class StudentRepositoryTest {

  @Autowired
  private StudentRepository sut;

  @Test
  void 受講生の全件検索が行えること() {
    List<Student> actual = sut.search();
    assertThat(actual.size()).isEqualTo(5);
  }

  @Test
  void 受講生の検索が行えること() {
    Student actual = sut.searchStudent("1");

    assertThat(actual).isNotNull();
    assertThat(actual.getName()).isEqualTo("山田太郎");
  }

  @Test
  void 受講生情報のコース情報の全件検索が行えること() {
    List<StudentCourse> actual = sut.searchStudentCourseList();
    assertThat(actual.size()).isEqualTo(10);
  }

  @Test
  void 受講生IDに紐づく受講生コース情報の検索が行えること() {
    List<StudentCourse> actual = sut.searchStudentCourse("5");

    assertThat(actual).hasSize(1);
    assertThat(actual.get(0).getCourse()).isEqualTo("AWSコース");
  }

  @Test
  void 受講生の登録が行えること() {
    Student student = new Student();
    student.setName("江波浩二");
    student.setFurigana("エナミコウジ");
    student.setNickname("コウジ");
    student.setEmail("test@example.com");
    student.setRegion("奈良県");
    student.setAge(36);
    student.setGender("男性");
    student.setRemarks("");
    student.setDeleted(false);

    sut.registerStudent(student);

    List<Student> actual = sut.search();

    assertThat(actual.size()).isEqualTo(6);
  }

  @Test
  void 受講生コースの登録が行えること() {
    StudentCourse studentCourse = new StudentCourse();
        studentCourse.setStudentId("6");
        studentCourse.setCourse("Javaコース");
        studentCourse.setClassopen(LocalDateTime.now());
        studentCourse.setClasscomp(LocalDateTime.now().plusYears(1));

        sut.registerStudentCourse(studentCourse);
  }

  @Test
  void 受講生の更新が行えること() {
    Student student = sut.searchStudent("1");
    student.setName("田中一郎");

    sut.updateStudent(student);

    Student updated = sut.searchStudent("1");
    assertThat(updated.getName()).isEqualTo("田中一郎");
  }

  @Test
  void 受講生コースの更新が行えること() {
    List<StudentCourse> courses = sut.searchStudentCourse("1");
    StudentCourse course = courses.get(0);
    course.setCourse("AWSコース");

    sut.updateStudentCourse(course);

    List<StudentCourse> updated = sut.searchStudentCourse("1");
    assertThat(updated.get(0).getCourse()).isEqualTo("AWSコース");
  }
}