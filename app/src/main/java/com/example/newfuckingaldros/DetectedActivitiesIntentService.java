package com.example.newfuckingaldros;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.google.android.gms.location.ActivityRecognitionResult; import com.google.android.gms.location.DetectedActivity;
import java.util.ArrayList;
/**
 *  DetectedActivitiesIntentService
 *  background class that gets the results of the activity recognition and sends it to PopayePage
 */
public class DetectedActivitiesIntentService  extends IntentService {

    public static final String BROADCAST_DETECTED_ACTIVITY = "activity_intent";
    protected static final String TAG = DetectedActivitiesIntentService.class.getSimpleName();

    public DetectedActivitiesIntentService() {
        // Use the TAG to name the worker thread.
        super(TAG);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    /**
     *  onHandleIntent
     *  get the probable activity
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        ActivityRecognitionResult result = ActivityRecognitionResult.extractResult(intent);

        // Get the list of the probable activities associated with the current state of the
        // device. Each activity is associated with a confidence level, which is an int between
        // 0 and 100.
        ArrayList<DetectedActivity> detectedActivities = (ArrayList) result.getProbableActivities();

        for (DetectedActivity activity : detectedActivities) {
            Log.e(TAG, "Detected activity: " + activity.getType() + ", " + activity.getConfidence());
            broadcastActivity(activity);
        }
    }
    /**
     *  broadcastActivity
     *  broadcast the data needed(in this case the detected activity)
     */
    private void broadcastActivity(DetectedActivity activity) {
        Intent intent = new Intent(BROADCAST_DETECTED_ACTIVITY);
        intent.putExtra("type", activity.getType());
        intent.putExtra("confidence", activity.getConfidence());
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}
