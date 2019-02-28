package com.example.pwsystem;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class StatusActivity extends AppCompatActivity {

    private String data;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference databaseReferenceSoil;
    DatabaseReference databaseReferenceWater;
    private TextView tvSoil;
    private TextView tvWater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        tvSoil = (TextView) findViewById(R.id.tvSoilStat);
        tvWater = (TextView) findViewById(R.id.tvWaterStat);

        databaseReferenceSoil =  db.getReference("Soil");
        databaseReferenceSoil.child("soilValue").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                data = retrieveDataSoil(dataSnapshot);
                tvSoil.setText(data);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReferenceWater =  db.getReference("Water");
        databaseReferenceWater.child("waterValue").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                data = retrieveDataWater(dataSnapshot);
                tvWater.setText(data);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private String retrieveDataSoil(DataSnapshot dataSnapshot) {
        StringBuilder data = new StringBuilder();
        Log.v("test1",String.valueOf(dataSnapshot.getValue()));
        data.append(dataSnapshot.getValue());
        return data.toString();
    }

    private String retrieveDataWater(DataSnapshot dataSnapshot) {
        StringBuilder data = new StringBuilder();
        Log.v("test1",String.valueOf(dataSnapshot.getValue()));
        data.append(dataSnapshot.getValue());
        return data.toString();
    }
}
