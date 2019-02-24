package com.example.pwsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TimePicker;

import com.example.pwsystem.Model.Schedule;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class timelayout extends AppCompatActivity {
    TimePicker timePicker;

    //CheckBoxes
    CheckBox chkSunday;
    CheckBox chkMonday;
    CheckBox chkTuesday;
    CheckBox chkWednesday;
    CheckBox chkThursday;
    CheckBox chkFriday;
    CheckBox chkSaturday;

    //Buttons
    Button btnBack;
    Button btnSet;

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference_main;
    DatabaseReference databaseReference_sched;

    private String hours;
    private String minutes;
    private  String day;

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getMinutes() {
        return minutes;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_timelayout);

        timePicker = (TimePicker) findViewById(R.id.timePicker);

        chkSunday = findViewById(R.id.chkSunday);
        chkMonday = findViewById(R.id.chkMonday);
        chkTuesday = findViewById(R.id.chkTuesday);
        chkWednesday = findViewById(R.id.chkWednesday);
        chkThursday = findViewById(R.id.chkThursday);
        chkFriday = findViewById(R.id.chkFriday);
        chkSaturday = findViewById(R.id.chkSaturday);
        btnBack = findViewById(R.id.btnBack);
        btnSet = findViewById(R.id.btnSet);

        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendData();

            }
        });

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener(){

            @Override
            public void onTimeChanged(TimePicker timePicker, int h, int m){
                setHours(String.valueOf(h));
                setMinutes(String.valueOf(m));
            }

        });


    }
    public void sendData(){
        boolean isSaved = false;
        if(chkMonday.isChecked()) {
            //TODO: Check if the schedule is already saved. By getting the data in firebase then compare
            databaseReference_main =  db.getReference("MainSchedule");
            databaseReference_main.child("Monday").setValue(String.format("%s:%s",getHours(),getMinutes()));
            databaseReference_sched = db.getReference("Schedule");
            String id = databaseReference_sched.push().getKey();
            Schedule scheduleObj = new Schedule(getHours(), getMinutes(), "Monday");
            databaseReference_sched.child(id).setValue(scheduleObj);
            isSaved = true;
        }
        if(chkTuesday.isChecked()) {
            //TODO: Check if the schedule is already saved. By getting the data in firebase then compare
            databaseReference_main =  db.getReference("MainSchedule");
            databaseReference_main.child("Tuesday").setValue(String.format("%s:%s",getHours(),getMinutes()));
            databaseReference_sched = db.getReference("Schedule");
            String id = databaseReference_sched.push().getKey();
            Schedule scheduleObj = new Schedule(getHours(), getMinutes(), "Tuesday");
            databaseReference_sched.child(id).setValue(scheduleObj);
            isSaved = true;
        }
        if(chkWednesday .isChecked()) {
            //TODO: Check if the schedule is already saved. By getting the data in firebase then compare
            databaseReference_main =  db.getReference("MainSchedule");
            databaseReference_main.child("Wednesday").setValue(String.format("%s:%s",getHours(),getMinutes()));
            databaseReference_sched = db.getReference("Schedule");
            String id = databaseReference_sched.push().getKey();
            Schedule scheduleObj = new Schedule(getHours(), getMinutes(), "Wednesday");
            databaseReference_sched.child(id).setValue(scheduleObj);
            isSaved = true;
        }
        if (chkThursday.isChecked() ){
            //TODO: Check if the schedule is already saved. By getting the data in firebase then compare
            databaseReference_main =  db.getReference("MainSchedule");
            databaseReference_main.child("Thursday").setValue(String.format("%s:%s",getHours(),getMinutes()));
            databaseReference_sched = db.getReference("Schedule");
            String id = databaseReference_sched.push().getKey();
            Schedule scheduleObj = new Schedule(getHours(), getMinutes(), "Thursday");
            databaseReference_sched.child(id).setValue(scheduleObj);
            isSaved = true;
        }
        if (chkFriday.isChecked()){
            //TODO: Check if the schedule is already saved. By getting the data in firebase then compare
            databaseReference_main =  db.getReference("MainSchedule");
            databaseReference_main.child("Friday").setValue(String.format("%s:%s",getHours(),getMinutes()));
            databaseReference_sched = db.getReference("Schedule");
            String id = databaseReference_sched.push().getKey();
            Schedule scheduleObj = new Schedule(getHours(), getMinutes(),"Friday");
            databaseReference_sched.child(id).setValue(scheduleObj);
            isSaved = true;
        }
        if (chkSaturday.isChecked()){
            //TODO: Check if the schedule is already saved. By getting the data in firebase then compare
            databaseReference_main =  db.getReference("MainSchedule");
            databaseReference_main.child("Saturday").setValue(String.format("%s:%s",getHours(),getMinutes()));
            databaseReference_sched = db.getReference("Schedule");
            String id = databaseReference_sched.push().getKey();
            Schedule scheduleObj = new Schedule(getHours(), getMinutes(), "Saturday");
            databaseReference_sched.child(id).setValue(scheduleObj);
            isSaved = true;
        }
        if (chkSunday.isChecked()){
            //TODO: Check if the schedule is already saved. By getting the data in firebase then compare
            databaseReference_main =  db.getReference("MainSchedule");
            databaseReference_main.child("Sunday").setValue(String.format("%s:%s",getHours(),getMinutes()));
            databaseReference_sched = db.getReference("Schedule");
            String id = databaseReference_sched.push().getKey();
            Schedule scheduleObj = new Schedule(getHours(), getMinutes(), "Sunday");
            databaseReference_sched.child(id).setValue(scheduleObj);
            isSaved = true;
        }

        if(isSaved) {
            Intent intent = new Intent(timelayout.this, MainActivity.class);
            startActivity(intent);

        }

    }
}
