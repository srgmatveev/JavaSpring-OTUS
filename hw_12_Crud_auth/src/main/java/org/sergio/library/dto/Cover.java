package org.sergio.library.dto;

import lombok.Data;
import org.bson.types.Binary;

import java.io.InputStream;
import java.util.Base64;

@Data
public class Cover {
    private String id;
    private String title;
    private String contentType;
    private String image;
}

