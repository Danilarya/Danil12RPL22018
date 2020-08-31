package com.workteam.danil12rpl22018;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {
    TextView tvToRegister;
    private EditText txtusername, txtpassword;
    private boolean isFormFilled = false;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtusername = findViewById(R.id.txtusername);
        txtpassword = findViewById(R.id.txtpassword);
        tvToRegister = findViewById(R.id.tv_toRegister);
        tvToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,RegiserActivity.class);
                startActivity(i);
            }
        });

        btnLogin = findViewById(R.id.btnlogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFormFilled = true;
                final String username = txtusername.getText().toString();
                final String password = txtpassword.getText().toString();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Harap lengkapi isian yang tersedia", Toast.LENGTH_SHORT).show();
                    isFormFilled = false;
                }

                if (isFormFilled) {
                    HashMap<String, String> body = new HashMap<>();
                    body.put("email", username);
                    body.put("password", password);
                    AndroidNetworking.post("http://192.168.137.181/rentalsepeda/login.php")
                            .addBodyParameter(body)
                            .setOkHttpClient(((Initial) getApplication()).getOkHttpClient())
                            .setPriority(Priority.MEDIUM)
                            .build()
                            .getAsJSONObject(new JSONObjectRequestListener() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    Log.d("RBA", "respon : " + response);
                                    String status = response.optString("success");
                                    String message = response.optString("message");
                                    if (status.equalsIgnoreCase("1")) {
//                                        JSONObject payload = response.optJSONObject("payload");
//                                        String U_ID = payload.optString("id");
//                                        String U_LOGIN_TOKEN = payload.optString("email");
//                                        String U_NAME = payload.optString("username");
//                                        String U_ADDRESS = payload.optString("password");
//                                        String U_AUTHORITY_ID_1 = payload.optString("U_AUTHORITY_ID_1");
//                                        String U_EMAIL = payload.optString("U_EMAIL");
//                                        String U_GROUP_ROLE = payload.optString("U_GROUP_ROLE");

                                        Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                                        startActivity(intent);
                                        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                                        finish();
                                        finishAffinity();
//                                        preferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
//                                        preferences.edit()
//                                                .putString(Config.LOGIN_ID_SHARED_PREF, U_ID)
//                                                .putString(Config.LOGIN_TOKEN_SHARED_PREF, U_LOGIN_TOKEN)
//                                                .putString(Config.LOGIN_NAME_SHARED_PREF, U_NAME)
//                                                .putString(Config.LOGIN_ADDRESS_SHARED_PREF, U_ADDRESS)
//                                                .putString(Config.LOGIN_KTP_SHARED_PREF, U_AUTHORITY_ID_1)
//                                                .putString(Config.LOGIN_EMAIL_SHARED_PREF, U_EMAIL)
//                                                .putString(Config.LOGIN_GROUP_NAME_SHARED_PREF, U_GROUP_ROLE)
//                                                .apply();
//                                        if (U_GROUP_ROLE.equalsIgnoreCase("customer")) {
//                                            Intent intent = new Intent(LoginActivity.this, CustomerDashboardActivity.class);
//                                            startActivity(intent);
//                                            Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
//                                            finish();
//                                            finishAffinity();
//                                        }else if (U_GROUP_ROLE.equalsIgnoreCase("admin")){
//                                            Intent intent = new Intent(LoginActivity.this, AdminDashboardActivity.class);
//                                            startActivity(intent);
//                                            Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
//                                            finish();
//                                            finishAffinity();
//                                        }
                                    }
                                    else {
                                        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                                    }

                                }

                                @Override
                                public void onError(ANError anError) {

                                    Log.d("RBA", "onError: " + anError.getErrorBody());
                                    Log.d("RBA", "onError: " + anError.getLocalizedMessage());
                                    Log.d("RBA", "onError: " + anError.getErrorDetail());
                                    Log.d("RBA", "onError: " + anError.getResponse());
                                    Log.d("RBA", "onError: " + anError.getErrorCode());
                                }
                            });
                }
            }
        });

    }
}
