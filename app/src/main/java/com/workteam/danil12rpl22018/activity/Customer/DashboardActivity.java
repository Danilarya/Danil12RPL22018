package com.workteam.danil12rpl22018.activity.Customer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.workteam.danil12rpl22018.R;
import com.workteam.danil12rpl22018.activity.Admin.AdminDashboardActivity;
import com.workteam.danil12rpl22018.helper.Config;

public class DashboardActivity extends AppCompatActivity {
    private ImageView iv_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        iv_logout = findViewById(R.id.iv_logout);
        iv_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
    }
    private void logout() {
        new AlertDialog.Builder(DashboardActivity.this)
                .setTitle("Logout")
                .setMessage("Anda yakin akan logout ?")
                .setNegativeButton("Tidak", null)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    private void doNothing() {

                    }

                    public void onClick(DialogInterface arg0, int arg1) {
                        Config.forceLogout(DashboardActivity.this);
                    }
                }).create().show();

    }
}
