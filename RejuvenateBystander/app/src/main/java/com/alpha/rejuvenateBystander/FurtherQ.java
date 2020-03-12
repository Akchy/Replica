package com.alpha.rejuvenateBystander;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class FurtherQ extends AppCompatActivity {

    RadioButton c1,c2,c3;
    int sum=0;
    TextView q1;
    Button b;
    int count=0;
    int limit =5;
    int counter=0;
    int i=0;
    private FirebaseFirestore db;
    List<String> l = new ArrayList<>();

    String disorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_further_q);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        setTitle("Questionare");

        float sum_res = bundle.getFloat("val",0);

        q1 = findViewById(R.id.q1);
        c1=findViewById(R.id.c1);
        c2=findViewById(R.id.c2);
        c3=findViewById(R.id.c3);
        b = findViewById(R.id.btn);
        b.setClickable(false);

        if(sum_res>0.19 && sum_res<0.38){
            disorder = "anxiety";
        }

        else if(sum_res>0.38 && sum_res<0.442){
            disorder = "depression";
        }


        else if(sum_res>0.442 && sum_res<0.5){
            disorder = "aggression";
        }
        db = FirebaseFirestore.getInstance();

        //Toast.makeText(FurtherQ.this, disorder +" " + sum_res,Toast.LENGTH_SHORT).show();

        db.collection(disorder)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String miss = document.getString("quest");
                        // Toast.makeText(Questionare.this, miss, Toast.LENGTH_SHORT).show();
                        l.add(miss);
                        count ++;
                        if(count==5)
                            break;
                    }
                    q1.setText(l.get(counter));
                    b.setClickable(true);
                } else {
                    Log.d("MissionActivity", "Error getting documents: ", task.getException());
                }
            }
        });
    }

    public void clear(View v){
//        Toast.makeText(Questionare.this, i++, Toast.LENGTH_SHORT).show();
        if(counter<limit-1) {

            counter++;
            q1.setText(l.get(counter));
            if(c2.isChecked()){
                sum+=1;
            }
            else if(c3.isChecked()){
                sum+=2;
            }

            Log.e("sum",String.valueOf(sum));
            c1.setChecked(false);
            c2.setChecked(false);
            c3.setChecked(false);


            // Intent I = new Intent(Questionare.this, Result.class);
            //startActivity(I);
        }
        else{

            float val = (float)sum/15;

                Toast.makeText(FurtherQ.this, "Thank you for answering!!" + String.valueOf(val), Toast.LENGTH_SHORT).show();
                Intent I = new Intent(FurtherQ.this, SumUp.class);
                I.putExtra("dis", disorder);
                I.putExtra("second", val);
                startActivity(I);


        }
    }
}
