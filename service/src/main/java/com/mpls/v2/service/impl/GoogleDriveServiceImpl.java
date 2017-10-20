package com.mpls.v2.service.impl;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.mpls.v2.service.GoogleDriveService;
import com.mpls.v2.service.exceptions.GoogleDriveException;
import com.mpls.v2.service.utils.FileBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

/**
 * GoogleDrive-specific service implementation.
 *
 * @author Kostyantyn Panchenko
 * @version 1.0
 * @since 24.12.2016
 */
@Service
public class GoogleDriveServiceImpl implements GoogleDriveService {

    /**
     * Temporary directory for storing uploading file
     */
    private static final String TEMP = System.getProperty("catalina.home");
    /**
     * Global instance of the scopes. DRIVE_APPDATA provide r/w access only to appDataFolder.
     */
    private static final java.util.Collection<String> SCOPES =
            DriveScopes.all();
    private final Logger LOGGER = LoggerFactory.getLogger(GoogleDriveServiceImpl.class);
    /**
     * Google's name for application root folder. Can be use in any place where fileId is needed.
     */
    private final String APP_FOLDER = "appDataFolder";
    /**
     * Application name. Arbitrary as we use service account.
     */
    private final String APPLICATION_NAME = "m-pls";
    /**
     * Core GoogleDrive file fields we will work with.
     */
    private final String CORE = "id, name, mimeType, parents";
    /**
     * Folder flag in File object.
     */
    private final String FOLDER_FLAG = "application/vnd.google-apps.folder";
    /**
     * Folder name pattern.
     */
    private final String PATTERN = "^[\\sа-яА-ЯіІїЇєЄa-zA-Z0-9_.-]{0,34}[а-яА-ЯіІїЇєЄa-zA-Z0-9_-]{1}$";
    /**
     * Global instance of the JSON factory.
     */
    private final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    @Autowired
    private FileBuilder fileBuilder;
    /**
     * GoogleDrive service.
     */
    private Drive driveService;

    /**
     * Global instance of the HTTP transport.
     */
    private HttpTransport HTTP_TRANSPORT;

    {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            Credential credential = GoogleCredential
                    .fromStream(GoogleDriveServiceImpl.class.getResourceAsStream("/m-pls-5a4304358789.json"))
                    .createScoped(SCOPES);

            driveService = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                    .setApplicationName(APPLICATION_NAME).build();
            LOGGER.info("-----------------------------------------");
            LOGGER.info("-----------------------------------------");
            LOGGER.info(credential.toString());
            LOGGER.info("-----------------------------------------");
            LOGGER.info("-----------------------------------------");

        } catch (GeneralSecurityException e) {
            LOGGER.info("-----------------------------------------");
            LOGGER.info("-----------------------------------------");
            LOGGER.info("-----------------------------------------");
            LOGGER.error(e.getMessage());
            LOGGER.info("-----------------------------------------");
            LOGGER.info("-----------------------------------------");
            LOGGER.info("-----------------------------------------");
        } catch (IOException e) {
            LOGGER.info("-----------------------------------------");
            LOGGER.error(e.getMessage());
            LOGGER.info("-----------------------------------------");

        }
    }

    @Override
    public File createFolder(String name, String parentId) {
        validateName(name);
        checkIfExist(name, parentId);

        File file = getFileWithMetadata(name, parentId);
        file.setMimeType(FOLDER_FLAG);

        File newFolder = null;
        try {
            newFolder = driveService.files().create(file).setFields(CORE).execute();
            LOGGER.info(new StringBuilder("Folder ").append(newFolder.getName())
                    .append(" with id=").append(newFolder.getId())
                    .append(" was created").toString());
        } catch (IOException e) {
            processGDE("Could not create folder " + name);
        }

        return newFolder;
    }

    private void validateName(String folderName) {
        if (!folderName.matches(PATTERN)) {
            throw new IllegalArgumentException("Folder name '" + folderName + "' not allowed!");
        }
    }

    private void checkIfExist(String folderName, String parentId) {
        findByParentId(parentId).forEach(file -> {
            if (file.getName().equalsIgnoreCase(folderName)) {
                throw new IllegalArgumentException("Folder '" + folderName + "' already exist!");
            }
        });
    }

    private File getFileWithMetadata(String name, String parentId) {
        File file = new File();
        file.setName(name);
//        file.setParents(Collections.singletonList(parentId));
        return file;
    }

    @Override
    public String delete(String id) {
        try {
            driveService.files().delete(id).execute();
            LOGGER.info("File with id = " + id + " was deleted");
        } catch (IOException e) {
            processIAE("Folder with id = '" + id + "' does not exist!");
        }
        return id;
    }

    @Override
    public File getFile(String id) {
        return getFileWithFields(id, CORE);
    }

    private File getFileWithFields(String id, String fields) {
        File file = null;
        try {
            file = driveService.files().get(id).setFields(fields).execute();
        } catch (IOException e) {
            processIAE("File with id = '" + id + "' does not exist!");
        }

        return file;
    }

    @Override
    public List<File> getAll() {
        List<File> result = new ArrayList<>();
        try {
            List<File> files = driveService.files().list()
                    .setFields("nextPageToken, files(" + CORE + ")")
                    .execute().getFiles();
            result.addAll(files);
        } catch (IOException e) {
            processGDE("Could not retrieve files");
        }
        return result;
    }

    @Override
    public List<File> findByParentId(String id) {
        List<File> result = new ArrayList<>();
        try {
            result.addAll(driveService.files().list()
                    .setQ("'" + id + "' in parents").setSpaces(APP_FOLDER)
                    .setFields("nextPageToken, files(" + CORE + ")").execute().getFiles());
        } catch (IOException e) {
            processIAE("Folder with id = '" + id + "' does not exist!");
        }
        return result;
    }

    @Override
    public File update(String parentId, String name) {
        validateName(name);
        checkIfExist(name, parentId);

        File file = getFileWithFields(parentId, "name");
        file.setName(name);

        try {
            driveService.files().update(parentId, file).setFields("name").execute();
        } catch (IOException e) {
            processGDE("IO error occured. Could not update " + name);
        }

        return getFileWithFields(parentId, CORE);
    }

    @Override
    public String upload(MultipartFile uploading, String folderId) {
        String fileName = uploading.getOriginalFilename();
        String returnFileName = fileName;

        validateName(fileName);
//        checkIfExist(fileName, folderId);

        fileName = new StringBuilder(TEMP).append("/resources/").append(fileName).toString();
        fileName = fileBuilder.saveFile(uploading);
        System.err.println("--------------------------");
        System.err.println(fileName);
        System.err.println("--------------------------");
        File fileMetadata = new File();

        java.io.File content = new java.io.File(fileName);
        fileMetadata.setName(content.getName());
        FileContent mediaContent = new FileContent(null, content);

        try {
            returnFileName = driveService.files().create(fileMetadata, mediaContent).setFields("id").execute().getId();
            content.delete();
            System.err.println("-------------------------");
            System.err.println("-------------------------");
            System.err.println("-------------------------");
            System.err.println("-------------------------");
            System.err.println("id: " + returnFileName);
            System.err.println("-------------------------");
            System.err.println("-------------------------");
            System.err.println("-------------------------");
            System.err.println("-------------------------");
            return returnFileName;
        } catch (IOException e) {
            LOGGER.error("Could not upload " + fileName, e);
            throw new GoogleDriveException("Could not upload " + fileName);
        }

    }

    //
    private void createTempCopy(String path, InputStream in) {
        try (FileOutputStream fos = new FileOutputStream(new java.io.File(path))) {
            byte[] buffer = new byte[1024];
            int len = in.read(buffer);
            while (len != -1) {
                fos.write(buffer, 0, len);
                len = in.read(buffer);
            }
        } catch (FileNotFoundException e) {
            processGDE("Couldnot upload " + path + ". File not found");
        } catch (IOException e) {
            processGDE("IO error occured while trying to make a temporary local copy at " + path);
        }
    }


    @Override
    public void download(String id, HttpServletResponse response) {
        try {
            driveService.files().get(id).executeMediaAndDownloadTo(response.getOutputStream());
        } catch (IOException e) {
            processGDE("Error occured while trying to download file with id = " + id);
        }
    }

    private void processIAE(String message) {
        LOGGER.error(message);
        throw new IllegalArgumentException(message);
    }

    private void processGDE(String message) {
        LOGGER.error(message);
        throw new GoogleDriveException(message);
    }
}