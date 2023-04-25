package com.example.planapp;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
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

import java.util.ArrayList;
import java.util.List;

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
    private RecyclerView recyclerView;
    private EventAdapter eventAdapter;
    private List<Event> eventList;


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
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        eventList = new ArrayList<>();
        eventAdapter = new EventAdapter(eventList);
        recyclerView.setAdapter(eventAdapter);


        return view;
    }

    public void onStart(){
        super.onStart();
        calendarView = context.findViewById(R.id.calendarView);
        Button button = context.findViewById(R.id.button);

        calendarView.setOnDateChangeListener((calendarView, i, i1, i2) -> {
            dateSelected = Integer.toHexString(i) + Integer.toHexString(i1) + Integer.toHexString(i2);
            calendarClick();
        });

        button.setOnClickListener(v -> {
            if (dateSelected != null) {
                EventDialog dialog = EventDialog.newInstance(dateSelected);
                Bundle args = new Bundle();
                args.putString("dateSelected", dateSelected);
                dialog.setArguments(args);
                dialog.show(getParentFragmentManager(), "EventDialog");
            }
        });
        databaseReference = FirebaseDatabase.getInstance().getReference("Calendar");
    }

    private void calendarClick(){
        DatabaseReference eventsRef = FirebaseDatabase.getInstance().getReference("Calendar");
        eventsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Retrieve the list of events from the database
                List<Event> eventList = new ArrayList<>();
                for (DataSnapshot dateSnapshot : dataSnapshot.getChildren()) {
                    String date = dateSnapshot.getKey();
                    if(date.equals(dateSelected)){
                        for (DataSnapshot eventSnapshot : dateSnapshot.getChildren()) {
                            Event event = eventSnapshot.getValue(Event.class);
                            eventList.add(event);
                        }
                    }
                }

                // Update the eventList in the adapter with the new data
                eventAdapter.setEventList(eventList);

                // Notify the adapter that the data has changed
                eventAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "Failed to read value.", databaseError.toException());
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        calendarClick();
    }
}