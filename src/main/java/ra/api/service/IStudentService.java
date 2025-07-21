package ra.api.service;

import ra.api.exception.NotFoundException;
import ra.api.model.dto.request.StudentAddDto;
import ra.api.model.dto.request.StudentUpdateDto;
import ra.api.model.dto.response.DataResponse;
import ra.api.model.dto.response.StudentDto;
import ra.api.model.entity.Student;

import java.util.List;

public interface IStudentService {
   DataResponse<List<Student>> findAll();
    List<StudentDto> findAllStudentForManager();
   DataResponse<Student> addStudent(StudentAddDto request);
   DataResponse<Student> updateStudent(Integer id, StudentUpdateDto request) throws NotFoundException;
   void deleteStudent(Integer id) throws NotFoundException;
}
