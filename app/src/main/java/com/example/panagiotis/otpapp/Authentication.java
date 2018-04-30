package com.example.panagiotis.otpapp;

import android.net.Uri;
import android.util.JsonReader;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class Authentication extends Thread {
    String otp;
    public Boolean aithma=false,diwkseto=false,test=false;
    public Authentication( String otp) {
        this.otp = otp;
    }
    public boolean gettest(){
        return test;
    }

    public boolean getDiwkseto(){
        return diwkseto;
    }
    @Override
    public void run() {
        super.run();
        String link = "http://192.168.1.7:80/otp/authentication.php";
        URL myURL = null;
        try {
            myURL = new URL(link);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) myURL.openConnection();
            conn.setDoOutput(true);
            try {
                conn.setRequestMethod("POST");
            } catch (ProtocolException e) {
                e.printStackTrace();
            }
            Uri.Builder postmsg = new Uri.Builder()
                    .appendQueryParameter("otp", otp);
            String query = postmsg.build().getEncodedQuery();
            OutputStreamWriter out = new OutputStreamWriter(
                    conn.getOutputStream());
            out.write(query);
            out.close();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            conn.getInputStream()));
            String k;
            while ((k = in.readLine()) != null) {
                Log.d("server reply", k+diwkseto.toString());

                if (k.equals("yesthe last one is alive") ) {
                    diwkseto = true;
                    Log.d("poutsa", "big");
                    //otan girnaei deny einai lathos to hash
                } else if(k.equals("yescan not authenticate") || k.equals("yesthelei neo token")) {
                    diwkseto = false;
                }
                else{
                    diwkseto=false;
                }

            }


            in.close();

            conn.connect();
            Log.d("hit", "connected");
            int rescode = conn.getResponseCode();
            //meta pairnei apanthsh
            String resmsg = conn.getResponseMessage();
            Log.d("paralia", resmsg);
            if (rescode == HttpURLConnection.HTTP_OK) {
                aithma = true;
                conn.disconnect();
                Log.d("http", "ok"+aithma.toString());

            } else {
                aithma = false;
                Log.d("http", "den anagnwristhke");
            }
            if(aithma && diwkseto){
                test=true;
                Log.d("test", test.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
