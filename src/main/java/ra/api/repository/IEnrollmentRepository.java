package ra.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.api.model.entity.Enrollment;

import java.util.List;

public interface IEnrollmentRepository extends JpaRepository<Enrollment, Integer> {
    List<Enrollment> findByStudentId(Integer studentId);
    List<Enrollment> findBySubjectId(Integer subjectId);
}
