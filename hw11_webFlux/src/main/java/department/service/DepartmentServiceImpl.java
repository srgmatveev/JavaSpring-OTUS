package department.service;

import department.domain.Department;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class DepartmentServiceImpl implements DepartmentService{
    @Override
    public Mono<Department> createDepartment(Department Department) {
        return null;
    }

    @Override
    public Mono<Department> updateDepartment(Department Department, String id) {
        return null;
    }

    @Override
    public Flux<Department> findAll() {
        return null;
    }

    @Override
    public Mono<Department> findOne(String id) {
        return null;
    }

    @Override
    public Flux<Department> findByTitle(String title) {
        return null;
    }

    @Override
    public Mono<Boolean> delete(String id) {
        return null;
    }
}
