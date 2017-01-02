package com.example.webprog26.asyncproggcm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.android.gms.iid.InstanceIDListenerService;

/**
 * Created by webprog26 on 02.01.2017.
 */

public class MyInstanceIDListenerService extends InstanceIDListenerService {

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        Intent intent = new Intent(this, RegistrationIntentService.class);
        startService(intent);
        sharedPreferences.edit().putBoolean(MessagingActivity.SENT_TOKEN_TO_SERVER, false).apply();
    }
}
