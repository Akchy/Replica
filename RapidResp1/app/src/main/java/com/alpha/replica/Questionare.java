package com.alpha.replica;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Questionare extends AppCompatActivity {

    RadioButton option1, option2, option3, option4;
    int sum = 0;
    TextView q1;
    Button b;
    int count = 0;
    int limit = 9;
    int counter = 0;
    int i = 0;

    private FirebaseFirestore db;

    List<String> l = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionare);
        setTitle("Questionare");

        //Initialise
        q1 = findViewById(R.id.q1);
        option1 = findViewById(R.id.c1);
        option2 = findViewById(R.id.c2);
        option3 = findViewById(R.id.c3);
        option4 = findViewById(R.id.c4);
        b = findViewById(R.id.btn);


        db = FirebaseFirestore.getInstance();

        db.collection("questions")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String miss = document.getString("quest");
                        //Toast.makeText(Questionare.this, miss, Toast.LENGTH_SHORT).show();
                        l.add(miss);
                        count++;
                        if (count == 9) // Limit the questions from the set of questions
                            break;
                    }
                    q1.setText(l.get(counter));
                    //Log.e("count", String.valueOf(count), task.getException());
                } else {
                    Log.d("MissionActivity", "Error getting documents: ", task.getException());
                }
            }
        });


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counter < limit - 1) {

                    //Get the cumulative answer
                    counter++;
                    q1.setText(l.get(counter));
                    if (option1.isChecked()) {
                        sum += 1;
                    } else if (option2.isChecked()) {
                        sum += 2;
                    } else if (option3.isChecked()) {
                        sum += 4;
                    } else if (option4.isChecked()) {
                        sum += 7;
                    }

                    Log.e("val", String.valueOf(sum));
                   //clear the checksum
                    option1.setChecked(false);
                    option2.setChecked(false);
                    option3.setChecked(false);
                    option4.setChecked(false);


                    // Intent I = new Intent(Questionare.this, Result.class);
                    //startActivity(I);
                } else {


                    float val = (float) sum / 126; // TO get a value between 0 and 1


                        Intent I = new Intent(Questionare.this, TextInputs.class);
                        I.putExtra("val", val);
                        startActivity(I);

                    /*
                    //The result is negative
                    if (val > 0.19 && val < 0.5) {
                        Intent I = new Intent(Questionare.this, FurtherQ.class);
                        I.putExtra("val", val);
                        startActivity(I);
                    }
                    //the result is comparitively positive
                    else {
                        Toast.makeText(Questionare.this, "Thank you for answering!!", Toast.LENGTH_SHORT).show();
                        Intent I = new Intent(Questionare.this, Result.class);
                        I.putExtra("first", val);
                        startActivity(I);
                    }
*/
                }
            }
        });


    }

}
