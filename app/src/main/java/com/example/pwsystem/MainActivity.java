package com.example.pwsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private Button btnSetSchedule;
    private Button button2;
    private Button btnPower;

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);

        btnPower = (Button) findViewById(R.id.btnPower);
        btnPower.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Get Data from Firebase then compare with o and 1 then execute code.
                //databaseReference =  db.getReference("Pump");
                //databaseReference.child("pumpState").setValue("1");
            }
        });

        btnSetSchedule = (Button) findViewById(R.id.btnSetSchedule);
        btnSetSchedule.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, timelayout.class);
                startActivity(intent);
            }
        });


        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, logsActivity.class);
                startActivity(intent);
            }
        });
    }
}