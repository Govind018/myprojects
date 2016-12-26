package com.technical.quiz.gcm;

import android.util.Log;

/**
 * Created by M1032185 on 3/31/2016.
 */
public abstract class RegistrationCompletedHandler {

    public abstract void onSuccess(String registrationId, boolean isNewRegistration);

    public void onFailure(String ex) {
        // If there is an error, don't just keep trying to register.
        // Require the user to click a button again, or perform
        // exponential back-off.
        Log.e("", ex);
    }
}

