package department.service;

import department.Main;
import department.dto.DtoDepartment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;



@SpringBootTest(classes = Main.class) //(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DtoDepartmentServiceTest {
    @Autowired
    @Qualifier("DtoDepartmentService")
    private DepartmentService service;


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

   /* @Test
    void updateDepartment() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findOne() {
    }

    @Test
    void findByName() {
    }

    @Test
    void delete() {
    }

    */
}