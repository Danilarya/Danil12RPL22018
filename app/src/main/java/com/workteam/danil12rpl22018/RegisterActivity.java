package com.workteam.danil12rpl22018;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    private TextView tvToLogin, txtusername, txtEmail, txtNomorHp, txtAlamat, txtNoKtp, txtpassword;
    private Button btnRegister;
    private boolean mIsFormFilled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regiser);
        txtusername = findViewById(R.id.txtusername);
        txtpassword = findViewById(R.id.txtpassword);
        txtNoKtp = findViewById(R.id.txtNoKtp);
        txtAlamat = findViewById(R.id.txtAlamat);
        txtNomorHp = findViewById(R.id.txtNomorHp);
        txtEmail = findViewById(R.id.txtEmail);
        tvToLogin = findViewById(R.id.tv_toLogin);
        tvToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });


        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mIsFormFilled = true;
                final String username = txtusername.getText().toString();
                final String email = txtEmail.getText().toString().trim();
                final String password = txtpassword.getText().toString();
                final String notlp = txtNomorHp.getText().toString();
                final String noktp = txtNoKtp.getText().toString();
                final String alamat = txtAlamat.getText().toString();



                if (username.isEmpty() || notlp.isEmpty() || alamat.isEmpty() || noktp.isEmpty() || password.isEmpty() || email.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Harap lengkapi kolom register !", Toast.LENGTH_SHORT).show();
                    mIsFormFilled = false;
                }

//                if(tilReferal.getVisibility() == View.VISIBLE) {
//                    if(referal.isEmpty()) {
//                        Toast.makeText(RegisterActivity.this, "Harap lengkapi isian yang tersedia", Toast.LENGTH_SHORT).show();
//                        mIsFormFilled = false;
//                    }
//                }

//                if(password.length() < 8) {
//                    Toast.makeText(RegisterActivity.this, "Panjang password minimal 8 karakter", Toast.LENGTH_SHORT).show();
//                    mIsFormFilled = false;
//                }
//                if(!password.equalsIgnoreCase(passwordConfirm)) {
//                    Toast.makeText(RegisterActivity.this, "Harap samakan isian password dan konfirmasi password", Toast.LENGTH_SHORT).show();
//                    mIsFormFilled = false;
//                }

//                if(tilReferal.getVisibility() == View.VISIBLE && referal.isEmpty()) {
//                    Toast.makeText(RegisterActivity.this, "Harap isikan nama marketing yang mereferensikan", Toast.LENGTH_SHORT).show();
//                    mIsFormFilled = false;
//                }


                if (mIsFormFilled) {
                    HashMap<String, String> body = new HashMap<>();
//                        body.put("a", "register");
//                    try {
//                        final MCrypt mcrypt = new MCrypt();
//                        String encrypted = MCrypt.bytesToHex(mcrypt.encrypt(password));
//                        body.put("passwordEnc", encrypted);
//                    }
//                    catch (Exception e) {
//                        Log.d("RBA", "Exception : " + e.getMessage());
//                        body.put("password", password);
//                    }

                    body.put("email", email);
                    body.put("password", password);
                    body.put("username", username);
                    body.put("notlp", notlp);
                    body.put("alamat", alamat);
                    body.put("noktp", noktp);
                    AndroidNetworking.post(config.BASE_URL+"register.php")
                            .addBodyParameter(body)
                            .setPriority(Priority.MEDIUM)
                            .setOkHttpClient(((Initial) getApplication()).getOkHttpClient())
                            .build()
                            .getAsJSONObject(new JSONObjectRequestListener() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try {

//                                            String status = response.getString(config.RESPONSE_STATUS_FIELD);
                                        String message = response.getString(config.RESPONSE_MESSAGE_FIELD);

                                        Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_LONG).show();
                                        Log.d("f", "response: "+message);
                                        if (message.equalsIgnoreCase(config.RESPONSE_STATUS_VALUE_SUCCESS)) {

                                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                            startActivity(intent);
                                            finishAffinity();
                                        }
                                    }
                                    catch (JSONException e) {
                                        e.printStackTrace();
                                        Log.d("b", "JSONException: " + e.getMessage());
                                    }
                                }

                                @Override
                                public void onError(ANError anError) {
//                                        mProgress.dismiss();
                                    Toast.makeText(RegisterActivity.this, config.TOAST_AN_EROR, Toast.LENGTH_SHORT).show();
                                    Log.d("ab", "onError: " + anError.getErrorBody());
                                    Log.d("ab", "onError: " + anError.getLocalizedMessage());
                                    Log.d("ab", "onError: " + anError.getErrorDetail());
                                    Log.d("ab", "onError: " + anError.getResponse());
                                    Log.d("ab", "onError: " + anError.getErrorCode());
                                }
                            });
                }

            }
        });
    }
}
