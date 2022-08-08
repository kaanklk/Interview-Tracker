package tcs.interviewtracker.persistence;

import java.util.ArrayList;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
public class Task {
    String areaOfResponsibility;
    String subtask;
    String kpi;

    public String getAreaOfResponsibility() {
        return areaOfResponsibility;
    }

    public void setAreaOfResponsibility(String areaOfResponsibility) {
        this.areaOfResponsibility = areaOfResponsibility;
    }

    public String getSubtask() {
        return subtask;
    }

    public void setSubTask(String subtask) {
        this.subtask = subtask;
    }

    public String getKpi() {
        return kpi;
    }

    public void setKpi(String kpi) {
        this.kpi = kpi;
    }

}
