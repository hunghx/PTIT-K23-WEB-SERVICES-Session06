package ra.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ra.api.model.dto.response.StudentDto;
import ra.api.model.entity.Student;

import java.util.List;

@Repository
public interface IStudentRepository extends JpaRepository<Student,Integer> {
    @Query("select new ra.api.model.dto.response.StudentDto(s.id, s.name, s.email, s.dob, s.address, s.phoneNumber) " +
           "from Student s")
    List<StudentDto> findStudentForManager();
}
