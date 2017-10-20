package com.mpls.v2.controller;

import com.google.api.services.drive.model.File;
import com.mpls.v2.dro.DriveFile;
import com.mpls.v2.service.GoogleDriveService;
import com.mpls.v2.service.exceptions.GoogleDriveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.List;


@CrossOrigin()
@RestController
@RequestMapping("/restful/google-drive")
public class GoogleDriveController {

    private final String FOLDER_FLAG = "application/vnd.google-apps.folder";

    @Autowired
    private GoogleDriveService driveService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public DriveFile getFile(@PathVariable String id) {
        return getDriveFileFrom(driveService.getFile(id));
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "parent/{id}", method = RequestMethod.GET)
    public List<DriveFile> findByParentId(@PathVariable String id) {
        return getDriveFilesFrom(driveService.findByParentId(id));
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "create/{parentId}", method = RequestMethod.POST)
    public DriveFile createFolder(@PathVariable String parentId, @RequestBody String name) {
        return getDriveFileFrom(driveService.createFolder(name, parentId));
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable String id) {

        return driveService.delete(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
    public DriveFile update(@PathVariable String id, @RequestBody String name) {
        return getDriveFileFrom(driveService.update(id, name));
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "upload/{id}", method = RequestMethod.POST)
    public void upload(@PathVariable String id, @RequestParam("file") MultipartFile file) {
        driveService.upload(file, id);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "download/{id}", method = RequestMethod.GET)
    public void download(@PathVariable String id, HttpServletResponse response) {
//        System.err.println("---------------------------------");
//        System.err.println("---------------------------------");
//        System.err.println("---------------------------------");
//        List<File> file = driveService.getAll();
//        String tempId = "";
//        for (File file1 : file) {
//            System.err.println("bool 1 :|" + file1.getName() + "|" + id + "|= " + file1.getName().contains(id));
//            if (file1.getName().contains(id)) {
//                tempId = file1.getId();
//            }
//        }
//
//        System.err.println("---------------------------------");
//        System.err.println("---------------------------------");
//        System.err.println("---------------------------------");

        driveService.download(id, response);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public Error illegalFileName(IllegalArgumentException exception) {
        String message = exception.getLocalizedMessage();
        return new Error(400 + message);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(GoogleDriveException.class)
    public Error googleDriveError(GoogleDriveException exception) {
        String message = exception.getMessage();
        return new Error(500 + message);
    }

    private List<DriveFile> getDriveFilesFrom(List<File> list) {
        List<DriveFile> files = new LinkedList<>();
        list.forEach(file -> {
            files.add(getDriveFileFrom(file));
        });
        return files;
    }

    private DriveFile getDriveFileFrom(File file) {
        DriveFile driveFile = new DriveFile();
        driveFile.setId(file.getId());
        driveFile.setName(file.getName());
        driveFile.setFolder(file.getMimeType().equals(FOLDER_FLAG));
        driveFile.setParents(file.getParents());
        return driveFile;
    }

}