package com.example.planapp;

import com.example.planapp.Event;

import java.util.ArrayList;
import java.util.List;

public class Day {
    private int dayOfMonth;
    private List<Event> events;

    public Day(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
        this.events = new ArrayList<>();
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public void removeEvent(Event event) {
        events.remove(event);
    }
}
