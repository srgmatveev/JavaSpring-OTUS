package users.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import users.test_controllers.TestDepartmentRepo;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentTest {
    private final TestDepartmentRepo departmentRepo;
    private Department department;
    private final String depName = "Culture";

    DepartmentTest(@Qualifier("testDepartmentRepo") TestDepartmentRepo departmentRepo) {
        this.departmentRepo = departmentRepo;
    }

    @BeforeEach
    void setUp() {
        department = new Department();
        department.setName(depName);
        departmentRepo.insert(department);
    }

    @AfterEach
    void tearDown() {
        departmentRepo.deleteAll();
    }

    @Test
    void getId() {
        Optional<Department> dep = departmentRepo.findOne(Example.of(department));
        assertFalse(dep.isEmpty());
        assertNotNull(dep.get().getId());
        assertEquals(dep.get().getId(), department.getId());
    }

    @Test
    void getName() {
        Optional<Department> dep = departmentRepo.findOne(Example.of(department));
        assertFalse(dep.isEmpty());
        assertEquals(dep.get().getName(), depName);
    }

    @Test
    void setName() {
        String newDep = "Scientology";
        String id = department.getId();
        department.setName(newDep);
        departmentRepo.save(department);
        assertEquals(departmentRepo.count(),1);
        Optional<Department> dep = departmentRepo.findOne(Example.of(department));
        assertFalse(dep.isEmpty());
        assertEquals(dep.get().getName(), newDep);
        assertEquals(dep.get().getId(), id);
    }
}