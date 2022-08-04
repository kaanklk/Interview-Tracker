package tcs.interviewtracker.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EducationDTO {
    /*
        "start": "2019-09-01 00:00:00",
        "end": "2023-01-10 00:00:00",
        "institution": "BME",
        "information": "Computer engineering Bsc"
     */

     private String start;

     private String end;

     private String institution;

     private String information;
}
