package ma.bakkou.spring_angular.Repository;

import ma.bakkou.spring_angular.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student , String> {

    Student findByCode(String code);
    List<Student> findByProgramId(String programId);

}
