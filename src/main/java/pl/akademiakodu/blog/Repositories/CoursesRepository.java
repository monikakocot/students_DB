package pl.akademiakodu.blog.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.akademiakodu.blog.model.Courses;

@Repository
public interface CoursesRepository extends JpaRepository<Courses, Long>{

}
