package tcs.interviewtracker.DTOs;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TimeslotDTO {
    
    private UUID uuid;

    private String start;

    private String end;

}
