package com.example.planapp;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;


public class UserQuery extends AppCompatActivity {

    Button wakeUpButton;
    Button sleepButton;
    // sleep hour and min
    int sHour, sMin;
    // wake up hour and min
    int wHour, wMin;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_user_query);

        wakeUpButton = findViewById(R.id.sleepTime);
        sleepButton = findViewById(R.id.wakeTime);
    }

    public void popupTimer(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMin) {
                sHour = selectedHour;
                sMin = selectedMin;
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, sHour, sMin, true);
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
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, sHour, sMin, true);
        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }
}