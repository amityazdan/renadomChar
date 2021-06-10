package com.amit.randomchar;

import android.app.Application;
import android.content.Context;
import android.graphics.PixelFormat;
import android.media.AudioManager;
import android.os.Build;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import static android.content.Context.WINDOW_SERVICE;

public class MyPhoneStateListener extends PhoneStateListener {

    public static Boolean phoneRinging = false;
    private String servicePhoneNumber1 = "9786661";
    private String servicePhoneNumber2 = "765994500";
    private Context context;

    public MyPhoneStateListener(Context context) {
        this.context = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onCallStateChanged(int state, String incomingNumber) {

        switch (state) {
            case TelephonyManager.CALL_STATE_RINGING:
                phoneRinging = true;
                if (incomingNumber.contains(servicePhoneNumber1) || incomingNumber.contains(servicePhoneNumber2)) {
//                    showCustomPopupMenu();
                    Toast.makeText(context, "Registration was approved", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void showCustomPopupMenu()
    {
        WindowManager windowManager2 = (WindowManager) context.getSystemService(WINDOW_SERVICE);
        LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.my_dialog, null);
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        params.gravity= Gravity.CENTER|Gravity.CENTER;
        params.x=0;
        params.y=0;
        windowManager2.addView(view, params);
    }

}