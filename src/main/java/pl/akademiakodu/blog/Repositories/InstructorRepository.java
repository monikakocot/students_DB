package pl.akademiakodu.blog.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.akademiakodu.blog.model.Instructor;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor,Long> {
}
