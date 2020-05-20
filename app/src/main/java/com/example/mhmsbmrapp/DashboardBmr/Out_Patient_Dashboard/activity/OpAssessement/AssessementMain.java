package com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity.OpAssessement;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mhmsbmrapp.AddPatientFolder.AddPatients;
import com.example.mhmsbmrapp.Login.MHPFlow;
import com.example.mhmsbmrapp.Login.SessionInformation;
import com.example.mhmsbmrapp.R;
import com.example.mhmsbmrapp.model.Assessment;
import com.example.mhmsbmrapp.model.Composition;
import com.example.mhmsbmrapp.utility.AssessmentUtility;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AssessementMain extends Fragment {


    Assessment assessment = new Assessment();

    String loginToken;
    String sessionToken;
    String userUUID;
    String userName;
    String orgUUID;
    String patientId;


    private String education = "education1";
    private String occupation = "occupation1";
    private String referredBy = "referredBy eight";
    private String reasonForReferral = "IQ Assessment";
    private String referralNote = "referralNote eight";
    private String languageTestedIn = "Kannada";
    private String backgroundInformation = "backgroundInformation eight";
    private String informant = "informant eight";
    private String informantRelationship = "informantRelationship eight";
    private String salientBehavioralObservation = "salientBehavioralObservation eight";
    private String impression = "impression eight";
    private String testScale = "testScale eight";
    private String testScore = "testScore eight";
    private String recommendation = "recommendation eight";
    private String reliability = "reliability eight";
    private String adequacy = "adequacy eight";
    private String assessorFullName = "assessorFullName eight";
    private String assessorQualification = "assessorQualification eight";
    private String supervisorFullName = "supervisorFullName eight";
    private String supervisorQualification = "getSupervisorQualification eight";


    String mheName;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.opassessementmain, container, false);




        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        System.out.println(sharedPreferences.getString("loginToken", ""));

        mheName = sharedPreferences.getString("mheName", "");
        loginToken = sharedPreferences.getString("loginToken", "");
        patientId = sharedPreferences.getString("patientId", "");
        try {
            Log.e("session info", "&&&&&&&&&&&&&&&&&&&&&&&&&&&");
            sessionToken = SessionInformation.sessionToken;
            userUUID = SessionInformation.userUUID;
            orgUUID = SessionInformation.orgUUID;
            userName = SessionInformation.userName;
            System.out.println(loginToken);
            Log.e("session info", "&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        }catch (Exception e){ e.printStackTrace(); }


        Button button = (Button) view.findViewById(R.id.assessmentSubmit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("inside onclick of submit therapy button");
                setValues();

                System.out.println("------------------------------");
                System.out.println(languageTestedIn);
                System.out.println(reasonForReferral);
                System.out.println(occupation);
                System.out.println(recommendation);
                System.out.println("------------------------------");

                goToAssessment();
            }
        });

        return view;
    }


    private void setValues() {
        EditText et = (EditText)getActivity().findViewById(R.id.asmtEducation);
        education = et.getText().toString().trim();
        assessment.setEducation(et.getText().toString().trim());

        et = (EditText)getActivity().findViewById(R.id.asmtOccupation);
        occupation = et.getText().toString().trim();
        assessment.setOccupation(et.getText().toString().trim());

        et = (EditText)getActivity().findViewById(R.id.referred_By);
        referredBy = et.getText().toString().trim();
        assessment.setReferredBy(et.getText().toString().trim());

        et = (EditText)getActivity().findViewById(R.id.referral_Note);
        referralNote = et.getText().toString().trim();
        assessment.setReferralNote(et.getText().toString().trim());

        Spinner spinner = (Spinner)getActivity().findViewById(R.id.reason_for_Referral);
        //reasonForReferral = spinner.getSelectedItem().toString().trim();

        et = (EditText)getActivity().findViewById(R.id.language_Tested_In);
        languageTestedIn = et.getText().toString().trim();
        assessment.setTestedLanguage(et.getText().toString().trim());

        et = (EditText)getActivity().findViewById(R.id.background_Information);
        backgroundInformation = et.getText().toString().trim();
        assessment.setBackgroundInformation(et.getText().toString().trim());

        et = (EditText)getActivity().findViewById(R.id.informant);
        informant = et.getText().toString().trim();
        assessment.setInformant(et.getText().toString().trim());

        et = (EditText)getActivity().findViewById(R.id.informant_Relationship);
        informantRelationship = et.getText().toString().trim();
        assessment.setInformantRelationship(et.getText().toString().trim());

        et = (EditText)getActivity().findViewById(R.id.salient_Behavioral_Obeservations);
        salientBehavioralObservation = et.getText().toString().trim();
        assessment.setBackgroundInformation(et.getText().toString().trim());

        et = (EditText)getActivity().findViewById(R.id.impressions);
        impression = et.getText().toString().trim();
        assessment.setImpression(et.getText().toString().trim());

        et = (EditText)getActivity().findViewById(R.id.test_Scales_Administered);
        testScale = et.getText().toString().trim();
        assessment.setTestScale(et.getText().toString().trim());

        et = (EditText)getActivity().findViewById(R.id.test_Score);
        testScore = et.getText().toString().trim();
        assessment.setTestScores(et.getText().toString().trim());

        et = (EditText)getActivity().findViewById(R.id.recommendation);
        recommendation = et.getText().toString().trim();
        assessment.setRecommendation(et.getText().toString().trim());

        et = (EditText)getActivity().findViewById(R.id.assessor_Full_Name);
        assessorFullName = et.getText().toString().trim();
        assessment.setAssessorName(et.getText().toString().trim());

        et = (EditText)getActivity().findViewById(R.id.assessor_Qualification);
        assessorQualification = et.getText().toString().trim();
        assessment.setAssessorQualification(et.getText().toString().trim());

        et = (EditText)getActivity().findViewById(R.id.supervisor_Full_Name);
        supervisorFullName = et.getText().toString().trim();
        assessment.setSupervisorName(et.getText().toString().trim());

        et = (EditText)getActivity().findViewById(R.id.supervisorQualification);
        supervisorQualification = et.getText().toString().trim();
        assessment.setSupervisorQualification(et.getText().toString().trim());

    }



    public void goToAssessment(){

        Thread thread = new Thread() {
            public void run() {
                String values1[] = {userName, salientBehavioralObservation, backgroundInformation, testScale, testScore, impression, recommendation, languageTestedIn, informant, informantRelationship, reliability, adequacy, assessorFullName, assessorQualification, supervisorFullName, supervisorQualification, mheName};
                String values12[] = {userName, reasonForReferral, referralNote, referredBy, mheName};
                System.out.println(values1.length);

                List<Composition> list = new ArrayList<Composition>();

                list.add(new AssessmentUtility().createCompositionEHRC_Psychological_assessmentv0(values1, loginToken, sessionToken, userUUID, orgUUID, patientId));
                list.add(new AssessmentUtility().createCompositionEHRC_Service_requestv0(values12, loginToken, sessionToken, userUUID, orgUUID, patientId));

                for(int i=0;i<list.size();i++) {
                    System.out.println(list.get(i).toString());
                }

                JSONObject object = new AssessmentUtility().saveAllAssessmentCompositions(list,loginToken, sessionToken, orgUUID, patientId, userUUID);
                System.out.println("\n\n\n\n\n----------------------------------------------------------------");
                System.out.println(object.toString());
            }
        };
        thread.start();

    }


}


