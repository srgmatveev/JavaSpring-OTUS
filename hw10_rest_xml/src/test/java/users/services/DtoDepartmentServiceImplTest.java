package users.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import users.dto.DtoDepartment;
import users.test_controllers.TestDepartmentRepo;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DtoDepartmentServiceImplTest {
    private final TestDepartmentRepo departmentRepo;
    private final DtoDepartmentService departmentService;
    DtoDepartmentServiceImplTest(@Qualifier("testDepartmentRepo") TestDepartmentRepo departmentRepo,
                                 @Qualifier("DtoDepService") DtoDepartmentService departmentService) {
        this.departmentRepo = departmentRepo;
        this.departmentService = departmentService;
    }

    @Test
    void post() {
        DtoDepartment dtoDepartment = new DtoDepartment(null, "sergio");
        DtoDepartment testDtoDepartment = departmentService.post(dtoDepartment);
        assertNotNull(testDtoDepartment.getId());
        assertEquals(testDtoDepartment.getName(),"sergio");
    }
}