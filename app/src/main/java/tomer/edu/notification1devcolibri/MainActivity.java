package tomer.edu.notification1devcolibri;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private NotificationManager nm;
    private final int NOTIFICATION_ID = 127;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nm = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void showNotification(View view){

        Notification.Builder builder = new Notification.Builder(getApplicationContext());

        Intent intent = new Intent(getApplicationContext(), FinishActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        builder
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getApplication().getResources(),R.drawable.ic_launcher))
                .setTicker("Novoe uvedomlenie")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setContentTitle("Uvedomlenie")
                .setContentText("Press to know the secret")
                .setProgress(100, 20, true);

        Notification notification = builder.build();
        //notification.defaults = Notification.DEFAULT_ALL;
        notification.defaults = Notification.DEFAULT_SOUND;
        ////notification.defaults = Notification.DEFAULT_SOUND;
        //notification.sound = Uri.parse("android.resource://tomer.edu.notification1devcolibri/" + R.raw.my_sound);

        //long[] vibrate = new long[]{1500, 1000, 1500, 1000};
        //notification.vibrate =vibrate;

        //notification.flags = notification.flags | Notification.FLAG_ONGOING_EVENT;
        notification.flags = notification.flags | Notification.FLAG_INSISTENT;

        nm.notify(NOTIFICATION_ID, notification);

     }

    public void CancelNotificstion(View view) {
        nm.cancel(NOTIFICATION_ID);
    }
}
