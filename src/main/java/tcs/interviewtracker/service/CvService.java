package tcs.interviewtracker.service;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import tcs.interviewtracker.exceptions.CvNotFoundException;
import tcs.interviewtracker.exceptions.CvStorageException;
import tcs.interviewtracker.properties.CvStorageProperties;

@Service
public class CvService {
    private final Path cvStorageLocation;

    public CvService(CvStorageProperties cvStorageProperties) {
        this.cvStorageLocation = Paths.get(cvStorageProperties.getUploadDirectory())
                .toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.cvStorageLocation);
        } catch (Exception e) {
            throw new CvStorageException("Could not create directory");
        }
    }

    public String storeCv(MultipartFile cv) {
        String fileName = StringUtils.cleanPath(cv.getOriginalFilename());
        try {
            if (fileName.contains("..")) {
                throw new CvStorageException("Invalid filename!");
            }

            Path targetLocation = this.cvStorageLocation.resolve(fileName);
            Files.copy(cv.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (Exception e) {
            throw new CvStorageException("Could not store file!");
        }
    }

    public Resource loadCvAsResource(String fileName) {
        try {
            Path filePath = this.cvStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new CvNotFoundException("Cv not found!");
            }
        } catch (MalformedURLException e) {
            throw new CvNotFoundException("Cv not found!");
        }
    }
}
