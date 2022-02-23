package com.example.myapplication.faculty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.myapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UploadFaculty extends AppCompatActivity {

    FloatingActionButton fab;
    private RecyclerView csDepartment, businessadminDepartment, camapplicationDepartment;
    private LinearLayout csNOData, baNOData, caNOData;
    private List<TeacherData> list1, list2, list3;
    private TeacherAdapter adapter;
    private DatabaseReference reference, dbRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_faculty);

        csDepartment = findViewById(R.id.csDepartment);
        businessadminDepartment = findViewById(R.id.businessadminDepartment);
        camapplicationDepartment = findViewById(R.id.camapplicationDepartment);

        csNOData = findViewById(R.id.csNOData);
        baNOData = findViewById(R.id.baNOData);
        caNOData = findViewById(R.id.caNOData);

        reference = FirebaseDatabase.getInstance().getReference().child("teacher");

        csDepartment();
        businessadminDepartment();
        camapplicationDepartment();

        fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UploadFaculty.this,AddTeacher.class));
            }
        });
    }

    private void csDepartment() {
        dbRef = reference.child("Computer Science");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
             list1 = new ArrayList<>();
             if (!snapshot.exists()){
                 csNOData.setVisibility(View.VISIBLE);
                 csDepartment.setVisibility(View.GONE);
             }else {
                 csNOData.setVisibility(View.GONE);
                 csDepartment.setVisibility(View.VISIBLE);
                 for (DataSnapshot snapshot1: snapshot.getChildren() ){
                     TeacherData data = snapshot1.getValue(TeacherData.class);
                     list1.add(data);
                 }
                 csDepartment.setHasFixedSize(true);
                 csDepartment.setLayoutManager(new LinearLayoutManager(UploadFaculty.this));
                 adapter = new TeacherAdapter(list1, UploadFaculty.this,"Computer Science");
                 csDepartment.setAdapter(adapter);
             }
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {
                Toast.makeText(UploadFaculty.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void businessadminDepartment() {
        dbRef = reference.child("Business Administration");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                list2 = new ArrayList<>();
                if (!snapshot.exists()){
                    baNOData.setVisibility(View.VISIBLE);
                    businessadminDepartment.setVisibility(View.GONE);
                }else {
                   baNOData.setVisibility(View.GONE);
                    businessadminDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot1: snapshot.getChildren() ){
                        TeacherData data = snapshot1.getValue(TeacherData.class);
                        list2.add(data);
                    }
                    businessadminDepartment.setHasFixedSize(true);
                    businessadminDepartment.setLayoutManager(new LinearLayoutManager(UploadFaculty.this));
                    adapter = new TeacherAdapter(list2, UploadFaculty.this,"Business Administration");
                    businessadminDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {
                Toast.makeText(UploadFaculty.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void camapplicationDepartment() {
        dbRef = reference.child("Computer Application");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                list3 = new ArrayList<>();
                if (!snapshot.exists()) {
                    caNOData.setVisibility(View.VISIBLE);
                    camapplicationDepartment.setVisibility(View.GONE);
                }else{
                    caNOData.setVisibility(View.GONE);
                    camapplicationDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot1: snapshot.getChildren() ){
                        TeacherData data = snapshot1.getValue(TeacherData.class);
                        list3.add(data);
                    }
                    camapplicationDepartment.setHasFixedSize(true);
                    camapplicationDepartment .setLayoutManager(new LinearLayoutManager(UploadFaculty.this));
                    adapter = new TeacherAdapter(list3, UploadFaculty.this,"Computer Application");
                    camapplicationDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {
                Toast.makeText(UploadFaculty.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}