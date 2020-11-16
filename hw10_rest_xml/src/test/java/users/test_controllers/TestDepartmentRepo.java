package users.test_controllers;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import users.domain.Department;

@Repository(value = "testDepartmentRepo")
public interface TestDepartmentRepo extends MongoRepository<Department, String> {
}
