package autoxwatchdog.commander;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
    private NotificationManager mNotifyManager;
    private static final int NOTIFICATION_ID = 0;
    private Button button_notify;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationChannel();
        button_notify = findViewById(R.id.notify);
        button_notify.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                sendNotification();
            }
        });
    }

    public void testCommands(View view) {
        Intent intent = new Intent(this, CameraCommand.class);
        startActivity(intent);


    }

    public void viewCaptures(View view) {
        Intent intent = new Intent(this, AutoXWatchdogImages.class);
        startActivity(intent);
    }

    //Create Notification for Alerts
    public void sendNotification(){
        NotificationCompat.Builder notifyBuilder = getNotificationBuilder();
        mNotifyManager.notify(NOTIFICATION_ID, notifyBuilder.build());
    }

    public void createNotificationChannel()
    {
        mNotifyManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        if(android.os.Build.VERSION.SDK_INT >=
                                    android.os.Build.VERSION_CODES.O){
            //Create notification channel
            NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID,
                    "Object Motion Detected", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("AutoX Watchdog: Motion Detected!!!");
            mNotifyManager.createNotificationChannel(notificationChannel);

        }
    }

    private NotificationCompat.Builder getNotificationBuilder(){
        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID)
                .setContentTitle("Object Motion Detected")
                .setContentText("Motion has been detected near or around your vehicle")
                .setSmallIcon(R.drawable.ic_car_icon);

            return notifyBuilder;

    }

}
