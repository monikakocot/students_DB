package pl.akademiakodu.blog.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JoinColumnOrFormula;

import javax.persistence.*;
import java.util.List;

/** LOMBOK
@Data // getters seetters  methods Object
@AllArgsConstructor
@NoArgsConstructor
*/
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
//active when adding courses to instructors is @ManyToMany
@Entity
@Table(name="instructor")
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @Column(name="name")
    private String name;

    //KOD1
    //@OnetoMany(mappedBy="instructor")
    //private Set<Courses> course;
    //KOD1

    //Version L has @OnetoMany
    //element mappedBy - owner of relation
    @OneToMany(mappedBy="instructor",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private List<Courses> courses; //- here we didnt succed with adding courses to instructor

/*@ManyToMany
    @JoinTable(name="course_instructor", joinColumns = @JoinColumn(name="instructor_id"),
    inverseJoinColumns = @JoinColumn(name="course_id"))
    private List<Courses> courses;
*/
    public List<Courses> getCourses() {
        return courses;
    }

    public void setCourses(List<Courses> courses) {
        this.courses = courses;
    }

    public String getCommunicate(){
        return "The course has not have a Instructor :(";
    }

    //CONSTRUCTORS, GETTERS,SETTERS
    public Instructor() {
    }

    public Instructor(Long id, String name, List<Courses> courses) {

        this.id = id;
        this.name = name;
        this.courses = courses;
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

}
