package ra.api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ra.api.exception.NotFoundException;
import ra.api.model.dto.request.StudentAddDto;
import ra.api.model.dto.request.StudentUpdateDto;
import ra.api.model.dto.response.DataResponse;
import ra.api.model.dto.response.StudentDto;
import ra.api.model.entity.Student;
import ra.api.repository.IStudentRepository;
import ra.api.service.IStudentService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl  implements IStudentService {
    private final IStudentRepository studentRepository;
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
    public DataResponse<Student> addStudent(StudentAddDto request) {
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
    public DataResponse<Student> updateStudent(Integer id, StudentUpdateDto request) throws NotFoundException {
        if (!studentRepository.existsById(id)){
            throw new NotFoundException("Student with id " + id + " does not exist.");
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
}
