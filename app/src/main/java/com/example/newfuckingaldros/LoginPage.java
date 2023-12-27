package com.example.newfuckingaldros;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Objects;
/**
 *  LoginPage
 *  activity for login an existing account
 */
public class LoginPage extends AppCompatActivity {
    public static final String SHARED_PREFS = "sharedPrefs";

    TextView LoginPage;
    TextInputEditText Gmail_LoginPage;
    TextInputEditText Password_LoginPage;
    Button LoginB_LoginPage;
    Button ForgotPasswordB_LoginPage;
    ImageView Back_LoginPage;
    CheckBox RememberMe_LoginPage;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        LoginPage=(TextView)findViewById(R.id.LoginPage);
        Gmail_LoginPage=(TextInputEditText)findViewById(R.id.Gmail_LoginPage);
        Password_LoginPage=(TextInputEditText)findViewById(R.id.Password_LoginPage);
        LoginB_LoginPage=(Button)findViewById(R.id.LoginB_LoginPage);
        ForgotPasswordB_LoginPage=(Button)findViewById(R.id.ForgotPasswordB_LoginPage);
        Back_LoginPage=(ImageView)findViewById(R.id.Back_LoginPage);
        RememberMe_LoginPage = (CheckBox) findViewById(R.id.RememberMe_LoginPage);


        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        LoginB_LoginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth= FirebaseAuth.getInstance();

                String Gmail,Password;
                Gmail=String.valueOf(Gmail_LoginPage.getText());
                Password=String.valueOf(Password_LoginPage.getText());

                if(TextUtils.isEmpty(Gmail)){
                    Gmail_LoginPage.setError("please enter Gmail");
                    Gmail_LoginPage.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty(Password)){
                    Password_LoginPage.setError("please enter Password");
                    Password_LoginPage.requestFocus();
                    return;
                }

                auth.signInWithEmailAndPassword(Gmail, Password).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful() && Objects.requireNonNull(auth.getCurrentUser()).isEmailVerified()) {
                            editor.putString("back","Login");
                            editor.putString("Gmail",Gmail);
                            editor.putString("Password",Password);
                            if (RememberMe_LoginPage.isChecked()) {
                                editor.putString("rememberMe","true");
                            }
                            else {
                                editor.putString("rememberMe","false");
                            }
                            editor.apply();

                            //taking data from realtime

                            User myUser = new User(auth.getCurrentUser(), Gmail,Password);
                            GlobalUser.setUser(myUser);

                            HashMap<String, Object> userData = new HashMap<>();

                            DatabaseReference databaseReference69 = FirebaseDatabase.getInstance().getReference(auth.getCurrentUser().getUid()).child("UserData");

                            databaseReference69.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (!(snapshot.exists())) {
                                        return;
                                    }

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
                                        if (isDateWithinLast24Hours(userData.get("lastSituationsReset").toString())) {
                                            GlobalUser.getUser().setDailySituations(Integer.parseInt(userData.get("dailySituations").toString()));
                                        }
                                        else {
                                            GlobalUser.getUser().setDailySituations(0);
                                            FirebaseDatabase.getInstance().getReference(auth.getCurrentUser().getUid()).child("UserData").child("dailySituations").setValue(0);
                                            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                            // Create a SimpleDateFormat object with your desired format
                                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);

                                            // Get the current date and time
                                            Date currentDate = new Date();

                                            // Format the date and time
                                            String formattedDate = dateFormat.format(currentDate);
                                            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                            FirebaseDatabase.getInstance().getReference(auth.getCurrentUser().getUid()).child("UserData").child("lastSituationsReset").setValue(formattedDate);
                                        }
                                    }
                                    editor.putString("firebaseUserUid",auth.getCurrentUser().getUid());
                                    editor.apply();

                                    Toast.makeText(getApplicationContext(), "Welcome back: " + GlobalUser.getUser().getDisplayName(), Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(LoginPage.this,AllowPermissionsPage.class);
                                    startActivity(intent);

                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    String dd = error.toString();
                                    String ssd = error.getMessage();
                                }
                            });

                            FirebaseDatabase.getInstance().getReference(auth.getCurrentUser().getUid()).child("isActive").setValue(true);

                        } else if (task.isSuccessful() && (!Objects.requireNonNull(auth.getCurrentUser()).isEmailVerified())) {

                            Objects.requireNonNull(auth.getCurrentUser()).sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        editor.putString("back","Login");
                                        editor.putString("Gmail",Gmail);
                                        editor.putString("Password",Password);
                                        if (RememberMe_LoginPage.isChecked()) {
                                            editor.putString("rememberMe","true");
                                        }
                                        else {
                                            editor.putString("rememberMe","false");
                                        }
                                        editor.apply();

                                        User myUser = new User(auth.getCurrentUser(), Gmail,Password);
                                        GlobalUser.setUser(myUser);

                                        HashMap<String, Object> userData = new HashMap<>();

                                        DatabaseReference databaseReference69 = FirebaseDatabase.getInstance().getReference(auth.getCurrentUser().getUid()).child("UserData");

                                        databaseReference69.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                if (!(snapshot.exists())) {
                                                    return;
                                                }

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
                                                    if (isDateWithinLast24Hours(userData.get("lastSituationsReset").toString())) {
                                                        GlobalUser.getUser().setDailySituations(Integer.parseInt(userData.get("dailySituations").toString()));
                                                    }
                                                    else {
                                                        GlobalUser.getUser().setDailySituations(0);
                                                        FirebaseDatabase.getInstance().getReference(auth.getCurrentUser().getUid()).child("UserData").child("dailySituations").setValue(0);
                                                        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                        // Create a SimpleDateFormat object with your desired format
                                                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);

                                                        // Get the current date and time
                                                        Date currentDate = new Date();

                                                        // Format the date and time
                                                        String formattedDate = dateFormat.format(currentDate);
                                                        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                        FirebaseDatabase.getInstance().getReference(auth.getCurrentUser().getUid()).child("UserData").child("lastSituationsReset").setValue(formattedDate);
                                                    }
                                                }

                                                editor.putString("firebaseUserUid",auth.getCurrentUser().getUid());
                                                editor.apply();

                                                Toast.makeText(getApplicationContext(), "Welcome back: " + GlobalUser.getUser().getDisplayName(), Toast.LENGTH_SHORT).show();
                                                Toast.makeText(getApplicationContext(),"please verify your gmail", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(LoginPage.this, ClickLinkCreateAccountPage.class);
                                                startActivity(intent);

                                            }
                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {
                                                String dd = error.toString();
                                                String ssd = error.getMessage();
                                            }
                                        });

                                        FirebaseDatabase.getInstance().getReference(auth.getCurrentUser().getUid()).child("isActive").setValue(true);

                                    }
                                    else {
                                        Toast.makeText(LoginPage.this, "error please try again in a few seconds", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                        else {
                            Toast.makeText(LoginPage.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        ForgotPasswordB_LoginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginPage.this,ForgotPasswordPage.class);
                startActivity(intent);
            }
        });

        Back_LoginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    //end of onCreate

    /**
     *  isDateWithinLast24Hours
     *  calculates the time between the date received and the current time and return true if it's
     *  less that 24 hours
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