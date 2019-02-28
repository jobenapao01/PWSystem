package com.example.pwsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pwsystem.Model.Logs;
import com.example.pwsystem.ViewHolder.LogsViewHolder;
import com.example.pwsystem.ViewHolder.LogsViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class optionLogs extends AppCompatActivity {

    RecyclerView mLogsList;
    DatabaseReference mDatabase;
    FirebaseRecyclerOptions<Logs> options;
    FirebaseRecyclerAdapter<Logs, LogsViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_logs);

        mLogsList = (RecyclerView) findViewById(R.id.optionlogsRV);
        mLogsList.setHasFixedSize(true);
        mDatabase = FirebaseDatabase.getInstance().getReference("Logs");

        options = new FirebaseRecyclerOptions.Builder<Logs>()
                .setQuery(mDatabase, Logs.class).build();

        adapter = new FirebaseRecyclerAdapter<Logs, LogsViewHolder>(options) {
            @Override
            protected void onBindViewHolder(LogsViewHolder holder, int position,Logs model) {
                holder.tv_day.setText(model.getLogs());
                holder.tv_time.setText(model.getTimeStamp());
            }

            @Override
            public LogsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

                View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.schedule_cardview, viewGroup, false);

                return new LogsViewHolder(mView);
            }
        };

        GridLayoutManager mGridLayoutManager = new GridLayoutManager(getBaseContext(), 1);
        mLogsList.setLayoutManager(mGridLayoutManager);
        adapter.startListening();
        mLogsList.setAdapter(adapter);

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