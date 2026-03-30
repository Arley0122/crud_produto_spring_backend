package com.example.demo;

import java.time.Instant;

public class ErrorResponse {
    int status;
    String message;
    Instant timeAmp;

    public void setStatus(int status){this.status = status;}
    public void setMessage(String message){this.message = message;}
    public void setTimeAmp(Instant timeAmp){this.timeAmp = timeAmp;}

    public int getStatus(){return this.status;}
    public String getMessage(){return this.message;}
    public Instant getTimeAmp(){return this.timeAmp;}
    
}
