package com.example.mhmsbmrapp.model;

public class Therapy {

    private String education;
    private String occupation;

    private String sessionNumber;
    private String typeOfTherapy;
    private String therapyMethod;
    private String objectiveSession;
    private String therapyTechnique;
    private String keyIssue;
    private String psychiatricDiagnosis;
    private String followUpDate;
    private String followUpPlan;
    private String therapistName;
    private String therapistQualification;
    private String therapistObservation;
    private String supervisorName;
    private String supervisorQualification;


    public void setEducation(String education) { this.education = education; }

    public String getEducation() { return education; }

    public void setOccupation(String occupation) { this.occupation = occupation; }

    public String getOccupation() { return occupation; }

    public void setSessionNumber(String sessionNumber) {
        this.sessionNumber = sessionNumber;
    }

    public void setTypeOfTherapy(String typeOfTherapy) {
        this.typeOfTherapy = typeOfTherapy;
    }

    public void setTherapyMethod(String therapyMethod) {
        this.therapyMethod = therapyMethod;
    }

    public void setObjectiveSession(String objectiveSession) {
        this.objectiveSession = objectiveSession;
    }

    public void setTherapyTechnique(String therapyTechnique) {
        this.therapyTechnique = therapyTechnique;
    }

    public void setKeyIssue(String keyIssue) {
        this.keyIssue = keyIssue;
    }

    public void setPsychiatricDiagnosis(String psychiatricDiagnosis) {
        this.psychiatricDiagnosis = psychiatricDiagnosis;
    }

    public void setFollowUpDate(String followUpDate) {
        this.followUpDate = followUpDate;
    }

    public void setFollowUpPlan(String followUpPlan) {
        this.followUpPlan = followUpPlan;
    }

    public void setTherapistName(String therapistName) {
        this.therapistName = therapistName;
    }

    public void setTherapistQualification(String therapistQualification) {
        this.therapistQualification = therapistQualification;
    }

    public void setTherapistObservation(String therapistObservation) {
        this.therapistObservation = therapistObservation;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public void setSupervisorQualification(String supervisorQualification) {
        this.supervisorQualification = supervisorQualification;
    }

    public String getSessionNumber() {
        return sessionNumber;
    }

    public String getTypeOfTherapy() {
        return typeOfTherapy;
    }

    public String getTherapyMethod() {
        return therapyMethod;
    }

    public String getObjectiveSession() {
        return objectiveSession;
    }

    public String getTherapyTechnique() {
        return therapyTechnique;
    }

    public String getKeyIssue() {
        return keyIssue;
    }

    public String getPsychiatricDiagnosis() {
        return psychiatricDiagnosis;
    }

    public String getFollowUpDate() {
        return followUpDate;
    }

    public String getFollowUpPlan() {
        return followUpPlan;
    }

    public String getTherapistName() {
        return therapistName;
    }

    public String getTherapistQualification() {
        return therapistQualification;
    }

    public String getTherapistObservation() {
        return therapistObservation;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public String getSupervisorQualification() {
        return supervisorQualification;
    }

    @Override
    public String toString() {
        return "Therapy{" +
                "sessionNumber='" + sessionNumber + '\'' +
                ", typeOfTherapy='" + typeOfTherapy + '\'' +
                ", therapyMethod='" + therapyMethod + '\'' +
                ", objectiveSession='" + objectiveSession + '\'' +
                ", therapyTechnique='" + therapyTechnique + '\'' +
                ", keyIssue='" + keyIssue + '\'' +
                ", psychiatricDiagnosis='" + psychiatricDiagnosis + '\'' +
                ", followUpDate='" + followUpDate + '\'' +
                ", followUpPlan='" + followUpPlan + '\'' +
                ", therapistName='" + therapistName + '\'' +
                ", therapistQualification='" + therapistQualification + '\'' +
                ", therapistObservation='" + therapistObservation + '\'' +
                ", supervisorName='" + supervisorName + '\'' +
                ", supervisorQualification='" + supervisorQualification + '\'' +
                '}';
    }
}
