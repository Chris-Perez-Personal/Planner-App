package com.example.planapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import org.w3c.dom.Text;


public class UserQuery extends AppCompatActivity {

    TextView bed, wake, studyGoalTime;

    Button wakeUpButton;
    Button saveTime;
    Button studyGoalButton;
    Button sleepButton;
    // sleep hour and min
    int sHour = 0, sMin = 0;
    // wake up hour and min
    int wHour = 0, wMin = 0;
    int studyGoalHour = 0, studyGoalMin = 0;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_user_query);

        wakeUpButton = findViewById(R.id.sleepTime);
        sleepButton = findViewById(R.id.wakeTime);
        bed = (TextView) findViewById(R.id.textViewBed);
        wake = (TextView) findViewById(R.id.textViewWake);
        saveTime = findViewById(R.id.saveButton);
        studyGoalButton = findViewById(R.id.studyGoal);
        studyGoalTime = (TextView) findViewById(R.id.textViewStudyGoal);


        saveTime.setOnClickListener(new View.OnClickListener() {
            // marks true if AM and false if PM
            // w == wake; s == sleep;
            boolean wPM = false;
            boolean sPM = false;

            @Override
            public void onClick(View view) {

                if (wHour == 0) {
                    wake.setText("Set when you wake up");
                }
                if (wHour > 12) {
                    wPM = true;
                    wHour = wHour % 12;
                    if (wHour % 12 == 0) wHour++;
                    if (wMin < 10) {
                        String temp;
                        temp = Integer.toString(wMin);
                        temp = String.format("%02d", wMin);
                        wake.setText(Integer.toString(wHour) + ":" + temp + " " + (wPM ? "PM" : "AM"));
                    } else {
                        wake.setText(Integer.toString(wHour) + ":" + Integer.toString(wMin) + " " + (wPM ? "PM" : "AM"));
                    }
                } else {
                    if (wMin < 10) {
                        String temp;
                        temp = Integer.toString(wMin);
                        temp = String.format("%02d", wMin);
                        wake.setText(Integer.toString(wHour) + ":" + temp + " " + (wPM ? "PM" : "AM"));
                    } else {
                        wake.setText(Integer.toString(wHour) + ":" + Integer.toString(wMin) + " " + (wPM ? "PM" : "AM"));
                    }
                }
                if (sHour > 12) {
                    sPM = true;
                    sHour = sHour % 12;
                    if (sHour % 12 == 0) wHour++;
                    if (sMin < 10) {
                        String temp;
                        temp = Integer.toString(sMin);
                        temp = String.format("%02d", sMin);
                        bed.setText(Integer.toString(sHour) + ":" + temp + " " + (sPM ? "PM" : "AM"));
                    } else {
                        bed.setText(Integer.toString(sHour) + ":" + Integer.toString(sMin) + " " + (sPM ? "PM" : "AM"));
                    }
                } else {
                    if (sMin < 10) {
                        String temp;
                        temp = Integer.toString(sMin);
                        temp = String.format("%02d", sMin);
                        bed.setText(Integer.toString(sHour) + ":" + temp + " " + (sPM ? "PM" : "AM"));
                    } else {
                        bed.setText(Integer.toString(sHour) + ":" + Integer.toString(sMin) + " " + (sPM ? "PM" : "AM"));
                    }
                }
                if(studyGoalHour > 0 || studyGoalMin > 0){
                    studyGoalTime.setText((studyGoalHour > 0 ? Integer.toString(studyGoalHour) : "0") + " hours and " +
                    (studyGoalMin > 0 ? Integer.toString(studyGoalMin) : "0") + " mins");


                }
            }
        });
    }


    public void popupTimer(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMin) {
                sHour = selectedHour;
                sMin = selectedMin;
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, sHour, sMin, false);
        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();

    }

    public void popupTimerW(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMin) {
                wHour = selectedHour;
                wMin = selectedMin;
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, sHour, sMin, false);
        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();

    }

    public void studyGoalTimer(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMin) {
                studyGoalHour = selectedHour;
                studyGoalMin = selectedMin;
            }
        };
        int style = AlertDialog.THEME_HOLO_DARK;

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, style, onTimeSetListener, studyGoalHour, studyGoalMin, true);
        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();

    }
}