package com.example.mhmsbmrapp.model;

public class OPBmr {

    private String complaints;
    private String duration;
    private String historyAndMentalStatus;
    private String illnessSummary;
    private String diagnosisType;
    private String icdDescription;
    private String icdCode;
    private String improvementStatus;
    private String medicineName;
    private String dosage;
    private String dosingTime;
    private String medDuration;
    private String durationType;
    private String remarks;
    private String treatmentInstruction;

    private String followUpDate;

    private String referredDoctor;
    private String referredHospital;
    private String comorbidDiagnosis;
    private String additionalTreatmentDetails;
    private String reasonForReferral;


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

    public String getHistoryAndMentalStatus() {
        return historyAndMentalStatus;
    }

    public void setHistoryAndMentalStatus(String historyAndMentalStatus) {
        this.historyAndMentalStatus = historyAndMentalStatus;
    }

    public String getIllnessSummary() {
        return illnessSummary;
    }

    public void setIllnessSummary(String illnessSummary) {
        this.illnessSummary = illnessSummary;
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

    public String getImprovementStatus() {
        return improvementStatus;
    }

    public void setImprovementStatus(String improvementStatus) {
        this.improvementStatus = improvementStatus;
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

    public String getTreatmentInstruction() {
        return treatmentInstruction;
    }

    public void setTreatmentInstruction(String treatmentInstruction) {
        this.treatmentInstruction = treatmentInstruction;
    }

    public String getFollowUpDate() {
        return followUpDate;
    }

    public void setFollowUpDate(String followUpDate) {
        this.followUpDate = followUpDate;
    }

    public String getReferredDoctor() {
        return referredDoctor;
    }

    public void setReferredDoctor(String referredDoctor) {
        this.referredDoctor = referredDoctor;
    }

    public String getReferredHospital() {
        return referredHospital;
    }

    public void setReferredHospital(String referredHospital) {
        this.referredHospital = referredHospital;
    }

    public String getComorbidDiagnosis() {
        return comorbidDiagnosis;
    }

    public void setComorbidDiagnosis(String comorbidDiagnosis) {
        this.comorbidDiagnosis = comorbidDiagnosis;
    }

    public String getAdditionalTreatmentDetails() {
        return additionalTreatmentDetails;
    }

    public void setAdditionalTreatmentDetails(String additionalTreatmentDetails) {
        this.additionalTreatmentDetails = additionalTreatmentDetails;
    }

    public String getReasonForReferral() {
        return reasonForReferral;
    }

    public void setReasonForReferral(String reasonForReferral) {
        this.reasonForReferral = reasonForReferral;
    }


    @Override
    public String toString() {
        return "OPBmr{" +
                "complaints='" + complaints + '\'' +
                ", duration='" + duration + '\'' +
                ", historyAndMentalStatus='" + historyAndMentalStatus + '\'' +
                ", illnessSummary='" + illnessSummary + '\'' +
                ", diagnosisType='" + diagnosisType + '\'' +
                ", icdDescription='" + icdDescription + '\'' +
                ", icdCode='" + icdCode + '\'' +
                ", medicineName='" + medicineName + '\'' +
                ", dosage='" + dosage + '\'' +
                ", dosingTime='" + dosingTime + '\'' +
                ", medDuration='" + medDuration + '\'' +
                ", durationType='" + durationType + '\'' +
                ", remarks='" + remarks + '\'' +
                ", treatmentInstruction='" + treatmentInstruction + '\'' +
                ", followUpDate='" + followUpDate + '\'' +
                ", referredDoctor='" + referredDoctor + '\'' +
                ", referredHospital='" + referredHospital + '\'' +
                ", comorbidDiagnosis='" + comorbidDiagnosis + '\'' +
                ", additionalTreatmentDetails='" + additionalTreatmentDetails + '\'' +
                ", reasonForReferral='" + reasonForReferral + '\'' +
                '}';
    }
}
