package department.service;

import department.domain.Department;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DepartmentService {
    Mono<Department> createDepartment(Department Department);

    Mono<Department> updateDepartment(Department Department, String id);

    Flux<Department> findAll();

    Mono<Department> findOne(String id);

    Flux<Department> findByTitle(String title);

    Mono<Boolean> delete(String id);
}
