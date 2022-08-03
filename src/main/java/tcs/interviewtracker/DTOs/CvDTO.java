package tcs.interviewtracker.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CvDTO {

    private String fileName;
    private String fileUrl;
    private String fileFormat;
    private Long size;

}
