package com.example.systemparking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ImageView ivData, ivCctv, ivTAmbah;
    private TextView P1,P2,P3,P4,P5,P6,slotParkir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//firebaseData Parkir 1===================================
        P1 = findViewById(R.id.nilai1);
        DatabaseReference dref = FirebaseDatabase.getInstance().getReference().child("Parkir1");
        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("Parkir1", Objects.requireNonNull(snapshot.getValue()).toString());
                if (snapshot.getValue().toString().equals("0")){
                    P1.setText("Terisi");
                }
                else {P1.setText("Kosong");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }});

//firebaseData Parkir 2===================================
        P2 = findViewById(R.id.nilai2);
        dref = FirebaseDatabase.getInstance().getReference().child("Parkir2");
        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue().toString().equals("0")){
                    P2.setText("Terisi");
                }
                else {P2.setText("Kosong");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }});

//firebaseData Parkir 3===================================
        P3 = findViewById(R.id.nilai3);
        dref = FirebaseDatabase.getInstance().getReference().child("Parkir3");
        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue().toString().equals("0")){
                    P3.setText("Terisi");
                }
                else {P3.setText("Kosong");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }});

//firebaseData Parkir 4===================================
        P4 = findViewById(R.id.nilai4);
        dref = FirebaseDatabase.getInstance().getReference().child("Parkir4");
        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue().toString().equals("0")){
                    P4.setText("Terisi");
                }
                else {
                    P4.setText("Kosong");}
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }});

//firebaseData Parkir 5===================================
        P5 = findViewById(R.id.nilai5);
        dref = FirebaseDatabase.getInstance().getReference().child("Parkir5");
        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue().toString().equals("0")){
                    P5.setText("Terisi");
                }
                else {P5.setText("Kosong");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }});

//firebaseData Parkir 6===================================
        P6 = findViewById(R.id.nilai6);
        dref = FirebaseDatabase.getInstance().getReference().child("Parkir6");
        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue().toString().equals("0")){
                    P6.setText("Terisi");
                }
                else {P6.setText("Kosong");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }});

//firebase slot====================================
        slotParkir = findViewById(R.id.slotParkir);
        dref = FirebaseDatabase.getInstance().getReference().child("Slot");
        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String slotP = snapshot.getValue().toString();
                slotParkir.setText(slotP);}
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }});

        ivData = findViewById(R.id.ivData);
        ivData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,historyLaporan.class));
            }
        });

        ivCctv = findViewById(R.id.ivCctv);
        ivCctv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,hCCTV.class));
            }
        });

        ivTAmbah = findViewById(R.id.ivTambah);
        ivTAmbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,CRUD.class));
            }
        });
    }

    //fungsi tanggal
    private String getWaktu(){
        SimpleDateFormat s = new SimpleDateFormat("ddMMyyyyhhmmss");
        String format = s.format(new Date());
        return format;
    }
}