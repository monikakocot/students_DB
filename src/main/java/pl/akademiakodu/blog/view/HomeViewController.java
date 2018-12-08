package pl.akademiakodu.blog.view;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import pl.akademiakodu.blog.Repositories.StudentRepository;
import pl.akademiakodu.blog.controllers.CourseController;
import pl.akademiakodu.blog.controllers.InstructorController;
import pl.akademiakodu.blog.controllers.StudentController;
import pl.akademiakodu.blog.model.Courses;
import pl.akademiakodu.blog.model.Instructor;
import pl.akademiakodu.blog.model.LoginForm;
import pl.akademiakodu.blog.model.Student;

@Controller
public class HomeViewController {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    StudentController studentController;
    @Autowired
    CourseController courseController;
    @Autowired
    InstructorController instructorController;

/*
Typically, if we want to implement the URL handler using traditional @RequestMapping annotation, it would have been something like this:
@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
The new approach makes it possible to shorten this simply to:
@GetMapping("/get/{id}")
 */
    @GetMapping("/")
    public String homePage(){
        return "home/home";
    }

    @GetMapping("/students/all")
    public String allStudents(ModelMap modelMap, Student student){
        modelMap.addAttribute("students", studentController.getAllStudents());
        modelMap.addAttribute("loginForm", new LoginForm());
        return "students/students";
    }
    @GetMapping("/courses/all")
    public String allCourses(ModelMap modelMap, Courses courses){
        modelMap.addAttribute("courses", courseController.getAllCourses());
        return "courses";
    }
    @GetMapping("/instructors/all")
    public String allInstructors (ModelMap modelMap, Instructor instructor){
        modelMap.addAttribute("instructors", instructorController.getAllInstructor());
        return "instructors";
    }

}
