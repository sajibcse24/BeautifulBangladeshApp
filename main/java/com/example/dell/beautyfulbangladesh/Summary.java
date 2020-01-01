package com.example.dell.beautyfulbangladesh;

/**
 * Created by Dell on 4/4/2017.
 */


import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Summary extends AppCompatActivity {
    String json_string;

    //JSONArray jsonArray;
    informationAdapter informationAdapter;
    ListView listView;
    String description;
    String link;
    Button btn1,btn2;
    String web;
    double lat;
    double longt;
    String place_name;
    String image_url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        informationAdapter =new informationAdapter(this, R.layout.summary_layout);
        addListenerOnButton();

        listView=(ListView)findViewById(R.id.listview);
        listView.setAdapter(informationAdapter);
        json_string=getIntent().getExtras().getString("json_data");
        // Toast.makeText(getApplicationContext(), json_string,Toast.LENGTH_LONG).show();

        try {
            JSONObject parentobject= new JSONObject(json_string);
            JSONArray parentarray = parentobject.getJSONArray("placedata");

            for(int i=0;i<=parentarray.length();i++) {
                JSONObject finalobject = parentarray.getJSONObject(i);
                description =finalobject.getString("description");
                link =finalobject.getString("link");
                lat =Double.parseDouble(finalobject.getString("lat"));
                longt =Double.parseDouble(finalobject.getString("lon"));
                place_name =finalobject.getString("placename");
                image_url=finalobject.getString("image_link");
                Information information =new Information(description);
                informationAdapter.add(information);

            }

        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        WebView bro = (WebView)findViewById(R.id.webview);
        bro.setWebViewClient(new webbrowse());
        bro.getSettings().setJavaScriptEnabled(true);
        bro.loadUrl(image_url);

        bro.getSettings().setLoadWithOverviewMode(true);
        bro.getSettings().setUseWideViewPort(true);

       //Toast.makeText(this,image_url,Toast.LENGTH_LONG).show();
    }
    private class webbrowse extends WebViewClient {
        //@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            return true;
        }
    }
    public void addListenerOnButton() {


        btn1 = (Button) findViewById(R.id.button1);
        btn1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {

                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse(link));
                startActivity(intent);

            }
        });
        btn2 = (Button) findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                Intent intent = new Intent(Summary.this, MapsActivity.class);
                intent.putExtra("lattitude",lat);
                intent.putExtra("longitude",longt);
                intent.putExtra("place_name",place_name);

                startActivity(intent);
            }
        });
    }


}
