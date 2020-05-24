package com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity.OpBmrTab;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mhmsbmrapp.Login.MHPFlow;
import com.example.mhmsbmrapp.Login.SessionInformation;
import com.example.mhmsbmrapp.R;
import com.example.mhmsbmrapp.model.Composition;
import com.example.mhmsbmrapp.utility.BmrCreateCompositionUtility;
import com.example.mhmsbmrapp.utility.MHPAssignmentUtility;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;


public class FragmentOP extends Fragment {


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

    private boolean referral = true;
    private boolean followUp = false;




    public FragmentOP() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.opbmrfinal, container, false);
        Button button = (Button) view.findViewById(R.id.button5);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View view) {

                setValues();

                try {
                    Thread thread = new Thread() {
                        public void run() {

                            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                            String loginToken = sharedPreferences.getString("loginToken", "");
                            String facilityName = sharedPreferences.getString("mheName", "");
                            String patientId = sharedPreferences.getString("patientId", "");

                            JSONObject composition = new JSONObject();
                            try {
                                composition.put("/composer|identifier", SessionInformation.userUUID);
                                composition.put("/composer|name", SessionInformation.userName);
                                composition.put("/context/end_time", "2020-05-09T11:27:42.347Z");
                                composition.put("/context/health_care_facility|identifier", SessionInformation.orgUUID);
                                composition.put("/context/health_care_facility|name", facilityName);
                                composition.put("/context/location", "Bengaluru");
                                composition.put("/language", "en");
                                composition.put("/territory", "IN");

                            } catch (Exception e) { e.printStackTrace(); }


                            BmrCreateCompositionUtility utility = new BmrCreateCompositionUtility();
                            List<Composition> list = new ArrayList<Composition>();
                            String sessionToken = SessionInformation.sessionToken;
                            System.out.println(utility.createComposition_EHRC_Diagnosisv0(new String[]{diagnosisType, icdDescription, icdCode}, composition, patientId, loginToken, sessionToken));

                            try {
                                System.out.println("inside try");
                                System.out.println(utility.createComposition_EHRC_Complaintsv0(new String[]{complaints, duration}, composition, patientId,loginToken, sessionToken));
                                //list.add(new Composition("complaints_matches_compositionIDs", "EHRC - Complaints.v0", utility.createComposition_EHRC_Complaintsv0(new String[]{complaints, duration}, composition, patientId, loginToken,sessionToken).getString("compositionUid")));
                                list.add(utility.createComposition_EHRC_Complaintsv0(new String[]{complaints, duration}, composition, patientId, loginToken,sessionToken));

                                //list.add(new Composition("diagnosis_matches_compositionIDs", "EHRC - Diagnosis.v0", utility.createComposition_EHRC_Diagnosisv0(new String[]{diagnosisType, icdDescription, icdCode}, composition, patientId, loginToken, sessionToken).getString("compositionUid")));
                                list.add(utility.createComposition_EHRC_Diagnosisv0(new String[]{diagnosisType, icdDescription, icdCode}, composition, patientId, loginToken, sessionToken));

                                //list.add(new Composition("clinical_history_matches_compositionIDs", "EHRC - Clinical history.v0", utility.createCompostion_EHRC_Clinical_historyv0(historyAndMentalStatus, composition, patientId,loginToken, sessionToken).getString("compositionUid")));
                                list.add(utility.createCompostion_EHRC_Clinical_historyv0(historyAndMentalStatus, composition, patientId,loginToken, sessionToken));

                                //list.add(new Composition("summary_of_illness_matches_compositionIDs", "EHRC - Summary of illness.v0", utility.createComposition_EHRC_Summary_of_illnessv0(illnessSummary, composition, patientId,loginToken, sessionToken).getString("compositionUid")));
                                list.add(utility.createComposition_EHRC_Summary_of_illnessv0(illnessSummary, composition, patientId,loginToken, sessionToken));

                                //list.add(new Composition("clinical_notes_order_matches_compositionIDs", "EHRC - Clinical notes.v0", utility.createCompositionEHRC_Clinical_notesv0(treatmentInstruction, composition, patientId, loginToken, sessionToken).getString("compositionUid")));
                                list.add(utility.createCompositionEHRC_Clinical_notesv0(treatmentInstruction, composition, patientId, loginToken, sessionToken));

                                final String[] medicationOrder = {medicineName, dosage, dosingTime, medDuration, durationType, remarks};
                                //list.add(new Composition("medication_order_matches_compositionIDs", "EHRC - Medication order.v0", utility.createCompositionEHRC_Medication_orderv0(medicationOrder, composition, patientId, loginToken, sessionToken).getString("compositionUid")));
                                list.add(utility.createCompositionEHRC_Medication_orderv0(medicationOrder, composition, patientId, loginToken, sessionToken));

                                //list.add(new Composition("cgi_scale_matches_compositionIDs", "EHRC - CGI Scale.v0", utility.createComposition_EHRC_CGI_Scalev0(improvementStatus, composition, patientId, loginToken, sessionToken).getString("compositionUid")));
                                list.add(utility.createComposition_EHRC_CGI_Scalev0(improvementStatus, composition, patientId, loginToken, sessionToken));

                                if (referral) {
                                    String[] referralValues = {"a", "b", "c", "Referral", "e", "f"};
                                    //list.add(new Composition("service_request_matches_compositionIDs", "EHRC - Service request.v0", "Referral", utility.createComposition_EHRC_Service_requestv0(referralValues, composition, patientId, loginToken, sessionToken).getString("compositionUid")));
                                    list.add(utility.createComposition_EHRC_Service_requestv0(referralValues, composition, patientId, loginToken, sessionToken));
                                } else if (followUp) {
                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                                    sdf.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
                                    String time = sdf.format(System.currentTimeMillis());
                                    String time2 = sdf.format(System.currentTimeMillis()*60*60*1000); //followupDate

                                    String[] followUpValues = {time+"::"+time2, "Followup"};
                                    //list.add(new Composition("service_request_matches_compositionIDs", "EHRC - Service request.v0", "Followup", utility.createComposition_EHRC_Service_requestv01(followUpValues, composition, patientId, loginToken, sessionToken).getString("compositionUid")));
                                    list.add(utility.createComposition_EHRC_Service_requestv01(followUpValues, composition, patientId, loginToken, sessionToken));
                                }

                            } catch (Exception e) { e.printStackTrace(); }

                            for (int i = 0; i < list.size(); i++) {
                                Composition object = list.get(i);
                                System.out.println(object.getName() + "    " + object.getTemplateId() + "     " + object.getCompositionUid());
                            }

                            System.out.println(utility.saveAllCompositions(patientId, list, loginToken, sessionToken));

                        }//run()
                    };//Thread
                    thread.start();
                } catch (Exception e) { e.printStackTrace(); }
            }
        });
        return view;
    }



    public void setValues() {

        EditText et = (EditText) getActivity().findViewById(R.id.Complaints);
        complaints = et.getText().toString();
        System.out.println("complaints = "+complaints);
        et = (EditText) getActivity().findViewById(R.id.Duration_complaint);
        duration = et.getText().toString();
        System.out.println(duration);
        et = (EditText) getActivity().findViewById(R.id.History);
        historyAndMentalStatus = et.getText().toString();
        et = (EditText) getActivity().findViewById(R.id.Illness_Summery);
        illnessSummary = et.getText().toString();

        Spinner spinner = (Spinner) getActivity().findViewById(R.id.Diagnosis_Type);
        diagnosisType = spinner.getSelectedItem().toString();

        et = (EditText) getActivity().findViewById(R.id.ICD_Description);
        icdDescription = et.getText().toString();
        et = (EditText) getActivity().findViewById(R.id.ICD_10_Code);
        icdCode = et.getText().toString();

        improvementStatus = "2|local::at0027|Very much improved|";

        et = (EditText) getActivity().findViewById(R.id.Medicine_Name);
        medicineName = et.getText().toString();
        et = (EditText) getActivity().findViewById(R.id.Dosage);
        dosage = et.getText().toString();
        et = (EditText) getActivity().findViewById(R.id.Dosage_Time);
        dosingTime = et.getText().toString();
        et = (EditText) getActivity().findViewById(R.id.Duration_med);
        medDuration = et.getText().toString();
        et = (EditText) getActivity().findViewById(R.id.Duration_Type);
        durationType = et.getText().toString();
        et = (EditText) getActivity().findViewById(R.id.Remarks);
        remarks = et.getText().toString();
        et = (EditText) getActivity().findViewById(R.id.Treatment_Instructions);
        treatmentInstruction = et.getText().toString();

    }


}


