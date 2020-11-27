package com.example.kadesa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kadesa.helper.AppSession;
import com.example.kadesa.helper.apihelper.BaseApiService;
import com.example.kadesa.helper.apihelper.UtilsApi;
import com.example.kadesa.model.User;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    TextView btnLupaPass, btnRegister;
    EditText tv_username, tv_password;
    private final BaseApiService baseApiService = UtilsApi.getApiService();
    User user;
    AppSession appSession;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.btn_signIn);
        btnLupaPass = (TextView) findViewById(R.id.btn_lupaPass);
        btnRegister = (TextView) findViewById(R.id.btn_goToRegister);
        tv_username = (EditText) findViewById(R.id.tv_username);
        tv_password = (EditText) findViewById(R.id.tv_password);

        appSession = new AppSession(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignInNow();
            }
        });

        btnLupaPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ResetPasswordActivity.class);
                startActivity(intent);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    public void SignInNow(){

        String username = tv_username.getText().toString();
        String password = tv_password.getText().toString();

        baseApiService.loginRequest(username, password).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        String result = response.body().string();
                        if (result!=null){
                            user = new Gson().fromJson(result, User.class);
                            appSession.createSession(user.getToken(), user);
                            appSession.setData(AppSession.USER, new Gson().toJson(user));
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    View parentLayout = findViewById(android.R.id.content);
                    Snackbar.make(parentLayout, response.message(), Snackbar.LENGTH_LONG);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                View parentLayout = findViewById(android.R.id.content);
                Snackbar.make(parentLayout, t.getMessage(), Snackbar.LENGTH_LONG);
            }
        });

    }
}