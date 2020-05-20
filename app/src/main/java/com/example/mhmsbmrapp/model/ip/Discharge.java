package com.example.mhmsbmrapp.model.ip;

public class Discharge {
    private String typeOfDischarge;
    private String conditionAtDischarge;
    private String conditionDescription;
    private String medicine;
    private String dosage;
    private String dosingTime;
    private String duration;
    private String durationType;
    private String remarks;
    private String followUpRecommendation;
    private String followUpDate;
    private String nameOfDoctor;
    private String designation;
    private String dischargeDate;
    private String dischargeTime;
    private String am_pm;

    public String getTypeOfDischarge() {
        return typeOfDischarge;
    }

    public void setTypeOfDischarge(String typeOfDischarge) {
        this.typeOfDischarge = typeOfDischarge;
    }

    public String getConditionAtDischarge() {
        return conditionAtDischarge;
    }

    public void setConditionAtDischarge(String conditionAtDischarge) {
        this.conditionAtDischarge = conditionAtDischarge;
    }

    public String getConditionDescription() {
        return conditionDescription;
    }

    public void setConditionDescription(String conditionDescription) {
        this.conditionDescription = conditionDescription;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
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

    public void setDosingTime(String disongTime) {
        this.dosingTime = disongTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
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

    public String getFollowUpRecommendation() {
        return followUpRecommendation;
    }

    public void setFollowUpRecommendation(String followUpRecommendation) {
        this.followUpRecommendation = followUpRecommendation;
    }

    public String getFollowUpDate() {
        return followUpDate;
    }

    public void setFollowUpDate(String followUpDate) {
        this.followUpDate = followUpDate;
    }

    public String getNameOfDoctor() {
        return nameOfDoctor;
    }

    public void setNameOfDoctor(String nameOfDoctor) {
        this.nameOfDoctor = nameOfDoctor;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(String dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public String getDischargeTime() {
        return dischargeTime;
    }

    public void setDischargeTime(String dischargeTime) {
        this.dischargeTime = dischargeTime;
    }

    public String getAm_pm() {
        return am_pm;
    }

    public void setAm_pm(String am_pm) {
        this.am_pm = am_pm;
    }


    @Override
    public String toString() {
        return "Discharge{" +
                "typeOfDischarge='" + typeOfDischarge + '\'' +
                ", conditionAtDischarge='" + conditionAtDischarge + '\'' +
                ", conditionDescription='" + conditionDescription + '\'' +
                ", medicine='" + medicine + '\'' +
                ", dosage='" + dosage + '\'' +
                ", disongTime='" + dosingTime + '\'' +
                ", duration='" + duration + '\'' +
                ", durationType='" + durationType + '\'' +
                ", remarks='" + remarks + '\'' +
                ", followUpRecommendation='" + followUpRecommendation + '\'' +
                ", followUpDate='" + followUpDate + '\'' +
                ", nameOfDoctor='" + nameOfDoctor + '\'' +
                ", designation='" + designation + '\'' +
                ", dischargeDate='" + dischargeDate + '\'' +
                ", dischargeTime='" + dischargeTime + '\'' +
                ", am_pm='" + am_pm + '\'' +
                '}';
    }
}
