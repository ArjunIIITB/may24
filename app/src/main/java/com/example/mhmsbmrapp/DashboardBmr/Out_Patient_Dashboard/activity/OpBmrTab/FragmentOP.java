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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

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


    RadioButton conditionRadioButton;
    RadioGroup conditionGroup;

    RadioButton followUpRecommendationRadioButton;
    RadioGroup followUpGroup;



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

    boolean treatmentPlan = false;
    boolean diagnosis = false;

    private boolean referral = true;
    private boolean followUp = false;




    public FragmentOP() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.opbmrfinal, container, false);

        conditionGroup = (RadioGroup)view.findViewById(R.id.conditionRadioGroup);
        System.out.println(conditionGroup.toString());
        followUpGroup = (RadioGroup)view.findViewById(R.id.followUpRecommGroup);
        System.out.println(followUpGroup.toString());

        Button button = (Button) view.findViewById(R.id.button5);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View view) {
                boolean val = setValues();
                if(val == false)
                    return;
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
                            //System.out.println(utility.createComposition_EHRC_Diagnosisv0(new String[]{diagnosisType, icdDescription, icdCode}, composition, patientId, loginToken, sessionToken));

                            try {
                                System.out.println("inside try");
                                //list.add(new Composition("complaints_matches_compositionIDs", "EHRC - Complaints.v0", utility.createComposition_EHRC_Complaintsv0(new String[]{complaints, duration}, composition, patientId, loginToken,sessionToken).getString("compositionUid")));
                                list.add(utility.createComposition_EHRC_Complaintsv0(new String[]{complaints, duration}, composition, patientId, loginToken,sessionToken));

                                //list.add(new Composition("diagnosis_matches_compositionIDs", "EHRC - Diagnosis.v0", utility.createComposition_EHRC_Diagnosisv0(new String[]{diagnosisType, icdDescription, icdCode}, composition, patientId, loginToken, sessionToken).getString("compositionUid")));
                                Log.e("***$$$$***", String.valueOf(diagnosis));
                                if(diagnosis)
                                    list.add(utility.createComposition_EHRC_Diagnosisv0(new String[]{diagnosisType, icdDescription, icdCode}, composition, patientId, loginToken, sessionToken));

                                //list.add(new Composition("clinical_history_matches_compositionIDs", "EHRC - Clinical history.v0", utility.createCompostion_EHRC_Clinical_historyv0(historyAndMentalStatus, composition, patientId,loginToken, sessionToken).getString("compositionUid")));
                                list.add(utility.createCompostion_EHRC_Clinical_historyv0(historyAndMentalStatus, composition, patientId,loginToken, sessionToken));

                                //list.add(new Composition("summary_of_illness_matches_compositionIDs", "EHRC - Summary of illness.v0", utility.createComposition_EHRC_Summary_of_illnessv0(illnessSummary, composition, patientId,loginToken, sessionToken).getString("compositionUid")));
                                list.add(utility.createComposition_EHRC_Summary_of_illnessv0(illnessSummary, composition, patientId,loginToken, sessionToken));

                                //list.add(new Composition("clinical_notes_order_matches_compositionIDs", "EHRC - Clinical notes.v0", utility.createCompositionEHRC_Clinical_notesv0(treatmentInstruction, composition, patientId, loginToken, sessionToken).getString("compositionUid")));
                                list.add(utility.createCompositionEHRC_Clinical_notesv0(treatmentInstruction, composition, patientId, loginToken, sessionToken));

                                final String[] medicationOrder = {medicineName, dosage, dosingTime, medDuration, durationType, remarks};
                                //list.add(new Composition("medication_order_matches_compositionIDs", "EHRC - Medication order.v0", utility.createCompositionEHRC_Medication_orderv0(medicationOrder, composition, patientId, loginToken, sessionToken).getString("compositionUid")));
                                Log.e("***$$$$***", String.valueOf(treatmentPlan));
                                if(treatmentPlan)
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



    public boolean setValues() {

        EditText et = (EditText) getActivity().findViewById(R.id.Complaints);
        complaints = et.getText().toString();
        if(complaints.trim().isEmpty()) {
            et.setError("this field is mandatory");
            return false;
        }

        et = (EditText) getActivity().findViewById(R.id.Duration_complaint);
        duration = et.getText().toString();
        if(duration.trim().isEmpty()) {
            et.setError("this field is mandatory");
            return false;
        }

        et = (EditText) getActivity().findViewById(R.id.History);
        historyAndMentalStatus = et.getText().toString();
        if(historyAndMentalStatus.trim().isEmpty()) {
            et.setError("this field is mandatory");
            return false;
        }

        et = (EditText) getActivity().findViewById(R.id.Illness_Summery);
        illnessSummary = et.getText().toString();
        if(illnessSummary.trim().isEmpty()) {
            et.setError("this field is mandatory");
            return false;
        }




        //---------------diagnosis

        diagnosis = false;
        boolean ret1 = false;

        Spinner spinner = (Spinner) getActivity().findViewById(R.id.Diagnosis_Type);
        diagnosisType = spinner.getSelectedItem().toString();
        if(diagnosisType.equals("Diagnosis Type"))
            diagnosisType = "";
        if(! diagnosisType.trim().isEmpty())
            diagnosis = true;

        et = (EditText) getActivity().findViewById(R.id.ICD_Description);
        icdDescription = et.getText().toString();
        if(! icdDescription.trim().isEmpty())
            diagnosis = true;


        et = (EditText) getActivity().findViewById(R.id.ICD_10_Code);
        icdCode = et.getText().toString();
        if(! icdCode.trim().isEmpty())
            diagnosis = true;



        spinner = (Spinner) getActivity().findViewById(R.id.Diagnosis_Type);
        diagnosisType = spinner.getSelectedItem().toString();
        if(diagnosisType.equals("Diagnosis Type"))
            diagnosisType = "";
        if(diagnosisType.trim().isEmpty() ){
            if(diagnosis == true) {
                Toast.makeText(getActivity(), "select an option from drop down menu \"Diagnosis Type\"", Toast.LENGTH_LONG).show();
                ret1 = true;
            }else
                et.setError(null);
        }else{
            et.setError(null);
        }

        et = (EditText) getActivity().findViewById(R.id.ICD_Description);
        icdDescription = et.getText().toString();
        if(icdDescription.trim().isEmpty() ){
            if(diagnosis == true) {
                et.setError("this field is required");
                ret1 = true;
            }else
                et.setError(null);
        }else{
            et.setError(null);
        }

        et = (EditText) getActivity().findViewById(R.id.ICD_10_Code);
        icdCode = et.getText().toString();
        if(icdCode.trim().isEmpty() ){
            if(diagnosis == true) {
                et.setError("this field is required");
                ret1 = true;
            }else
                et.setError(null);
        }else{
            et.setError(null);
        }

        if(ret1 == true)
            return false;

        //---------------diagnosis

        //---------------improvement status

        int selectedId = conditionGroup.getCheckedRadioButtonId();
        conditionRadioButton = (RadioButton)getActivity().findViewById(selectedId);
        if(selectedId == -1){
            Log.e("selection error", "Nothing selected");
            conditionRadioButton = (RadioButton)getActivity().findViewById(R.id.Condition_Worsened);
            conditionRadioButton.setError("select an option");
            return false;
        }else {
            conditionRadioButton = (RadioButton)getActivity().findViewById(R.id.Condition_Worsened);
            conditionRadioButton.setError(null);
            System.out.println("===================");
            System.out.println("inside else part "+ conditionRadioButton.getText().toString());
            if(conditionRadioButton.getText().toString().equals("Very much improved"))
                improvementStatus = "2|local::at0027|Very much improved|";
            else if(conditionRadioButton.getText().toString().equals("No Change in Condition"))
                improvementStatus = "5|local::at0030|No change|";
            else if(conditionRadioButton.getText().toString().equals("Condition Worsened"))
                improvementStatus = "7|local::at0032|Much worse|";
            System.out.println(improvementStatus);
            System.out.println("===================");
        }

        //---------------improvement status

        //---------------treatment plan

        treatmentPlan = false;
        boolean ret2 = false;
        et = (EditText) getActivity().findViewById(R.id.Medicine_Name);
        medicineName = et.getText().toString();
        if(! medicineName.trim().isEmpty() )
            treatmentPlan = true;

        et = (EditText) getActivity().findViewById(R.id.Dosage);
        dosage = et.getText().toString();
        if(! dosage.trim().isEmpty() )
            treatmentPlan = true;

        et = (EditText) getActivity().findViewById(R.id.Dosage_Time);
        dosingTime = et.getText().toString();
        if(! dosingTime.trim().isEmpty() )
            treatmentPlan = true;

        et = (EditText) getActivity().findViewById(R.id.Duration_med);
        medDuration = et.getText().toString();
        if(! medDuration.trim().isEmpty() )
            treatmentPlan = true;

        spinner =  getActivity().findViewById(R.id.Duration_Type);
        durationType = spinner.getSelectedItem().toString();
        if(durationType.equals("Duration Type"))
            durationType = "";
        if(! durationType.trim().isEmpty() )
            treatmentPlan = true;


        et = (EditText) getActivity().findViewById(R.id.Remarks);
        remarks = et.getText().toString();
        if(! remarks.trim().isEmpty() )
            treatmentPlan = true;




        et = (EditText) getActivity().findViewById(R.id.Medicine_Name);
        medicineName = et.getText().toString();
        if(medicineName.trim().isEmpty() ){
            if(treatmentPlan == true) {
                et.setError("this field is required");
                ret2 = true;
            }else
                et.setError(null);
        }else{
            et.setError(null);
        }


        et = (EditText) getActivity().findViewById(R.id.Dosage);
        dosage = et.getText().toString();
        if(dosage.trim().isEmpty() ){
            if(treatmentPlan == true) {
                et.setError("this field is required");
                ret2 = true;
            }else
                et.setError(null);
        }else{
            et.setError(null);
        }

        et = (EditText) getActivity().findViewById(R.id.Dosage_Time);
        dosingTime = et.getText().toString();
        if(dosingTime.trim().isEmpty() ){
            if(treatmentPlan == true) {
                et.setError("this field is required");
                ret2 = true;
            }else
                et.setError(null);

        }else{
            et.setError(null);
        }

        et = (EditText) getActivity().findViewById(R.id.Duration_med);
        medDuration = et.getText().toString();
        if(medDuration.trim().isEmpty() ){
            if(treatmentPlan == true) {
                et.setError("this field is required");
                ret2 = true;
            }else
                et.setError(null);
        }else{
            et.setError(null);
        }

        spinner =  getActivity().findViewById(R.id.Duration_Type);
        durationType = spinner.getSelectedItem().toString();
        if(durationType.equals("Duration Type"))
            durationType = "";
        if(durationType.trim().isEmpty() ){
            if(treatmentPlan == true) {
                Toast.makeText(getActivity(), "select an option from drop down menu \"Duration Type\"", Toast.LENGTH_LONG).show();
                ret2 = true;
            }else
                et.setError(null);
        }else{
            et.setError(null);
        }

        et = (EditText) getActivity().findViewById(R.id.Remarks);
        remarks = et.getText().toString();
        if(remarks.trim().isEmpty() ){
            if(treatmentPlan == true) {
                et.setError("this field is required");
                ret2 = true;
            }else
                et.setError(null);
        }else{
            et.setError(null);
        }

        if(ret2 == true) {
            return false;
        }

        //---------------treatment plan

        et = (EditText) getActivity().findViewById(R.id.Treatment_Instructions);
        treatmentInstruction = et.getText().toString();
        if(treatmentInstruction.trim().isEmpty()) {
            et.setError("this field is mandatory");
            return false;
        }

        //---------------follow up recommendation

        int selId = followUpGroup.getCheckedRadioButtonId();
        followUpRecommendationRadioButton = (RadioButton) getActivity().findViewById(selId);
        if(selId == -1){
            System.out.println("++++++++++++++++++++ NOTHING SELECTED ++++++++++++++++++++");
            followUpRecommendationRadioButton = getActivity().findViewById(R.id.Refer);
            followUpRecommendationRadioButton.setError("select an option");
            return false;
        }else {
            followUpRecommendationRadioButton = (RadioButton)getActivity().findViewById(R.id.Refer);
            followUpRecommendationRadioButton.setError(null);
            if(followUpRecommendationRadioButton.getText().toString().equals("Follow Up")) {
                followUp = true;
                referral = false;
                System.out.println("===================");
                System.out.println("followUp = "+followUp +"        "+ "referral = "+referral);
                System.out.println("===================");
            }
            else if(followUpRecommendationRadioButton.getText().toString().equals("Refer")) {
                followUp = false;
                referral = true;
                System.out.println("===================");
                System.out.println("followUp = "+followUp +"        "+ "referral = "+referral);
                System.out.println("===================");
            }

        }

        //---------------follow up recommendation

        return true;
    }

}


