package com.example.systemparking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CRUD extends AppCompatActivity {

    private Spinner spinner;
    private ArrayList<String> arrayParkir;
    private EditText nama, nomorTNBK, merekKendaraan, tipeKendaraan, tahunKendaraan, tanggal;
    private Button simpan;
    private String id = "";
    private ProgressDialog progressDialog;
    ArrayAdapter<String> adapter;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);

        nama = findViewById(R.id.edNamapemilik);
        nomorTNBK = findViewById(R.id.edNTNBK);
        merekKendaraan = findViewById(R.id.edMerek);
        tipeKendaraan = findViewById(R.id.edTipe);
        tahunKendaraan = findViewById(R.id.edTahunkendaraan);
        tanggal = findViewById(R.id.edTanggal);
        simpan = findViewById(R.id.btnSimpan);

        progressDialog = new ProgressDialog(CRUD.this);
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("menyimpan");

        spinner = findViewById(R.id.sTParkir);
        arrayParkir = new ArrayList<String>();
        arrayParkir.add("Parkir 1");
        arrayParkir.add("Parkir 2");
        arrayParkir.add("Parkir 3");
        arrayParkir.add("Parkir 4");
        arrayParkir.add("Parkir 5");
        arrayParkir.add("Parkir 6");

        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, arrayParkir);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                simpan.setOnClickListener(view1 -> {
                    if (nama.getText().length()>0&&
                            nomorTNBK.getText().length()>0&&
                            merekKendaraan.getText().length()>0&&
                            tipeKendaraan.getText().length()>0&&
                            tahunKendaraan.getText().length()>0){

                        simpanData(spinner.getSelectedItem().toString(),
                                nama.getText().toString(),
                                nomorTNBK.getText().toString(),
                                merekKendaraan.getText().toString(),
                                tipeKendaraan.getText().toString(),
                                tahunKendaraan.getText().toString());
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Silakan Isi Semua Data", Toast.LENGTH_SHORT).show();
                    }

                });
                Intent intent = getIntent();
                if (intent!=null){
                    id = intent.getStringExtra("id");
                    nama.setText(intent.getStringExtra("Nama Pemilik"));
                    nomorTNBK.setText(intent.getStringExtra("Nomor Plat"));
                    merekKendaraan.setText(intent.getStringExtra("Merek Kendaraan"));
                    tipeKendaraan.setText(intent.getStringExtra("Tipe Kendaraan"));
                    tahunKendaraan.setText(intent.getStringExtra("Tahun Kendaraan"));
                    tanggal.setText(intent.getStringExtra("Tanggal"));

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    //fungsi button simpan parkir 1
    private void simpanData(String spinner, String nama, String nomorTNBK, String jenisKendaraan, String tipeKendaraan, String tahunKendaraan){
        Map<String, Object> parkir = new HashMap<>();
        parkir.put("Parkir", spinner);
        parkir.put("Nama Pemilik", nama);
        parkir.put("Nomor Plat", nomorTNBK);
        parkir.put("Merek Kendaraan", jenisKendaraan);
        parkir.put("Tipe Kendaraan", tipeKendaraan);
        parkir.put("Tahun Kendaraan", tahunKendaraan);
        parkir.put("Tanggal", getWaktu());

        progressDialog.show();
        if (id!=null){
            db.collection("user").document(id)
                    .set(parkir)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(getApplicationContext(), "Data Diperbaharui", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "Data Gagal Tersimpan", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        }
        else {
            db.collection("user")
                    .add(parkir)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(getApplicationContext(), "Data Tersimpan", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    });
        }
    }



    private String getWaktu() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy/HH:mm", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());
        return currentDateandTime;
    }
}