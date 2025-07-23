package ra.api.service;

import ra.api.exception.NotFoundException;
import ra.api.exception.ResourceExistException;
import ra.api.model.dto.request.EnrollmentDto;
import ra.api.model.dto.request.StudentAddDto;
import ra.api.model.dto.request.StudentUpdateDto;
import ra.api.model.dto.response.DataResponse;
import ra.api.model.dto.response.StudentDto;
import ra.api.model.entity.Enrollment;
import ra.api.model.entity.Student;

import java.util.List;

public interface IStudentService {
   DataResponse<List<Student>> findAll();
   Student findById(Integer id) throws NotFoundException;
   List<StudentDto> findAllStudentForManager();
   DataResponse<Student> addStudent(StudentAddDto request)throws ResourceExistException;
   DataResponse<Student> updateStudent(Integer id, StudentUpdateDto request) throws NotFoundException, ResourceExistException;
   void deleteStudent(Integer id) throws NotFoundException;
   DataResponse<Enrollment> registerSubject(EnrollmentDto request) throws NotFoundException;
   DataResponse<List<Enrollment>> getEnrollmentsByStudentId(Integer id) throws NotFoundException;
}
