package com.example.mhmsbmrapp.model;

public class RestraintMonitoring {



    private String startDate;
    private String startTime;
    private String endDate;
    private String endTime;




    private String nominatedRepresentativeName;
    private String nameOfMHE;
    private String inchargePsychiatrist;
    private String setting;
    private String informedToNR;
    private String psychiatricDiagnosis;
    private String type;
    private String duration;
    private String date;// only for history
    private String reason;
    private String injuries;
    private String limbIschaemia;
    private String others;
    private String monitoringDate;
    private String respiratoryRate;
    private String pulse;
    private String breathingProblem;
    private String temperature;
    private String noBloodSupply;
    private String injuries2;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getNominatedRepresentativeName() {
        return nominatedRepresentativeName;
    }

    public void setNominatedRepresentativeName(String nominatedRepresentativeName) {
        this.nominatedRepresentativeName = nominatedRepresentativeName;
    }

    public String getNameOfMHE() {
        return nameOfMHE;
    }

    public void setNameOfMHE(String nameOfMHE) {
        this.nameOfMHE = nameOfMHE;
    }

    public String getInchargePsychiatrist() {
        return inchargePsychiatrist;
    }

    public void setInchargePsychiatrist(String inchargePsychiatrist) {
        this.inchargePsychiatrist = inchargePsychiatrist;
    }

    public String getSetting() {
        return setting;
    }

    public void setSetting(String setting) {
        this.setting = setting;
    }

    public String getInformedToNR() {
        return informedToNR;
    }

    public void setInformedToNR(String informedToNR) {
        this.informedToNR = informedToNR;
    }

    public String getPsychiatricDiagnosis() {
        return psychiatricDiagnosis;
    }

    public void setPsychiatricDiagnosis(String psychiatricDiagnosis) {
        this.psychiatricDiagnosis = psychiatricDiagnosis;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getInjuries() {
        return injuries;
    }

    public void setInjuries(String injuries) {
        this.injuries = injuries;
    }

    public String getLimbIschaemia() {
        return limbIschaemia;
    }

    public void setLimbIschaemia(String limbIschaemia) {
        this.limbIschaemia = limbIschaemia;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public String getMonitoringDate() {
        return monitoringDate;
    }

    public void setMonitoringDate(String monitoringDate) {
        this.monitoringDate = monitoringDate;
    }

    public String getRespiratoryRate() {
        return respiratoryRate;
    }

    public void setRespiratoryRate(String respiratoryRate) {
        this.respiratoryRate = respiratoryRate;
    }

    public String getPulse() {
        return pulse;
    }

    public void setPulse(String pulse) {
        this.pulse = pulse;
    }

    public String getBreathingProblem() {
        return breathingProblem;
    }

    public void setBreathingProblem(String breathingProblem) {
        this.breathingProblem = breathingProblem;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getNoBloodSupply() {
        return noBloodSupply;
    }

    public void setNoBloodSupply(String noBloodSupply) {
        this.noBloodSupply = noBloodSupply;
    }

    public String getInjuries2() {
        return injuries2;
    }

    public void setInjuries2(String injuries2) {
        this.injuries2 = injuries2;
    }

    @Override
    public String toString() {
        return "RestraintMonitoring{" +
                "nominatedRepresentativeName='" + nominatedRepresentativeName + '\'' +
                ", nameOfMHE='" + nameOfMHE + '\'' +
                ", inchargePsychiatrist='" + inchargePsychiatrist + '\'' +
                ", setting='" + setting + '\'' +
                ", informedToNR='" + informedToNR + '\'' +
                ", psychiatricDiagnosis='" + psychiatricDiagnosis + '\'' +
                ", type='" + type + '\'' +
                ", duration='" + duration + '\'' +
                ", date='" + date + '\'' +
                ", reason='" + reason + '\'' +
                ", injuries='" + injuries + '\'' +
                ", limbIschaemia='" + limbIschaemia + '\'' +
                ", others='" + others + '\'' +
                ", monitoringDate='" + monitoringDate + '\'' +
                ", respiratoryRate='" + respiratoryRate + '\'' +
                ", pulse='" + pulse + '\'' +
                ", breathingProblem='" + breathingProblem + '\'' +
                ", temperature='" + temperature + '\'' +
                ", noBloodSupply='" + noBloodSupply + '\'' +
                ", injuries2='" + injuries2 + '\'' +
                '}';
    }
}
