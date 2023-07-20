package com.azhar.wisatapurwakarta.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.azhar.wisatapurwakarta.R;
import com.azhar.wisatapurwakarta.api.Api;
import com.azhar.wisatapurwakarta.model.ModelKomunitas;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

public class DetailKomunitasActivity extends AppCompatActivity {
    Toolbar tbDetailKomunitas;
    TextView tvNamaKomunitas, tvPhoneKomunitas, tvOpenTime, tvDesc;
    String idKomunitas, NamaKomunitas, PhoneKomunitas, OpenTime, Desc;
    ModelKomunitas modelKomunitas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_komunitas);

        tbDetailKomunitas = findViewById(R.id.tbDetailKomunitas);
        tbDetailKomunitas.setTitle("Detail Komunitas");
        setSupportActionBar(tbDetailKomunitas);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        modelKomunitas = (ModelKomunitas) getIntent().getSerializableExtra("detailKomunitas");
        if (modelKomunitas != null) {
            idKomunitas = modelKomunitas.getIdKomunitas();
            NamaKomunitas = modelKomunitas.getTxtNamaKomunitas();

            //set Id
            tvNamaKomunitas = findViewById(R.id.tvNamaKomunitas);
            tvPhoneKomunitas = findViewById(R.id.tvPhoneKomunitas);
            tvOpenTime = findViewById(R.id.tvOpenTime);
            tvDesc = findViewById(R.id.tvDesc);
            getDetailKomunitas();
        }
    }

    private void getDetailKomunitas() {
        AndroidNetworking.get(Api.DetailKomunitas)
                .addPathParameter("id", idKomunitas)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                //get String Api
                                NamaKomunitas = response.getString("nama");
                                PhoneKomunitas = response.getString("kontak");
                                OpenTime = response.getString("jadwal");
                                Desc = response.getString("deskripsi");

                                //set Text
                                tvNamaKomunitas.setText(NamaKomunitas);
                                tvPhoneKomunitas.setText(PhoneKomunitas);
                                tvOpenTime.setText(OpenTime);
                                tvDesc.setText(Desc);

                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(DetailKomunitasActivity.this,
                                        "Gagal menampilkan data!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(DetailKomunitasActivity.this,
                                "Tidak ada jaringan internet!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}