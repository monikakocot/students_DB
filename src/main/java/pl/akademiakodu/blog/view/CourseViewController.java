package pl.akademiakodu.blog.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import pl.akademiakodu.blog.Repositories.CoursesRepository;
import pl.akademiakodu.blog.controllers.CourseController;
import pl.akademiakodu.blog.model.*;


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
    @PostMapping("/delete/course")
    public String deleteSCourse (@RequestParam Long id,
                                 ModelMap modelMap, Courses course){

        courseController.deleteCourseById(id);
        modelMap.addAttribute("courses", courseController.getAllCourses());
        return "courses/courseDetails";
    }

    @PostMapping("/add/course")
    public String addStudent (@ModelAttribute Courses course,
                              @ModelAttribute CourseDescription courseDescription,
                              ModelMap modelMap){

        modelMap.addAttribute("newCourse", courseController.addNewCourseWithDescription(course,courseDescription));

        modelMap.addAttribute("courses", courseController.getAllCourses());
        return "courses/courseDetails";
    }

    @GetMapping("/courses/{idCourse}")
    public String showInstructor (@PathVariable Long idCourse,
                               ModelMap modelMap,Courses courses){

        //DODAC OBSLUGE ZE GDY NIE MA INSTRUKTORA TO WYSWIETLA INFORMACJĘ O KONIECZNOSCI DODANIA GO
        modelMap.addAttribute("instructor",courseController.showInsstructorOfCourse(idCourse));

        modelMap.addAttribute("courses", courseController.getAllCourses());
        return "courses/courseInstructor";
    }

}