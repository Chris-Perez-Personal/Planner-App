package com.example.planapp;

public class Event {
    private String name;
    private String description;
    private String startTime;
    private String endTime;

    public Event(){
        //empty constructor
    }

    public Event(String name, String description, String startTime, String endTime){
        this.name = name;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartTime() { return startTime; }

    public void setStartTime(String startTime) { this.startTime = startTime;}

    public String getEndTime() { return endTime; }

    public void setEndTime(String endTime) { this.endTime = endTime; }
}
