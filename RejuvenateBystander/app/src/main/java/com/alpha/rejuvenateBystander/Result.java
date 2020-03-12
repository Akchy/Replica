package com.alpha.rejuvenateBystander;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    int PERMISSION_ALL = 1;
    String[] PERMISSIONS = {Manifest.permission.READ_CONTACTS, Manifest.permission.CALL_PHONE};

    public static boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    TextView t,t2;
    Button audio,ano;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        setTitle("Result");

        audio = findViewById(R.id.audio);
        ano  = findViewById(R.id.ano);

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

        audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:8089435053"));
                callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(callIntent);
            }
        });
        ano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I = new Intent(Result.this, SelfCounciling.class);

                startActivity(I);
            }
        });




    }
}
