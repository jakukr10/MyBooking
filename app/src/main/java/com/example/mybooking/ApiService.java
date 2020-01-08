package com.example.mybooking;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {
    @FormUrlEncoded
    @POST("api/login/authorize")//send data to the server
    Call<LoginResp> getLogin(//create model class LoginResp first.
                             @Field("email") String email,//in fied represent the key
                             @Field("password") String password
    );

}