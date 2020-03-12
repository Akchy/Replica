package com.alpha.rejuvenateBystander;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SumUp extends AppCompatActivity {


    TextView cv,sent,quest;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sum_up);

        cv = findViewById(R.id.emoVal);
        sent = findViewById(R.id.sentiVal);
        quest = findViewById(R.id.questVal);
        b = findViewById(R.id.submit);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();



        final float first = bundle.getFloat("first",0);
        final float second = bundle.getFloat("second",-1);
        final String dis = bundle.getString("dis","null");

        if(first !=0)
            quest.setText(String.valueOf(first));

        if(second !=-1)
            quest.setText(String.valueOf(second));
     /*   Intent I = new Intent(SumUp.this, Result.class);
        I.putExtra("dis", dis);
        I.putExtra("second", second);
        I.putExtra("first", first);
        startActivity(I);
       */
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I = new Intent(SumUp.this, Result.class);
                I.putExtra("dis", dis);
                I.putExtra("second", second);
                I.putExtra("first", first);
                startActivity(I);
            }
        });



    }
}
