package com.workteam.danil12rpl22018.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.workteam.danil12rpl22018.R;
import com.workteam.danil12rpl22018.activity.Admin.AdminSepedaActivity;
import com.workteam.danil12rpl22018.helper.Config;
import com.workteam.danil12rpl22018.model.SepedaModel;

import org.json.JSONObject;

import java.util.List;

public class AdminSepedaAdapter extends RecyclerView.Adapter<AdminSepedaAdapter.SepedaViewHolder> {
    private Context mContext;
    private List<SepedaModel> mList;
    private String mLoginToken = "";
    private boolean mBusy = false;
    private ProgressDialog mProgressDialog;
    private AdminSepedaActivity mAdminUserActivity;

    public AdminSepedaAdapter(Context context, List<SepedaModel> mList, String loginToken, Activity SepedaActivity) {
        this.mContext = context;
        this.mList = mList;
        this.mLoginToken = loginToken;
        this.mAdminUserActivity = (AdminSepedaActivity) SepedaActivity;

    }

    @NonNull
    @Override
    public AdminSepedaAdapter.SepedaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_admin_sepeda_layout, parent, false);
        return new SepedaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminSepedaAdapter.SepedaViewHolder holder, int position) {
        final SepedaModel Amodel = mList.get(position);
        holder.bind(Amodel);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void clearData() {
        int size = this.mList.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                this.mList.remove(0);
            }
        }
    }

        public class SepedaViewHolder extends RecyclerView.ViewHolder {
            private RelativeLayout divDetail;
            private TextView tvKodeSepeda, tvMerkSepeda;
            private ImageView divDelete;
            public SepedaViewHolder(@NonNull View itemView) {
                super(itemView);
                divDelete = itemView.findViewById(R.id.ivDelete);
                tvKodeSepeda = itemView.findViewById(R.id.tvKode);
                tvMerkSepeda = itemView.findViewById(R.id.tvMerk);
            }
            private void bind(final SepedaModel Amodel) {
                tvKodeSepeda.setText(Amodel.getUNIT_KODE());
                tvMerkSepeda.setText(Amodel.getUNIT_MERK());
//            if(Amodel.getUNIT_GAMBAR().contains(Config.UPLOAD_FOLDER)) {
//                Picasso.with(mContext)
//                        .load(Config.BASE_URL + Amodel.getUNIT_GAMBAR())
//                        .into(iv_sepeda);
//            }
//            else {
//                Picasso.with(mContext)
//                        .load(Config.BASE_URL_UPLOADS + Amodel.getUNIT_GAMBAR())
//                        .into(iv_sepeda);
//            }
//            divDetail.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    AppHelper.goToAdminSepedaDetail(mContext, Amodel);
//                }
//            });

                divDelete.setOnClickListener(new View.OnClickListener() {
                    private void doNothing() {

                    }

                    @Override
                    public void onClick(View view) {
                        if(mBusy) {
                            Toast.makeText(mContext, "Harap tunggu proses sebelumnya selesai...", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
                        alertDialogBuilder.setMessage("Hapus data image ?");
                        alertDialogBuilder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            private void doNothing() {

                            }

                            public void onClick(DialogInterface arg0, int arg1) {
                                deleteData(String.valueOf(Amodel.getUNIT_ID()));
                            }
                        });
                        alertDialogBuilder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            private void doNothing() {

                            }

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                arg0.dismiss();
                            }
                        });

                        //Showing the alert dialog
                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                    }
                });
            }

            private void deleteData(String id) {
                if(mBusy) {
                    Toast.makeText(mContext, "Harap tunggu proses sebelumnya selesai...", Toast.LENGTH_SHORT).show();
                    return;
                }

                mProgressDialog = new ProgressDialog(mContext);
                mProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                mProgressDialog.setMessage("Proses ...");
                mProgressDialog.setCancelable(true);
                mProgressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Batal", new DialogInterface.OnClickListener() {
                    private void doNothing() {

                    }

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                if(!mProgressDialog.isShowing())    mProgressDialog.show();

                Log.d("HBB", "act:delete_unit\n" +
                        "loginToken:" + mLoginToken + "\n" +
                        "imgId:" + id);

                AndroidNetworking.post(Config.BASE_URL_API + "unitfrontend.php")
                        .addBodyParameter("act", "delete_unit")
                        .addBodyParameter("loginToken", mLoginToken)
                        .addBodyParameter("unitId", id)
                        .setPriority(Priority.HIGH)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject jsonResponse) {
                                mBusy = false;
                                if(mProgressDialog != null) mProgressDialog.dismiss();

                                String message = jsonResponse.optString(Config.RESPONSE_MESSAGE_FIELD);
                                String status = jsonResponse.optString(Config.RESPONSE_STATUS_FIELD);

                                if(status != null && status.equalsIgnoreCase(Config.RESPONSE_STATUS_VALUE_SUCCESS)) {
                                    mAdminUserActivity.getSepedaList();
                                }
                                else {
                                    Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onError(ANError anError) {
                                mBusy = false;
                                if(mProgressDialog != null) mProgressDialog.dismiss();

                                Toast.makeText(mContext, Config.TOAST_AN_ERROR, Toast.LENGTH_SHORT).show();
                                Log.d("HBB", "onError: " + anError.getErrorBody());
                                Log.d("HBB", "onError: " + anError.getLocalizedMessage());
                                Log.d("HBB", "onError: " + anError.getErrorDetail());
                                Log.d("HBB", "onError: " + anError.getResponse());
                                Log.d("HBB", "onError: " + anError.getErrorCode());
                            }
                        });
            }

        }

}
