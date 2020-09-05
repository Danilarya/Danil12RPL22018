package com.workteam.danil12rpl22018;

import androidx.appcompat.app.AppCompatActivity;

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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {
    TextView tvToRegister;
    private EditText txtusername, txtpassword;
    private boolean isFormFilled = false;
    private Button btnLogin;
    private String roleuser;

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
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
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
                    AndroidNetworking.post(config.BASE_URL+"login.php")
                            .addBodyParameter(body)
                            .setOkHttpClient(((Initial) getApplication()).getOkHttpClient())
                            .setPriority(Priority.MEDIUM)
                            .build()
                            .getAsJSONObject(new JSONObjectRequestListener() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    Log.d("e", "respon : " + response);
                                    String status = response.optString(config.RESPONSE_STATUS_FIELD);
                                    String message = response.optString(config.RESPONSE_MESSAGE_FIELD);
//                                    String login = response.optString("login");
                                    if (message.equalsIgnoreCase(config.RESPONSE_STATUS_VALUE_SUCCESS)) {
                                        JSONArray loginArray = response.optJSONArray("login");
                                        if (loginArray == null) return;
                                        for (int i = 0; i <loginArray.length(); i++) {
                                            final JSONObject aLogin = loginArray.optJSONObject(i);
                                            roleuser = aLogin.optString("roleuser");
                                        }
                                        Log.d("AGG", "respon : " + roleuser);
                                        if (roleuser.equalsIgnoreCase("admin")) {
                                            Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
                                            startActivity(intent);
                                            Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                                            finish();
                                            finishAffinity();
                                        }else {
                                            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                                            startActivity(intent);
                                            Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                                            finish();
                                            finishAffinity();
                                        }
                                    }
                                    else {
                                        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                                    }

                                }

                                @Override
                                public void onError(ANError anError) {

                                    Log.d("e", "onError: " + anError.getErrorBody());
                                    Log.d("e", "onError: " + anError.getLocalizedMessage());
                                    Log.d("e", "onError: " + anError.getErrorDetail());
                                    Log.d("e", "onError: " + anError.getResponse());
                                    Log.d("e", "onError: " + anError.getErrorCode());
                                }
                            });
                }
            }
        });

    }
}
