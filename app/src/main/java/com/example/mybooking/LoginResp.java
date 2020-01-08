package com.example.mybooking;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResp {
    @SerializedName("staff_id")
    @Expose
    public String staff_id;

    @SerializedName("status")
    @Expose
    public String status;

}