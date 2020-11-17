package users.contollers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import users.domain.Department;
import users.dto.DtoDepartment;
import users.services.DtoDepartmentService;

import javax.xml.bind.JAXBException;
import java.io.Serializable;

@RestController
@RequestMapping(value = "${rest.api.version1_0.path}")
@Slf4j
public class DepartmentController {
    @Autowired
    @Qualifier("DtoDepService")
    private DtoDepartmentService dtoDepartmentService;


    @PostMapping("/departments")
    DtoDepartment newDepartment(@RequestBody DtoDepartment newEmployee) {
        DtoDepartment dep = null;
            dep = dtoDepartmentService.post(newEmployee);
        return dep;
    }


    @GetMapping(value = "foos", produces = MediaType.APPLICATION_JSON_VALUE)
    public String duplicateJson() {
        return "hello";
    }


}
