package users.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import users.domain.Department;
import users.dto.DtoDepartment;
import users.dto.DtoDepartmentList;
import users.repository.DepartmentRepo;

import java.util.Optional;
import java.util.stream.Collectors;

@Service("DtoDepService")
public class DtoDepartmentServiceImpl implements DtoDepartmentService {
    @Autowired
    DepartmentRepo departmentRepo;

    @Override
    public DtoDepartment post(DtoDepartment dtoDepartment) {
        Department department = new Department();
        department.setName(dtoDepartment.getName());
        department = departmentRepo.save(department);
        return new DtoDepartment(department);
    }

    @Override
    public DtoDepartment put(DtoDepartment dtoDepartment) {
        Department department = new Department(dtoDepartment.getId(), dtoDepartment.getName());
        department = departmentRepo.save(department);
        return new DtoDepartment(department);
    }

    @Override
    public void delete(String id) {
        departmentRepo.deleteById(id);
    }

    @Override
    public DtoDepartmentList finaAll() {
        DtoDepartmentList departmentList = new DtoDepartmentList();
        departmentList.setDepartments(departmentRepo.findAll().stream().map(DtoDepartment::toDto).collect(Collectors.toList()));
        return departmentList;
    }

    @Override
    public Optional<DtoDepartment> findById(String id) {
        Optional<Department> department = departmentRepo.findById(id);
        return department.isEmpty() ? Optional.empty() : Optional.of(new DtoDepartment(department.get()));
    }
}
