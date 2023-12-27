package com.example.newfuckingaldros;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.SharedPreferences;
import android.os.Build;
import androidx.annotation.NonNull;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.HashMap;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
/**
 *  DailyNotificationReceiver
 *  background class that send the user notification once a day
 */
public class DailyNotificationReceiver extends BroadcastReceiver {
    public static final String SHARED_PREFS = "sharedPrefs";

    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String firebaseUserUid = sharedPreferences.getString("firebaseUserUid", "");
        String rememberMe = sharedPreferences.getString("rememberMe", "");

        if (intent.getAction() != null && intent.getAction().equals("DAILY_NOTIFICATION_ACTION") && rememberMe.equals("true") && !firebaseUserUid.equals("")) {
            // Create a notification channel (required for Android 8.0 and higher)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                String channelId = "daily_notification_channel";
                CharSequence channelName = "Daily Notification Channel";
                int importance = NotificationManager.IMPORTANCE_DEFAULT;
                NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
                NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
                notificationManager.createNotificationChannel(channel);
            }

            HashMap<String, Object> userData = new HashMap<>();
            DatabaseReference databaseReference69 = FirebaseDatabase.getInstance().getReference(firebaseUserUid).child("UserData");

            databaseReference69.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (!(snapshot.exists())) {
                        return;
                    }
                    for (DataSnapshot dataSnapshot:snapshot.getChildren()) {
                        userData.put(dataSnapshot.getKey(), dataSnapshot.getValue());
                    }

                    if (userData.get("dailySituations") != null) {
                        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                        // Create a SimpleDateFormat object with your desired format
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);

                        // Get the current date and time
                        Date currentDate = new Date();

                        // Format the date and time
                        String formattedDate = dateFormat.format(currentDate);
                        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                        // Create the notification
                        Notification.Builder builder = new Notification.Builder(context)
                                .setSmallIcon(R.drawable.aldrossymbol)
                                .setContentTitle(formattedDate + ": You survived!")
                                .setContentText("Aldros notified you: "+ userData.get("dailySituations").toString() +" in the last 24 hours")
                                .setChannelId("daily_notification_channel")
                                .setWhen(System.currentTimeMillis());

                        // Create an intent to open your app when the notification is tapped
                        Intent notificationIntent = new Intent(context, AldrosEnterPage.class);
                        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_MUTABLE);
                        builder.setContentIntent(pendingIntent);

                        // Send the notification
                        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                        notificationManager.notify(1, builder.build());

                        FirebaseDatabase.getInstance().getReference(firebaseUserUid ).child("UserData").child("dailySituations").setValue(0);


                        FirebaseDatabase.getInstance().getReference(firebaseUserUid ).child("UserData").child("lastSituationsReset").setValue(formattedDate);
                        try {
                            GlobalUser.getUser().resetDailySituations();
                        } catch (Exception e) {
                            String dd = e.getMessage();
                        }
                    }

                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    String dd = error.toString();
                    String ssd = error.getMessage();
                }
            });
        }
    }
    //end of onReceive
}
