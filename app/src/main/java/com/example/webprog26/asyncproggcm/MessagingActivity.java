package com.example.webprog26.asyncproggcm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MessagingActivity extends AppCompatActivity {

    private static final String TAG = "MessagingActivity";

    public static final String SENT_TOKEN_TO_SERVER = "sent2Server";
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(checkPlayServices()){
            Log.i(TAG, "Registering to GCM");
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
            boolean sentToken = sharedPref.getBoolean(SENT_TOKEN_TO_SERVER, false);

            if(!sentToken){
                //Error handling
            }

            Intent intent = new Intent(this, RegistrationIntentService.class);
            startService(intent);

            Button button = (Button)findViewById(R.id.sendButton);
            //button.setOnClickListener(sendListener);
        }
    }

    private boolean checkPlayServices(){
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int rc = apiAvailability.isGooglePlayServicesAvailable(this);
        if(rc != ConnectionResult.SUCCESS){
            if(apiAvailability.isUserResolvableError(rc)){
                apiAvailability.getErrorDialog(this, rc, PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Log.i(TAG, "This device is not supported.");
                // Finishing the Activiy
                finish();
            }
            return false;
        }
        return true;
    }
}
