package org.sergio.library.repository;

import com.mongodb.client.gridfs.model.GridFSFile;
import org.sergio.library.dto.Cover;
import org.sergio.library.exceptions.UniqueFileUploadException;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;

public interface GridFSRepo {
    String fileUpload(Path path);

    String fileUpload(MultipartFile file);

    GridFSFile findOne(String id);

    void delete(String id);

    long count();

    void drop();

    GridFsResource[] getResources(String pattern);

    String uniqFileUpload(Path path) throws UniqueFileUploadException;

    String uniqFileUpload(MultipartFile file) throws UniqueFileUploadException;

    List<Cover> findAll();
}
