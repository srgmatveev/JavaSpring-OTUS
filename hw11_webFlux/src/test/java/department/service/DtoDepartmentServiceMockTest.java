package department.service;

import department.dao.DepartmentRepo;
import department.domain.Department;
import department.dto.DtoDepartment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class DtoDepartmentServiceMockTest {
    @Autowired
    @Qualifier("DtoDepartmentService")
    private DepartmentService service;


    @MockBean
    private DepartmentRepo repo;

    @Test
    @SuppressWarnings("unchecked")
    void createDepartment_switchIfEmpty() {
        Department department = new Department();
        department.setName("Sergio");

        Mockito.when(repo.insert(department)).thenReturn(Mono.empty());

        DtoDepartment dtoDepartment = new DtoDepartment();
        dtoDepartment.setName("Sergio");
        Mono<DtoDepartment> resultMono = service.createDepartment(dtoDepartment);
        StepVerifier
                .create(resultMono)
                .expectErrorMessage("No Department created with Name: Sergio")
                .verify();
    }

}
