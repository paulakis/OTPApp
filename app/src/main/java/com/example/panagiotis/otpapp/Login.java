package com.example.panagiotis.otpapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button bt = (Button) findViewById(R.id.authb);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText pare = (EditText) findViewById(R.id.Otppedio);
                String otp=pare.getText().toString();
                if(!otp.isEmpty()){
                    ServerVerify s=new ServerVerify();
                    s.execute(otp);
                    try {
                        Boolean[] na=s.get();
                        if(na[1]){
                            CheckBox mybox= (CheckBox) findViewById(R.id.checkBox);
                            mybox.setChecked(true);
                        }else{
                            CheckBox mybox= (CheckBox) findViewById(R.id.checkBox);
                            mybox.setChecked(false);
                        }
                        if(!na[0]) {
                        Toast.makeText(Login.this, "Your OTP token is no more alive.Go back to sign up", Toast.LENGTH_SHORT).show();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(Login.this, "If you don't have the right OTP go back and try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
