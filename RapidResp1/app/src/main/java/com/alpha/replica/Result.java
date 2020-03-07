package com.alpha.replica;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Result extends AppCompatActivity {


    TextView t,t2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        setTitle("Result");

        t= findViewById(R.id.res);
        t2 = findViewById(R.id.answer);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        float first = bundle.getFloat("first",0);
        float second = bundle.getFloat("second",-1);
        String dis = bundle.getString("dis","null");

       // Toast.makeText(Result.this, first+ "" + second, Toast.LENGTH_SHORT).show();

        if(first<0.08){
            t.setText("Your mental state is sound but preferably you could go for some calm and suiting remedies for betterment of your mental health.");
        }
        else if(first>0.08 && first<0.245){
            t.setText("Your mental state is very good and sound, so you nearly need further procedures. So its your choice to go for any remedies. ");
        }
        if(second>0 && second<0.15){
            t2.setText("Low " + dis);
            t.setText("Your analysis results reports that you are at a very less risk of having "+dis+ " issues, but mild symptoms are identified. So it is advised to have some calm remedies.");
        }
        else if(second>0.15 && second<0.45){
            t2.setText("Moderate " + dis);
            t.setText("Your analysis results reports that you are at a very moderate risk of having "+dis+ " issues, and symptoms are identified accordingly. So its your choice to go for any remedies.");
        }
        else if(second>0.45 && second<0.6){
            t2.setText("High " + dis);
            t.setText("Your analysis results reports that you are at a very likely have "+dis+ ", and symptoms are accurately identified thus denoting to the same. Preferable go for all the solutions given below So");
        }

    }
}
