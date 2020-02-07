package com.distributed.fileServer.controller;


import com.distributed.fileServer.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@RestController
public class FileController {

    @Autowired
    FileService fileService;


    @RequestMapping(value = "/{fileName}", method = RequestMethod.GET)
    public ResponseEntity<Resource> downloadFile(@PathVariable("fileName") String fileName) {
        Resource resource = null;
        File file;
        try {
            file = fileService.getFile(fileName);
            resource = new UrlResource(file.toURI());
            if (resource.exists()) {
                String hashCode = fileService.getHash(file);
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION,
                                "fileName: " + fileName, "hashcode: " + hashCode)
                        .contentLength(file.length())
                        .body(resource);
            }
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }


}
