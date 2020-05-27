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
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mhmsbmrapp.AddPatientFolder.AddPatients;
import com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.Success;
import com.example.mhmsbmrapp.Login.MHPFlow;
import com.example.mhmsbmrapp.Login.SessionInformation;
import com.example.mhmsbmrapp.R;
import com.example.mhmsbmrapp.model.Assessment;
import com.example.mhmsbmrapp.model.Composition;
import com.example.mhmsbmrapp.utility.AssessmentUtility;
import com.example.mhmsbmrapp.utility.RestraintMonitoringUtility;

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
                boolean ret = setValues();
                if(ret == false)
                    return;

                System.out.println("------------------------------");
                System.out.println(assessment.getReasonForReferral());
                System.out.println(assessment.getAdequacy());
                System.out.println(assessment.getReliability());
                System.out.println("------------------------------");

                goToAssessment();
            }
        });

        return view;
    }


    private boolean setValues() {
        EditText et = (EditText)getActivity().findViewById(R.id.asmtEducation);
        education = et.getText().toString().trim();
        if(education.trim().isEmpty()){
            et.setError(getResources().getString(R.string.fieldEmptyError));
            return false;
        }
        assessment.setEducation(et.getText().toString().trim());

        et = (EditText)getActivity().findViewById(R.id.asmtOccupation);
        occupation = et.getText().toString().trim();
        if(occupation.trim().isEmpty()){
            et.setError(getResources().getString(R.string.fieldEmptyError));
            return false;
        }
        assessment.setOccupation(et.getText().toString().trim());

        et = (EditText)getActivity().findViewById(R.id.referred_By);
        referredBy = et.getText().toString().trim();
        assessment.setReferredBy(et.getText().toString().trim());

        et = (EditText)getActivity().findViewById(R.id.referral_Note);
        referralNote = et.getText().toString().trim();
        assessment.setReferralNote(et.getText().toString().trim());

        Spinner spinner = (Spinner)getActivity().findViewById(R.id.reason_for_Referral);
        reasonForReferral = spinner.getSelectedItem().toString();
        if(reasonForReferral.equals("Reason for Referral"))
            reasonForReferral = "";
        assessment.setReasonForReferral(spinner.getSelectedItem().toString());

        spinner = (Spinner) getActivity().findViewById(R.id.language_Tested_In);
        languageTestedIn = spinner.getSelectedItem().toString().trim();
        if(languageTestedIn.equals("Language Tested In *")){
            Toast.makeText(getActivity(), getResources().getString(R.string.spinnerEmpytError)+"\"Language Tested In\"", Toast.LENGTH_LONG).show();
            return false;
        }
        assessment.setTestedLanguage(et.getText().toString().trim());

        et = (EditText)getActivity().findViewById(R.id.background_Information);
        backgroundInformation = et.getText().toString().trim();
        if(backgroundInformation.isEmpty()){
            et.setError(getResources().getString(R.string.fieldEmptyError));
            return false;
        }
        assessment.setBackgroundInformation(et.getText().toString().trim());

        et = (EditText)getActivity().findViewById(R.id.informant);
        informant = et.getText().toString().trim();
        assessment.setInformant(et.getText().toString().trim());

        et = (EditText)getActivity().findViewById(R.id.informant_Relationship);
        informantRelationship = et.getText().toString().trim();
        assessment.setInformantRelationship(et.getText().toString().trim());

        et = (EditText)getActivity().findViewById(R.id.salient_Behavioral_Obeservations);
        salientBehavioralObservation = et.getText().toString().trim();
        if(salientBehavioralObservation.isEmpty()){
            et.setError(getResources().getString(R.string.fieldEmptyError));
            return false;
        }
        assessment.setBackgroundInformation(et.getText().toString().trim());

        et = (EditText)getActivity().findViewById(R.id.impressions);
        impression = et.getText().toString().trim();
        if(impression.isEmpty()){
            et.setError(getResources().getString(R.string.fieldEmptyError));
            return false;
        }
        assessment.setImpression(et.getText().toString().trim());

        et = (EditText)getActivity().findViewById(R.id.test_Scales_Administered);
        testScale = et.getText().toString().trim();
        if(testScale.isEmpty()){
            et.setError(getResources().getString(R.string.fieldEmptyError));
            return false;
        }
        assessment.setTestScale(et.getText().toString().trim());

        et = (EditText)getActivity().findViewById(R.id.test_Score);
        testScore = et.getText().toString().trim();
        if(testScore.isEmpty()){
            et.setError(getResources().getString(R.string.fieldEmptyError));
            return false;
        }
        assessment.setTestScores(et.getText().toString().trim());

        spinner = (Spinner)getActivity().findViewById(R.id.reliability);
        reliability = spinner.getSelectedItem().toString();
        if(reliability.equals("Reliability *")){
            Toast.makeText(getActivity(), getResources().getString(R.string.spinnerEmpytError)+"\"Reliability *\"", Toast.LENGTH_LONG).show();
            return false;
        }
        assessment.setReliability(spinner.getSelectedItem().toString());

        spinner = (Spinner)getActivity().findViewById(R.id.adequacy);
        adequacy = spinner.getSelectedItem().toString();
        if(adequacy.equals("Adequacy *")){
            Toast.makeText(getActivity(), getResources().getString(R.string.spinnerEmpytError)+"\"Adequacy *\"", Toast.LENGTH_LONG).show();
            return false;
        }
        assessment.setAdequacy(spinner.getSelectedItem().toString());


        et = (EditText)getActivity().findViewById(R.id.recommendation);
        recommendation = et.getText().toString().trim();
        if(recommendation.isEmpty()){
            et.setError(getResources().getString(R.string.fieldEmptyError));
            return false;
        }
        assessment.setRecommendation(et.getText().toString().trim());

        et = (EditText)getActivity().findViewById(R.id.assessor_Full_Name);
        assessorFullName = et.getText().toString().trim();
        if(assessorFullName.isEmpty()){
            et.setError(getResources().getString(R.string.fieldEmptyError));
            return false;
        }
        assessment.setAssessorName(et.getText().toString().trim());

        et = (EditText)getActivity().findViewById(R.id.assessor_Qualification);
        assessorQualification = et.getText().toString().trim();
        if(assessorQualification.isEmpty()){
            et.setError(getResources().getString(R.string.fieldEmptyError));
            return false;
        }
        assessment.setAssessorQualification(et.getText().toString().trim());

        et = (EditText)getActivity().findViewById(R.id.supervisor_Full_Name);
        supervisorFullName = et.getText().toString().trim();
        assessment.setSupervisorName(et.getText().toString().trim());

        et = (EditText)getActivity().findViewById(R.id.supervisorQualification);
        supervisorQualification = et.getText().toString().trim();
        assessment.setSupervisorQualification(et.getText().toString().trim());

        System.out.println(assessment.toString());

        if(informantRelationship.isEmpty())
            informant = "";
        if(informant.isEmpty())
            informantRelationship = "";

        return true;

    }



    public void goToAssessment(){

        Thread thread = new Thread() {
            public void run() {
                String values1[] = {salientBehavioralObservation, backgroundInformation, testScale, testScore, impression, recommendation, languageTestedIn, informant, informantRelationship, reliability, adequacy, assessorFullName, assessorQualification, supervisorFullName, supervisorQualification};
                String values12[] = {reasonForReferral, referralNote, referredBy};
                System.out.println(values1.length);

                List<Composition> list = new ArrayList<Composition>();

                list.add(new AssessmentUtility().createCompositionEHRC_Psychological_assessmentv0(values1, loginToken, sessionToken, patientId, mheName));
                if((!values12[0].isEmpty()) || (!values12[1].isEmpty()) || (!values12[2].isEmpty()))
                    list.add(new AssessmentUtility().createCompositionEHRC_Service_requestv0(values12, loginToken, sessionToken, patientId, mheName));

                for(int i=0;i<list.size();i++) {
                    System.out.println(list.get(i).toString());
                }


                try {
                    JSONObject obj = new AssessmentUtility().saveAllAssessmentCompositions(list,loginToken, sessionToken, orgUUID, patientId, userUUID);
                    if (obj.isNull("uuid") == false) {
                        new AssessmentUtility().updateIPPatientQueue(loginToken, patientId);
                        Intent intent = new Intent(getActivity(), Success.class);
                        getActivity().startActivity(intent);
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();

    }


}

