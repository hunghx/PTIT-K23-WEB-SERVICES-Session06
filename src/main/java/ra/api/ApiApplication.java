package ra.api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ra.api.model.entity.Subject;
import ra.api.repository.ISubjectRepository;

@SpringBootApplication
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(ISubjectRepository subjectRepository) {
        return args -> {
//            Subject subject1 = new Subject(null, "Mathematics", "MATH101", null);
//            Subject subject2 = new Subject(null, "Physics", "PHYS101", null);
//            Subject subject3 = new Subject(null, "Chemistry", "CHEM101", null);
//            subjectRepository.save(subject1);
//            subjectRepository.save(subject2);
//            subjectRepository.save(subject3);
        };
    }
}
