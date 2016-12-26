package com.technical.quiz.gcm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.gcm.GcmListenerService;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.technical.quiz.views.activities.LoginActivity;

/**
 * Created by M1032185 on 3/31/2016.
 */
public class PushNotificationService extends GcmListenerService {
    @Override
    public void onMessageReceived(String from, Bundle data) {
        super.onMessageReceived(from, data);
        String message = data.getString("message");

        Intent intent = new Intent(LoginActivity.ACTION);
        intent.putExtra("message",message);
        sendBroadcast(intent);

        Log.d("Message",message);

//        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();

    }
}
