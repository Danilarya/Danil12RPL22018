package com.workteam.danil12rpl22018.activity.Admin;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.workteam.danil12rpl22018.Initial;
import com.workteam.danil12rpl22018.R;
import com.workteam.danil12rpl22018.helper.Config;
import com.workteam.danil12rpl22018.model.UserAdminModel;

import org.json.JSONObject;

import java.util.HashMap;

public class AdminUserDetailActivity extends AppCompatActivity {
    private EditText etNama, etAlamat, etKtp, etPhone, etEmail;
    private Button btn_simpan;

    private String U_ID;
    private String mLoginToken = "";
    private String mUserId = "";
    private UserAdminModel mOrderData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sp = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

        mLoginToken = sp.getString(Config.LOGIN_TOKEN_SHARED_PREF,"");
        mUserId = sp.getString(Config.LOGIN_ID_SHARED_PREF, "");

        if(mLoginToken.equalsIgnoreCase("") || mUserId.equalsIgnoreCase("")) {
            finish();
            Config.forceLogout(AdminUserDetailActivity.this);
        }
        setContentView(R.layout.activity_detail_user);
        binding();
        mOrderData = getIntent().getExtras().getParcelable("extra_user");
        if(/*bundle*/ mOrderData != null) {
            U_ID = mOrderData.getU_ID();

            etNama.setText(mOrderData.getU_NAME());
            etPhone.setText(mOrderData.getU_PHONE());
            etEmail.setText(mOrderData.getU_EMAIL());
            etKtp.setText(mOrderData.getU_AUTHORITY_ID_1());
            etAlamat.setText(mOrderData.getU_ADDRESS());

        }

    }
    private void binding(){
        etNama = findViewById(R.id.tv_username);
        etAlamat = findViewById(R.id.tv_alamat);
        etEmail = findViewById(R.id.tv_email);
        etKtp = findViewById(R.id.tv_noKtp);
        etPhone = findViewById(R.id.tv_phone);
        btn_simpan = findViewById(R.id.btn_simpan);
        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> body = new HashMap<>();
                body.put("act", "update_user");
                body.put("loginToken", mLoginToken);
                body.put("uId", U_ID);
                body.put("uName", etNama.getText().toString());
                body.put("uAddress", etAlamat.getText().toString());
                body.put("uEmail", etEmail.getText().toString());
                body.put("uKtp", etKtp.getText().toString());
                body.put("uPhone", etPhone.getText().toString());

                AndroidNetworking.post(Config.BASE_URL_API + "auth.php")
                        .addBodyParameter(body)
                        .setPriority(Priority.MEDIUM)
                        .setOkHttpClient(((Initial) getApplication()).getOkHttpClient())
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {

                                String message = response.optString(Config.RESPONSE_MESSAGE_FIELD);
                                String status = response.optString(Config.RESPONSE_STATUS_FIELD);

                                Toast.makeText(AdminUserDetailActivity.this, message, Toast.LENGTH_LONG).show();

                                if (status.equalsIgnoreCase(Config.RESPONSE_STATUS_VALUE_SUCCESS)) {
                                    Toast.makeText(AdminUserDetailActivity.this,"Update berhasil",Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                                else {
                                    Toast.makeText(AdminUserDetailActivity.this,"Update gagal",Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onError(ANError anError) {
                                Toast.makeText(AdminUserDetailActivity.this, Config.TOAST_AN_ERROR, Toast.LENGTH_SHORT).show();
                                Log.d("RBA", "onError: " + anError.getErrorBody());
                                Log.d("RBA", "onError: " + anError.getLocalizedMessage());
                                Log.d("RBA", "onError: " + anError.getErrorDetail());
                                Log.d("RBA", "onError: " + anError.getResponse());
                                Log.d("RBA", "onError: " + anError.getErrorCode());

                            }
                        });

            }
        });
    }
}
