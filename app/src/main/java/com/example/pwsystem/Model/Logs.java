package com.example.pwsystem.Model;

public class Logs {

    private String logs;
    private String timeStamp;

    public Logs() {
    }

    public Logs(String logs, String timeStamp) {
        this.logs = logs;
        this.timeStamp = timeStamp;
    }

    public String getLogs() {
        return logs;
    }

    public void setLogs(String logs) {
        this.logs = logs;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
