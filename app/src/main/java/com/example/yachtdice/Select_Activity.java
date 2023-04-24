package com.example.yachtdice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;

public class Select_Activity extends AppCompatActivity {

    String loginUserNickname;
    String TAG = "Select_Activity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        TextView userNickname = (TextView) findViewById(R.id.User_nickname);
        Button vsUser_bt = (Button) findViewById(R.id.VS_USER);
        Button vsMulti_bt = (Button) findViewById(R.id.VS_MULTI_USER);
        Button vsCPU_bt = (Button) findViewById(R.id.VS_CPU);
        Button ranking_bt = (Button) findViewById(R.id.RANKING);

        Intent selectIntent = getIntent();
        loginUserNickname = selectIntent.getStringExtra("loginNickname");
        Log.e(TAG,"get 값 : " + loginUserNickname);

        userNickname.setText(loginUserNickname);


        userNickname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Select_Activity.this,Mypage_Activity.class);
                startActivityForResult(intent, 1);
            }
        });

        vsUser_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e(TAG,"1:1대전 클릭되나?");

                new Thread(() -> {
                    try {
                        Log.e(TAG,"스레드 들어오나?");
                        Socket sk = new Socket("172.30.1.17",6000);
                        Log.e(TAG,"서버와 연결이 되었습니다.");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();

            }
        });

        vsCPU_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Select_Activity.this,Ingame_Activity.class);
                intent.putExtra("loginUserNickName",loginUserNickname);
                startActivity(intent);
            }
        });


    }
}