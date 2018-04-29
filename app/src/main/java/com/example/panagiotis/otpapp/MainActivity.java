package com.example.panagiotis.otpapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn1 = (Button) findViewById(R.id.exit);
        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();
                System.exit(0);
            }
        });
        Button btn2 = (Button) findViewById(R.id.signup);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //prwta stelnei ta stoixeia sto erwthma php
                Boolean diwkseto = false, aithma = false;
                EditText pass = (EditText) findViewById(R.id.pass);
                String p = pass.getText().toString();
                EditText email = (EditText) findViewById(R.id.email);
                String mail = email.getText().toString();
                if (!(p.isEmpty() && mail.isEmpty())) {
                   ServerSignup s=new ServerSignup();
                   s.execute(mail,p);
                    try {
                        Boolean test=s.get();
                        if(test) {
                            Intent a = new Intent(MainActivity.this, Login.class);
                            startActivity(a);
                        }else{

                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }

                }
                else {
                    Toast.makeText(MainActivity.this, "Please enter your username and your password before sign up", Toast.LENGTH_SHORT).show();
                    pass.clearFocus();
                    email.clearFocus();

                }

            }

        });
    }
}
