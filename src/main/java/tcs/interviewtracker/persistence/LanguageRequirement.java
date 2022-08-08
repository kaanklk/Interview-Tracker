package tcs.interviewtracker.persistence;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
public class LanguageRequirement {
    String language;
    String requiredLevel;
    String importance;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getRequiredLevel() {
        return requiredLevel;
    }

    public void setRequiredLevel(String requiredLevel) {
        this.requiredLevel = requiredLevel;
    }

    public String getImportance() {
        return importance;
    }

    public void setImportance(String importance) {
        this.importance = importance;
    }

}
