package com.example.pwsystem;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TimePicker;

import com.example.pwsystem.Model.Schedule;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    DatabaseReference databaseReference;

    private int hours;
    private int minutes;
    private  String day;
    private String[] scheduleData = new String[7];
    private String dataSchedule;

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
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
        btnSet = findViewById(R.id.btnSet);

        databaseReference = db.getReference("MainSchedule");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                scheduleData[0] = retrieveData(dataSnapshot,"monday");
                scheduleData[1] = retrieveData(dataSnapshot,"tuesday");
                scheduleData[2] = retrieveData(dataSnapshot,"wednesday");
                scheduleData[3] = retrieveData(dataSnapshot,"thursday");
                scheduleData[4] = retrieveData(dataSnapshot,"friday");
                scheduleData[5] = retrieveData(dataSnapshot,"saturday");
                scheduleData[6] = retrieveData(dataSnapshot,"sunday");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
               Log.v("Error", "error");
            }
        });


        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendData();
            }
        });

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener(){

            @Override
            public void onTimeChanged(TimePicker timePicker, int h, int m){
                setHours(h);
                setMinutes(m);
            }

        });


    }
    public void sendData() {
        boolean isSaved = false;
        boolean isExist = false;
        int _length = 0;
        if (chkMonday.isChecked()) {
            dataSchedule = scheduleData[0];
            String[] data = dataSchedule.split(",");
            _length = data.length;
            while (_length != 0) {
                String dataSched = String.format("%02d:%02d", getHours(), getMinutes());
                for (int ctr = 0; ctr < data.length; ctr++) {
                    if (dataSched.equals(data[ctr])) {
                        isExist = true;
                        _length = 0;
                        break;
                    } else {
                        isExist = false;
                    }
                    _length = 0;
                }
            }
            if (!isExist) {
                databaseReference_main = db.getReference("MainSchedule");
                if (dataSchedule.equals("null")) {
                    databaseReference_main.child("monday").setValue(String.format("%02d:%02d", getHours(), getMinutes()));
                } else {
                    databaseReference_main.child("monday").setValue(String.format("%s,%s", dataSchedule, String.format("%02d:%02d", getHours(), getMinutes())));
                }

                databaseReference_sched = db.getReference("Schedule");
                String id = databaseReference_sched.push().getKey();
                Schedule scheduleObj = new Schedule(String.format("%02d", getHours()), String.format("%02d", getMinutes()), "monday");
                databaseReference_sched.child(id).setValue(scheduleObj);
                isSaved = true;
            } else {
                isSaved = false;
            }
        }
        if (chkTuesday.isChecked()) {
            dataSchedule = scheduleData[1];
            String[] data = dataSchedule.split(",");
            _length = data.length;
            while (_length != 0) {
                String dataSched = String.format("%02d:%02d", getHours(), getMinutes());
                for (int ctr = 0; ctr < data.length; ctr++) {
                    if (dataSched.equals(data[ctr])) {
                        isExist = true;
                        _length = 0;
                        break;
                    } else {
                        isExist = false;
                    }
                    _length = 0;
                }
            }
            if (!isExist) {
                databaseReference_main = db.getReference("MainSchedule");
                if (dataSchedule.equals("null")) {
                    databaseReference_main.child("tuesday").setValue(String.format("%02d:%02d", getHours(), getMinutes()));
                } else {
                    databaseReference_main.child("tuesday").setValue(String.format("%s,%s", dataSchedule, String.format("%02d:%02d", getHours(), getMinutes())));
                }

                databaseReference_sched = db.getReference("Schedule");
                String id = databaseReference_sched.push().getKey();
                Schedule scheduleObj = new Schedule(String.format("%02d", getHours()), String.format("%02d", getMinutes()), "tuesday");
                databaseReference_sched.child(id).setValue(scheduleObj);
                isSaved = true;
            } else {
                isSaved = false;
            }
        }
        if (chkWednesday.isChecked()) {
            dataSchedule = scheduleData[2];
            String[] data = dataSchedule.split(",");
            _length = data.length;
            while (_length != 0) {
                String dataSched = String.format("%02d:%02d", getHours(), getMinutes());
                for (int ctr = 0; ctr < data.length; ctr++) {
                    if (dataSched.equals(data[ctr])) {
                        isExist = true;
                        _length = 0;
                        break;
                    } else {
                        isExist = false;
                    }
                    _length = 0;
                }
            }
            if (!isExist) {
                databaseReference_main = db.getReference("MainSchedule");
                if (dataSchedule.equals("null")) {
                    databaseReference_main.child("wednesday").setValue(String.format("%02d:%02d", getHours(), getMinutes()));
                } else {
                    databaseReference_main.child("wednesday").setValue(String.format("%s,%s", dataSchedule, String.format("%02d:%02d", getHours(), getMinutes())));
                }

                databaseReference_sched = db.getReference("Schedule");
                String id = databaseReference_sched.push().getKey();
                Schedule scheduleObj = new Schedule(String.format("%02d", getHours()), String.format("%02d", getMinutes()), "wednesday");
                databaseReference_sched.child(id).setValue(scheduleObj);
                isSaved = true;
            } else {
                isSaved = false;
            }
        }
        if (chkThursday.isChecked()) {
            dataSchedule = scheduleData[3];
            String[] data = dataSchedule.split(",");
            _length = data.length;
            while (_length != 0) {
                String dataSched = String.format("%02d:%02d", getHours(), getMinutes());
                for (int ctr = 0; ctr < data.length; ctr++) {
                    if (dataSched.equals(data[ctr])) {
                        isExist = true;
                        _length = 0;
                        break;
                    } else {
                        isExist = false;
                    }
                    _length = 0;
                }
            }
            if (!isExist) {
                databaseReference_main = db.getReference("MainSchedule");
                if (dataSchedule.equals("null")) {
                    databaseReference_main.child("thursday").setValue(String.format("%02d:%02d", getHours(), getMinutes()));
                } else {
                    databaseReference_main.child("thursday").setValue(String.format("%s,%s", dataSchedule, String.format("%02d:%02d", getHours(), getMinutes())));
                }

                databaseReference_sched = db.getReference("Schedule");
                String id = databaseReference_sched.push().getKey();
                Schedule scheduleObj = new Schedule(String.format("%02d", getHours()), String.format("%02d", getMinutes()), "thursday");
                databaseReference_sched.child(id).setValue(scheduleObj);
                isSaved = true;
            } else {
                isSaved = false;
            }
        }
        if (chkFriday.isChecked()) {
            dataSchedule = scheduleData[4];
            String[] data = dataSchedule.split(",");
            _length = data.length;
            while (_length != 0) {
                String dataSched = String.format("%02d:%02d", getHours(), getMinutes());
                for (int ctr = 0; ctr < data.length; ctr++) {
                    if (dataSched.equals(data[ctr])) {
                        isExist = true;
                        _length = 0;
                        break;
                    } else {
                        isExist = false;
                    }
                    _length = 0;
                }
            }
            if (!isExist) {
                databaseReference_main = db.getReference("MainSchedule");
                if (dataSchedule.equals("null")) {
                    databaseReference_main.child("friday").setValue(String.format("%02d:%02d", getHours(), getMinutes()));
                } else {
                    databaseReference_main.child("friday").setValue(String.format("%s,%s", dataSchedule, String.format("%02d:%02d", getHours(), getMinutes())));
                }

                databaseReference_sched = db.getReference("Schedule");
                String id = databaseReference_sched.push().getKey();
                Schedule scheduleObj = new Schedule(String.format("%02d", getHours()), String.format("%02d", getMinutes()), "friday");
                databaseReference_sched.child(id).setValue(scheduleObj);
                isSaved = true;
            } else {
                isSaved = false;
            }
        }
        if (chkSaturday.isChecked()) {
            dataSchedule = scheduleData[5];
            String[] data = dataSchedule.split(",");
            _length = data.length;
            while (_length != 0) {
                String dataSched = String.format("%02d:%02d", getHours(), getMinutes());
                for (int ctr = 0; ctr < data.length; ctr++) {
                    if (dataSched.equals(data[ctr])) {
                        isExist = true;
                        _length = 0;
                        break;
                    } else {
                        isExist = false;
                    }
                    _length = 0;
                }
            }
            if (!isExist) {
                databaseReference_main = db.getReference("MainSchedule");
                if (dataSchedule.equals("null")) {
                    databaseReference_main.child("saturday").setValue(String.format("%02d:%02d", getHours(), getMinutes()));
                } else {
                    databaseReference_main.child("saturday").setValue(String.format("%s,%s", dataSchedule, String.format("%02d:%02d", getHours(), getMinutes())));
                }

                databaseReference_sched = db.getReference("Schedule");
                String id = databaseReference_sched.push().getKey();
                Schedule scheduleObj = new Schedule(String.format("%02d", getHours()), String.format("%02d", getMinutes()), "saturday");
                databaseReference_sched.child(id).setValue(scheduleObj);
                isSaved = true;
            } else {
                isSaved = false;
            }
        }
        if (chkSunday.isChecked()) {
            dataSchedule = scheduleData[6];
            String[] data = dataSchedule.split(",");
            _length = data.length;
            while (_length != 0) {
                String dataSched = String.format("%02d:%02d", getHours(), getMinutes());
                for (int ctr = 0; ctr < data.length; ctr++) {
                    if (dataSched.equals(data[ctr])) {
                        isExist = true;
                        _length = 0;
                        break;
                    } else {
                        isExist = false;
                    }
                    _length = 0;
                }
            }
            if (!isExist) {
                databaseReference_main = db.getReference("MainSchedule");
                if (dataSchedule.equals("null")) {
                    databaseReference_main.child("sunday").setValue(String.format("%02d:%02d", getHours(), getMinutes()));
                } else {
                    databaseReference_main.child("sunday").setValue(String.format("%s,%s", dataSchedule, String.format("%02d:%02d", getHours(), getMinutes())));
                }

                databaseReference_sched = db.getReference("Schedule");
                String id = databaseReference_sched.push().getKey();
                Schedule scheduleObj = new Schedule(String.format("%02d", getHours()), String.format("%02d", getMinutes()), "sunday");
                databaseReference_sched.child(id).setValue(scheduleObj);
                isSaved = true;
            } else {
                isSaved = false;
            }
        }
        if(isSaved) {
            Intent intent = new Intent(timelayout.this, MainActivity.class);
            startActivity(intent);
        }
    }

    private String retrieveData(DataSnapshot dataSnapshot,String day) {
        StringBuilder data = new StringBuilder();
            Log.v("test1",String.valueOf(dataSnapshot.child(day).getValue()));
            data.append(dataSnapshot.child(day).getValue());
        return data.toString();
    }
}
