package pl.akademiakodu.blog.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.akademiakodu.blog.Repositories.CoursesRepository;
import pl.akademiakodu.blog.controllers.CourseController;
import pl.akademiakodu.blog.model.Courses;

import pl.akademiakodu.blog.model.LoginForm;
import pl.akademiakodu.blog.model.LoginFormCourses;



@Controller
public class CourseViewController {

    @Autowired
    CourseController courseController;
    @Autowired
    CoursesRepository coursesRepository;

    @PostMapping ("/loginCourses")
    public String login (@ModelAttribute LoginForm loginForm, ModelMap modelMap, Courses courses) {

        if (loginForm.getLogin().equals("admin") && loginForm.getPassword().equals("admin")){
            modelMap.addAttribute("courses", courseController.getAllCourses());
            return "courses/courseDetails";
        }
        return "courses/courses";
    }

    @GetMapping ("/courses/details")
    public String courseDetails (ModelMap modelMap,Courses courses) {

        modelMap.addAttribute("courses", courseController.getAllCourses());

        return "courses/courseDetails";

    }
}