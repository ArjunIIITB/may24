package com.example.mhmsbmrapp.model.ip;

public class IPBmr {

    //Identification Mark
    private String identificationMarks;
    //Section of Admission
    private String sectionOfAdmission;
    private String nextAssessmentDate;//today_date::7dayslater_date
    //Clinical Profile
    private String complaints;
    private String duration;
    private String historyOfIllness;
    private String summaryOfVitals;
    private String summaryOfGenPhyExam;
    private String summaryOfMenStatusExam;
    private String summaryOfInvestigation;
    private String getSummaryOfInvestigationFile;
    //Diagnosis
    private String diagnosisType;
    private String icdDescription;
    private String icdCode;
    //Course in Hospital (Treatment and Progress)
    private String courseInHospital;
    private String medicineName;
    private String dosage;
    private String dosingTime;
    private String medDuration;
    private String durationType;
    private String remarks;
    private String pastPrescription;
    private String pastPrescriptionFile;


    public String getIdentificationMarks() {
        return identificationMarks;
    }

    public void setIdentificationMarks(String identificationMarks) {
        this.identificationMarks = identificationMarks;
    }

    public String getSectionOfAdmission() {
        return sectionOfAdmission;
    }

    public void setSectionOfAdmission(String sectionOfAdmission) {
        this.sectionOfAdmission = sectionOfAdmission;
    }

    public String getComplaints() {
        return complaints;
    }

    public void setComplaints(String complaints) {
        this.complaints = complaints;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getHistoryOfIllness() {
        return historyOfIllness;
    }

    public void setHistoryOfIllness(String historyOfIllness) {
        this.historyOfIllness = historyOfIllness;
    }

    public String getSummaryOfVitals() {
        return summaryOfVitals;
    }

    public void setSummaryOfVitals(String summaryOfVitals) {
        this.summaryOfVitals = summaryOfVitals;
    }

    public String getSummaryOfGenPhyExam() {
        return summaryOfGenPhyExam;
    }

    public void setSummaryOfGenPhyExam(String summaryOfGenPhyExam) {
        this.summaryOfGenPhyExam = summaryOfGenPhyExam;
    }

    public String getSummaryOfMenStatusExam() {
        return summaryOfMenStatusExam;
    }

    public void setSummaryOfMenStatusExam(String summaryOfMenStatusExam) {
        this.summaryOfMenStatusExam = summaryOfMenStatusExam;
    }

    public String getSummaryOfInvestigation() {
        return summaryOfInvestigation;
    }

    public void setSummaryOfInvestigation(String summaryOfInvestigation) {
        this.summaryOfInvestigation = summaryOfInvestigation;
    }

    public String getGetSummaryOfInvestigationFile() {
        return getSummaryOfInvestigationFile;
    }

    public void setGetSummaryOfInvestigationFile(String getSummaryOfInvestigationFile) {
        this.getSummaryOfInvestigationFile = getSummaryOfInvestigationFile;
    }

    public String getDiagnosisType() {
        return diagnosisType;
    }

    public void setDiagnosisType(String diagnosisType) {
        this.diagnosisType = diagnosisType;
    }

    public String getIcdDescription() {
        return icdDescription;
    }

    public void setIcdDescription(String icdDescription) {
        this.icdDescription = icdDescription;
    }

    public String getIcdCode() {
        return icdCode;
    }

    public void setIcdCode(String icdCode) {
        this.icdCode = icdCode;
    }

    public String getCourseInHospital() {
        return courseInHospital;
    }

    public void setCourseInHospital(String courseInHospital) {
        this.courseInHospital = courseInHospital;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getDosingTime() {
        return dosingTime;
    }

    public void setDosingTime(String dosingTime) {
        this.dosingTime = dosingTime;
    }

    public String getMedDuration() {
        return medDuration;
    }

    public void setMedDuration(String medDuration) {
        this.medDuration = medDuration;
    }

    public String getDurationType() {
        return durationType;
    }

    public void setDurationType(String durationType) {
        this.durationType = durationType;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getPastPrescription() {
        return pastPrescription;
    }

    public void setPastPrescription(String pastPrescription) {
        this.pastPrescription = pastPrescription;
    }

    public String getPastPrescriptionFile() {
        return pastPrescriptionFile;
    }

    public void setPastPrescriptionFile(String pastPrescriptionFile) {
        this.pastPrescriptionFile = pastPrescriptionFile;
    }

    @Override
    public String toString() {
        return "IPBmr{" +
                "identificationMarks='" + identificationMarks + '\'' +
                ", sectionOfAdmission='" + sectionOfAdmission + '\'' +
                ", complaints='" + complaints + '\'' +
                ", duration='" + duration + '\'' +
                ", historyOfIllness='" + historyOfIllness + '\'' +
                ", summaryOfVitals='" + summaryOfVitals + '\'' +
                ", summaryOfGenPhyExam='" + summaryOfGenPhyExam + '\'' +
                ", summaryOfMenStatusExam='" + summaryOfMenStatusExam + '\'' +
                ", summaryOfInvestigation='" + summaryOfInvestigation + '\'' +
                ", getSummaryOfInvestigationFile='" + getSummaryOfInvestigationFile + '\'' +
                ", diagnosisType='" + diagnosisType + '\'' +
                ", icdDescription='" + icdDescription + '\'' +
                ", icdCode='" + icdCode + '\'' +
                ", courseInHospital='" + courseInHospital + '\'' +
                ", medicineName='" + medicineName + '\'' +
                ", dosage='" + dosage + '\'' +
                ", dosingTime='" + dosingTime + '\'' +
                ", medDuration='" + medDuration + '\'' +
                ", durationType='" + durationType + '\'' +
                ", remarks='" + remarks + '\'' +
                ", pastPrescription='" + pastPrescription + '\'' +
                ", pastPrescriptionFile='" + pastPrescriptionFile + '\'' +
                '}';
    }

}
