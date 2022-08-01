package tcs.interviewtracker.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties(prefix = "cv")
@Data
public class CvStorageProperties {
    private String uploadDirectory;
}
