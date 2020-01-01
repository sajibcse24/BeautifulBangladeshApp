package com.example.dell.beautyfulbangladesh;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Locale;

import cz.msebera.android.httpclient.Header;
import android.graphics.Color;
public class Place extends AppCompatActivity {
    Context context =this;
    // Declare Variables
    ListView list;
    ListViewAdapter adapter;
    EditText editsearch;
    String[] location;
    String[] address;
    ProgressDialog progress;
    Intent intent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_main);
        list = (ListView) findViewById(R.id.listview1);
        getData();


    }

    public void getData() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.setTimeout(20 * 1000);
        client.get(Place.this, "http://codecloud.info/s_place.php/", new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                progress = ProgressDialog.show(Place.this, "Please wait",
                        "Loading...", true);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                if (response != null)
                    updateData(response);
                progress.dismiss();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Toast.makeText(Place.this,"Something wrong",Toast.LENGTH_SHORT).show();
                progress.dismiss();

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Toast.makeText(Place.this,"Something wrong",Toast.LENGTH_SHORT).show();
                progress.dismiss();

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Toast.makeText(Place.this,"Something wrong",Toast.LENGTH_SHORT).show();
                progress.dismiss();

            }
        });
    }

    public void updateData(JSONObject response) {
        final ArrayList<PlacePopulation> arraylist = new ArrayList<PlacePopulation>();

        try {
            JSONArray contactsArray = response.getJSONArray("placedata");
            for (int i = 0; i < contactsArray.length(); i++) {
                JSONObject myRow = contactsArray.getJSONObject(i);
                PlacePopulation wp = new PlacePopulation(myRow.getString("pname"), myRow.getString("division"),myRow.getString("description"));
                // Binds all strings into an array
                arraylist.add(wp);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Pass results to ListViewAdapter Class
        adapter = new ListViewAdapter(this, arraylist);

        // Binds the Adapter to the ListView
        list.setAdapter(adapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                String name = arraylist.get(position).getName();
                Toast.makeText(Place.this, "" + arraylist.get(position).getName(), Toast.LENGTH_LONG).show();

                //Intent in1 = getIntent();
                //dname = in1.getStringExtra("pos");
                summarybackworker summarybackworker =new summarybackworker(context);
                summarybackworker.execute(name);
               // Intent i = new Intent(Place.this, PlaceDescription.class);
               // i.putExtra("pos", name);
               // startActivity(i);
            }
        });







    // Locate the EditText in listview_main.xml
        editsearch = (EditText) findViewById(R.id.search);

        // Capture Text in EditText
        editsearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                String text = editsearch.getText().toString().toLowerCase(Locale.getDefault());
                adapter.filter(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                // TODO Auto-generated method stub
            }
        });

    }

}