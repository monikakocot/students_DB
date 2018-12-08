package pl.akademiakodu.blog.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.akademiakodu.blog.Repositories.CoursesRepository;
import pl.akademiakodu.blog.Repositories.StudentRepository;
import pl.akademiakodu.blog.model.Courses;
import pl.akademiakodu.blog.model.Student;
import pl.akademiakodu.blog.model.StudentDetails;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping("/students/all") //endpoint
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @PostMapping("students/add")
    public Student addNewStudent (@ModelAttribute Student student, StudentDetails studentDetails){

        StudentDetails sd = new StudentDetails();
        //sd=studentDetails;
        Student result = new Student(
                student.getName(),
                student.getEmail(),
                studentDetails
        );

        return studentRepository.save(result);
    }

    @DeleteMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable("id") Long id){

        studentRepository.deleteById(id);
        return "Student from id:" + id + "deleted!";
    }

   // HERE WAS WORKING ONLY CHANGING OF ONE FIELD, ANOTHEERS ARE GIVING NULL
    //WHEN YOU CHANGE ONE FIELD E.G. phoneNumber in another fields we have null
    @PutMapping("/students/update/{id}")
    public String updateStudentById(@ModelAttribute Student student, @ModelAttribute StudentDetails studentDetails,
                                    @PathVariable Long id) {

        Optional<Student> resultOptional = studentRepository.findById(id); //Entitimanager

        //Student result =resultOptional.get();
        resultOptional.ifPresent((Student result) -> {

            result.getStudentDetails().setPhoneNumber(studentDetails.getPhoneNumber());
            result.getStudentDetails().setLastname(studentDetails.getLastname());

            studentRepository.save(result);
        });
        return "Student updated";
    }

    @Autowired
    CoursesRepository coursesRepository;

    @GetMapping("/students/{idStudent}")
    public List<Courses> showCoursesOfStudent (@PathVariable Long idStudent){

        Optional<Student> studentOptional = studentRepository.findById(idStudent);
        //Optional<Courses> courseOptional = coursesRepository.findById(idStudent);

            List<Courses> courseList = studentOptional.get().getCourses();

        return courseList;
    }

    @GetMapping("/students/{idStudent}/course/{idCourse}")
    public String addCourseToStudent(
            @PathVariable Long idStudent,
            @PathVariable Long idCourse,
            //   @ModelAttribute Student student,
            @ModelAttribute Courses courses
    ){
        Optional<Student> studentOptional = studentRepository.findById(idStudent);
        Optional<Courses> courseOptional = coursesRepository.findById(idCourse);
        studentOptional.ifPresent(result -> {
            //   Courses c = courseOptional.get();
            //   Student s = studentOptional.get();
            List<Courses> courseList = studentOptional.get().getCourses();
            //  List<Courses> newCourse = new ArrayList<>();
            courseList.add(courseOptional.get());
            //   newCourse.add(c);
            result.setCourses(courseList);
            //    result.setCourses(studentOptional.get().getCourses().add(courseOptional.get()));
            studentRepository.save(result);
        });
        return "Student id: " + idStudent + " has new Course: " + idCourse;

    }


       /* @PostMapping("/students/form/update/phone")
    public String updateStudentByForm(@RequestParam Long id, @RequestParam String phoneNumber) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        studentOptional.ifPresent(result -> {
            result = studentOptional.get();
            //    result.setEmail(student.getEmail());
            //    result.setStudentDetails(studentDetails);
            result.getStudentDetails().setPhoneNumber(phoneNumber);
            studentRepository.save(result);
        });
        return "Student form id: " + id + " updated!";

    }*/

/*
    //HERE WAS WORKING ONLY CHANGING OF ONE FIELD, ANOTHEERS ARE GIVING NULL - probably solution possible with IFs
        @PutMapping("/students/update/{id}")
        public String updateStudentById(@ModelAttribute Student student, @ModelAttribute StudentDetails studentDetails,
                                        @PathVariable Long id){

            Optional<Student> resultOptional = studentRepository.findById(id); //Entitimanager
               //resultOptional.ifPresent((Student result) -> {
               // result.setEmail(student.getEmail());
                //result.setStudentDetails(studentDetails);

                Student result =resultOptional.get();

                //result.getStudentDetails().setPhoneNumber(studentDetails.getPhoneNumber()); //now we dont have null in another data but we can change only phoneNumber
                //result.getStudentDetails().setLastname(studentDetails.getLastname()); // here after adding the change in lastname and email I can change everything but if it changes one thing in, the other is null


            if(result.getStudentDetails().getPhoneNumber().equals(null)) {

                    result.getStudentDetails().setLastname(studentDetails.getLastname());
                    result.getStudentDetails().getPhoneNumber();

                } else if (result.getStudentDetails().getLastname().equals(null)) {

                    result.getStudentDetails().setPhoneNumber(studentDetails.getPhoneNumber());
                    result.getStudentDetails().getLastname();
                }else {

                    result.getStudentDetails().setPhoneNumber(studentDetails.getPhoneNumber());
                    result.getStudentDetails().setLastname(studentDetails.getLastname());
                }
                // result.setEmail(student.getEmail());
                studentRepository.save(result);
            return "Student updated";

        }
*/

/** HERE WAS WORKING ONLY CHANGING OF ONE FIELD, ANOTHEERS ARE GIVING NULL
    WHEN YOU CHANGE ONE FIELD E.G. phoneNumber in another fields we have null
    @PutMapping("/students/update/{id}")
    public String updateStudentById(@ModelAttribute Student student, @ModelAttribute StudentDetails studentDetails,
                                    @PathVariable Long id){

        Optional<Student> resultOptional = studentRepository.findById(id); //Entitimanager
        resultOptional.ifPresent((Student result) -> {
        // result.setEmail(student.getEmail());
        //result.setStudentDetails(studentDetails);


        result.getStudentDetails().setPhoneNumber(studentDetails.getPhoneNumber()); // now we dont have null in another data but we can change only phoneNumber
        result.getStudentDetails().setLastname(studentDetails.getLastname()); // here after adding the change in lastname and email I can change everything but if it changes one thing in, the other is null

        // result.setEmail(student.getEmail());
        studentRepository.save(result);

        return "Student updated";

    }

*/
/* OLDER UPDATE THAN ABOVE
   WHEN YOU CHANGE ONE FIELD E.G. phoneNumber in another fields we have null

        @PutMapping("/students/update/{id}")
        public String updateStudentById(@ModelAttribute Student student, @ModelAttribute StudentDetails studentDetails,
                                        @PathVariable Long id){

            Optional<Student> resultOptional = studentRepository.findById(id); //Entitimanager
            resultOptional.ifPresent(result-> {
                result.setEmail(student.getEmail());
                result.setStudentDetails(studentDetails);
                studentRepository.save(result);
            });
        return "Student updated";

        }
*/

}


