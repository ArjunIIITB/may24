package com.example.mhmsbmrapp.model;

public class Assessment {


    private String education;
    private String occupation;
    private String referredBy;
    private String referralNote;
    private String reasonForReferral;
    private String informantRelationship;
    private String assessorQualification;
    private String supervisorQualification;

    private String testedLanguage;
    private String backgroundInformation;
    private String salientBehaviouralObservation;
    private String impression;
    private String testScale;
    private String testScores;
    private String recommendation;
    private String reliability;
    private String adequacy;
    private String informant;
    private String assessorName;
    private String supervisorName;


    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getReferredBy() {
        return referredBy;
    }

    public void setReferredBy(String referredBy) {
        this.referredBy = referredBy;
    }

    public String getReferralNote() {
        return referralNote;
    }

    public void setReferralNote(String referralNote) {
        this.referralNote = referralNote;
    }

    public String getReasonForReferral() {
        return reasonForReferral;
    }

    public void setReasonForReferral(String reasonForReferral) {
        this.reasonForReferral = reasonForReferral;
    }

    public String getInformantRelationship() {
        return informantRelationship;
    }

    public void setInformantRelationship(String informantRelationship) {
        this.informantRelationship = informantRelationship;
    }

    public String getAssessorQualification() {
        return assessorQualification;
    }

    public void setAssessorQualification(String assessorQualification) {
        this.assessorQualification = assessorQualification;
    }

    public String getSupervisorQualification() {
        return supervisorQualification;
    }

    public void setSupervisorQualification(String supervisorQualification) {
        this.supervisorQualification = supervisorQualification;
    }

    public String getTestedLanguage() {
        return testedLanguage;
    }

    public void setTestedLanguage(String testedLanguage) {
        this.testedLanguage = testedLanguage;
    }

    public String getBackgroundInformation() {
        return backgroundInformation;
    }

    public void setBackgroundInformation(String backgroundInformation) {
        this.backgroundInformation = backgroundInformation;
    }

    public String getSalientBehaviouralObservation() {
        return salientBehaviouralObservation;
    }

    public void setSalientBehaviouralObservation(String salientBehaviouralObservation) {
        this.salientBehaviouralObservation = salientBehaviouralObservation;
    }

    public String getImpression() {
        return impression;
    }

    public void setImpression(String impression) {
        this.impression = impression;
    }

    public String getTestScale() {
        return testScale;
    }

    public void setTestScale(String testScale) {
        this.testScale = testScale;
    }

    public String getTestScores() {
        return testScores;
    }

    public void setTestScores(String testScores) {
        this.testScores = testScores;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public String getReliability() {
        return reliability;
    }

    public void setReliability(String reliability) {
        this.reliability = reliability;
    }

    public String getAdequacy() {
        return adequacy;
    }

    public void setAdequacy(String adequacy) {
        this.adequacy = adequacy;
    }

    public String getInformant() {
        return informant;
    }

    public void setInformant(String informant) {
        this.informant = informant;
    }

    public String getAssessorName() {
        return assessorName;
    }

    public void setAssessorName(String assessorName) {
        this.assessorName = assessorName;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    @Override
    public String toString() {
        return "Assessment{" +
                "education='" + education + '\'' +
                ", occupation='" + occupation + '\'' +
                ", referredBy='" + referredBy + '\'' +
                ", referralNote='" + referralNote + '\'' +
                ", reasonForReferral='" + reasonForReferral + '\'' +
                ", informantRelationship='" + informantRelationship + '\'' +
                ", assessorQualification='" + assessorQualification + '\'' +
                ", supervisorQualification='" + supervisorQualification + '\'' +
                ", testedLanguage='" + testedLanguage + '\'' +
                ", backgroundInformation='" + backgroundInformation + '\'' +
                ", salientBehaviouralObservation='" + salientBehaviouralObservation + '\'' +
                ", impression='" + impression + '\'' +
                ", testScale='" + testScale + '\'' +
                ", testScores='" + testScores + '\'' +
                ", recommendation='" + recommendation + '\'' +
                ", reliability='" + reliability + '\'' +
                ", adequacy='" + adequacy + '\'' +
                ", informant='" + informant + '\'' +
                ", assessorName='" + assessorName + '\'' +
                ", supervisorName='" + supervisorName + '\'' +
                '}';
    }
}