package com.example.planapp;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EventDialog extends DialogFragment {
    private DatabaseReference databaseReference;
    private String selectedDate;

    public EventDialog() {
        // Required empty public constructor
    }

    public static EventDialog newInstance(String selectedDate) {
        EventDialog dialog = new EventDialog();
        Bundle args = new Bundle();
        args.putString("selectedDate", selectedDate);
        dialog.setArguments(args);
        return new EventDialog();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_dialog, container, false);

        databaseReference = FirebaseDatabase.getInstance().getReference("Calendar");

        EditText nameEditText = view.findViewById(R.id.nameEditText);
        EditText descriptionEditText = view.findViewById(R.id.descriptionEditText);
        TimePicker startTimePicker = view.findViewById(R.id.startTimePicker);
        TimePicker endTimePicker = view.findViewById(R.id.endTimePicker);
        Button saveButton = view.findViewById(R.id.saveButton);

        saveButton.setOnClickListener(v -> {
            String name = nameEditText.getText().toString().trim();
            String description = descriptionEditText.getText().toString().trim();

            // Get the selected start and end times from the time picker
            String startTime = String.format("%02d:%02d", startTimePicker.getHour(), startTimePicker.getMinute());
            String endTime = String.format("%02d:%02d", endTimePicker.getHour(), endTimePicker.getMinute());

            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(description)) {
                Toast.makeText(getActivity(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
            } else if (isValidTime(startTime) || isValidTime(endTime)) {
                Toast.makeText(getActivity(), "Invalid time format", Toast.LENGTH_SHORT).show();
            } else {
                Event event = new Event(name, description, startTime, endTime);
                assert getArguments() != null;
                databaseReference.child(getArguments().getString("dateSelected")).push().setValue(event);
                dismiss();
            }
        });

        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setTitle("New Event");
        return dialog;
    }

    // Helper method to check if a time string is valid
    private boolean isValidTime(String time) {
        return !time.matches("^([01][0-9]|2[0-3]):[0-5][0-9]$");
    }
}
