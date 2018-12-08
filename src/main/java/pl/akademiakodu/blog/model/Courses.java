package pl.akademiakodu.blog.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;

@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class,property="id" )
//active when adding courses to instructors is @ManyToMany

@Entity
@Table(name = "courses")
public class Courses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "level")
    private String level;

    //KOD1

    //@ManyToOne
    //@ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    // @JoinColumn(name = "instructor_id") //creating in table 'courses', 'instructor_id' column
    //private Instructor instructor; // here we didnt succed with adding courses to instructor

    //KOD1

    //@ManyToOne
    //@ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    // @JoinColumn(name = "student_id")// creating in table 'courses', 'student_id' column

    @JsonBackReference //to not show field 'student' in courses

    @ManyToMany
    @JoinTable(name="course_student",joinColumns = @JoinColumn(name="course_id"),
            inverseJoinColumns = @JoinColumn(name="student_id"))

    private List<Student> student;

//LUKASZ MA @ManyToOne
    @JsonBackReference //  //to not show field 'instructor' in courses
    @ManyToOne(cascade = {CascadeType.PERSIST,
        CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

/*
    @ManyToMany
    @JoinTable(name="course_instructor", joinColumns = @JoinColumn(name="course_id"),
            inverseJoinColumns = @JoinColumn(name="instructor_id"))
*/
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_description_id")
    private CourseDescription courseDescription;


    // W WERSJI LUKASZA
    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

/* ZAKOMENTOWANE W WERSJI LUKASZA
    // this for @ManyToMany
   // private List<Instructor> instructor;
    public List<Instructor> getInstructor() {
        return instructor;
    }

    public void setInstructor(List<Instructor> instructor) {
        this.instructor = instructor;
    }
*/

    public List<Student> getStudent() {
        return student;
    }

    public void setStudent(List<Student> student) {
        this.student = student;
    }

    public CourseDescription getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(CourseDescription courseDescription) {
        this.courseDescription = courseDescription;
    }

    //CONSTRUCTORS, GETTERS,SETTERS

    public Courses() {
    }

    public Courses(String title, String level) {
        this.title = title;
        this.level = level;
    }

    public Courses(String title, String level, CourseDescription courseDescription) {
        this.title = title;
        this.level = level;
        this.courseDescription = courseDescription;
    }

    public Courses(String title, String level, List<Student> student, Instructor instructor, CourseDescription courseDescription) {
        this.title = title;
        this.level = level;
        this.student = student;
        this.instructor = instructor;
        this.courseDescription = courseDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Courses{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", level='" + level + '\'' +
                '}';
    }

}
