package pl.akademiakodu.blog.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.akademiakodu.blog.Repositories.CoursesRepository;
import pl.akademiakodu.blog.Repositories.InstructorRepository;
import pl.akademiakodu.blog.model.CourseDescription;
import pl.akademiakodu.blog.model.Courses;
import pl.akademiakodu.blog.model.Instructor;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CourseController {

    @Autowired
    CoursesRepository coursesRepository;

    @GetMapping("/courses/all")
    public List<Courses> getAllCourses (){
        return coursesRepository.findAll();
    }

    @PostMapping("/courses/add")
    public Courses addNewCourse (@ModelAttribute Courses courses){
        return coursesRepository.save(courses);
    }

    //WERSJA LUKASZA
    @PostMapping("courses/add/desc")
    public Courses addNewCourseWithDescription(@ModelAttribute Courses course, @ModelAttribute CourseDescription courseDescription) {
        Courses result = new Courses(
                course.getTitle(),
                course.getLevel(),
                courseDescription
        );
        return coursesRepository.save(result);
    }
//WERSJA LUKASZA
// HERE WAS WORKING ONLY CHANGING OF ONE FIELD, ANOTHEERS ARE GIVING NULL
//WHEN YOU CHANGE ONE FIELD E.G. phoneNumber in another fields we have null
    @PutMapping("/courses/update/{id}")
    public String updateCourse(@ModelAttribute Courses courses,@PathVariable Long id){
        Optional<Courses> resultOptional= coursesRepository.findById(id);
        resultOptional.ifPresent(result-> {
            result.setTitle(courses.getTitle());
            result.setLevel(courses.getLevel());
            coursesRepository.save(result);
        });

        return "course id:" + id + "updated!";
    }
//WERSJA LUKASZA

    @DeleteMapping("/courses/delete/{id}")
    public String deleteCourseById(@PathVariable("id") Long id){
        coursesRepository.deleteById(id);
        return "deleted";
    }

    @Autowired
    InstructorRepository instructorRepository;

    // //OD KOMENTOWANE W WERSJI LUKASZA
    //KOD1
    @GetMapping("/courses/{idCourse}/instructors/{idInstructor}")
    public String addInstructorToCourse(@PathVariable Long idInstructor,
                                        @PathVariable Long idCourse
                                        // @ModelAttribute Courses courses,
                                        //@ModelAttribute Instructor instructor
    )
    {
        Optional<Instructor> instructorOptional = instructorRepository.findById(idInstructor);
        Optional<Courses> coursesOptional = coursesRepository.findById(idCourse);

        coursesOptional.ifPresent(result -> {

            Instructor instructor=(instructorOptional.get());
            instructorRepository.save(instructor);
            result.setInstructor(instructor);
            coursesRepository.save(result);
        });

        return "Instructor added to course!";

    }
//KOD1

/*// check if it is working // ZAKOMENTOWANE W WERSJI LUKASZA
    @GetMapping("/courses/{idCourses}/instructors/{idInstructor}")
    public String addCourseToInstructor(@PathVariable Long idInstructor,
                                        @PathVariable Long idCourse
                                        // @ModelAttribute Courses courses,
                                        //@ModelAttribute Instructor instructor
    )
    {

        Optional<Instructor> instructorOptional = instructorRepository.findById(idInstructor);
        Optional<Courses> coursesOptional = coursesRepository.findById(idCourse);

        coursesOptional.ifPresent(result -> {

            List<Instructor> instructorList = coursesOptional.get().getInstructor();
            instructorList.add(instructorOptional.get());
            result.setInstructor(instructorList);
            coursesRepository.save(result);
        });

        return "Instructor has new course!";
    }
*/


    /* // WERSJA LUAKSZA
    @PostMapping("courses/add")
    public Courses addNewCourse(@ModelAttribute Courses course) {
        Courses result = new Courses();
        result.setTitle(course.getTitle());
        result.setLevel(course.getLevel());
        return coursesRepository.save(result);
    }
    */



}
