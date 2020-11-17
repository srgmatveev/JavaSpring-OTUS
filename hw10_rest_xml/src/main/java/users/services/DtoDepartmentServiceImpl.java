package users.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import users.domain.Department;
import users.dto.DtoDepartment;
import users.repository.DepartmentRepo;

@Service("DtoDepService")
public class DtoDepartmentServiceImpl implements DtoDepartmentService{
    @Autowired
    DepartmentRepo departmentRepo;

    @Override
    public DtoDepartment post(DtoDepartment dtoDepartment)  {
        Department department = new Department();
        department.setName(dtoDepartment.getName());
        department = departmentRepo.save(department);
        return new DtoDepartment(department);
    }
}
