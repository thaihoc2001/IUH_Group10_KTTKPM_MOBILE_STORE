package com.service.shopPhone.utility;


import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.UUID;

import javax.annotation.PostConstruct;

@Service
@Slf4j
public class FirebaseUpload{
    
    private StorageOptions storageOptions;
    private String bucketName;
    private String projectId;

    public FirebaseUpload() {
    }

    @PostConstruct
    private void initializeFirebase() throws Exception {
        bucketName = "tkpm-shop-phone.appspot.com";
        projectId = "tkpm-shop-phone";
        FileInputStream serviceAccount =
                new FileInputStream("firebase.json");

        this.storageOptions = StorageOptions.newBuilder()
                .setProjectId(projectId)
                .setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();

    }

    private String uploadFile(File file, String fileName) throws IOException {
        BlobId blobId = BlobId.of("tkpm-shop-phone.appspot.com", fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();
        // Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("downloaded private key JSON file path"));
        Storage storage = this.storageOptions.getService();
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));
        return String.format("https://firebasestorage.googleapis.com/v0/b/tkpm-shop-phone.appspot.com/o/%s?alt=media&token=%s", URLEncoder.encode(fileName, StandardCharsets.UTF_8), "ktpm");
    }

    private File convertToFile(MultipartFile multipartFile, String fileName) throws IOException {
        File tempFile = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(multipartFile.getBytes());
            fos.close();
        }
        return tempFile;
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    public String upload(MultipartFile multipartFile) {

        try {
            String fileName = multipartFile.getOriginalFilename();
            fileName = UUID.randomUUID().toString().concat(this.getExtension(fileName));

            File file = this.convertToFile(multipartFile, fileName);
            String TEMP_URL = this.uploadFile(file, fileName);
            file.delete();
            return TEMP_URL;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
