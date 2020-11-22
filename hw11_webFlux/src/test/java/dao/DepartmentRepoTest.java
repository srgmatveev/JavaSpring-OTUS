package dao;

import department.Main;
import department.dao.DepartmentRepo;
import department.domain.Department;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest (classes = Main.class)
class DepartmentRepoTest {
    @Autowired
    private DepartmentRepo departmentRepo;

    @BeforeEach
    void setUp() {
        departmentRepo.deleteAll().block();
    }

    @AfterEach
    void tearDown() {
        departmentRepo.deleteAll().block();
    }

    @Test
    void findByName() {
        final String name = "Sergio";
        assertEquals(departmentRepo.count().block(),0);
        Department department = new Department();
        department.setName(name);
        departmentRepo.save(department).block();
        assertEquals(departmentRepo.count().block(),1);
        Department department1 = departmentRepo.findByName(name).block();
        assertNotNull(department1);
        assertNotNull(department1.getId());
        assertEquals(department1.getName(), name);
    }

    @Test
    void findByIdAndDeleteIsFalse() {
        final String name = "Sergio";
        assertEquals(departmentRepo.count().block(),0);
        Department department = new Department();
        department.setName(name);
        departmentRepo.save(department).block();
        assertEquals(departmentRepo.count().block(),1);
        Department department1 = departmentRepo.findByName(name).block();
        assertNotNull(department1);
        assertNotNull(department1.getId());
        assertEquals(department1.getName(), name);
        assertFalse(department.getDelete());
    }
}