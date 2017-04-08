package blankthings.rip.services;

import android.app.Notification;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import blankthings.rip.R;

/**
 * Created by iosifvilcea on 7/24/16.
 */
public class AppFbmService extends FirebaseMessagingService {

    private static final String TAG = AppFbmService.class.getSimpleName();
    private static final String NOTIFICATION_GROUP = "notification_group";
    private NotificationManagerCompat notificationManager;
    private static int notificationId = 1;

    @Override
    public void onCreate() {
        super.onCreate();
        notificationManager
                = (NotificationManagerCompat) NotificationManagerCompat.from(getApplicationContext());
    }


    @Override
    public void onMessageReceived(final RemoteMessage remoteMessage) {
        sendNotification(remoteMessage);
    }


    private void sendNotification(final RemoteMessage message) {
        // TODO - Display Notifications properly.
        final String title = "From " + message.getFrom();
        final String description = message.getNotification().getBody();

        final NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
        final Notification notification = builder
                .setContentTitle(title)
                .setContentText(description)
                .setSmallIcon(R.drawable.ic_notifications_white_18dp)
                .setGroup(NOTIFICATION_GROUP)
                .build();

        notificationManager.notify(notificationId, notification);
        notificationId++;
    }



    private void mockNotification() {
        final NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
        final Notification summaryNotification = builder
                .setContentTitle(notificationId + " new notifications!")
                .setSmallIcon(R.drawable.ic_notifications_white_18dp)
                .setStyle(new NotificationCompat.InboxStyle()
                        .addLine("n1 here")
                        .addLine("n2 here")
                        .setBigContentTitle("big content title")
                        .setSummaryText("some summary."))
                .setGroup(NOTIFICATION_GROUP)
                .setGroupSummary(true)
                .build();

        notificationManager.notify(notificationId, summaryNotification);
    }
}
