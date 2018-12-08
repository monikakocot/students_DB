package pl.akademiakodu.blog.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import pl.akademiakodu.blog.Repositories.StudentRepository;
import pl.akademiakodu.blog.controllers.StudentController;
import pl.akademiakodu.blog.model.LoginForm;
import pl.akademiakodu.blog.model.Student;

import java.util.Optional;


@Controller
public class StudentViewController {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    StudentController studentController;


    @PostMapping ("/login")
    public String login (@ModelAttribute LoginForm loginForm) {

        if (loginForm.getLogin().equals("admin") && loginForm.getPassword().equals("admin")){
            return "students/studentDetails";
        }
        return "students/students";
    }


// DOPRACOWAC POŹNIEJ
    @GetMapping("/update/student/phonenumber")
    public String updatePhoneNumber(){
        return "students/updatedStudent";
    }

    @ResponseBody
    @PostMapping("/update/student/phonenumber")
    public String updatedPhoneNumber(@RequestParam Long id,
                                     @RequestParam String phoneNumber){

        Optional<Student> studentOptional = studentRepository.findById(id);
        studentOptional.ifPresent(result ->{
            //result = studentOptional.get(); // ZAKOMENTOWANE W WERSJI LUKASZA
            //result.getStudentDetails().setPhoneNumber(phoneNumber); // ZAKOMENTOWANE W WERSJI LUKASZA
            result.getStudentDetails().setPhoneNumber(phoneNumber);
            studentRepository.save(result);
        });
        return "updatedStudent";

    }
// DOPRACOWAC POŹNIEJ
}
