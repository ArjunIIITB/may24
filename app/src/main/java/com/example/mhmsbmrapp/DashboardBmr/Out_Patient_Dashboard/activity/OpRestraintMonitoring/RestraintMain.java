package com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity.OpRestraintMonitoring;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.Success;
import com.example.mhmsbmrapp.R;
import com.example.mhmsbmrapp.model.Composition;
import com.example.mhmsbmrapp.model.RestraintMonitoring;
import com.example.mhmsbmrapp.utility.RestraintMonitoringUtility;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class RestraintMain extends Fragment {

    RestraintMonitoring rm = new RestraintMonitoring();

    EditText dateText;
    final Calendar calendar = Calendar.getInstance();;

    private String loginToken;
    private String patientId;

    private String mheName;



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
    private String bloodSupplyToLimb = "Yes";
    private String breathingProblem = "Yes";





    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.op_restraint_main, container, false);



        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        System.out.println(sharedPreferences.getString("loginToken", ""));

        mheName = sharedPreferences.getString("mheName", "");
        loginToken = sharedPreferences.getString("loginToken", "");
        patientId = sharedPreferences.getString("patientId", "");

        EditText et = (EditText)view.findViewById(R.id.nameOfMHE);
        et.setText(mheName);


        Button button = (Button) view.findViewById(R.id.submit_Restraint_Monitoring);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            System.out.println("inside onclick of submit therapy button");
            setValues();

                System.out.println("===============================================");
                System.out.println(rm.toString());
                System.out.println("===============================================");


                goToRestraintMonitoring();
            }
        });

        return view;
    }

    public void setValues(){
        EditText et = (EditText)getActivity().findViewById(R.id.nominatedRepresentativeName);
        rm.setNominatedRepresentativeName(et.getText().toString().trim());
        et = (EditText)getActivity().findViewById(R.id.nameOfMHE);
        et.setText(mheName);
        rm.setNameOfMHE(mheName);
        et = (EditText)getActivity().findViewById(R.id.inChargePsychiatry);
        rm.setInchargePsychiatrist(et.getText().toString().trim());
        Spinner spinner = (Spinner)getActivity().findViewById(R.id.Setting);
        rm.setSetting(spinner.getSelectedItem().toString());

        spinner = (Spinner)getActivity().findViewById(R.id.InformedToNR);
        if(spinner.getSelectedItem().toString().equals("Yes"))
            rm.setInformedToNR("true");
        else
            rm.setInformedToNR("false");

        et = (EditText)getActivity().findViewById(R.id.PsychiatricDiagnosis);
        rm.setPsychiatricDiagnosis(et.getText().toString());
        et = (EditText)getActivity().findViewById(R.id.Duration);
        rm.setDuration(et.getText().toString().trim());
        spinner = (Spinner)getActivity().findViewById(R.id.Duration_Type);
        rm.setType(spinner.getSelectedItem().toString());
        et = (EditText)getActivity().findViewById(R.id.Reason);
        rm.setReason(et.getText().toString().trim());
        spinner = (Spinner)getActivity().findViewById(R.id.SpinnerInjuries);
        rm.setInjuries(spinner.getSelectedItem().toString());
        spinner = (Spinner)getActivity().findViewById(R.id.limb_Ischaemia);
        rm.setLimbIschaemia(spinner.getSelectedItem().toString());
        et = (EditText)getActivity().findViewById(R.id.Others);
        rm.setOthers(et.getText().toString().trim());

        rm.setMonitoringDate(monitoringDate);

        et = (EditText)getActivity().findViewById(R.id.Pulse);
        rm.setPulse(et.getText().toString().trim());
        et = (EditText)getActivity().findViewById(R.id.Temperature);
        rm.setTemperature(et.getText().toString().trim());
        et = (EditText)getActivity().findViewById(R.id.Respiratory_Rate);
        rm.setRespiratoryRate(et.getText().toString().trim());
        et = (EditText)getActivity().findViewById(R.id.Injuries);
        rm.setInjuries2(et.getText().toString().trim());

        spinner = (Spinner)getActivity().findViewById(R.id.BloodSupplyToLimbs);
        if(spinner.getSelectedItem().toString().equals("Yes"))
            rm.setNoBloodSupply("true");
        else
            rm.setNoBloodSupply("false");

        spinner = (Spinner)getActivity().findViewById(R.id.BreathingProblem);
        if(spinner.getSelectedItem().toString().equals("Yes"))
            rm.setBreathingProblem("true");
        else
            rm.setBreathingProblem("false");

        rm.setStartDate("2020-05-20T09:49:12.014Z");

        System.out.println(rm.toString());
    }



    public void goToRestraintMonitoring() {
        Thread thread = new Thread() {
            public void run() {
                List<Composition> list = new ArrayList<>();
                list.add(new RestraintMonitoringUtility().createEHRC_Proceduresv3(rm, loginToken, mheName, patientId));
                list.add(new RestraintMonitoringUtility().createDiagnosisTemplate(rm, loginToken, mheName, patientId));
                list.add(new RestraintMonitoringUtility().createCompositionEHRC_Restraint_monitoringv3(rm, loginToken, mheName, patientId));
                try {
                    JSONObject obj = new JSONObject(new RestraintMonitoringUtility().saveAllAssessmentCompositions(list, loginToken, patientId).toString());
                    if (obj.isNull("uuid") == false) {
                        new RestraintMonitoringUtility().updateIPPatientQueue(loginToken, patientId);
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


