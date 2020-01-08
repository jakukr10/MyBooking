package com.example.mybooking;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class Login extends AppCompatActivity {
    EditText email, password;
    ProgressBar progressbar;
    Button loginbutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        progressbar = (ProgressBar) findViewById(R.id.progressbar);
        email.clearFocus();//email and passwordnu clear focus use cheyyunnu
        progressbar.clearFocus();
        loginbutton = (Button) findViewById(R.id.loginbtn);

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().toString().equals("") || password.getText().toString().equals("")) {
                    Toast.makeText(Login.this, "enter email and password", Toast.LENGTH_SHORT).show();
                } else {
                    checkLogin(email.getText().toString(), password.getText().toString());
                }


            }


        });

    }

    public void checkLogin(String username, String password) {

        ApiService service = ApiClient.getApiService().create(ApiService.class);//before calling this method create retrofit.(go ApiClient.java)


        //each call from the ApiService can make Http request to the server
        Call<LoginResp> resultCall = service.getLogin(username, password);

        //get results from asynchronous requests.impliment callback with enqueue

        resultCall.enqueue(new Callback<LoginResp>() {
            @Override
            public void onResponse(Call<LoginResp> call, Response<LoginResp> response) {//call,responce overide cheytappo vanna variable
                Log.d("pro fetch", "-- " + call.request().url().toString());//for debugging need to look

                LoginResp resp = response.body();//resp enna variable created and stored responce of the body


                if (resp.status.equals("200")) {

                    SharedPreferences sharedPreferences = getSharedPreferences("myp" + "ref", 0);//obtain the instance of the shared preferences class
                    //("myp" + "ref" is file name )

                    SharedPreferences.Editor edit = sharedPreferences.edit();//create the editor object
                    edit.putString("userid",resp.staff_id);//save key(userid) value(staff_id) in sharedpreferences
                    edit.commit();//commit for save changes in sharedprefrences

                    Intent intent = new Intent(Login.this,MainActivity.class);
                    startActivity(intent);
                    Login.this.finish();
                }

                else{

                    Toast.makeText(getApplicationContext(),"Invalid credentials!",Toast.LENGTH_SHORT).show();
                }
                progressbar.setVisibility(View.VISIBLE);

            }

            @Override
            public void onFailure(Call<LoginResp> call, Throwable t) {
                Log.d("errrr", t.getMessage());
                Toast.makeText(getApplicationContext(),"Login Failed!",Toast.LENGTH_SHORT).show();
                progressbar
                        .setVisibility(View.VISIBLE);


            }
        });}



}
