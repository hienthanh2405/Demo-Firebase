package com.example.my.learnfirebase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtName, edtMssv;
    Button btnCreate, btnShowStudent;

    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtMssv = findViewById(R.id.edt_mssv);
        edtName = findViewById(R.id.edt_name);

        btnCreate = findViewById(R.id.btn_create);
        btnShowStudent = findViewById(R.id.btn_show_student);

        btnCreate.setOnClickListener(this);
        btnShowStudent.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnCreate){
            createStudent();
        } else if (view == btnShowStudent){
            startActivity(new Intent(MainActivity.this, StudentActivity.class));
        }
    }

    private void createStudent() {
        String name = edtName.getText().toString();
        String mssv = edtMssv.getText().toString();

        final Student student = new Student(name, mssv);

        mData.child("Student").push().setValue(student).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    edtName.setText("");
                    edtMssv.setText("");
                    edtName.requestFocus();
                    Toast.makeText(MainActivity.this, "Create " + student.toString() + " success!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Create " + student.toString() + " failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
