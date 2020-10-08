package com.rs.stuManage.service.serviceImpl;

import com.rs.stuManage.dao.StudentMapper;
import com.rs.stuManage.entity.Student;
import com.rs.stuManage.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public int addStu(Student student) {
        if(studentMapper.selectStuByPrimaryKey(student.getStudentId())==null){
            studentMapper.insertStuSelective(student);
            return 1;
        }
        else{
            return 0;
        }
    }

    @Override
    public List<Student> getAll() {

        return studentMapper.selectAll();
    }

    @Override
    public Student getStu(String studentId) {
        return studentMapper.selectStuByPrimaryKey(studentId);
    }

    @Override
    public int changeStu(Student student) {
        if(studentMapper.selectStuByPrimaryKey(student.getStudentId())!=null){
            studentMapper.updateByPrimaryKeySelective(student);
            return 1;
        }
        else{
            return this.addStu(student);
        }
    }

    @Override
    public int deleteStu(String studentId) {
        return studentMapper.deleteByPrimaryKey(studentId);
    }
}
