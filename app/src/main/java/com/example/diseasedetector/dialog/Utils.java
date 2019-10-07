package com.example.diseasedetector.dialog;

import android.content.res.Resources;

public class Utils {

    public static String user_token = "";

    public static int LogoutInt = 1;
    public static int UploadInt = 2;

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
}
