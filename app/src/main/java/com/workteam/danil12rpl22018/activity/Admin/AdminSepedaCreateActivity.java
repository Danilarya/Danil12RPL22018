package com.workteam.danil12rpl22018.activity.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;
import com.workteam.danil12rpl22018.Initial;
import com.workteam.danil12rpl22018.R;
import com.workteam.danil12rpl22018.helper.Config;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import id.zelory.compressor.Compressor;

public class AdminSepedaCreateActivity extends AppCompatActivity implements IPickResult {

    private EditText et_kode, et_merk, et_warna, et_hargasewa, et_jenis;
    private Button btn_buat;
    private ImageView iv_sepeda;
    private String mSelectedImagePath = "";
    File mSelectedFileBanner;
    private Bitmap mSelectedImage;
    private boolean mIsFormFilled = false;
    private String mLoginToken = "";
    private String mUserId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sp = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        mLoginToken = sp.getString(Config.LOGIN_TOKEN_SHARED_PREF,"");
        mUserId = sp.getString(Config.LOGIN_ID_SHARED_PREF, "");

        if(mLoginToken.equalsIgnoreCase("") || mUserId.equalsIgnoreCase("")) {
            finish();
            Config.forceLogout(AdminSepedaCreateActivity.this);
        }
        setContentView(R.layout.activity_admin_sepeda_create);
        binding();
    }
    private void binding(){
        iv_sepeda = findViewById(R.id.ivUnitImg);
        iv_sepeda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PickImageDialog.build(new PickSetup()).show(AdminSepedaCreateActivity.this);
                new PickSetup().setCameraButtonText("Gallery");
            }
        });
        et_kode = findViewById(R.id.et_kode);
        et_merk = findViewById(R.id.et_merk);
        et_warna = findViewById(R.id.et_warna);
        et_jenis = findViewById(R.id.et_jenis);
        et_hargasewa = findViewById(R.id.et_hargasewa);
        btn_buat = findViewById(R.id.btn_buat);
        btn_buat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIsFormFilled = true;
                final String name = et_kode.getText().toString();
                final String phone = et_merk.getText().toString();
                final String address = et_warna.getText().toString();
                final String jenis = et_jenis.getText().toString();
                final String ktp = et_hargasewa.getText().toString();


                if (name.isEmpty() || phone.isEmpty() || address.isEmpty() || ktp.isEmpty() || jenis.isEmpty()) {
                    Toast.makeText(AdminSepedaCreateActivity.this, "Harap lengkapi isian yang tersedia", Toast.LENGTH_SHORT).show();
                    mIsFormFilled = false;
                }

                if (mIsFormFilled) {
                    HashMap<String, String> body = new HashMap<>();
                    body.put("act", "create_unit");
                    body.put("loginToken",mLoginToken);
                    body.put("unitKode", phone);
                    body.put("unitMerk", name);
                    body.put("unitWarna", address);
                    body.put("unitJenis", jenis);
                    body.put("unitHargasewa", ktp);

                    AndroidNetworking.upload(Config.BASE_URL_API + "unit.php")
                            .addMultipartParameter(body)
                            .addMultipartFile("uploadFile", mSelectedFileBanner)
                            .setPriority(Priority.MEDIUM)
                            .setOkHttpClient(((Initial) getApplication()).getOkHttpClient())
                            .build()
                            .getAsJSONObject(new JSONObjectRequestListener() {
                                @Override
                                public void onResponse(JSONObject response) {

                                    Log.d("HBB", "onResponseImage: " + response);
                                    String status = response.optString(Config.RESPONSE_STATUS_FIELD);
                                    String message = response.optString(Config.RESPONSE_MESSAGE_FIELD);

                                    Toast.makeText(AdminSepedaCreateActivity.this, message, Toast.LENGTH_LONG).show();

                                    if (status.trim().equalsIgnoreCase(Config.RESPONSE_STATUS_VALUE_SUCCESS)) {
                                        Intent intent = new Intent(AdminSepedaCreateActivity.this, AdminSepedaActivity.class);
                                        startActivity(intent);
                                        finishAffinity();
                                    }
                                    else {
                                        Toast.makeText(AdminSepedaCreateActivity.this,"proses gagal",Toast.LENGTH_SHORT).show();
                                    }

                                }

                                @Override
                                public void onError(ANError anError) {

                                    Log.d("HBB", "onError: " + anError.getErrorBody());
                                    Log.d("HBB", "onError: " + anError.getLocalizedMessage());
                                    Log.d("HBB", "onError: " + anError.getErrorDetail());
                                    Log.d("HBB", "onError: " + anError.getResponse());
                                    Log.d("HBB", "onError: " + anError.getErrorCode());
                                    Toast.makeText(AdminSepedaCreateActivity.this, Config.TOAST_AN_ERROR, Toast.LENGTH_SHORT).show();

                                }
                            });
                }

            }
        });
    }

    @Override
    public void onPickResult(PickResult r) {
        if(r.getError() == null){
            try {
                File fileku = new Compressor(this)
                        .setQuality(50)
                        .setCompressFormat(Bitmap.CompressFormat.WEBP)
                        .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath())
                        .compressToFile(new File(r.getPath()));
                mSelectedImagePath = fileku.getAbsolutePath();
                mSelectedFileBanner = new File(mSelectedImagePath);
                mSelectedImage=r.getBitmap();
                iv_sepeda.setImageBitmap(mSelectedImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            Toast.makeText(AdminSepedaCreateActivity.this, r.getError().getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}
