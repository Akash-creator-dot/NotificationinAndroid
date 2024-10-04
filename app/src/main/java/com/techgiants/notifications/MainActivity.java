package com.techgiants.notifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
private static final String CHANNEL_ID="my_channel";
    private static final int Notification_id=100;
    private static final int REQ_CODE=100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Intent iNotify=new Intent(getApplicationContext(),MainActivity.class);
        iNotify.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pi=PendingIntent.getActivity(this,REQ_CODE,iNotify,PendingIntent.FLAG_UPDATE_CURRENT);
        Drawable drawable= ResourcesCompat.getDrawable(getResources(),R.drawable.img,null);
        BitmapDrawable bitmapDrawable=(BitmapDrawable) drawable;
        Bitmap largeincon=bitmapDrawable.getBitmap();
        NotificationManager nm=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification= new Notification.Builder(this).setLargeIcon(largeincon).setSmallIcon(R.drawable.img).
                setContentText("New Message").setSubText("This Message from Akash").setChannelId(CHANNEL_ID).setContentIntent(pi).build();
        nm.createNotificationChannel(new NotificationChannel(CHANNEL_ID,"NEW_CHANNEL",NotificationManager.IMPORTANCE_HIGH));
        nm.notify(Notification_id,notification);

    }
}