package com.example.pwsystem.Model;

public class Schedule {


    String hours;
    String minutes;
    String day;

    public Schedule() {

    }


    public Schedule(String hours, String minutes, String day) {
        this.hours = hours;
        this.minutes = minutes;
        this.day = day;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }

    public String getHours() {
        return hours;
    }

    public String getMinutes() {
        return minutes;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
