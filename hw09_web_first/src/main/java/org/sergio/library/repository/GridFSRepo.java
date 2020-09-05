package org.sergio.library.repository;

import com.mongodb.client.gridfs.model.GridFSFile;
import org.sergio.library.exceptions.UniqueFileUploadException;
import org.springframework.data.mongodb.gridfs.GridFsResource;

import java.nio.file.Path;

public interface GridFSRepo {
    String fileUpload(Path path);

    GridFSFile findOne(String id);

    void delete(String id);

    long count();

    void drop();

    GridFsResource[] getResources(String pattern);

    String uniqFileUpload(Path path) throws UniqueFileUploadException;
}
