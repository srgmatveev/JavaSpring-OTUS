package department.service;

import department.dao.DepartmentRepo;
import department.domain.Department;
import department.dto.DtoDepartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.Disposable;
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
    public Mono<DtoDepartment> updateDepartment(DtoDepartment dtoDepartment, String id) {
        return findDepOne(id).doOnSuccess(dep -> {
            String name = dtoDepartment.getName();
            dep.setName(name);
            save(dep).subscribe();
        }).flatMap(d -> Mono.just(new DtoDepartment(d)));
    }

    private Mono<Department> save(Department department) {
        return repo.save(department).
                switchIfEmpty(Mono.error(new Exception("No Department update with Name: " + department.getName())));

    }

    private Mono<Department> findDepOne(String id) {
        return repo.findByIdAndDeleteIsFalse(id).
                switchIfEmpty(Mono.error(new Exception("No Department found with Id: " + id)));
    }

    @Override
    public Flux<DtoDepartment> findAll() {
        return repo.findAll().flatMap(dep1 -> Mono.just(new DtoDepartment(dep1)));
    }

    @Override
    public Mono<DtoDepartment> findOne(String id) {
        return repo.findByIdAndDeleteIsFalse(id).
                switchIfEmpty(Mono.error(new Exception("No Department found with Id: " + id)))
                .flatMap(dep1 -> Mono.just(new DtoDepartment(dep1)));
    }

    @Override
    public Mono<DtoDepartment> findByName(String name) {
        return repo.findByNameAndDeleteIsFalse(name).
                switchIfEmpty(Mono.error(new Exception("No Department found with name Containing : " + name)))
                .flatMap(dep1 -> Mono.just(new DtoDepartment(dep1)));
    }

    @Override
    public Mono<Boolean> delete(String id) {
        return findDepOne(id).doOnSuccess(dep -> {
            dep.setDelete(true);
            repo.save(dep).subscribe();
        }).flatMap(dep -> Mono.just(Boolean.TRUE));
    }
}
