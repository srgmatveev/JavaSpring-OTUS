package org.sergio.library.repository;

import com.mongodb.client.gridfs.model.GridFSFile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.sergio.library.dto.Cover;
import org.sergio.library.exceptions.UniqueFileUploadException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class GridFSRepoTest {
    private final GridFSRepo fsRepo;

    GridFSRepoTest(@Qualifier("gridFSRepo") GridFSRepo fsRepo) {
        this.fsRepo = fsRepo;
    }

    @Test
    void uploadFile() {
        String filePath = "src/test/resources/images/Testing.png";
        String id = fsRepo.fileUpload(Paths.get(filePath));
        assertNotNull(id);
    }

    @Test
    void findOne() {
        String filePath = "src/test/resources/images/Testing.png";
        String id = fsRepo.fileUpload(Paths.get(filePath));
        GridFSFile fsFile = fsRepo.findOne(id);
        assertEquals(fsFile.getObjectId().toString(), id);
        assertEquals(fsFile.getFilename(), "Testing.png");
    }

    @Test
    void delete() {
        String filePath = "src/test/resources/images/Testing.png";
        String id = fsRepo.fileUpload(Paths.get(filePath));
        GridFSFile fsFile = fsRepo.findOne(id);
        assertEquals(fsFile.getObjectId().toString(), id);
        assertEquals(fsFile.getFilename(), "Testing.png");
        fsRepo.delete(id);
        fsFile = fsRepo.findOne(id);
        assertNull(fsFile);
    }


    @Test
    void drop() {
        fsRepo.drop();
        assertEquals(fsRepo.count(), 0);
        String filePath = "src/test/resources/images/Testing.png";
        fsRepo.fileUpload(Paths.get(filePath));
        assertEquals(fsRepo.count(), 1);
        fsRepo.drop();
        assertEquals(fsRepo.count(), 0);
    }

    @Test
    void getResources() {
        fsRepo.drop();
        String filePath = "src/test/resources/images/Testing.png";
        fsRepo.fileUpload(Paths.get(filePath));
        filePath = "src/test/resources/images/microscope.png";
        fsRepo.fileUpload(Paths.get(filePath));
        fsRepo.fileUpload(Paths.get(filePath));

        GridFsResource[] arr = fsRepo.getResources("Testing.png");
        assertEquals(arr.length, 1);
        assertTrue(arr[0].exists());

        arr = fsRepo.getResources("microscope*.png");
        assertEquals(arr.length, 2);
        assertTrue(arr[0].exists());

        arr = fsRepo.getResources("microscope.png"); // find only unique
        assertEquals(arr.length, 1);
        assertTrue(arr[0].exists());

        arr = fsRepo.getResources("microscope1.png");
        assertFalse(arr[0].exists());
    }

    @Test
    void uniqGeneratedFileUpload() throws UniqueFileUploadException {
        fsRepo.drop();
        String filePath = "src/test/resources/images/microscope.png";
        fsRepo.fileUpload(Paths.get(filePath));
        Assertions.assertThrows(UniqueFileUploadException.class, () -> {
            fsRepo.uniqFileUpload(Paths.get(filePath));
        });

        assertEquals(fsRepo.count(), 1);

        fsRepo.drop();
        String id = fsRepo.uniqFileUpload(Paths.get(filePath));
        assertEquals(fsRepo.count(), 1);
        GridFSFile fsFile = fsRepo.findOne(id);
        assertEquals(fsFile.getObjectId().toString(), id);
        assertEquals(fsFile.getFilename(), "microscope.png");
    }

    @Test
    void findAll() {
        fsRepo.drop();
        String filePath = "src/test/resources/images/microscope.png";
        fsRepo.fileUpload(Paths.get(filePath));
        filePath = "src/test/resources/images/Testing.png";
        fsRepo.fileUpload(Paths.get(filePath));
        List<Cover> lst = fsRepo.findAll();
        assertNotNull(lst);
        assertEquals(lst.size(), 2);
        assertEquals(lst.get(0).getTitle(), "microscope.png");
        assertEquals(lst.get(1).getTitle(), "Testing.png");
        fsRepo.drop();
    }
}