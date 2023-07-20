package com.azhar.wisatapurwakarta.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.azhar.wisatapurwakarta.R;
import com.azhar.wisatapurwakarta.adapter.KomunitasAdapter;
import com.azhar.wisatapurwakarta.adapter.KomunitasAdapter;
import com.azhar.wisatapurwakarta.api.Api;
import com.azhar.wisatapurwakarta.decoration.LayoutMarginDecoration;
import com.azhar.wisatapurwakarta.model.ModelKomunitas;
import com.azhar.wisatapurwakarta.model.ModelKomunitas;
import com.azhar.wisatapurwakarta.utils.Tools;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class KomunitasActivity extends AppCompatActivity implements KomunitasAdapter.onSelectData {

    RecyclerView rvKomunitas;
    LayoutMarginDecoration gridMargin;
    KomunitasAdapter komunitasAdapter;
    ProgressDialog progressDialog;
    List<ModelKomunitas> modelKomunitas = new ArrayList<>();
    Toolbar tbKomunitas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_komunitas);

        tbKomunitas = findViewById(R.id.toolbar_komunitas);
        tbKomunitas.setTitle("Daftar Komunitas Purwakarta");
        setSupportActionBar(tbKomunitas);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Mohon Tunggu");
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Sedang menampilkan data...");

        rvKomunitas = findViewById(R.id.rvKomunitas);
        GridLayoutManager mLayoutManager = new GridLayoutManager(this,
                2, RecyclerView.VERTICAL, false);
        rvKomunitas.setLayoutManager(mLayoutManager);
        gridMargin = new LayoutMarginDecoration(2, Tools.dp2px(this, 4));
        rvKomunitas.addItemDecoration(gridMargin);
        rvKomunitas.setHasFixedSize(true);

        getKomunitas();
    }
    private void getKomunitas() {
        progressDialog.show();
        AndroidNetworking.get(Api.Komunitas)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            progressDialog.dismiss();
                            JSONArray playerArray = response.getJSONArray("komunitas");
                            for (int i = 0; i < playerArray.length(); i++) {
                                JSONObject temp = playerArray.getJSONObject(i);
                                ModelKomunitas dataApi = new ModelKomunitas();
                                dataApi.setIdKomunitas(temp.getString("id"));
                                dataApi.setTxtNamaKomunitas(temp.getString("nama"));
                                dataApi.setGambarKomunitas(temp.getString("logo_url"));
                                dataApi.setKategoriKomunitas(temp.getString("kategori"));
                                modelKomunitas.add(dataApi);
                                showKomunitas();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(KomunitasActivity.this,
                                    "Gagal menampilkan data!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        progressDialog.dismiss();
                        Toast.makeText(KomunitasActivity.this,
                                "Tidak ada jaringan internet!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void showKomunitas() {
        komunitasAdapter = new KomunitasAdapter(KomunitasActivity.this, modelKomunitas, this);
        rvKomunitas.setAdapter(komunitasAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSelected(ModelKomunitas modelKomunitas) {
        Intent intent = new Intent(KomunitasActivity.this, DetailKomunitasActivity.class);
        intent.putExtra("detailKomunitas", modelKomunitas);
        startActivity(intent);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}