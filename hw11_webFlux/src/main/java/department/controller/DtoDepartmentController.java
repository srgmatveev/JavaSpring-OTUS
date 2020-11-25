package department.controller;

import department.dto.DtoDepartment;
import department.dto.DtoDepartmentList;
import department.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@RestController
public class DtoDepartmentController {

    @Autowired
    @Qualifier("DtoDepartmentService")
    DepartmentService service;

    @GetMapping
    @SuppressWarnings("unchecked")
    public Flux<DtoDepartment> findAll(@RequestHeader("accept") String accept) {
        log.debug("findAll departments");
        if (accept.equals("application/xml")) {
            log.debug("XML is not an asynchronous call. Not Supported");
            return Flux.empty();
        } else
            return service.findAll();
    }


    @GetMapping("/find")
    @SuppressWarnings("unchecked")
    public Mono<DtoDepartment> findByName(@RequestParam String name) {
        log.debug("findByName Department with name : {}", name);
        return service.findByName(name);
    }


    @GetMapping("/{id}")
    @SuppressWarnings("unchecked")
    public Mono<DtoDepartment> findOne(@PathVariable String id) {
        log.debug("findOne DtoDepartment with id : {}", id);
        return service.findOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @SuppressWarnings("unchecked")
    public Mono<DtoDepartment> create(@RequestBody DtoDepartment dtoDepartment) {
        log.debug("create DtoDepartment with department : {}", dtoDepartment);
        return service.createDepartment(dtoDepartment);
    }

    @DeleteMapping("/{id}")
    @SuppressWarnings("unchecked")
    public Mono<Boolean> delete(@PathVariable String id) {
        log.debug("delete DtoDepartment with id : {}", id);
        return service.delete(id);
    }

    @PutMapping("/{id}")
    @SuppressWarnings("unchecked")
    public Mono<DtoDepartment> updateDepartment(@RequestBody DtoDepartment department, @PathVariable String id) {
        log.debug("updateDepartment Department with id : {} and department : {}", id, department);
        return service.updateDepartment(department, id);
    }

}
