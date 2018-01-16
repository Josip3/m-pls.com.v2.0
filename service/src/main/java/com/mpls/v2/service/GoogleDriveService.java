package com.mpls.v2.service;

import com.google.api.services.drive.model.File;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface GoogleDriveService {
    File createFolder(String name, String parentId);

    String delete(String id);

    File getFile(String id);

    List<File> getAll();

    List<File> findByParentId(String id);

    File update(String id, String name);

    String upload(MultipartFile file);

    void download(String id, HttpServletResponse response);
}