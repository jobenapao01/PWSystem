package com.example.pwsystem;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;


import com.example.pwsystem.Model.Logs;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private Button btnSetSchedule;
    private Button button2;
    private Button btnPower;
    private Button btnstats;
    Button btn;
    EditText numbTxt;
    String sNum;
    private String data;
    private String soilData;
    private String waterData;


    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;
    DatabaseReference databaseReference_soil;
    DatabaseReference databaseReference_logs;
    DatabaseReference databaseReference_water;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);
        btnPower = (Button) findViewById(R.id.btnPower);

        databaseReference =  db.getReference("Pump");
        databaseReference.child("pumpState").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                data = retrieveData(dataSnapshot);
                if(data.equals("0")) {
                    btnPower.setText("OFF");
                } else {
                    btnPower.setText("ON");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.v("Error", "error");
            }
        });

        databaseReference_soil = db.getReference("Soil");
        databaseReference_soil.child("soilValue").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                soilData = retrieveSoil(dataSnapshot);
                if(soilData.equals("WET")) {
                    Snackbar snackbar = Snackbar.make(findViewById(R.id.idLayout), "SOIL IS ALREADY WET", Snackbar.LENGTH_LONG)
                            .setAction("", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //
                                }
                            });

                    snackbar.show();
                } else {
                    Snackbar snackbar = Snackbar.make(findViewById(R.id.idLayout), "SOIL IS ALREADY DRY", Snackbar.LENGTH_LONG)
                            .setAction("", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //
                                }
                            });

                    snackbar.show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference_water = db.getReference("Water");
        databaseReference_water.child("waterValue").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                waterData = retrieveWater(dataSnapshot);
                if(waterData.equals("EMPTY")) {
                    Snackbar snackbar = Snackbar.make(findViewById(R.id.idLayout), "PLEASE REFILL YOUR WATER STATION.", Snackbar.LENGTH_LONG)
                            .setAction("", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //
                                }
                            });

                    snackbar.show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnPower.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(soilData.equals("DRY")) {
                    if(data.equals("0")) {
                        if(waterData.equals("FILLED")) {
                            btnPower.setText("ON");
                            databaseReference.child("pumpState").setValue("1");
                            Snackbar snackbar = Snackbar.make(findViewById(R.id.idLayout), "PUMP STATE IS ON", Snackbar.LENGTH_LONG)
                                    .setAction("", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            //
                                        }
                                    });

                            snackbar.show();
                            databaseReference_logs = db.getReference("Logs");
                            String id = databaseReference_logs.push().getKey();
                            Logs logsObj = new Logs("PWSystem successfully watered the plant.", "");
                            databaseReference_logs.child(id).setValue(logsObj);
                        } else {
                            Snackbar snackbar = Snackbar.make(findViewById(R.id.idLayout), "PLEASE REFILL YOUR WATER STATION.", Snackbar.LENGTH_LONG)
                                    .setAction("", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            //
                                        }
                                    });

                            snackbar.show();
                        }
                    } else {
                        btnPower.setText("OFF");
                        databaseReference.child("pumpState").setValue("0");
                        Snackbar snackbar = Snackbar.make(findViewById(R.id.idLayout), "PUMP STATE IS OFF", Snackbar.LENGTH_LONG)
                                .setAction("", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        //
                                    }
                                });

                        snackbar.show();
                    }
                } else {
                    btnPower.setText("OFF");
                    Snackbar snackbar = Snackbar.make(findViewById(R.id.idLayout), "SOIL IS ALREADY WET", Snackbar.LENGTH_LONG)
                            .setAction("", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //
                                }
                            });

                    snackbar.show();
                }
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

        btnstats = (Button) findViewById(R.id.btnstats);
        btnstats.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StatusActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.layout.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Logs:
                Toast.makeText(this, "LOGS", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, optionLogs.class);
                startActivity(intent);
                return true;
            case R.id.About:
                Toast.makeText(this, "ABOUT", Toast.LENGTH_SHORT).show();
                Intent _intent = new Intent(MainActivity.this, optionAbout.class);
                startActivity(_intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private String retrieveData(DataSnapshot dataSnapshot) {
        StringBuilder data = new StringBuilder();
        Log.v("test1",String.valueOf(dataSnapshot.getValue()));
        data.append(dataSnapshot.getValue());
        return data.toString();
    }

    private String retrieveSoil(DataSnapshot dataSnapshot) {
        StringBuilder data = new StringBuilder();
        Log.v("test1",String.valueOf(dataSnapshot.getValue()));
        data.append(dataSnapshot.getValue());
        return data.toString();
    }

    private String retrieveWater(DataSnapshot dataSnapshot) {
        StringBuilder data = new StringBuilder();
        Log.v("test1",String.valueOf(dataSnapshot.getValue()));
        data.append(dataSnapshot.getValue());
        return data.toString();
    }
}




