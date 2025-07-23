package ra.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.api.model.entity.Subject;

public interface ISubjectRepository extends JpaRepository<Subject,Integer> {
}
