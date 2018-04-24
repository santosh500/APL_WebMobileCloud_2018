package com.google.android.gms.samples.vision.face.multitracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
//I created this to view the results but it wasn't used
public class Results extends AppCompatActivity {

    ArrayList<Float> averageSmile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);


        Bundle bundle = getIntent().getExtras();
        float yourFloat = bundle.getFloat("StatisticsArray");
        //averageSmile = getIntent().getFloatExtra("")
        Log.i("HI MAN", String.valueOf(yourFloat));

    }
}
