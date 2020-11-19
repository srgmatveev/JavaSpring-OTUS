package users.services;

import users.dto.DtoDepartment;
import users.dto.DtoDepartmentList;

import java.util.Optional;


public interface DtoDepartmentService {
    DtoDepartment post(DtoDepartment dtoDepartment);
    DtoDepartment put(DtoDepartment dtoDepartment);
    void delete(String id);
    DtoDepartmentList finaAll();
    Optional<DtoDepartment> findById(String id);
}
