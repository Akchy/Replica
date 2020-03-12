package com.alpha.DoctorReju;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    EditText question;
    Button chat;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chat = findViewById(R.id.proc);
        question = findViewById(R.id.questions);

        db = FirebaseFirestore.getInstance();
        Map <String,Object> newQuest = new HashMap<>();
        newQuest.put("quest",question.getText().toString());

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map <String,Object> newQuest = new HashMap<>();
                newQuest.put("quest",question.getText().toString());

                db.collection("questions")
                        .add(newQuest)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                //Toast.makeText(EditProf.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                                //progressDialog.dismiss();
                                Toast.makeText(MainActivity.this, "Question Uploaded", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });

            }
        });







    }



}
