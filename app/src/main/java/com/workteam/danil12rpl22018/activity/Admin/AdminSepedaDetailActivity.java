package com.workteam.danil12rpl22018.activity.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
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
import com.squareup.picasso.Picasso;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;
import com.workteam.danil12rpl22018.Initial;
import com.workteam.danil12rpl22018.R;
import com.workteam.danil12rpl22018.helper.Config;
import com.workteam.danil12rpl22018.model.SepedaModel;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import id.zelory.compressor.Compressor;

public class AdminSepedaDetailActivity extends AppCompatActivity implements IPickResult {

    private EditText etKodeSepeda, etWarnaSepeda, etMerkSepeda, etHargaSepeda, etJenisSepeda;
    private ImageView ivSepeda;
    private Button btnSave;

    private String mLoginToken = "";
    private String mUserId = "";

    private Bitmap mSelectedImage;
    private String mSelectedImagePath ;
    private File uploadFile;

    private SepedaModel model;
    private String U_ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sp = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        mLoginToken = sp.getString(Config.LOGIN_TOKEN_SHARED_PREF,"");
        mUserId = sp.getString(Config.LOGIN_ID_SHARED_PREF, "");

        if(mLoginToken.equalsIgnoreCase("") || mUserId.equalsIgnoreCase("")) {
            finish();
            Config.forceLogout(AdminSepedaDetailActivity.this);
        }
        setContentView(R.layout.activity_admin_sepeda_detail);
        binding();
        model = getIntent().getExtras().getParcelable("extra_sepeda");
        if(/*bundle*/ model != null) {
            U_ID = model.getUNIT_ID();

            etKodeSepeda.setText(model.getUNIT_KODE());
            etMerkSepeda.setText(model.getUNIT_MERK());
            etJenisSepeda.setText(model.getUNIT_JENIS());
            etWarnaSepeda.setText(model.getUNIT_WARNA());
            etHargaSepeda.setText(model.getUNIT_GAMBAR());
            Picasso.with(AdminSepedaDetailActivity.this)
                    .load(Config.BASE_URL_UPLOADS + model.getUNIT_GAMBAR())
                    .into(ivSepeda);

        }
    }
    private void binding(){
        etKodeSepeda = findViewById(R.id.et_kode);
        etMerkSepeda = findViewById(R.id.et_merk);
        etWarnaSepeda = findViewById(R.id.et_warna);
        etJenisSepeda = findViewById(R.id.et_jenis);
        etHargaSepeda = findViewById(R.id.et_hargasewa);
        ivSepeda = findViewById(R.id.ivUnitImg);
        ivSepeda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PickImageDialog.build(new PickSetup()).show(AdminSepedaDetailActivity.this);
                new PickSetup().setCameraButtonText("Gallery");
            }
        });
        btnSave = findViewById(R.id.btn_buat);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

    }

    private void saveData() {

        if (etKodeSepeda.getText().toString().trim().equalsIgnoreCase("") || TextUtils.isEmpty(etKodeSepeda.getText().toString().trim())) {
            Toast.makeText(this, "Harap isikan kode sepeda", Toast.LENGTH_SHORT).show();
            return;
        }

        if (etMerkSepeda.getText().toString().trim().equalsIgnoreCase("") || TextUtils.isEmpty(etMerkSepeda.getText().toString().trim())) {
            Toast.makeText(this, "Harap isikan merk sepeda", Toast.LENGTH_SHORT).show();
            return;
        }

        if (etWarnaSepeda.getText().toString().trim().equalsIgnoreCase("") || TextUtils.isEmpty(etWarnaSepeda.getText().toString().trim())) {
            Toast.makeText(this, "Harap isikan warna sepeda", Toast.LENGTH_SHORT).show();
            return;
        }

        if (etJenisSepeda.getText().toString().trim().equalsIgnoreCase("") || TextUtils.isEmpty(etJenisSepeda.getText().toString().trim())) {
            Toast.makeText(this, "Harap isikan jenis sepeda", Toast.LENGTH_SHORT).show();
            return;
        }

        if (etHargaSepeda.getText().toString().trim().equalsIgnoreCase("") || TextUtils.isEmpty(etHargaSepeda.getText().toString().trim())) {
            Toast.makeText(this, "Harap isikan harga sepeda", Toast.LENGTH_SHORT).show();
            return;
        }

        HashMap<String, String> body = new HashMap<>();
        body.put("act", "update_unit");
        body.put("unitId",U_ID);
        body.put("unitMerk", etMerkSepeda.getText().toString().trim());
        body.put("unitHargasewa", etHargaSepeda.getText().toString().trim());
        body.put("unitWarna", etWarnaSepeda.getText().toString().trim().toUpperCase());
        body.put("unitJenis", etJenisSepeda.getText().toString().trim().toUpperCase());
        body.put("unitKode", etKodeSepeda.getText().toString().trim().toUpperCase());
        body.put("loginToken", mLoginToken);

        AndroidNetworking.upload(Config.BASE_URL_API + "unit.php")
                .addMultipartFile("uploadFile",uploadFile)
                .addMultipartParameter(body)
                .setPriority(Priority.HIGH)
                .setOkHttpClient(((Initial) this.getApplication()).getOkHttpClient())
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    private void doNothing() {

                    }

                    @Override
                    public void onResponse(JSONObject response) {


                        String message = response.optString(Config.RESPONSE_MESSAGE_FIELD);
                        String status = response.optString(Config.RESPONSE_STATUS_FIELD);

                        Toast.makeText(AdminSepedaDetailActivity.this, message, Toast.LENGTH_LONG).show();

                        if (status.equalsIgnoreCase(Config.RESPONSE_STATUS_VALUE_SUCCESS)) {

                            Intent i = new Intent(getApplicationContext(),AdminSepedaActivity.class);
                            startActivity(i);
                            finish();
                        } else {
                            Toast.makeText(AdminSepedaDetailActivity.this, "Proses update sepeda gagal\nCoba ulangi lagi", Toast.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(AdminSepedaDetailActivity.this, "Proses update sepeda gagal\nCoba ulangi lagi", Toast.LENGTH_LONG).show();
                        Log.d("RBA", "onError: " + anError.getErrorBody());
                        Log.d("RBA", "onError: " + anError.getLocalizedMessage());
                        Log.d("RBA", "onError: " + anError.getErrorDetail());
                        Log.d("RBA", "onError: " + anError.getResponse());
                        Log.d("RBA", "onError: " + anError.getErrorCode());
                        finish();
                    }
                });

    }

    @Override
    public void onPickResult(PickResult r) {
        if (r.getError() == null) {
            try {
                File fileku = new Compressor(this)
                        .setQuality(50)
                        .setCompressFormat(Bitmap.CompressFormat.WEBP)
                        .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath())
                        .compressToFile(new File(r.getPath()));

                mSelectedImagePath = fileku.getAbsolutePath();
                uploadFile = new File(mSelectedImagePath);

                Log.d("RBA", "onPickResult: "+ mSelectedImagePath);

                mSelectedImage = r.getBitmap();
                ivSepeda.setImageBitmap(mSelectedImage);
                //selectedImageFile = new File(r.getPath());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            //Handle possible errors
            //TODO: do what you have to do with r.getError();
            Toast.makeText(this, r.getError().getMessage(), Toast.LENGTH_LONG).show();
        }

    }
}
