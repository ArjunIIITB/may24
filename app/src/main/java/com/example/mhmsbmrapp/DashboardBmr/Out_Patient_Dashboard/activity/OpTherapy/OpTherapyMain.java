package com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity.OpTherapy;

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

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.Success;
import com.example.mhmsbmrapp.Login.MHPFlow;
import com.example.mhmsbmrapp.Login.SessionInformation;
import com.example.mhmsbmrapp.R;
import com.example.mhmsbmrapp.model.Composition;
import com.example.mhmsbmrapp.model.Therapy;
import com.example.mhmsbmrapp.utility.AssessmentUtility;
import com.example.mhmsbmrapp.utility.TherapyUtility;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class OpTherapyMain extends Fragment {

    String loginToken;
    String sessionToken;
    String userUUID;
    String userName;
    String orgUUID;
    String patientId;
    String mheName;


    private Therapy therapy = new Therapy();

    private String education="education one";
    private String occupation = "occupation two";
    private String sessionNumber = "3";
    private String therapyMethod = "therapyMethod four eight";
    private String typeOfTherapy = "typeOfTherapy five eight";
    private String psychiatricDiagnosis = "psychiatricDiagnosis six eight";
    private String objectiveOfSession = "objectiveOfSession seven eight";
    private String keyIssue = "keyIssue eight eight";
    private String therapyTechniqueUsed = "therapyTechniqueUsed nine eight";
    private String therapistObservation = "therapistObservation ten eight";
    private String planForNextSession = "planForNextSession eleven eight";
    private String dateForNextSession = "2020-05-01T09:17:41.873Z::2020-05-07T18:30:00.000Z";
    private String therapistFullName = "therapistFullName thirteen eight";
    private String therapistQualification = "therapistQualification fourteen eight";
    private String supervisorFullName = "supervisorFullName fifteen eight";
    private String supervisorQualification = "supervisorQualification sixteen eight";





    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.op_therapy_main, container, false);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        System.out.println(sharedPreferences.getString("loginToken", ""));

        mheName = sharedPreferences.getString("mheName", "");
        loginToken = sharedPreferences.getString("loginToken", "");
        patientId = sharedPreferences.getString("patientId", "");
        try {
            sessionToken = new JSONObject(MHPFlow.decoded(loginToken)).getString("sessionToken");
            SessionInformation.sessionToken = sessionToken;
            userUUID = new JSONObject(MHPFlow.decoded(loginToken)).getString("userUUID");
            SessionInformation.userUUID = userUUID;
            orgUUID = new JSONObject(MHPFlow.decoded(loginToken)).getString("orgUUID");
            SessionInformation.orgUUID = orgUUID;
            userName = new JSONObject(MHPFlow.decoded(loginToken)).getString("userName");
            SessionInformation.userName = userName;

            Log.e("patientIdpatientId", patientId);
            Log.e("patientIdpatientId", patientId);Log.e("patientIdpatientId", patientId);
            Log.e("patientIdpatientId", patientId);Log.e("patientIdpatientId", patientId);
            Log.e("patientIdpatientId", patientId);Log.e("patientIdpatientId", patientId);
            Log.e("patientIdpatientId", patientId);Log.e("patientIdpatientId", patientId);
            Log.e("patientIdpatientId", patientId);

        }catch (Exception e){ e.printStackTrace(); }

        Button button = (Button) view.findViewById(R.id.submit_therapy_op);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setValues();
                System.out.println("inside onclick of submit therapy button");
                goToTherapy();

            }
        });
        return view;
    }


    public void setValues() {

        Log.e("^^^^^^^^^^", "555555555555555555");
        EditText et = (EditText) getActivity().findViewById(R.id.trpEducation);
        education = et.getText().toString().trim();

        et = (EditText)getActivity().findViewById(R.id.trpOccupation);
        occupation = et.getText().toString();

        et = (EditText)getActivity().findViewById(R.id.Session_Number);
        sessionNumber = et.getText().toString();

        Spinner spinner = (Spinner)getActivity().findViewById(R.id.Therapy_Method);
        therapyMethod = spinner.getSelectedItem().toString();

        et = (EditText)getActivity().findViewById(R.id.Type_of_Therapy);
        typeOfTherapy = et.getText().toString();

        et = (EditText)getActivity().findViewById(R.id.Psychiatric_Diagnosis);
        psychiatricDiagnosis = et.getText().toString();

        et = (EditText)getActivity().findViewById(R.id.Objective_of_Session);
        objectiveOfSession = et.getText().toString();

        et = (EditText)getActivity().findViewById(R.id.Key_Issues_Theme_Discussed);
        keyIssue = et.getText().toString();

        et = (EditText)getActivity().findViewById(R.id.Therapy_Technique_Used);
        therapyTechniqueUsed = et.getText().toString();

        et = (EditText)getActivity().findViewById(R.id.Therapist_Observations_Reflections);
        therapistObservation = et.getText().toString();

        et = (EditText)getActivity().findViewById(R.id.Plan_for_next_session);
        planForNextSession = et.getText().toString();

        //et = (EditText)getActivity().findViewById(R.id.Date_of_next_session);
        //occupation = et.getText().toString();

        et = (EditText)getActivity().findViewById(R.id.Full_Name);
        therapistFullName = et.getText().toString();

        et = (EditText)getActivity().findViewById(R.id.Qualification);
        therapistQualification = et.getText().toString();

        et = (EditText)getActivity().findViewById(R.id.Full_Name_supervisor);
        supervisorFullName = et.getText().toString();

        et = (EditText)getActivity().findViewById(R.id.Qualification_supervisor);
        supervisorQualification = et.getText().toString();


        Log.e("^^^^^^^^^^", "555555555555555555");
    }



    public void goToTherapy() {

        Thread thread = new Thread(){
            public void run(){

                String values[] = {userName, mheName, psychiatricDiagnosis};

                String values2[] = {userName, dateForNextSession, planForNextSession, mheName};


                Log.e("()())()()()()()", "{}{}}{{}{}}{{}}{}{}{}{}{}{");
                System.out.println(Arrays.toString(values2));
                Log.e("()())()()()()()", "{}{}}{{}{}}{{}}{}{}{}{}{}{");

                String values3[] = {therapyTechniqueUsed, objectiveOfSession, therapistObservation, keyIssue, therapyMethod, typeOfTherapy, supervisorFullName, supervisorQualification, therapistFullName, therapistQualification, sessionNumber};

                List<Composition> compositionList = new ArrayList<Composition>();
                compositionList.add(new TherapyUtility().createCompositionEHRC_Diagnosisv0(values, loginToken, sessionToken, userUUID, orgUUID, patientId));
                compositionList.add(new TherapyUtility().createCompositionEHRC_Service_requestv0(values2, loginToken, sessionToken, userUUID, orgUUID, patientId));
                compositionList.add(new TherapyUtility().createCompositionEHRC_Proceduresv2(values3, loginToken, sessionToken, patientId));

                for (int i = 0; i < compositionList.size(); i++) {
                    System.out.println("---------------------->" + compositionList.get(i).toString());
                }


                try {
                    JSONObject obj = new TherapyUtility().saveAllAssessmentCompositionsTherapy(compositionList, loginToken, sessionToken, orgUUID, patientId, userUUID);
                    if (obj.isNull("uuid") == false) {
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


