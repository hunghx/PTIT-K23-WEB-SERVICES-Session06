package ra.api.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ra.api.exception.NotFoundException;
import ra.api.exception.ResourceExistException;
import ra.api.model.dto.request.EnrollmentDto;
import ra.api.model.dto.request.StudentAddDto;
import ra.api.model.dto.request.StudentUpdateDto;
import ra.api.model.dto.response.DataResponse;
import ra.api.model.dto.response.StudentDto;
import ra.api.model.entity.Enrollment;
import ra.api.model.entity.Student;
import ra.api.model.entity.Subject;
import ra.api.repository.IEnrollmentRepository;
import ra.api.repository.IStudentRepository;
import ra.api.repository.ISubjectRepository;
import ra.api.service.IStudentService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentServiceImpl  implements IStudentService {
    private final IStudentRepository studentRepository;
    private final ISubjectRepository subjectRepository;
    private final IEnrollmentRepository enrollmentRepository;
    @Override
    public DataResponse<List<Student>> findAll() {
        DataResponse<List<Student>> dataResponse = DataResponse.<List<Student>>builder()
                .key("students")
                .data(studentRepository.findAll())
                .build();
        return dataResponse;
    }

    @Override
    public List<StudentDto> findAllStudentForManager() {
        return studentRepository.findStudentForManager();
    }

    @Override
    public DataResponse<Student> addStudent(StudentAddDto request) throws ResourceExistException {
        if (studentRepository.existsByEmail(request.getEmail())){
            throw new ResourceExistException("Email " + request.getEmail() + " already exists.");
        }
        Student student = new Student();
        student.setName(request.getName());
        student.setEmail(request.getEmail());
        student.setDob(request.getDob());
        student.setAddress(request.getAddress());
        student.setPhoneNumber(request.getPhoneNumber());
        student.setCreatedAt(LocalDateTime.now());
        return DataResponse.<Student>builder()
                .key("student")
                .data(studentRepository.save(student))
                .build();
    }

    @Override
    public DataResponse<Student> updateStudent(Integer id, StudentUpdateDto request) throws NotFoundException, ResourceExistException {
        if (!studentRepository.existsById(id)){
            log.warn("Student with id {} does not exist.", id);
            throw new NotFoundException("Student with id " + id + " does not exist.");
        }
        String oldEmail = studentRepository.findById(id).get().getEmail();
        if (!request.getEmail().equals(oldEmail) && studentRepository.existsByEmail(request.getEmail())) {
            throw new ResourceExistException("Email " + request.getEmail() + " already exists.");
        }
        Student student = studentRepository.findById(id).get();
        student.setName(request.getName());
        student.setEmail(request.getEmail());
        student.setDob(request.getDob());
        student.setAddress(request.getAddress());
        student.setPhoneNumber(request.getPhoneNumber());
        student.setUpdatedAt(LocalDateTime.now());
        return DataResponse.<Student>builder()
                .key("student")
                .data(studentRepository.save(student))
                .build();
    }

    @Override
    public void deleteStudent(Integer id) throws NotFoundException {
    if (!studentRepository.existsById(id)) {
            throw new NotFoundException("Student with id " + id + " does not exist.");
        }
        studentRepository.deleteById(id);
    }

    @Override
    public Student findById(Integer id) throws NotFoundException {
        return studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Student with id " + id + " not found."));
    }

    @Override
    public DataResponse<Enrollment> registerSubject(EnrollmentDto request) throws NotFoundException {
        Student student = studentRepository.findById(request.getStudentId())

                .orElseThrow(() -> new NotFoundException("Student with id " + request.getStudentId() + " not found."));
        Subject subject = subjectRepository.findById(request.getSubjectId())
                .orElseThrow(() -> new NotFoundException("Subject with id " + request.getSubjectId() + " not found."));
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setSubject(subject);
        return DataResponse.<Enrollment>builder()
                .key("enrollment")
                .data(enrollmentRepository.save(enrollment))
                .build();
    }

    @Override
    public DataResponse<List<Enrollment>> getEnrollmentsByStudentId(Integer id) throws NotFoundException {
        if (!studentRepository.existsById(id)) {
            throw new NotFoundException("Student with id " + id + " does not exist.");
        }
        return DataResponse.<List<Enrollment>>builder()
                .key("enrollments")
                .data(enrollmentRepository.findByStudentId(id))
                .build();
    }
}
