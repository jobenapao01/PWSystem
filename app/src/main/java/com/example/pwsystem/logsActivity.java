package com.example.pwsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pwsystem.Model.Schedule;
import com.example.pwsystem.ViewHolder.ScheduleViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class logsActivity extends AppCompatActivity {

    RecyclerView mScheduleList;
    DatabaseReference mDatabase;
    FirebaseRecyclerOptions<Schedule> options;
    FirebaseRecyclerAdapter<Schedule, ScheduleViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logs);

        mScheduleList = (RecyclerView) findViewById(R.id.logsRV);
        mScheduleList.setHasFixedSize(true);
        mDatabase = FirebaseDatabase.getInstance().getReference("Schedule");

        options = new FirebaseRecyclerOptions.Builder<Schedule>()
                .setQuery(mDatabase, Schedule.class).build();

        adapter = new FirebaseRecyclerAdapter<Schedule, ScheduleViewHolder>(options) {
            @Override
            protected void onBindViewHolder(ScheduleViewHolder holder, int position,Schedule model) {
                holder.tv_day.setText(model.getDay());
                holder.tv_time.setText(String.format("%s:%s", model.getHours(), model.getMinutes()));
            }

            @Override
            public ScheduleViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

                View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.schedule_cardview, viewGroup, false);

                return new ScheduleViewHolder(mView);
            }
        };

        GridLayoutManager mGridLayoutManager = new GridLayoutManager(getBaseContext(), 1);
        mScheduleList.setLayoutManager(mGridLayoutManager);
        adapter.startListening();
        mScheduleList.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(adapter!=null) {
            adapter.startListening();
        }
    }

    @Override
    protected void onStop() {
        if(adapter!=null) {
            adapter.stopListening();
        }
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(adapter!=null) {
            adapter.startListening();
        }
    }
}

