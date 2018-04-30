package com.example.panagiotis.otpapp;

import android.os.AsyncTask;

public class ServerVerify extends AsyncTask<String,Void,Boolean[]> {
    @Override
    protected Boolean[] doInBackground(String... strings) {
        Boolean[] girna=new Boolean[2];
        Authentication a=new Authentication(strings[0]);
        a.start();
        try {
            a.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        girna[0]=a.getDiwkseto();
        girna[1]=a.gettest();
        return girna;
    }

    @Override
    protected void onPostExecute(Boolean[] booleans) {
        super.onPostExecute(booleans);
    }
}
