package com.example.dell.beautyfulbangladesh;

/**
 * Created by Dell on 4/4/2017.
 */


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


public class summarybackworker extends AsyncTask<String,Void,String > {

    Context context;
    AlertDialog alertDialog;
    summarybackworker (Context ctx){
        context = ctx;
        ProgressDialog progress;
    }
    @Override
    protected String doInBackground(String... params) {
        String uname =  params[0];

        String login_url="http://codecloud.info/s_search_link.php";
        try {

            URL url = new URL(login_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream =httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter =new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            //String post_data= URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"+URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
            String post_data= URLEncoder.encode("uname","UTF-8")+"="+URLEncoder.encode(uname,"UTF-8");
            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream =httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
            String result="";
            String line="";
            while((line=bufferedReader.readLine())!=null){
                result += line;
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return result;
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        // alertDialog = new AlertDialog.Builder(context).create();
        //  alertDialog.setTitle("Login..............");

    }

    @Override
    protected void onPostExecute(String result) {
        Intent intent=new Intent(context,Summary.class);
        intent.putExtra("json_data",result);
        context.startActivity(intent);

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
