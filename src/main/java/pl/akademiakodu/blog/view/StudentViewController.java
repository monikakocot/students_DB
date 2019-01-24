package pl.akademiakodu.blog.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import pl.akademiakodu.blog.Repositories.StudentRepository;
import pl.akademiakodu.blog.controllers.CourseController;
import pl.akademiakodu.blog.controllers.StudentController;
import pl.akademiakodu.blog.model.Courses;
import pl.akademiakodu.blog.model.LoginForm;
import pl.akademiakodu.blog.model.Student;
import pl.akademiakodu.blog.model.StudentDetails;

import java.util.Optional;


@Controller
public class StudentViewController {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    StudentController studentController;
    @Autowired
    CourseController courseController;


    @PostMapping ("/login")
    public String login (@ModelAttribute LoginForm loginForm, ModelMap modelMap, Student student) {

        if (loginForm.getLogin().equals("admin") && loginForm.getPassword().equals("admin")){
            modelMap.addAttribute("students", studentController.getAllStudents());
            return "students/studentDetails";
        }
        return "students/students";
    }

    @PostMapping("/update/student/phonenumber")
    public String updatedPhoneNumber(@RequestParam Long id,
                                     @RequestParam String phoneNumber,
                                     ModelMap modelMap, Student student){

        Optional<Student> studentOptional = studentRepository.findById(id);
        studentOptional.ifPresent(result ->{
            //result = studentOptional.get(); // Version L
            //result.getStudentDetails().setPhoneNumber(phoneNumber); // Version L
            result.getStudentDetails().setPhoneNumber(phoneNumber);
            studentRepository.save(result);
        });
        modelMap.addAttribute("students", studentController.getAllStudents());
        return "students/studentDetails";
    }

    @PostMapping("/delete/student")
    public String deleteStudent (@RequestParam Long id,
                                 ModelMap modelMap, Student student){

        studentController.deleteStudent(id);
        modelMap.addAttribute("students", studentController.getAllStudents());
        return "students/studentDetails";
    }

    @PostMapping("/add/student")
    public String addStudent (@ModelAttribute Student student,
                              @ModelAttribute StudentDetails studentDetails,
                                 ModelMap modelMap){

        modelMap.addAttribute("newStudent", studentController.addNewStudent(student,studentDetails));

        modelMap.addAttribute("students", studentController.getAllStudents());
        return "students/studentDetails";
    }

    @GetMapping("/students/{idStudent}")
    public String showCourses (@PathVariable Long idStudent,
                               ModelMap modelMap,Student student){

         modelMap.addAttribute("coursesList",studentController.showCoursesOfStudent(idStudent));
         modelMap.addAttribute("courses", courseController.getAllCourses());
         modelMap.addAttribute("students", studentController.getAllStudents());
         return "students/studentCourses";
    }

    // interesting fact that with @PathVariable instead of @RequestParam does not work

    @PostMapping("/student/addCourse")
    public String addCourseToStudent(@RequestParam Long idStudent,
                                     @RequestParam Long idCourse,
                                     Courses course,
                                     ModelMap modelMap){

        studentController.addCourseToStudent(idStudent,idCourse,course);
        modelMap.addAttribute("coursesList",studentController.showCoursesOfStudent(idStudent));
        modelMap.addAttribute("message","You succeed!");

        return "students/studentCourses";
    }

    @PostMapping("/student/addCourseByName")
    public String addCourseToStudentByname(@RequestParam String name,
                                     @RequestParam String title,
                                     Courses course,
                                     ModelMap modelMap){

        studentController.addCourseToStudentByName(name,title,course);
        modelMap.addAttribute("coursesList",studentController.showCoursesOfStudentByName(name));
        modelMap.addAttribute("courses", courseController.getAllCourses());
        modelMap.addAttribute("students", studentController.getAllStudents());
        modelMap.addAttribute("info","You succeed!");

        return "students/studentCourses";
    }

}

