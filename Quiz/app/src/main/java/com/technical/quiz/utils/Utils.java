package com.technical.quiz.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.technical.quiz.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

/**
 * Created by M1032185 on 3/20/2016.
 */
public class Utils {

    public static final String PREFRENCES_NAME = "quiz_pref";

    public static void changeActivity(Context context, Intent intent) {

        context.startActivity(intent);
    }

    public static void writeToPrefrences(Context context, String key, String value) {

        SharedPreferences preferences = context.getSharedPreferences("quiz_pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    //Gets stored data from prefrences
    public static String getFromPrefrencesBoolean(Context context, String type) {
        SharedPreferences preferences = context.getSharedPreferences(PREFRENCES_NAME, Context.MODE_PRIVATE);
        return preferences.getString(type, "");
    }

    public static boolean isUserSignUp(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("quiz_pref", Context.MODE_PRIVATE);

        return preferences.getBoolean("signup", false);
    }

    public static void sendNotify(Context mContext, String footsteps) {
        NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(mContext);
        Intent lIntent = new Intent();
        PendingIntent pendingNotificationIntent = PendingIntent.getActivity(
                mContext, 0, lIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Quiz")
                .setContentText(footsteps)
                .setContentIntent(pendingNotificationIntent);
        Notification notification = builder.getNotification();
        notificationManager.notify(R.mipmap.ic_launcher, notification);
    }

    //Parsing web service response and creating it in json format.
    public static String streamlineHttpResponse(HttpURLConnection con) throws IOException {
        if (con == null) {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        int responseCode = con.getResponseCode();
        InputStream content = responseCode >= 200 && responseCode <= 299 ? con.getInputStream() : con.getErrorStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(content));
        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line);
            builder.append("\n");
        }
        content.close();
        reader.close();
        return builder.toString();
    }

    public static ProgressDialog showProgressDialog(Context context, String message){

        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(message);
        progressDialog.setCanceledOnTouchOutside(false);

        return progressDialog;


    }
}
