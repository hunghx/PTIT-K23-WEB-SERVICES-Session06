package ra.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.api.exception.NotFoundException;
import ra.api.exception.ResourceExistException;
import ra.api.model.dto.request.EnrollmentDto;
import ra.api.model.dto.request.StudentAddDto;
import ra.api.model.dto.request.StudentUpdateDto;
import ra.api.model.dto.response.DataResponse;
import ra.api.model.entity.Enrollment;
import ra.api.model.entity.Student;
import ra.api.service.IStudentService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {
    private final IStudentService studentService;

    // laays danh sachs
    @GetMapping
    public ResponseEntity<DataResponse<List<Student>>> getAllStudents() {
        return new ResponseEntity<>(studentService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Integer id) throws NotFoundException {
        return new ResponseEntity<>(studentService.findById(id), HttpStatus.OK);
    }

    // Theem mowis
    @PostMapping
    public ResponseEntity<DataResponse<Student>> addStudent(@Valid @RequestBody StudentAddDto request)throws ResourceExistException {
        return new ResponseEntity<>(studentService.addStudent(request),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<Student>> updateStudent(@PathVariable Integer id, @RequestBody StudentUpdateDto request) throws ResourceExistException, NotFoundException {
        return new ResponseEntity<>(studentService.updateStudent(id, request), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Integer id) throws NotFoundException {
        studentService.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // đăng kí khoa học

    // API :  POST - /api/v1/students/{studentId}/subjects/{subjectId}
    @PostMapping("/enrollments")
    public ResponseEntity<DataResponse<Enrollment>> registerSubject(@RequestBody EnrollmentDto enrollmentDto) throws NotFoundException {
        return new ResponseEntity<>(studentService.registerSubject(enrollmentDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}/enrollments")
    public ResponseEntity<DataResponse<List<Enrollment>>> getEnrollmentsByStudentId(@PathVariable Integer id) throws NotFoundException {
        return new ResponseEntity<>(studentService.getEnrollmentsByStudentId(id), HttpStatus.OK);
    }
}
