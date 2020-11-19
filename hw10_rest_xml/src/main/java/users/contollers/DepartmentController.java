package users.contollers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import users.domain.Department;
import users.dto.DtoDepartment;
import users.dto.DtoDepartmentList;
import users.exceptions.DepartmentNotFoundException;
import users.services.DtoDepartmentService;

import javax.xml.bind.JAXBException;
import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping(value = "${rest.api.version1_0.path}")
@Slf4j
public class DepartmentController {
    @Autowired
    @Qualifier("DtoDepService")
    private DtoDepartmentService dtoDepartmentService;


    @PostMapping("/departments")
    DtoDepartment newDepartment(@RequestBody DtoDepartment dtoDepartment) {
        DtoDepartment dep = null;
        dep = dtoDepartmentService.post(dtoDepartment);
        return dep;
    }


    @PutMapping("/departments/{id}")
    DtoDepartment updateDepartment(@PathVariable(name = "id") String id, @RequestBody DtoDepartment dtoDepartment) {
        DtoDepartment dep = null;
        dep = dtoDepartmentService.put(dtoDepartment);
        return dep;
    }

    @DeleteMapping("/departments/{id}")
    void deleteDepartment(@PathVariable String id) {
        dtoDepartmentService.delete(id);
    }


    @GetMapping(value = "foos", produces = MediaType.APPLICATION_JSON_VALUE)
    public String duplicateJson() {
        return "hello";
    }

    @GetMapping(value="/departments", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    DtoDepartmentList all() {
        return dtoDepartmentService.finaAll();
    }

    @GetMapping(value="/departments/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    DtoDepartment getDepartment(@PathVariable String id) throws DepartmentNotFoundException {
        return  dtoDepartmentService.findById(id).orElseThrow(()->new DepartmentNotFoundException(id));
    }
}
