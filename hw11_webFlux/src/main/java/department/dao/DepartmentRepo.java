package department.dao;

import department.domain.Department;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface DepartmentRepo extends ReactiveMongoRepository<Department, String> {
    Mono<Department> findByName(String name);

    Mono<Department> findByIdAndDeleteIsFalse(String id);
}
