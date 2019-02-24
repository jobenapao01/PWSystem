package com.example.pwsystem.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.pwsystem.R;

public class ScheduleViewHolder extends RecyclerView.ViewHolder {

    public TextView tv_day;
    public TextView tv_time;

    public ScheduleViewHolder(View itemView) {
        super(itemView);

        tv_day = (TextView) itemView.findViewById(R.id.tv_day);
        tv_time = (TextView) itemView.findViewById(R.id.tv_time);
    }
}
