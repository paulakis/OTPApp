package com.example.panagiotis.otpapp;

import android.os.AsyncTask;

public class ServerSignup extends AsyncTask<String,Void,Boolean> {

    @Override
    protected Boolean doInBackground(String... strings) {
        Erwthma e=new Erwthma(strings[0],strings[1]);
        e.start();
        try {
            e.join();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        return e.gettest();

    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);


    }
}
