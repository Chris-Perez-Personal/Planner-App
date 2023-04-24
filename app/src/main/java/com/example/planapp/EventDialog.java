package com.example.planapp;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EventDialog extends DialogFragment {
    private DatabaseReference databaseReference;

    public EventDialog() {
        // Required empty public constructor
    }

    public static EventDialog newInstance() {
        return new EventDialog();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_dialog, container, false);

        databaseReference = FirebaseDatabase.getInstance().getReference("Calendar");

        EditText nameEditText = view.findViewById(R.id.nameEditText);
        EditText descriptionEditText = view.findViewById(R.id.descriptionEditText);
        EditText startTimeEditText = view.findViewById(R.id.startTimeEditText);
        EditText endTimeEditText = view.findViewById(R.id.endTimeEditText);
        Button saveButton = view.findViewById(R.id.saveButton);

        saveButton.setOnClickListener(v -> {
            String name = nameEditText.getText().toString().trim();
            String description = descriptionEditText.getText().toString().trim();
            String startTime = startTimeEditText.getText().toString().trim();
            String endTime = endTimeEditText.getText().toString().trim();

            if (name.isEmpty() || description.isEmpty() || startTime.isEmpty() || endTime.isEmpty()) {
                Toast.makeText(getActivity(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
            } else {
                Event event = new Event(name, description, startTime, endTime);
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
}
