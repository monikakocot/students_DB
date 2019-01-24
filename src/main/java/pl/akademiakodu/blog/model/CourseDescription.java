package pl.akademiakodu.blog.model;

import javax.persistence.*;

@Entity
@Table(name = "course_description")
public class CourseDescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "description")
    private String description;


    //CONSTRUCTORS, GETTERS,SETTERS
    public CourseDescription(Long id, String description) {
        this.id = id;
        this.description = description;
    }
    public CourseDescription() {
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}