package com.example.mhmsbmrapp;

import com.example.mhmsbmrapp.Login.SessionInformation;
import com.example.mhmsbmrapp.model.Composition;
import com.example.mhmsbmrapp.model.RestraintMonitoring;
import com.example.mhmsbmrapp.utility.AssessmentUtility;
import com.example.mhmsbmrapp.utility.RestraintMonitoringUtility;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    RestraintMonitoring rm = new RestraintMonitoring();

    private String loginToken = " eyJEZXZlbG9wZWQgQnkiOiJlLUhlYWx0aCBSZXNlYXJjaCBDZW50ZXIsIElJSVQgQmFuZ2Fsb3JlIiwiSG9zdCI6Ikthcm5hdGFrYSBNZW50YWwgSGVhbHRoIE1hbmFnZW1lbnQgU3lzdGVtIiwidHlwIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJwcm9mZXNzaW9uIjoiTUhNU1BzeWNoaWF0cmlzdCIsInN1YiI6Ik1ITVMgU2VjdXJpdHkgVG9rZW4iLCJsYXN0TG9naW5PcmdJZCI6ImEyMWI4ODVlLTJmM2EtNDQyNS04YjViLTBkMjc0YjQyYWYyNiIsInNlc3Npb25FbmRUaW1lIjoxNTkwMDA3NjYzLCJpc3MiOiJLTUhNUyIsInNlc3Npb25TdGFydFRpbWUiOjE1ODk5NjQ0NjMsInNlc3Npb25JZCI6ImY5MTM2NzM1LTU3MzMtNGQ3Yy1hODU0LTQ5ZmNlYjdjNzMzMSIsInVzZXJOYW1lIjoicHJhc2hhbnQiLCJvcmdVVUlEIjoiNGNjNzQyODAtZWZlNS00MDE2LWI0MWUtZjI5NDcyYTRlYzEyIiwibmJmIjoxNTg5OTY0NDYzLCJvcmdSb2xlIjoiTUhQcm9mZXNzaW9uYWwiLCJzZXNzaW9uVG9rZW4iOiJTZXNzaW9uSWQ6MTcyLjMxLjUuMTMjcHJhc2hhbnQ6NGNjNzQyODAtZWZlNS00MDE2LWI0MWUtZjI5NDcyYTRlYzEyOk1ITVM6TUhQcm9mZXNzaW9uYWwjMTU4OTk2NDQ2MzM3OSMxNTA4ODEwNjIwIzE1ODIiLCJwZXJzb25JZCI6IjkyNWQ2N2NkLTdkM2MtNDA3OC04OWZiLTY5NjNjNDdiNDk2YSIsInVzZXJVVUlEIjoiNzc1YjhjM2UtNjc0Mi00YjMwLWI0NDMtYzdkNmFhNmVjNGFjIiwiZXhwIjoxNTkwMDAwNDYzLCJpYXQiOjE1ODk5NjQ0NjN9._9S-XcJVNxIQWZQsTzBWK7PtYkxf9gdm6QXXkPoBAPc";
    private String sessionToken = "SessionId:172.31.5.13#prashant:4cc74280-efe5-4016-b41e-f29472a4ec12:MHMS:MHProfessional#1589964463379#1508810620#1582";
    private String userUUID = "775b8c3e-6742-4b30-b443-c7d6aa6ec4ac";
    private String orgUUID = "4cc74280-efe5-4016-b41e-f29472a4ec12";
    private String patientId = "23e6580f-57a9-41ab-93a6-7b5a4418ede6";
    String mheName = "psm321op";




    private String nominatedRepresentative = "nominatedRepresentativeName ONE";
    //private String nameOfMHP = "psm321op";
    private String inChargePsychiatrist = "inChargePsychiatrist";
    private String settings = "Out Patient";
    private String informedToNR = "true";
    private String psychiatricDiagnosis = "psychiatricDiagnosis";
    private String duration = "13";
    private String durationType = "Months";
    private String startDate = "startDate";
    private String startTime = "startTime";
    private String endDate = "endDate";
    private String endTime = "endTime";
    private String reasons = "reasons";
    private String injuries = "Yes";
    private String limbIschaemia = "Yes";
    private String others = "others";
    private String monitoringDate = "17/04/2020";
    private String pulse = "18";
    private String temperature = "19";
    private String respiratoryRate = "20";
    private String rmInjuries = "rmInjuries";
    private String bloodSupplyToLimb = "bloodSupplyToLimb";
    private String breathingProblem = "breathingProblem";


    String monitoringV3[] = {breathingProblem, rmInjuries, bloodSupplyToLimb, pulse, respiratoryRate, temperature, monitoringDate};
    String valuesV3[] = {injuries, limbIschaemia, others, reasons, duration, durationType, nominatedRepresentative, informedToNR, inChargePsychiatrist, settings};
    String valuesDiag[] = {psychiatricDiagnosis};



    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test1() {
        SessionInformation.userUUID = userUUID;
        SessionInformation.orgUUID = orgUUID;
        SessionInformation.sessionToken = sessionToken;

        setValues();

            List<Composition> list = new ArrayList<>();
            list.add(new RestraintMonitoringUtility().createEHRC_Proceduresv3(rm, loginToken, mheName, patientId));
            list.add(new RestraintMonitoringUtility().createDiagnosisTemplate(rm, loginToken, mheName, patientId));
            list.add(new RestraintMonitoringUtility().createCompositionEHRC_Restraint_monitoringv3(rm, loginToken, mheName, patientId));

            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n" + (new RestraintMonitoringUtility().saveAllAssessmentCompositions(list, loginToken, patientId)));


    }

    public void setValues(){
        rm.setNominatedRepresentativeName(nominatedRepresentative);
        rm.setNameOfMHE(mheName);
        rm.setInchargePsychiatrist(inChargePsychiatrist);
        rm.setSetting(settings);
        rm.setInformedToNR(informedToNR);
        rm.setPsychiatricDiagnosis(psychiatricDiagnosis);
        rm.setDuration(duration);
        rm.setType(durationType);
        rm.setReason(reasons);
        rm.setInjuries(injuries);
        rm.setLimbIschaemia(limbIschaemia);
        rm.setOthers(others);
        rm.setMonitoringDate(monitoringDate);
        rm.setPulse(pulse);
        rm.setTemperature(temperature);
        rm.setRespiratoryRate(respiratoryRate);
        rm.setInjuries2(rmInjuries);
        rm.setNoBloodSupply(bloodSupplyToLimb);
        rm.setBreathingProblem(breathingProblem);


        rm.setStartDate("2020-05-20T09:49:12.014Z");
    }
}