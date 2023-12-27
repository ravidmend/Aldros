package com.example.newfuckingaldros;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.Manifest;
import com.google.android.gms.location.ActivityRecognitionClient;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import android.os.Handler;
import androidx.core.app.ActivityCompat;
import android.location.LocationListener;
import android.location.LocationManager;
import com.google.android.gms.location.ActivityRecognition;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.util.Log;
import com.google.android.gms.location.DetectedActivity;
import com.google.firebase.database.ValueEventListener;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
/**
 *  PopayePage
 *  activity where all the magic happens, take the user data ans send to the database
 */
public class PopayePage extends AppCompatActivity implements LocationListener  {
    public static final String SHARED_PREFS = "sharedPrefs";

    private static final int GPS_TIME_INTERVAL = 1000 /* * 5*/; // get gps location every 1 sec
    private static final int GPS_DISTANCE = 1000; // set the distance value in meter
    private static final int HANDLER_DELAY = 1000 /* * 5*/;
    private static final int START_HANDLER_DELAY = 0;
    private static final int PERMISSIONS_FINE_LOCATION = 1;
    private static final int PERMISSIONS_COARSE_LOCATION = 5;
    private static final int PERMISSIONS_BODY_SENSORS = 2;
    private static final int PERMISSIONS_ACTIVITY_RECOGNITION = 3;
    private static final int MAX_SAMPLES = 20;
    private static  int NUM_SAMPLES = 0;
    private static double[] accelerationSamples = new double[MAX_SAMPLES];
    private int sampleIndex = 0;
    private static int ACCELEROMETER_SLOWER = 0;
    private static int ACCELEROMETER_SHOWTIME = 10;
    final static String[] PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION/*,Manifest.permission.ACCESS_BACKGROUND_LOCATION*/,Manifest.permission.BODY_SENSORS,Manifest.permission.ACTIVITY_RECOGNITION};
    final static int PERMISSION_ALL = 1;

    ////////////////////////////////////////////////////////////////////////////////////////
    public static final String BROADCAST_DETECTED_ACTIVITY = "activity_intent";

    public static final int CONFIDENCE = 70;
    private String TAG = PopayePage.class.getSimpleName();
    BroadcastReceiver broadcastReceiver;

    private TextView txtActivity, txtConfidence;
    private ImageView imgActivity;
    private Button btnStartTrcking, btnStopTracking;
    ////////////////////////////////////////////////////////////////////////////////////////

    Geocoder geocoder;

    LocationManager locationManager;
    SensorManager sensorManager;
    private Sensor linearAccelerometer;
    private OrientationSensor orientationSensor;

    ActivityRecognitionClient activityRecognitionClient;
    TextView Text_PopayePage, angle_PopayePage;
    ImageView Settings_PopayePage;
    Button WalkerPage_PopayePage;
    Button CarPage_PopayePage;
    Button MyCarLocation_PopayePage;
    TextView latitude_PopayePage;
    TextView longitude_PopayePage;
    TextView speed_PopayePage;
    TextView acceleration_PopayePage;
    TextView Address_PopayePage;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popaye_page);

        auth= FirebaseAuth.getInstance();

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Text_PopayePage=(TextView) findViewById(R.id.Text_PopayePage);
        angle_PopayePage=(TextView) findViewById(R.id.angle_PopayePage);
        Settings_PopayePage=(ImageView)findViewById(R.id.Settings_PopayePage);
        WalkerPage_PopayePage=(Button)findViewById(R.id.WalkerPage_PopayePage);
        WalkerPage_PopayePage.setBackgroundColor(ContextCompat.getColor(this,R.color.red));
        CarPage_PopayePage=(Button)findViewById(R.id.CarPage_PopayePage);
        CarPage_PopayePage.setBackgroundColor(ContextCompat.getColor(this,R.color.red));
        MyCarLocation_PopayePage=(Button)findViewById(R.id.MyCarLocation_PopayePage);
        MyCarLocation_PopayePage.setBackgroundColor(ContextCompat.getColor(this,R.color.teal_700));
        Text_PopayePage.setText("You are safe:\n" + GlobalUser.getUser().getDisplayName());
        latitude_PopayePage=(TextView) findViewById(R.id.latitude_PopayePage);
        longitude_PopayePage=(TextView) findViewById(R.id.longitude_PopayePage);
        speed_PopayePage=(TextView) findViewById(R.id.speed_PopayePage);
        acceleration_PopayePage=(TextView) findViewById(R.id.acceleration_PopayePage);
        Address_PopayePage=(TextView) findViewById(R.id.Address_PopayePage);


        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        linearAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        orientationSensor = new OrientationSensor(this);

        /////////////////////////////////////////////////////////////////////////
        txtActivity = findViewById(R.id.txt_activity);
        txtConfidence = findViewById(R.id.txt_confidence);
        imgActivity = findViewById(R.id.Pic_PopayePage);
        btnStartTrcking = findViewById(R.id.btn_start_tracking);
        btnStopTracking = findViewById(R.id.btn_stop_tracking);
        /////////////////////////////////////////////////////////////////////////


        geocoder = new Geocoder(this, Locale.getDefault());


        // Register the sensor listener
        if (linearAccelerometer != null) {
            sensorManager.registerListener(linearAccelerometerListener, linearAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }
        else {
            Toast.makeText(getApplicationContext(),"Sensors service not detected", Toast.LENGTH_SHORT).show();
        }

        Settings_PopayePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PopayePage.this,SettingsPage.class);
                startActivity(intent);
                //sensorManager.unregisterListener(accelerometerListener);
                //releaseLocationManager();
                sensorManager.unregisterListener(linearAccelerometerListener);
                releaseLocationManager();
            }
        });
        WalkerPage_PopayePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalUser.getUser().setUserType("driver");
                FirebaseDatabase.getInstance().getReference(auth.getCurrentUser().getUid()).child("should_notify").setValue(1);
                Intent intent=new Intent(PopayePage.this,WalkerOnTheRoad.class);
                startActivity(intent);
            }
        });

        CarPage_PopayePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalUser.getUser().setUserType("walker");
                FirebaseDatabase.getInstance().getReference(auth.getCurrentUser().getUid()).child("should_notify").setValue(1);
                Intent intent=new Intent(PopayePage.this,CarOnTheWay.class);
                startActivity(intent);
            }
        });

        MyCarLocation_PopayePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PopayePage.this,MyCarLocationPage.class);
                startActivity(intent);
                sensorManager.unregisterListener(linearAccelerometerListener);
                releaseLocationManager();
            }
        });

        /////////////////////////////////////////////////////////////////////////////////////////////
        btnStartTrcking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTracking();
            }
        });

        btnStopTracking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopTracking();
            }
        });

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(BROADCAST_DETECTED_ACTIVITY)) {
                    int type = intent.getIntExtra("type", -1);
                    int confidence = intent.getIntExtra("confidence", 0);
                    handleUserActivity(type, confidence);
                }
            }
        };
        /////////////////////////////////////////////////////////////////////////////////////////////


        if (Build.VERSION.SDK_INT >= 31) {
            requestPermissions(PERMISSIONS, PERMISSION_ALL);
        }

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                requestLocation();
                handler.postDelayed(this, HANDLER_DELAY);
            }
        }, START_HANDLER_DELAY);


        activityRecognitionClient = ActivityRecognition.getClient(this);


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            requestLocation();
            Location firstLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (firstLocation != null) {
                latitude_PopayePage.setText("Latitude: " + firstLocation.getLatitude());
                longitude_PopayePage.setText("Longitude: " + firstLocation.getLongitude());
                speed_PopayePage.setText("speed: " + firstLocation.getSpeed());

                GlobalUser.getUser().setCurrentLocation(firstLocation);

                try {
                    List<Address> addresses = geocoder.getFromLocation( firstLocation.getLatitude(), firstLocation.getLongitude(), 1);
                    if (addresses.size() > 0) {
                        Address address = addresses.get(0);
                        String userAddress = address.getAddressLine(0); // Get the first address line
                        Address_PopayePage.setText("Adress: " + userAddress);
                        GlobalUser.getUser().setUserAddress(userAddress);

                    } else {
                        // No address found for the coordinates
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"problemos: "+e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }
        else {
            checkAndRequestPermissions();
        }


        startTracking();


        //taking data from realtime
        DatabaseReference databaseReference5 = FirebaseDatabase.getInstance().getReference(auth.getCurrentUser().getUid()).child("should_notify");
        databaseReference5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String should = snapshot.getValue().toString();
                if (!(snapshot.exists())) {
                    return;
                }
                if (should.equals("1")) {
                    if (GlobalUser.getUser().getUserType().equals("walker")) {
                        sensorManager.unregisterListener(linearAccelerometerListener);
                        releaseLocationManager();
                        Intent intent=new Intent(PopayePage.this,CarOnTheWay.class);
                        startActivity(intent);
                    } else {
                        sensorManager.unregisterListener(linearAccelerometerListener);
                        releaseLocationManager();
                        Intent intent=new Intent(PopayePage.this,WalkerOnTheRoad.class);
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        ///////////////////////////////////////////
        // Set up the alarm to trigger daily at 18:07
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 10);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(PopayePage.this, DailyNotificationReceiver.class);
        intent.setAction("DAILY_NOTIFICATION_ACTION");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(PopayePage.this, 0, intent, PendingIntent.FLAG_MUTABLE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        ////////////////////////////////////////
    }
    //end of onCreate

    /**
     *  onLocationChanged
     *  updates the user's current location when it changes
     */
    @Override
    public void onLocationChanged(@NonNull Location location) {
        latitude_PopayePage.setText("Latitude: " + location.getLatitude());
        longitude_PopayePage.setText("Longitude: " + location.getLongitude());
        speed_PopayePage.setText("Speed: " + location.getSpeed());

        GlobalUser.getUser().setCurrentLocation(location);

        //locationManager.removeUpdates(this);

        try {
            List<Address> addresses = geocoder.getFromLocation( location.getLatitude(), location.getLongitude(), 1);
            if (addresses.size() > 0) {
                Address address = addresses.get(0);
                String userAddress = address.getAddressLine(0); // Get the first address line
                Address_PopayePage.setText("Adress: " + userAddress);
                GlobalUser.getUser().setUserAddress(userAddress);

            } else {
                // No address found for the coordinates
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"problemos: "+e.getMessage(), Toast.LENGTH_LONG).show();
        }
        //
        locationManager.removeUpdates(this);
    }

    /**
     *  requestLocation
     *  asks the location manager to get the location again
     */
    private void requestLocation() {
        if (locationManager == null)
            locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                        GPS_TIME_INTERVAL, GPS_DISTANCE, this);
            }
        }
    }
    /**
     *  onRequestPermissionsResult
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    requestLocation();
                    handler.postDelayed(this, HANDLER_DELAY);
                }
            }, START_HANDLER_DELAY);
        } else {
            finish();
        }
    }

    private void updateAccelerationUI(double acceleration) {
        GlobalUser.getUser().setAcceleration(acceleration);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Unregister sensor listener
        sensorManager.unregisterListener(linearAccelerometerListener);
        releaseLocationManager();
        FirebaseDatabase.getInstance().getReference(auth.getCurrentUser().getUid()).child("isActive").setValue(false);
        orientationSensor.stop();

    }
    @Override
    protected void onPause() {
        super.onPause();
        //sensorManager.unregisterListener(sensorEventListener);
        // sensorManager.unregisterListener(linearAccelerometerListener);

        if (locationManager != null) {
            locationManager.removeUpdates(this);
            locationManager = null;
        }


        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);

    }


    private SensorEventListener linearAccelerometerListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {

            FirebaseDatabase.getInstance().getReference(auth.getCurrentUser().getUid()).child("direction").setValue(orientationSensor.getAzimuth());
            angle_PopayePage.setText("angle: "+ orientationSensor.getAzimuth());


            float xAcceleration = event.values[0];
            float yAcceleration = event.values[1];
            float zAcceleration = event.values[2];

            if (Math.abs(zAcceleration)<0.206 && Math.abs(zAcceleration)>0.106 ) {
                if (zAcceleration<0) {
                    zAcceleration = (float) (zAcceleration +0.2);
                }
                else if (zAcceleration>0) {
                    zAcceleration = (float) (zAcceleration -0.2);
                }
            }

            double acceleration = Math.sqrt(Math.pow(xAcceleration, 2) + Math.pow(yAcceleration, 2) + Math.pow(zAcceleration, 2));
            if ((xAcceleration < 0) /*|| (yAcceleration < 0) || (zAcceleration < 0)*/) {
                acceleration = -acceleration;
            }

            accelerationSamples[sampleIndex] = acceleration;
            sampleIndex = (sampleIndex + 1) % MAX_SAMPLES;

            if (NUM_SAMPLES<MAX_SAMPLES) {
                NUM_SAMPLES++;
            }

            // Calculate the average of the stored samples
            float totalAcceleration = 0;
            for (int i = 0; i < NUM_SAMPLES; i++) {
                totalAcceleration += accelerationSamples[i];
            }
            totalAcceleration /= NUM_SAMPLES;


            if (ACCELEROMETER_SLOWER == ACCELEROMETER_SHOWTIME) {
                // Update UI with the smoothed acceleration
                if(Math.abs(totalAcceleration) < 0.21) {
                    totalAcceleration = 0;
                }
                updateAccelerationUI(totalAcceleration);
                acceleration_PopayePage.setText("Acceleration: " + totalAcceleration +"\nx: " + xAcceleration + "\ny: " + yAcceleration + "\nz: " + zAcceleration);
                ACCELEROMETER_SLOWER=0;
            }
            else {
                ACCELEROMETER_SLOWER++;
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // Handle accuracy changes if needed
        }
    };

    /**
     *  checkAndRequestPermissions
     *  request permissions if needed
     */
    private void checkAndRequestPermissions() {
        // Check and request location permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getApplicationContext(),"We need GPS to keep you safe", Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSIONS_COARSE_LOCATION);
        }

        // Check and request body sensors permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BODY_SENSORS) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getApplicationContext(),"We need Sensors access to keep you safe", Toast.LENGTH_LONG).show();
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BODY_SENSORS}, PERMISSIONS_BODY_SENSORS);
        }

        // Check and request ACTIVITY_RECOGNITION permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACTIVITY_RECOGNITION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getApplicationContext(),"We need ACTIVITY RECOGNITION access to keep you safe", Toast.LENGTH_LONG).show();
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACTIVITY_RECOGNITION}, PERMISSIONS_ACTIVITY_RECOGNITION);
        }
    }
    /**
     *  onBackPressed
     *  overrides default
     */
    @Override
    public void onBackPressed() {
    }
    /**
     *  releaseLocationManager
     *  release Location Manager
     */
    private void releaseLocationManager() {
        if (locationManager != null) {
            locationManager.removeUpdates(this);
            locationManager = null;
        }
    }
    /**
     *  handleUserActivity
     *  gets the user activity, update data base and changes photo
     */
    private void handleUserActivity(int type, int confidence) {
        String label = getString(R.string.activity_unknown);
        int icon = R.drawable.popaye;

        switch (type) {
            case DetectedActivity.IN_VEHICLE: {
                label = getString(R.string.activity_in_vehicle);
                icon = R.drawable.popeye_in_car;
                GlobalUser.getUser().setUserType("driver");
                break;
            }
            case DetectedActivity.ON_BICYCLE: {
                label = getString(R.string.activity_on_bicycle);
                icon = R.drawable.popeye_on_bikes;
                GlobalUser.getUser().setUserType("driver");
                break;
            }
            case DetectedActivity.ON_FOOT: {
                label = getString(R.string.activity_on_foot);
                icon = R.drawable.popeye_walking;
                GlobalUser.getUser().setUserType("walker");
                break;
            }
            case DetectedActivity.RUNNING: {
                label = getString(R.string.activity_running);
                icon = R.drawable.popeye_running;
                GlobalUser.getUser().setUserType("walker");
                break;
            }
            case DetectedActivity.STILL: {
                label = getString(R.string.activity_still);
                break;
            }
            case DetectedActivity.WALKING: {
                label = getString(R.string.activity_walking);
                icon = R.drawable.popeye_walking;
                GlobalUser.getUser().setUserType("walker");
                break;
            }
            case DetectedActivity.UNKNOWN: {
                label = getString(R.string.activity_unknown);
                icon = R.drawable.popeye_unknown;
                break;
            }
        }

        Log.e(TAG, "User activity: " + label + ", Confidence: " + confidence);

        if (confidence > CONFIDENCE) {
            txtActivity.setText(label);
            txtConfidence.setText("Confidence: " + confidence);
            imgActivity.setImageResource(icon);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver,
                new IntentFilter(BROADCAST_DETECTED_ACTIVITY));
    }
    /**
     *  startTracking
     *  start activity recognition
     */
    private void startTracking() {
        Intent intent1 = new Intent(PopayePage.this, BackgroundDetectedActivitiesService.class);
        startService(intent1);
    }
    /**
     *  stopTracking
     *  stop activity recognition
     */
    private void stopTracking() {
        Intent intent = new Intent(PopayePage.this, BackgroundDetectedActivitiesService.class);
        stopService(intent);
    }
}
