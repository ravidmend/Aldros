package com.example.newfuckingaldros;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.content.Context;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.os.VibrationEffect;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.widget.ImageView;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
/**
 *  CarOnTheWay
 *  activity for notifying a walker a car is about to run him over
 */
public class CarOnTheWay extends AppCompatActivity  {
    public static final String SHARED_PREFS = "sharedPrefs";

    private NotificationManagerCompat notificationManager;
    private Vibrator vibrator;
    private MediaPlayer mediaPlayer;

    Button Vibrate_CarOnTheWay;
    Button Sound_CarOnTheWay;
    Button StopVibrate_CarOnTheWay;
    Button StopSound_CarOnTheWay;
    Button SendNotification_CarOnTheWay;

    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_on_the_way);

        auth= FirebaseAuth.getInstance();

        Vibrate_CarOnTheWay =(Button) findViewById(R.id.Vibrate_CarOnTheWay);
        Sound_CarOnTheWay = (Button) findViewById(R.id.Sound_CarOnTheWay);
        StopVibrate_CarOnTheWay =(Button) findViewById(R.id.StopVibrate_CarOnTheWay);
        StopSound_CarOnTheWay = (Button) findViewById(R.id.StopSound_CarOnTheWay);
        SendNotification_CarOnTheWay =(Button) findViewById(R.id.SendNotification_CarOnTheWay);

        Vibrate_CarOnTheWay.setBackgroundColor(ContextCompat.getColor(this,R.color.red));
        Sound_CarOnTheWay.setBackgroundColor(ContextCompat.getColor(this,R.color.red));
        StopVibrate_CarOnTheWay.setBackgroundColor(ContextCompat.getColor(this,R.color.red));
        StopSound_CarOnTheWay.setBackgroundColor(ContextCompat.getColor(this,R.color.red));
        SendNotification_CarOnTheWay.setBackgroundColor(ContextCompat.getColor(this,R.color.red));

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        notificationManager = NotificationManagerCompat.from(this);


        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Vibrate_CarOnTheWay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrate();
            }
        });

        Sound_CarOnTheWay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start sound playback
                play();
            }
        });

        StopVibrate_CarOnTheWay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator.cancel();
            }
        });

        StopSound_CarOnTheWay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Stop sound playback
                pause(v);
            }
        });

        SendNotification_CarOnTheWay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification("Dear: "+GlobalUser.getUser().getDisplayName(), "אתה עומד להדרס");
            }
        });


        GlobalUser.getUser().addDailySituations();
        editor.putString("dailySituations",String.valueOf(GlobalUser.getUser().getDailySituations()));
        editor.apply();
        play();
        vibrate();
        sendNotification("Dear: "+GlobalUser.getUser().getDisplayName(), "אתה עומד להדרס");


        DatabaseReference databaseReference5 = FirebaseDatabase.getInstance().getReference(auth.getCurrentUser().getUid()).child("should_notify");
        databaseReference5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String should = snapshot.getValue().toString();
                if (!(snapshot.exists())) {
                    return;
                }
                if (should.equals("0")) {
                    vibrator.cancel();
                    stopPlayer();
                    Intent intent=new Intent(CarOnTheWay.this,PopayePage.class);
                    startActivity(intent);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    };
    //end of onCreate()

    /**
     *  sendNotification
     *  send a notification to the user
     */
    private void sendNotification(String title, String message) {

        String channelId = "message_notification_channel";
        String channelName = "Message Notifications";
        int notificationId = 1;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.aldrossymbol)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setWhen(System.currentTimeMillis());

        notificationManager.notify(notificationId, builder.build());
    }

    /**
     *  vibrate
     *  make the phone constantly vibrate
     */
    public  void vibrate() {
        long[] timings = {0, 300, 100, 500, 1000}; // Adjust the timing pattern as needed
        int[] amplitudes = {0, VibrationEffect.DEFAULT_AMPLITUDE, VibrationEffect.EFFECT_HEAVY_CLICK, VibrationEffect.DEFAULT_AMPLITUDE, VibrationEffect.DEFAULT_AMPLITUDE};
        int repeat = 0; // Set to 0 to play the pattern once
        VibrationEffect vibrationEffect = VibrationEffect.createWaveform(timings, amplitudes, repeat);
        vibrator.vibrate(vibrationEffect);
    }

    /**
     *  play
     *  plays the user's alarm sound
     */
    public  void play() {

        String Alarm = GlobalUser.getUser().getMyAlarm();

        if (mediaPlayer == null) {
            if (Alarm.equals("warning")) {
                mediaPlayer = MediaPlayer.create(this, R.raw.warning);
            }
            else if (Alarm.equals("MurderTrain")) {
                mediaPlayer = MediaPlayer.create(this, R.raw.murder_train);
            }
            else if (Alarm.equals("TheJigIsUp")) {
                mediaPlayer = MediaPlayer.create(this, R.raw.the__jig_is_up);
            }
            else if (Alarm.equals("OD")) {
                mediaPlayer = MediaPlayer.create(this, R.raw.od);
            }
            else if (Alarm.equals("MoonlightSonata")) {
                mediaPlayer = MediaPlayer.create(this, R.raw.moonlight_sonata);
            }
            else if (Alarm.equals("IcanDoWhatEverIWant_Finale")) {
                mediaPlayer = MediaPlayer.create(this, R.raw.i_can_do_whatever_i_want_finale);
            }
            else if (Alarm.equals("HipToBeScared")) {
                mediaPlayer = MediaPlayer.create(this, R.raw.hip_to_be_scared);
            }
            else if (Alarm.equals("HailToTheKing")) {
                mediaPlayer = MediaPlayer.create(this, R.raw.hail_to_the_king);
            }
            else if (Alarm.equals("FuneralDerangement")) {
                mediaPlayer = MediaPlayer.create(this, R.raw.funeral_derangement);
            }
            else if (Alarm.equals("Eyeless")) {
                mediaPlayer = MediaPlayer.create(this, R.raw.eyeless);
            }
            else if (Alarm.equals("CriticalAcclaimStart")) {
                mediaPlayer = MediaPlayer.create(this, R.raw.critical_acclaim_start);
            }
            else if (Alarm.equals("Critical Acclaim Solo")) {
                mediaPlayer = MediaPlayer.create(this, R.raw.critical_acclaim_solo);
            }
            else if (Alarm.equals("AmericanPsychoLastScene")) {
                mediaPlayer = MediaPlayer.create(this, R.raw.american_psycho_last_scene);
            }

            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mediaPlayer.start();
                }
            });
        }
        mediaPlayer.start();
    }

    /**
     *  pause
     *  pauses the media player
     */
    public  void pause(View V) {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }
    public  void stop(View V) {
        stopPlayer();
    }

    /**
     *  stopPlayer
     *  stop the mediaPlayer
     */
    private  void stopPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopPlayer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        vibrator.cancel();
        stopPlayer();
    }
    /**
     *  onBackPressed
     *  goes back to Popaye and stops vibration and sound
     */
    @Override
    public void onBackPressed() {
        FirebaseDatabase.getInstance().getReference(auth.getCurrentUser().getUid()).child("should_notify").setValue(0);
        vibrator.cancel();
        stopPlayer();
        Intent intent=new Intent(CarOnTheWay.this,PopayePage.class);
        startActivity(intent);
    }
}