package com.rs.stuManage;

import com.rs.stuManage.entity.Student;
import com.rs.stuManage.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StuManageApplicationTests {

	@Autowired
	private StudentService studentService;
	@Test
	void addStuTest() {
		Student student=new Student();
		student.setStudentId("20212010091");
		student.setName("Xingyu");
		student.setMajor("se");
		student.setDepartment("software");
		if(studentService.addStu(student)==1){
			System.out.println("添加学生成功！");
		}
		else{
			System.out.println("学号重复！");
		}
	}

}
