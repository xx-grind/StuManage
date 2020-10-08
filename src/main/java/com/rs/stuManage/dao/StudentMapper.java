package com.rs.stuManage.dao;

import com.rs.stuManage.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentMapper {
    int insertStuSelective(Student student);

    List<Student> selectAll();
    Student selectStuByPrimaryKey(String studentId);

    int updateByPrimaryKeySelective(Student student);

    int deleteByPrimaryKey(String studentId);



}
