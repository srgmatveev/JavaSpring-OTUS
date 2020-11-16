package users.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import users.domain.Department;

@Repository
public interface DepartmentRepo extends MongoRepository<Department, String> {
}
