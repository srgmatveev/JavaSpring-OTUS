package department.service;

import department.domain.Department;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DepartmentService<T> {
    Mono<T> createDepartment(T department);

    Mono<T> updateDepartment(T Department, String id);

    Flux<T> findAll();

    Mono<T> findOne(String id);

    Mono<T> findByName(String name);

    Mono<Boolean> delete(String id);
}
