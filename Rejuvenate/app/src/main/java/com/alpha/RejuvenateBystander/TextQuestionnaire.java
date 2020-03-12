package com.alpha.RejuvenateBystander;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class TextQuestionnaire extends AppCompatActivity {


    TextView question;
    EditText answer;

    float val;
    int count = 0;
    int limit = 2;
    int counter = 0;
    Button submit;
    private FirebaseFirestore db;

    List<String> l = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_inputs);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        setTitle("Questionare");

        val = bundle.getFloat("val",0);

        question = findViewById(R.id.q1);
        answer = findViewById(R.id.text_input);
        submit = findViewById(R.id.btn);


        db = FirebaseFirestore.getInstance();

        db.collection("Text_questions")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String miss = document.getString("quest");
                        //Toast.makeText(Questionare.this, miss, Toast.LENGTH_SHORT).show();
                        l.add(miss);
                        count++;
                        if (count == 2) // Limit the questions from the set of questions
                            break;
                    }
                    question.setText(l.get(counter));
                    //Log.e("count", String.valueOf(count), task.getException());
                } else {
                    Log.d("MissionActivity", "Error getting documents: ", task.getException());
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counter < limit - 1) {

                    //Get the cumulative answer
                    counter++;
                    question.setText(l.get(counter));
                   String get_text = answer.getText().toString();

                   answer.setText("");
                   //Send API to Sentiment Analysis


                } else {

                    Log.e("val", String.valueOf(val));
                    //The result is negative
                    if (val > 0.19 && val < 0.5) {
                        Intent I = new Intent(TextQuestionnaire.this, FurtherQ.class);
                        I.putExtra("val", val);
                        startActivity(I);
                    }
                    //the result is comparitively positive
                    else {
                        Toast.makeText(TextQuestionnaire.this, "Thank you for answering!!", Toast.LENGTH_SHORT).show();
                        Intent I = new Intent(TextQuestionnaire.this, SumUp.class);
                        I.putExtra("first", val);
                        startActivity(I);
                    }

                }
            }
        });


    }
}
