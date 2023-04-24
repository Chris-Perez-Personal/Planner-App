package com.example.planapp;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Calendar#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Calendar extends Fragment {

    Activity context;
    private CalendarView calendarView;
    private EditText nameEditText, descriptionEditText, startTimeEditText, endTimeEditText;
    private String dateSelected;
    private DatabaseReference databaseReference;

    public Calendar() {
        // Required empty public constructor
    }

    public static Calendar newInstance(String param1, String param2) {
        Calendar fragment = new Calendar();
        Bundle args = new Bundle();
        String ARG_PARAM1 = "argParam1";
        args.putString(ARG_PARAM1, param1);
        String ARG_PARAM2 = "argParam2";
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = getActivity();
        return inflater.inflate(R.layout.fragment_calendar, container, false);
    }

    public void onStart(){
        super.onStart();
        calendarView = context.findViewById(R.id.calendarView);
        nameEditText = context.findViewById(R.id.nameEditText);
        descriptionEditText = context.findViewById(R.id.descriptionEditText);
        startTimeEditText = context.findViewById(R.id.startTimeEditText);
        endTimeEditText = context.findViewById(R.id.endTimeEditText);
        Button button = context.findViewById(R.id.button);

        calendarView.setOnDateChangeListener((calendarView, i, i1, i2) -> {
            dateSelected = Integer.toHexString(i) + Integer.toHexString(i1) + Integer.toHexString(i2);
            calendarClick();
        });
        button.setOnClickListener(view -> {
            if (dateSelected != null) {
                Event event = new Event(nameEditText.getText().toString(),
                        descriptionEditText.getText().toString(),
                        startTimeEditText.getText().toString(),
                        endTimeEditText.getText().toString());
                databaseReference.child(dateSelected).setValue(event);
            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference("Calendar");
    }

    private void calendarClick(){
        databaseReference.child(dateSelected).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue() != null){
                    Event event = snapshot.getValue(Event.class);
                    assert event != null;
                    nameEditText.setText(event.getName());
                    descriptionEditText.setText(event.getDescription());
                    startTimeEditText.setText(event.getStartTime());
                    endTimeEditText.setText(event.getEndTime());
                } else {
                    nameEditText.setText("");
                    descriptionEditText.setText("");
                    startTimeEditText.setText("");
                    endTimeEditText.setText("");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}