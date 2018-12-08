package pl.akademiakodu.blog.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.akademiakodu.blog.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
    

}
