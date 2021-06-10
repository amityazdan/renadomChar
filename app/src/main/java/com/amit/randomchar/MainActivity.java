package com.amit.randomchar;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.provider.Settings;
import android.text.Editable;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.picup.sdk.PicUpSdk;
import com.picup.sdk.interfaces.ResultListener;
import com.picup.sdk.util.ErrorNo;
import com.picup.sdk.util.Result;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    public Random rnd = new Random();
    //    PicUpSdk mPicUpSdk;
    public EditText pnm;
    public TextView rdmCr;
    public Button auth;
    public Button fab;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();


//        mPicUpSdk = PicUpSdk.getInstance(MainActivity.this);
//        mPicUpSdk.disableService();
//        mPicUpSdk.setPermissionMode(PicUpSdk.PermissionMode.External);
//        mPicUpSdk.registerWithUI("", UUID.randomUUID().toString(), new ResultListener() {
//            @Override
//            public void onResult(Result result) {
//                if (result.isSuccess()) {
//                    Toast.makeText(MainActivity.this, "1", Toast.LENGTH_SHORT).show();
//                    makeACall("972586277130");
//                } else {
//                    Toast.makeText(MainActivity.this, "2", Toast.LENGTH_SHORT).show();
//                    makeACall("972586277130");
//                }
//            }
//        });



        auth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = pnm.getText().toString();
                makeACall(s);
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rdmCr.setText(getRandomChar());
            }
        });


        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CALL_LOG, Manifest.permission.READ_PHONE_STATE}, 1);
        startService(new Intent(this, ServiceReceiver.class));
//        requestWindowManager();
    }

    private void initViews() {
        pnm = (EditText) findViewById(R.id.pnm);
        rdmCr = (TextView) findViewById(R.id.rdmCr);
        auth = findViewById(R.id.auth);
        fab = findViewById(R.id.fab);
    }

//    private void requestWindowManager() {
//        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
//        startActivityForResult(intent, 0);
//    }

    private void makeACall(String userPhoneNumber) {

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://app.picup.io/makeTwilioCall?apiKey=3b95352bc755f92d4352bc751a&timeout=10&fromNumber=972765994500&toNumber=" + userPhoneNumber;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        int a = 5;
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        queue.add(stringRequest);
    }

//    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//    private String getHashSHA256(String valueToHash) {
//        MessageDigest digest;
//        try {
//            digest = MessageDigest.getInstance("SHA-256");
//            byte[] hash = digest.digest(valueToHash.getBytes(StandardCharsets.UTF_8));
//            return Base64.encodeToString(hash, Base64.NO_WRAP);
//        } catch (NoSuchAlgorithmException e) {
//            Log.d("TAG", "getHashSHA256: " + e.getMessage());
//            return null;
//        }
//    }

    private String getRandomChar() {
        return Character.toString((char) ('a' + rnd.nextInt(300)));
    }
}