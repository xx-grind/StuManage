package com.rs.stuManage.controller;

import com.rs.stuManage.entity.Student;
import com.rs.stuManage.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api/v1")
public class StudentController {
    @Autowired
    private StudentService studentService;
    /**
     * 添加学生
     * @param student
     * @return
     */
    @PostMapping("/student")
    public String addStu(@RequestBody Student student){
        if(studentService.addStu(student)==1){
            return student.toString()+"添加成功！";
        }
        else{
            return "ID错误,添加失败！";
        }
    }
    /**
     * 查看学生
     * @param studentId
     * @return
     */
    @GetMapping("/student/getone")
    public Student getStu(@RequestParam("studentId") String studentId){

        return studentService.getStu(studentId);
    }

    /**
     * 查看所有学生
     * @return
     */
    @GetMapping("/student")
    public List<Student> getAll(){
        return studentService.getAll();
    }

    /**
     * 修改学生信息
     * @param student
     * @return
     */
    @PutMapping("/student")
    public Student changeStu(@RequestBody Student student){
        studentService.changeStu(student);
        return student;
    }
    /**
     * 删除学生
     * @param studentId
     * @return
     */
    @DeleteMapping("/student")
    public String deleteStu(@RequestParam("studentId")String studentId){
        if(studentService.deleteStu(studentId)!=0){
            return "学号为"+studentId+"的学生信息删除成功！";
        }
        else{
            return "ID错误，删除失败！";
        }
    }
}
