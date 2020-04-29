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

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mhmsbmrapp.Login.MHPFlow;
import com.example.mhmsbmrapp.R;
import com.example.mhmsbmrapp.model.Composition;
import com.example.mhmsbmrapp.utility.BmrCreateCompositionUtility;
import com.example.mhmsbmrapp.utility.MHPAssignmentUtility;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FragmentOP extends Fragment {



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
                EditText et = (EditText) getActivity().findViewById(R.id.Complaints);
                final String complaints = et.getText().toString();
                System.out.println("compalints = "+complaints);
                et = (EditText) getActivity().findViewById(R.id.Duration_complaint);
                final String duration = et.getText().toString();
                System.out.println(duration);
                et = (EditText) getActivity().findViewById(R.id.History);
                final String history = et.getText().toString();
                et = (EditText) getActivity().findViewById(R.id.Illness_Summery);
                final String summaryOfIllness = et.getText().toString();
                et = (EditText) getActivity().findViewById(R.id.Diagnosis_Type);
                final String diagnosisType = et.getText().toString();
                et = (EditText) getActivity().findViewById(R.id.ICD_Description);
                final String ICD_Description = et.getText().toString();
                et = (EditText) getActivity().findViewById(R.id.ICD_10_Code);
                final String ICD_10_Code = et.getText().toString();

                final String improvementStatus = "2|local::at0027|Very much improved|";

                et = (EditText) getActivity().findViewById(R.id.Medicine_Name);
                final String medicineName = et.getText().toString();
                et = (EditText) getActivity().findViewById(R.id.Dosage);
                final String dosage = et.getText().toString();
                et = (EditText) getActivity().findViewById(R.id.Dosage_Time);
                final String dosingTime = et.getText().toString();
                et = (EditText) getActivity().findViewById(R.id.Duration_med);
                final String medDuration = et.getText().toString();
                et = (EditText) getActivity().findViewById(R.id.Duration_Type);
                final String durationType = et.getText().toString();
                et = (EditText) getActivity().findViewById(R.id.Remarks);
                final String remarks = et.getText().toString();
                et = (EditText) getActivity().findViewById(R.id.Treatment_Instructions);
                final String[] medicationOrder = {medicineName, dosage, dosingTime, medDuration, durationType, remarks};
                final String treatmentInstruction = et.getText().toString();
                Log.e("--------->", complaints + history + duration + summaryOfIllness);


                Log.e("--------->", "inside OP BMR");
                try {
                    Thread thread = new Thread() {
                        @RequiresApi(api = Build.VERSION_CODES.O)
                        public void run() {


                            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                            String loginToken = sharedPreferences.getString("loginToken", "");
                            String sessionToken = sharedPreferences.getString("sessionToken", "");
                            String facilityName = sharedPreferences.getString("mhpName", "");

                            System.out.println("three values " + loginToken + " " + sessionToken + " " + facilityName);



                        /*String duration = "new new duration";
                        String history = "new new history";
                        String summaryOfIllness = "new new summary of illness";
                        String diagnosisType = "Differential Diagnosis";
                        String ICD_Description = "new desc";
                        String ICD_10_Code = "new code";
                        String improvementStatus = "2|local::at0027|Very much improved|";
                        String medicineName = "new new medicine name";
                        String dosage = "new new dosage";
                        String dosingTime = "new new dosing time";
                        String medDuration = "10";
                        String durationType = "months";
                        String remarks = "new new remarks";
                        String[] medicationOrder = {medicineName, dosage, dosingTime, medDuration, durationType, remarks};
                        String treatmentInstruction = "new new instruction";*/


                            boolean referral = true;
                            boolean followUp = false;
                            Map<String, String> map = new HashMap<String, String>();

                            try {
                                String loginDecodedToken = MHPFlow.decoded(loginToken);
                                Log.e("loginDecodedToken", loginDecodedToken);


                                map.put("composer_name", new JSONObject(loginDecodedToken).getString("userName"));
                                map.put("composer_identifier", new JSONObject(loginDecodedToken).getString("userUUID"));
                                map.put("facility_identifier", new JSONObject(loginDecodedToken).getString("orgUUID"));
                                map.put("facility_name", facilityName);
                                map.put("location", "Bengaluru");
                                map.put("personId", "da2b5a15-be2f-4bc1-b5a0-d64661d47e6f");


                            } catch (Exception e) {
                            }


                            BmrCreateCompositionUtility utility = new BmrCreateCompositionUtility();
                            List<Composition> list = new ArrayList<Composition>();

                            System.out.println(utility.createComposition_EHRC_Diagnosisv0(new String[]{diagnosisType, ICD_Description, ICD_10_Code}, map, loginToken, sessionToken));

                            try {
                                System.out.println("inside try");
                                System.out.println(utility.createComposition_EHRC_Complaintsv0(new String[]{complaints, duration}, map, loginToken, sessionToken));
                                list.add(new Composition("complaints_matches_compositionIDs", "EHRC - Complaints.v0", utility.createComposition_EHRC_Complaintsv0(new String[]{complaints, duration}, map, loginToken, sessionToken).getString("compositionUid")));

                                list.add(new Composition("diagnosis_matches_compositionIDs", "EHRC - Diagnosis.v0", utility.createComposition_EHRC_Diagnosisv0(new String[]{diagnosisType, ICD_Description, ICD_10_Code}, map, loginToken, sessionToken).getString("compositionUid")));

                                list.add(new Composition("clinical_history_matches_compositionIDs", "EHRC - Clinical history.v0", utility.createCompostion_EHRC_Clinical_historyv0(history, map, loginToken, sessionToken).getString("compositionUid")));

                                list.add(new Composition("summary_of_illness_matches_compositionIDs", "EHRC - Summary of illness.v0", utility.createComposition_EHRC_Summary_of_illnessv0(summaryOfIllness, map, loginToken, sessionToken).getString("compositionUid")));

                                list.add(new Composition("clinical_notes_order_matches_compositionIDs", "EHRC - Clinical notes.v0", utility.createCompositionEHRC_Clinical_notesv0(treatmentInstruction, map, loginToken, sessionToken).getString("compositionUid")));

                                list.add(new Composition("medication_order_matches_compositionIDs", "EHRC - Medication order.v0", utility.createCompositionEHRC_Medication_orderv0(medicationOrder, map, loginToken, sessionToken).getString("compositionUid")));

                                list.add(new Composition("cgi_scale_matches_compositionIDs", "EHRC - CGI Scale.v0", utility.createComposition_EHRC_CGI_Scalev0(improvementStatus, map, loginToken, sessionToken).getString("compositionUid")));

                                if (referral) {
                                    String[] referralValues = {"a", "b", "c", "Referral", "e", "f"};
                                    list.add(new Composition("service_request_matches_compositionIDs", "EHRC - Service request.v0", "Referral", utility.createComposition_EHRC_Service_requestv0(referralValues, map, loginToken, sessionToken).getString("compositionUid")));
                                } else if (followUp) {
                                    String[] followUpValues = {"2020-04-23T04:56:46.793Z::2020-04-24T18:30:00.000Z", "Followup"};
                                    list.add(new Composition("service_request_matches_compositionIDs", "EHRC - Service request.v0", "Followup", utility.createComposition_EHRC_Service_requestv01(followUpValues, map, loginToken, sessionToken).getString("compositionUid")));
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            System.out.println("outside try");


                            for (int i = 0; i < list.size(); i++) {
                                Composition object = list.get(i);
                                System.out.println(object.getName() + "    " + object.getTemplateId() + "     " + object.getCompositionUid());
                            }

                            System.out.println("4444444444444444444444444444444444444444444444444444444444444");
                            System.out.println(utility.saveAllCompositions(map, list, loginToken, sessionToken));
                            System.out.println("4444444444444444444444444444444444444444444444444444444444444");

                            /*MHPAssignmentUtility util = new MHPAssignmentUtility();
                            try {
                                JSONObject mhp = new JSONObject();
                                String givenName = new MHPFlow().getuserbyuuid(loginToken, map.get("composer_identifier")).getJSONObject("person").getString("givenName");
                                String userName = map.get("composer_name");
                                String composer_identifier = map.get("composer_identifier");
                                String facility_identifier = map.get("facility_identifier");
                                mhp.put("givenName", givenName);
                                mhp.put("userName", userName);
                                mhp.put("composer_identifier", composer_identifier);
                                mhp.put("facility_identifier", facility_identifier);
                                JSONObject patient = new MHPAssignmentUtility().updateIPPatientQueue(util.getPatientByPatientId(loginToken, sessionToken, map.get("personId")), util.getParentOrg(loginToken), mhp, loginToken, "Completed");
                                Intent intent = new Intent(FragmentOP.this.getActivity(), AssignPatientTest.class);
                                intent.putExtra("patient", patient.toString());
                                FragmentOP.this.getActivity().startActivity(intent);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }*/

                        }//run()
                    };//Thread

                    thread.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return view;
    }
}


