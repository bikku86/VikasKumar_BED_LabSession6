package college.fest.debateevent.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import college.fest.debateevent.entity.Student;

public interface StudentService {
    List<Student> getAllStudents();
    Optional<Student> getStudentById(Long studentId);
    Student createStudent(Student student);
    ResponseEntity<Student> updateStudent(Long studentId, Student studentDetails);
    ResponseEntity<Void> deleteStudent(Long studentId);
}
