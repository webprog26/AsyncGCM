package com.example.webprog26.asyncproggcm;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;

import com.google.android.gms.gcm.GcmListenerService;

/**
 * Created by webprog26 on 02.01.2017.
 */

public class NotificationGCMHandler extends GcmListenerService {

    private static final String TAG = "NotifGCMHandler";

    public static final int NOTIFICATION_ID ="GCMNotification".hashCode();
    public static final String FORUM_TOPIC = "/topics/forum";
    public static final String USERNAME_KEY = "username";
    public static final String TEXT_KEY = "text";
    public static final String MSG_DELIVERY = "asynchronousandroid.forum";

    @Override
    public void onMessageReceived(String from, Bundle data) {
        super.onMessageReceived(from, data);

        String msgType = data.getString("type");

        if(msgType.startsWith("my_notifications")){
            createNotification(data.getString("title"), data.getString("body"));
        }
    }

    private void createNotification(String title, String body){
        android.support.v4.app.NotificationCompat.Builder builder =  new android.support.v4.app.
                NotificationCompat.Builder(this)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(android.R.drawable.stat_notify_chat)
                .setStyle(new android.support.v4.app.NotificationCompat.BigTextStyle()
                        .bigText(body));
        NotificationManager nm = (NotificationManager)
                this.getSystemService(
                        Context.NOTIFICATION_SERVICE);
        nm.notify(NOTIFICATION_ID, builder.build());
    }
}
