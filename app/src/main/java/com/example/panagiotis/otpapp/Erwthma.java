package com.example.panagiotis.otpapp;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class Erwthma extends Thread {


String email,pass;
public Boolean aithma=false,diwkseto=false,test=false;

    public Erwthma( String email, String pass) {
        this.email = email;
        this.pass = pass;
    }

    public boolean getaithma(){
        return aithma;
    }
    public boolean getdiwkseto(){
        return diwkseto;
    }
    public boolean gettest(){
        return test;
    }

    @Override
    public void run() {
        super.run();
        String link = "http://192.168.1.7:80/otp/erwthma.php";
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
                    .appendQueryParameter("email", email)
                    .appendQueryParameter("pass", pass);
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

                if (k.equals(" iparxei hdh")  || k.equals("New user successfully created") ) {
                    diwkseto = true;
                    Log.d("poutsa", "big");
                    //otan girnaei deny einai lathos to hash
                } else {
                    diwkseto = false;
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
