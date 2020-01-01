package com.example.dell.beautyfulbangladesh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PlaceDescription extends AppCompatActivity {
     Intent in1;
    String dname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_place_description);
        Intent in1 = getIntent();
        dname = in1.getStringExtra("pos");
        summarybackworker summarybackworker =new summarybackworker(this);
        summarybackworker.execute(dname);
    }
}
