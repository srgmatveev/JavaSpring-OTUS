package department.service;

import department.Main;
import department.dao.DepartmentRepo;
import department.dto.DtoDepartment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;



@SpringBootTest(classes = Main.class)
class DtoDepartmentServiceTest {
    @Autowired
    @Qualifier("DtoDepartmentService")
    private DepartmentService service;

    @Autowired
    DepartmentRepo departmentRepo;

    @BeforeEach
    void setUp() {
        departmentRepo.deleteAll().block();
    }

    @AfterEach
    void tearDown() {
        departmentRepo.deleteAll().block();
    }

    @Test
    @SuppressWarnings("unchecked")
    void createDepartment() {
        final String name = "Sergio1";
        DtoDepartment dtoDepartment = new DtoDepartment();
        dtoDepartment.setName(name);
        Mono<DtoDepartment> resultMono = service.createDepartment(dtoDepartment);
        StepVerifier
                .create(resultMono)
                .assertNext(dep->{
                    assertNotNull(dep);
                    assertNotNull(dep.getId());
                    assertEquals(dep.getName(), name);
                })
                .expectComplete()
                .verify();

    }

    @Test
    @SuppressWarnings("unchecked")
    void updateDepartment() {
        final String nameInsert = "Sergio_Insert1";
        final String nameUpdate = "Sergio_Update1";
        DtoDepartment dtoDepartment = new DtoDepartment();
        dtoDepartment.setName(nameInsert);
        dtoDepartment = (DtoDepartment) service.createDepartment(dtoDepartment).block();
        assertNotNull(dtoDepartment);
        assertNotNull(dtoDepartment.getId());
        assertEquals(dtoDepartment.getName(), nameInsert);
        String id = dtoDepartment.getId();

        dtoDepartment.setName(nameUpdate);
        assertEquals(dtoDepartment.getName(), nameUpdate);
        Mono<DtoDepartment> resultMono = service.updateDepartment(dtoDepartment, id);
        StepVerifier
                .create(resultMono)
                .assertNext(dep->{
                    assertNotNull(dep);
                    assertEquals(dep.getId(), id);
                    assertEquals(dep.getName(), nameUpdate);
                })
                .expectComplete()
                .verify();
    }

    @Test
    @SuppressWarnings("unchecked")
    void findAll() {
        DtoDepartment dtoDepartment = new DtoDepartment();
        dtoDepartment.setName("Hello");
        final DtoDepartment dtoDepartment_new = (DtoDepartment) service.createDepartment(dtoDepartment).block();

        DtoDepartment dtoDepartment1 = new DtoDepartment();
        dtoDepartment.setName("Sergio");
        final DtoDepartment dtoDepartment_new1 = (DtoDepartment)service.createDepartment(dtoDepartment1).block();
        Flux<DtoDepartment> resultFlux = service.findAll();
        StepVerifier
                .create(resultFlux)
                .assertNext(dep->{
                    assertNotNull(dep);
                    assertNotNull(dep.getId());
                    assertEquals(dep.getId(), dtoDepartment_new.getId());
                    assertEquals(dep.getName(), dtoDepartment_new.getName());
                })
                .assertNext(dep->{
                    assertNotNull(dep);
                    assertNotNull(dep.getId());
                    assertEquals(dep.getId(), dtoDepartment_new1.getId());
                    assertEquals(dep.getName(), dtoDepartment_new1.getName());
                })
                .expectComplete()
                .verify();
    }

    @Test
    @SuppressWarnings("unchecked")
    void findOne() {
        DtoDepartment dtoDepartment = new DtoDepartment();
        dtoDepartment.setName("Hello");
        final DtoDepartment dtoDepartment_new = (DtoDepartment) service.createDepartment(dtoDepartment).block();
        Mono<DtoDepartment> resultMono = service.findOne(dtoDepartment_new.getId());
        StepVerifier.create(resultMono)
                .assertNext(dep->{
                    assertNotNull(dep);
                    assertNotNull(dep.getId());
                    assertEquals(dep.getId(), dtoDepartment_new.getId());
                    assertEquals(dep.getName(), dtoDepartment_new.getName());
                })
                .expectComplete()
                .verify();
    }

    @Test
    @SuppressWarnings("unchecked")
    void findByName() {
        String name = "Hello";
        DtoDepartment dtoDepartment = new DtoDepartment();
        dtoDepartment.setName(name);
        final DtoDepartment dtoDepartment_new = (DtoDepartment) service.createDepartment(dtoDepartment).block();
        Mono<DtoDepartment> resultMono = service.findByName(name);
        StepVerifier.create(resultMono)
                .assertNext(dep->{
                    assertNotNull(dep);
                    assertNotNull(dep.getId());
                    assertEquals(dep.getId(), dtoDepartment_new.getId());
                    assertEquals(dep.getName(),name);
                })
                .expectComplete()
                .verify();
    }

    @Test
    @SuppressWarnings("unchecked")
    void delete() {
        String name = "Hello";
        DtoDepartment dtoDepartment = new DtoDepartment();
        dtoDepartment.setName(name);
        final DtoDepartment dtoDepartment_new = (DtoDepartment) service.createDepartment(dtoDepartment).block();
        Mono<Boolean> resultMono = service.delete(dtoDepartment_new.getId());
        StepVerifier.create(resultMono)
                .assertNext(d->{
                    assertTrue(d);
                })
                .expectComplete()
                .verify();
    }

}