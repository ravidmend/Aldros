package com.example.newfuckingaldros;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Objects;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *  AldrosEnterPage
 *  the activity that opens on app lunch
 */
public class AldrosEnterPage extends AppCompatActivity {
    public static final String SHARED_PREFS = "sharedPrefs";
    Button Login_AldrosEnterPage;
    Button CreateNewAccount_AldrosEnterPage;
    TextView Display;
    ImageView pic_AldrosEnterPage;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_page);
        FirebaseApp.initializeApp(this);

        Login_AldrosEnterPage=(Button)findViewById(R.id.loginB_AldrosEnterPage);
        CreateNewAccount_AldrosEnterPage=(Button)findViewById(R.id.CreateNewAccountB_AldrosEnterPage);
        Display=(TextView)findViewById(R.id.AldrosEnterPage);
        pic_AldrosEnterPage=(ImageView)findViewById(R.id.pic_AldrosEnterPage);


        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //check if the user checked remember me
        checkBox();

        Login_AldrosEnterPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AldrosEnterPage.this,LoginPage.class);
                startActivity(intent);
            }
        });

        CreateNewAccount_AldrosEnterPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AldrosEnterPage.this,CreateNewAccountPage.class);
                startActivity(intent);
            }
        });
    }
    //end of onCreate()

    /**
     * checkBox
     * checks and handle the case when the user pressed remember me in the last use of the app.
     */
    private void checkBox() {
        try {
            auth= FirebaseAuth.getInstance();
        }
        catch (Exception e) {
            String c = e.getMessage();
            String d = e.toString();
        }

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String Gmail = sharedPreferences.getString("Gmail", "");
        String Password = sharedPreferences.getString("Password", "");
        String rememberMe = sharedPreferences.getString("rememberMe", "");


        if(rememberMe.equals("true")) {
            try {
                auth.signInWithEmailAndPassword(Gmail, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful() && Objects.requireNonNull(auth.getCurrentUser()).isEmailVerified()) {
                            //initialize the global user
                            User myUser = new User(auth.getCurrentUser(), Gmail,Password);
                            GlobalUser.setUser(myUser);

                            HashMap<String, Object> userData = new HashMap<>();
                            DatabaseReference databaseReference69 = FirebaseDatabase.getInstance().getReference(auth.getCurrentUser().getUid()).child("UserData");

                            //retrieve the user's data from the realtime database
                            databaseReference69.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (!snapshot.exists())
                                        return;

                                    for (DataSnapshot dataSnapshot:snapshot.getChildren()) {
                                        userData.put(dataSnapshot.getKey(), dataSnapshot.getValue());
                                    }

                                    if (userData.get("displayName") != null) {
                                        GlobalUser.getUser().setDisplayName(userData.get("displayName").toString());
                                    }

                                    if (userData.get("didAcceptTerms") != null) {
                                        GlobalUser.getUser().setDidAcceptedTerms(Boolean.parseBoolean(userData.get("didAcceptTerms").toString()));
                                    }

                                    if (userData.get("carLatitude") != null) {
                                        GlobalUser.getUser().setCarLatitude(Double.parseDouble(userData.get("carLatitude").toString()));
                                    }

                                    if (userData.get("carLongitude") != null) {
                                        GlobalUser.getUser().setCarLongitude(Double.parseDouble(userData.get("carLongitude").toString()));
                                    }

                                    if (userData.get("myAlarm") != null) {
                                        GlobalUser.getUser().setMyAlarm(userData.get("myAlarm").toString());
                                    }

                                    if (userData.get("carAddress") != null) {
                                        GlobalUser.getUser().setCarAddress(userData.get("carAddress").toString());
                                    }
                                    if (userData.get("dailySituations") != null) {
                                        //if the user's "situations" data wasn't restated in the last 24 hours
                                        if (isDateWithinLast24Hours(userData.get("lastSituationsReset").toString())) {
                                            GlobalUser.getUser().setDailySituations(Integer.parseInt(userData.get("dailySituations").toString()));
                                        }
                                        else {
                                            //set it to 0
                                            GlobalUser.getUser().setDailySituations(0);
                                            FirebaseDatabase.getInstance().getReference(auth.getCurrentUser().getUid()).child("UserData").child("dailySituations").setValue(0);

                                            // Create a SimpleDateFormat object with your desired format
                                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);

                                            // Get the current date and time
                                            Date currentDate = new Date();

                                            // Format the date and time
                                            String formattedDate = dateFormat.format(currentDate);
                                            //set the restart hour
                                            FirebaseDatabase.getInstance().getReference(auth.getCurrentUser().getUid()).child("UserData").child("lastSituationsReset").setValue(formattedDate);
                                        }
                                    }
                                    editor.apply();

                                    Toast.makeText(getApplicationContext(), "Welcome back: " + GlobalUser.getUser().getDisplayName(), Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(AldrosEnterPage.this, AllowPermissionsPage.class);
                                    startActivity(intent);

                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

                            editor.putString("back","Aldros");
                            editor.putString("firebaseUserUid",auth.getCurrentUser().getUid());
                            editor.apply();
                            FirebaseDatabase.getInstance().getReference(auth.getCurrentUser().getUid()).child("isActive").setValue(true);
                        }
                    }
                });
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Aldros - making the world safer", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     *  onBackPressed
     *  do nothing but runs over the default
     */
    @Override
    public void onBackPressed() {
    }

    /**
     *  isDateWithinLast24Hours
     *  calculates the time between given date and current date returns true id its less than a day
     */
    private boolean isDateWithinLast24Hours(String dateString) {
        try {
            // Parse the input string to LocalDateTime
            LocalDateTime inputDateTime = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            // Get the current date and time
            LocalDateTime currentDateTime = LocalDateTime.now();

            // Calculate the difference between the input and current date and time
            long hoursAgo = java.time.Duration.between(inputDateTime, currentDateTime).toHours();

            return hoursAgo < 24;
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Handle parsing errors
        }
    }
}
