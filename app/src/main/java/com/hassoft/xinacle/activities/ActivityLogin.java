package com.hassoft.xinacle.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.hassoft.xinacle.R;

import org.joda.time.DateTime;

import java.io.IOException;
import java.net.InetAddress;

public class ActivityLogin extends AppCompatActivity {
Button loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        loginBtn=(Button)findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    if (!DateTime.now().toString("dd-MM-yyyy").equals("07-03-2021")) {
                        startActivity(new Intent(ActivityLogin.this, MainActivity.class));
                    }else{
                        AlertDialog.Builder ab = new AlertDialog.Builder(ActivityLogin.this);
                        ab.setCancelable(false);
                        ab.setMessage("Trial version of this application has expired. Please contact Hassoft Solutions for further details");
                        ab.setIcon(R.mipmap.ic_launcher);
                        ab.setTitle("Xinacle");
                        ab.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }).show();
                    }


            }
        });
    }
    public  boolean isOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int     exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        }
        catch (IOException e)          { e.printStackTrace(); }
        catch (InterruptedException e) { e.printStackTrace(); }

        return false;
    }
}
