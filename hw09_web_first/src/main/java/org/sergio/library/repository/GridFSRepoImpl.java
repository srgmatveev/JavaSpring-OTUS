package org.sergio.library.repository;

import com.mongodb.client.gridfs.model.GridFSFile;
import lombok.extern.slf4j.Slf4j;
import org.sergio.library.exceptions.UniqueFileUploadException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository("gridFSRepo")
@Slf4j
public class GridFSRepoImpl implements GridFSRepo {
    private final GridFsTemplate gridFsTemplate;
    private final MongoTemplate mongoTemplate;

    public GridFSRepoImpl(GridFsTemplate gridFsTemplate, MongoTemplate mongoTemplate) {
        this.gridFsTemplate = gridFsTemplate;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public String fileUpload(Path path) {
        String id = null;
        if (Files.notExists(path)) {
            String error = String.format("File %s is not exist", path.toString());
            log.error(error);
            return null;
        }

        if (!Files.isRegularFile(path)) {
            String error = String.format("%s is not file", path.toString());
            log.error(error);
            return null;
        }

        File file = path.toFile();
        try (InputStream inputStream = new FileInputStream(file);) {
            String mimeType = Files.probeContentType(path);
            id = gridFsTemplate.store(inputStream, file.getName(), mimeType).toString();

        } catch (FileNotFoundException e) {
            log.error(e.toString());
            return null;
        } catch (IOException e) {
            log.error(e.toString());
            return null;
        }
        return id;
    }

    @Override
    public String fileUpload(MultipartFile file) {
        String id = null;
        try {
            id = gridFsTemplate.store(file.getInputStream(), file.getOriginalFilename(), file.getContentType()).toString();
        } catch (IOException e) {
            log.error(e.toString());
            return null;
        }
        return id;
    }

    @Override
    public GridFSFile findOne(String id) {
        return gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
    }

    @Override
    public void delete(String id) {
        gridFsTemplate.delete(new Query(Criteria.where("_id").is(id)));
    }

    @Override
    public long count() {
        final String COLLECTION = "fs.files";
        return mongoTemplate.getCollection(COLLECTION).countDocuments();
    }

    @Override
    public void drop() {
        final String COLLECT_FILES = "fs.files";
        final String COLLECT_CHUNKS = "fs.chunks";
        mongoTemplate.dropCollection(COLLECT_FILES);
        mongoTemplate.dropCollection(COLLECT_CHUNKS);
    }

    @Override
    public GridFsResource[] getResources(String pattern) {
        return gridFsTemplate.getResources(pattern);
    }

    @Override
    public String uniqFileUpload(Path path) throws UniqueFileUploadException {
        List<GridFSFile> fileList = new ArrayList<GridFSFile>();
        gridFsTemplate.find(new Query(Criteria.where("filename").is(path.getFileName().toString()))).into(fileList);
        if (fileList.size() == 0)
            return fileUpload(path);

        throw new UniqueFileUploadException(path.toString());
    }

    @Override
    @Transactional
    public String uniqFileUpload(MultipartFile file) throws UniqueFileUploadException {
        List<GridFSFile> fileList = new ArrayList<GridFSFile>();
        gridFsTemplate.find(new Query(Criteria.where("filename").is(file.getOriginalFilename()))).into(fileList);
        if (fileList.size() == 0)
            return fileUpload(file);

        throw new UniqueFileUploadException(file.getOriginalFilename());
    }
}
