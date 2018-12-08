package pl.akademiakodu.blog.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;


@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class,property="id" ) //to not loop app (JSON)
@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;

    //@OneToMany(mappedBy = "student")
    @ManyToMany
    @JoinTable(name="course_student",joinColumns = @JoinColumn(name="student_id"),
    inverseJoinColumns = @JoinColumn(name="course_id"))

    private List<Courses> courses;

    @OneToOne(cascade = CascadeType.ALL)
   // @JoinColumn(name="studen/_details_id") // with this we have dobule student_details column
    private  StudentDetails studentDetails;


    public List<Courses> getCourses() {
        return courses;
    }

    public void setCourses(List<Courses> courses) {
        this.courses = courses;
    }

    public StudentDetails getStudentDetails() {
        return studentDetails;
    }

    public void setStudentDetails(StudentDetails studentDetails) {
        this.studentDetails = studentDetails;
    }



    //CONSTRUCTORS, GETTERS,SETTERS

    public Student() {
    }

    public Student(String name, String email, StudentDetails studentDetails) {
       // this.id = id;
        this.name = name;
        this.email = email;
        this.studentDetails = studentDetails;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
