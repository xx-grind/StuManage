# 使用docker将单服务应用封装成镜像
## 单服务应用后端接口
### 1. 该应用是基于spring boot的单服务应用，项目目录为
![00-1](https://github.com/xys-jill/StuManage/blob/master/src/main/resources/pic/00-1.PNG)
### 2. API接口controller类
```
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
```

## 使用docker封装成镜像
### 1. 编写dockerfile文件
```
# From java image, version : 8
FROM java:8

# 挂载app目录
VOLUME /app

#ADD stuManage-0.0.1-SNAPSHOT.jar /app.jar

#COPY or ADD to image
COPY stuManage-0.0.1-SNAPSHOT.jar app.jar

RUN bash -c "touch /app.jar"
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```
### 2. 编写docker-compose.yml文件
```
version: '3'  #compose 文件版本
services:
  #数据库服务名
  stuManage-database:
    image: mysql:latest #数据库容器所在的镜像
    container_name: database #数据库容器名
    environment:
      MYSQL_ROOT_PASSWORD: 123456 #数据库密码
      MYSQL_DATABASE: student_info #数据库名
    ports:
      - "3306:3306" #端口映射
  #后端接口服务名
  stuManage:
    depends_on:
      - stuManage-database #依赖的数据库
    image: stumanage:latest #容器所在的镜像
    ports:
      - "8080:8080" #端口映射
```
### 3. 更改项目中的application.yaml中数据库的配置信息
![01-1](https://github.com/xys-jill/StuManage/blob/master/src/main/resources/pic/01-1.jpg)
### 4. 利用maven将项目打包成jar包
### 5. 将jar包、dockerfile、docker-compose.yml放在docker文件夹中
![01-2](https://github.com/xys-jill/StuManage/blob/master/src/main/resources/pic/01-2.PNG)



## 运行镜像
### 1. 在项目的docke文件下，命令行输入 
``` docker build . -t stuManage ```
### 2. 利用docker-compose运行容器
``` docker-compose up ```
### 3. 运行后
![03-1](https://github.com/xys-jill/StuManage/blob/master/src/main/resources/pic/03-1.PNG)
![03-2](https://github.com/xys-jill/StuManage/blob/master/src/main/resources/pic/03-2.PNG)


## 访问相应的API
### 1. 查看学生
![04-1](https://github.com/xys-jill/StuManage/blob/master/src/main/resources/pic/04-1.PNG)

### 2. 添加学生
![04-2-1](https://github.com/xys-jill/StuManage/blob/master/src/main/resources/pic/04-2-1.PNG)

![04-2-2](https://github.com/xys-jill/StuManage/blob/master/src/main/resources/pic/04-2-2.PNG)

### 3. 修改学生信息
![04-3-1](https://github.com/xys-jill/StuManage/blob/master/src/main/resources/pic/04-3-1.PNG)

![04-3-2](https://github.com/xys-jill/StuManage/blob/master/src/main/resources/pic/04-3-2.PNG)

![04-3-3](https://github.com/xys-jill/StuManage/blob/master/src/main/resources/pic/04-3-3.PNG)

### 4. 删除学生
![04-4-1](https://github.com/xys-jill/StuManage/blob/master/src/main/resources/pic/04-4-1.PNG)
![04-4-2](https://github.com/xys-jill/StuManage/blob/master/src/main/resources/pic/04-4-2.PNG)

### 5. 在命令行查看docker中的数据库
![04-5](https://github.com/xys-jill/StuManage/blob/master/src/main/resources/pic/04-5.PNG)


