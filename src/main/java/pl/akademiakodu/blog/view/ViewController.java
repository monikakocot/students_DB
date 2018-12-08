package pl.akademiakodu.blog.view;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.akademiakodu.blog.Repositories.StudentRepository;
import pl.akademiakodu.blog.model.Student;

import java.util.Optional;

@Controller
public class ViewController {

    @Autowired
    StudentRepository studentRepository;

/*
Typically, if we want to implement the URL handler using traditional @RequestMapping annotation, it would have been something like this:
@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
The new approach makes it possible to shorten this simply to:
@GetMapping("/get/{id}")
 */
    //WERSJA LUKASZA
    @RequestMapping("/home")
    public String homePage(){
        return "home";
    }

    @RequestMapping("/update/student/phonenumber")
    public String updatePhoneNumber(){
        return "updatedStudent";
    }


    @PostMapping("/students/from/update/phone")
    public String updateStudentByForm(@RequestParam Long id,
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
}
