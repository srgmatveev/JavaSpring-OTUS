package department.service;

import department.dao.DepartmentRepo;
import department.domain.Department;
import department.dto.DtoDepartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service("DtoDepartmentService")
public class DtoDepartmentService implements DepartmentService<DtoDepartment> {
    @Autowired
    private DepartmentRepo repo;

    @Override
    public Mono<DtoDepartment> createDepartment(DtoDepartment department) {
        Department dep = new Department();
        dep.setName(department.getName());
        return repo.insert(dep).
                switchIfEmpty(Mono.error(new Exception("No Department created with Name: " + department.getName())))
                .flatMap(dep1 -> Mono.just(new DtoDepartment(dep1)));
    }

    @Override
    public Mono<DtoDepartment> updateDepartment(DtoDepartment Department, String id) {
        return null;
    }

    @Override
    public Flux<DtoDepartment> findAll() {
        return null;
    }

    @Override
    public Mono<DtoDepartment> findOne(String id) {
        return null;
    }

    @Override
    public Mono<DtoDepartment> findByName(String name) {
        return null;
    }

    @Override
    public Mono<Boolean> delete(String id) {
        return null;
    }
}
