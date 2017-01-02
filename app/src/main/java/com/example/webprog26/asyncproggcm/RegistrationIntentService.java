package com.example.webprog26.asyncproggcm;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

/**
 * Created by webprog26 on 02.01.2017.
 */

public class RegistrationIntentService extends IntentService {

    private static final String TAG = "AsPrGCM";

    public RegistrationIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        try {
            InstanceID instanceID = InstanceID.getInstance(this);
            Log.i("AsPrGCM", "UUID: " + instanceID.getId());

            String senderId = getString(R.string.gcm_defaultSenderId);

            String token = instanceID.getToken(senderId, GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);

            sendRegistrationToServer(token);

            sharedPreferences.edit().putBoolean(MessagingActivity.SENT_TOKEN_TO_SERVER, true).apply();
        } catch (Exception e){
            e.printStackTrace();
            sharedPreferences.edit().
                    putBoolean(MessagingActivity.SENT_TOKEN_TO_SERVER, false).
                    apply();
        }
    }

    private void sendRegistrationToServer(String token) {
        Log.i(TAG, "Token: " + token );
    }
}
