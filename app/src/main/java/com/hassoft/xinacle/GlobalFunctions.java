package com.hassoft.xinacle;

import android.content.Context;
import android.widget.Toast;

import java.net.InetAddress;

public class GlobalFunctions {
    public static boolean isInternetAvailable(Context context) {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com");
            //You can replace it with your name
            return !ipAddr.equals("");

        } catch (Exception e) {
            Toast.makeText(context, "Please check your internet connection and try again", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
