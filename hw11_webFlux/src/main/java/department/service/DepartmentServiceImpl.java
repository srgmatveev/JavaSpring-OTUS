package department.service;

import department.dao.DepartmentRepo;
import department.domain.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service("DepartmentService")
public class DepartmentServiceImpl implements DepartmentService<Department>{
    @Autowired
    private DepartmentRepo repo;

    @Override
    public Mono<Department> createDepartment(Department department) {
        return repo.insert(department);
    }

    @Override
    public Mono<Department> updateDepartment(Department department, String id) {
        return findOne(id).doOnSuccess(dep->{
            dep.setName(department.getName());
            repo.save(dep).subscribe();
        });
    }



    @Override
    public Flux<Department> findAll() {
        return repo.findAll();
    }

    @Override
    public Mono<Department> findOne(String id) {
        return repo.findByIdAndDeleteIsFalse(id).
                switchIfEmpty(Mono.error(new Exception("No Department found with Id: " + id)));
    }

    @Override
    public Mono<Department> findByName(String name) {
        return repo.findByNameAndDeleteIsFalse(name).
                switchIfEmpty(Mono.error(new Exception("No Department found with name Containing : " + name)));
    }

    @Override
    public Mono<Boolean> delete(String id) {
        return findOne(id).doOnSuccess(dep->{
                    dep.setDelete(true);
                    repo.save(dep).subscribe();
                }).flatMap(dep->Mono.just(Boolean.TRUE));
    }
}
