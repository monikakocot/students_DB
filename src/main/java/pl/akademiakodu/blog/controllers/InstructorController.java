package pl.akademiakodu.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.akademiakodu.blog.Repositories.CoursesRepository;
import pl.akademiakodu.blog.Repositories.InstructorRepository;
import pl.akademiakodu.blog.model.Courses;
import pl.akademiakodu.blog.model.Instructor;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class InstructorController {


    @Autowired
    InstructorRepository instructorRepository;
    @Autowired
    CoursesRepository coursesRepository;

    @GetMapping("/instructor/all")
    public List<Instructor> getAllInstructor(){
        return instructorRepository.findAll();
    }

/*
    //With @OnetToMany and @ManyToOne does not work -
    //Method work in CourseController

    @GetMapping("/instructors/{idInstructor}/course/{idCourse}")
    public String addCourseToInstructor(@PathVariable Long idInstructor,
                                        @PathVariable Long idCourse
                                       // @ModelAttribute Courses courses,
                                        //@ModelAttribute Instructor instructor
    ){

        Optional<Instructor> instructorOptional = instructorRepository.findById(idInstructor);
        Optional<Courses> coursesOptional = coursesRepository.findById(idCourse);

        instructorOptional.ifPresent(result -> {

            List<Courses> coursesList = instructorOptional.get().getCourses();
            coursesList.add(coursesOptional.get());
            result.setCourses(coursesList);
            instructorRepository.save(result);

        });

        return "Instructor has new course!";

    }
*/
    /** you can use this code with @ManyToMany
    @GetMapping("/instructors/{idInstructor}/course/{idCourse}")
    public String addCourseToInstructor(
            @PathVariable("idInstructor") Long idInstructor,
            @PathVariable("idCourse") Long idCourse) {

        Optional<Instructor> instructorOptional = instructorRepository.findById(idInstructor);
        Optional<Courses> coursesOptional = coursesRepository.findById(idCourse);

        System.out.println(coursesOptional.get()); //dziala
        System.out.println(instructorOptional.get());

        instructorOptional.ifPresent(result -> {
            List<Courses> coursesList = instructorOptional.get().getCourses();

            coursesList.add(coursesOptional.get());

            System.out.println(coursesList.isEmpty());

            result.setCourses(coursesList);
            System.out.println(result);

            instructorRepository.save(result);
        });
        return "Instructor has new course!";
    }
*/
}
