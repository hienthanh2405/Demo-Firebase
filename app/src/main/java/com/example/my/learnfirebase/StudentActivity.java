package com.example.my.learnfirebase;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StudentActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnBack;
    RecyclerView rcv;

    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
    List<Student> list;
    StudentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        list = new ArrayList<>();

        rcv=findViewById(R.id.rcv_student);

        adapter = new StudentAdapter(this, list);
        rcv.setLayoutManager(new LinearLayoutManager(this));
        rcv.setAdapter(adapter);

        btnBack = findViewById(R.id.btn_back);

        btnBack.setOnClickListener(this);
        getDataStudent();
    }

    private void getDataStudent() {
        mData.child("Student").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!list.isEmpty()) list.clear();
                for (DataSnapshot item : dataSnapshot.getChildren()) {
                    list.add(item.getValue(Student.class));
                }
                loadDataSuccess();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void loadDataSuccess() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        if (view == btnBack){
            onBackPressed();
        }
    }
}
