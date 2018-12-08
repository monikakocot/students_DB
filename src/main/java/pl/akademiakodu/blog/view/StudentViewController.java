package pl.akademiakodu.blog.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import pl.akademiakodu.blog.Repositories.StudentRepository;
import pl.akademiakodu.blog.controllers.StudentController;
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
            //result = studentOptional.get(); // ZAKOMENTOWANE W WERSJI LUKASZA
            //result.getStudentDetails().setPhoneNumber(phoneNumber); // ZAKOMENTOWANE W WERSJI LUKASZA
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



}

