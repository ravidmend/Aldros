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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Objects;
/**
 *  SettingsPage
 *  activity settings, let the user change the app settings to his preferences
 */
public class SettingsPage extends AppCompatActivity {
    public static final String SHARED_PREFS = "sharedPrefs";

    TextView SettingsPage;
    TextView ShowGmail_SettingsPage;
    TextView ShowDisplayName_SettingsPage;
    TextView AreYouSure_SettingsPage;
    Button ChangeGmail_SettingsPage;
    Button ChangePassword_SettingsPage;
    Button ChangeDisplayName_SettingsPage;
    Button LogOut_SettingsPage;
    Button DeleteAccount_SettingsPage;
    Button No_SettingsPage;
    Button Yes_SettingsPage;
    Button ChangeAlarm_SettingsPage;
    ImageView Back_SettingsPage;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);
        SettingsPage = (TextView) findViewById(R.id.SettingsPage);
        ShowGmail_SettingsPage= (TextView) findViewById(R.id.ShowGmail_SettingsPage);
        ShowDisplayName_SettingsPage = (TextView) findViewById(R.id.ShowDisplayName_SettingsPage);
        AreYouSure_SettingsPage = (TextView) findViewById(R.id.AreYouSure_SettingsPage);
        LogOut_SettingsPage=(Button)findViewById(R.id.LogOut_SettingsPage);
        DeleteAccount_SettingsPage=(Button)findViewById(R.id.DeleteAccount_SettingsPage);
        ChangePassword_SettingsPage=(Button)findViewById(R.id.ChangePassword_SettingsPage);
        ChangeGmail_SettingsPage=(Button)findViewById(R.id.ChangeGmail_SettingsPage);
        ChangeDisplayName_SettingsPage=(Button)findViewById(R.id.ChangeDisplayName_SettingsPage);
        No_SettingsPage=(Button)findViewById(R.id.No_SettingsPage);
        Yes_SettingsPage=(Button)findViewById(R.id.Yes_SettingsPage);
        ChangeAlarm_SettingsPage=(Button)findViewById(R.id.ChangeAlarm_SettingsPage);
        Back_SettingsPage=(ImageView) findViewById(R.id.Back_SettingsPage);


        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String Gmail = sharedPreferences.getString("Gmail", "");
        String Password = sharedPreferences.getString("Password", "");


        DeleteAccount_SettingsPage.setBackgroundColor(getResources().getColor(R.color.red));
        Yes_SettingsPage.setBackgroundColor(getResources().getColor(R.color.red));
        No_SettingsPage.setBackgroundColor(getResources().getColor(R.color.green));
        AreYouSure_SettingsPage.setVisibility(View.GONE);
        Yes_SettingsPage.setVisibility(View.GONE);
        No_SettingsPage.setVisibility(View.GONE);
        ShowGmail_SettingsPage.setText("Gmail: " + GlobalUser.getUser().getGmail());
        ShowDisplayName_SettingsPage.setText("DisplayName: " + GlobalUser.getUser().getDisplayName());


        LogOut_SettingsPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth= FirebaseAuth.getInstance();

                editor.putString("rememberMe","false");
                editor.putString("Gmail","");
                editor.putString("Password","");
                editor.putString("back","");
                editor.putString("firebaseUserUid","");
                editor.apply();
                GlobalUser.getUser().setNotActive();

                auth.signOut();

                Toast.makeText(getApplicationContext(),"Logging Out ", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(SettingsPage.this,AldrosEnterPage.class);
                startActivity(intent);
            }
        });

        ChangeAlarm_SettingsPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SettingsPage.this,ChangeAlarmPage.class);
                startActivity(intent);
                finish();
            }
        });

        ChangeGmail_SettingsPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth= FirebaseAuth.getInstance();

                editor.putString("back","Settings");
                editor.apply();

                Intent intent=new Intent(SettingsPage.this,UpdateGmailPage.class);
                startActivity(intent);
                finish();
            }
        });

        ChangePassword_SettingsPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SettingsPage.this,UpdatePasswordPage.class);
                startActivity(intent);
                finish();
            }
        });

        Back_SettingsPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        DeleteAccount_SettingsPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingsPage.setVisibility(View.GONE);
                ShowGmail_SettingsPage.setVisibility(View.GONE);
                ShowDisplayName_SettingsPage.setVisibility(View.GONE);
                ChangeGmail_SettingsPage.setVisibility(View.GONE);
                ChangePassword_SettingsPage.setVisibility(View.GONE);
                ChangeDisplayName_SettingsPage.setVisibility(View.GONE);
                LogOut_SettingsPage.setVisibility(View.GONE);
                DeleteAccount_SettingsPage.setVisibility(View.GONE);
                Back_SettingsPage.setVisibility(View.GONE);
                ChangeAlarm_SettingsPage.setVisibility(View.GONE);

                AreYouSure_SettingsPage.setVisibility(View.VISIBLE);
                Yes_SettingsPage.setVisibility(View.VISIBLE);
                No_SettingsPage.setVisibility(View.VISIBLE);
            }
        });

        No_SettingsPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingsPage.setVisibility(View.VISIBLE);
                ShowGmail_SettingsPage.setVisibility(View.VISIBLE);
                ShowDisplayName_SettingsPage.setVisibility(View.VISIBLE);
                ChangeGmail_SettingsPage.setVisibility(View.VISIBLE);
                ChangePassword_SettingsPage.setVisibility(View.VISIBLE);
                ChangeDisplayName_SettingsPage.setVisibility(View.VISIBLE);
                LogOut_SettingsPage.setVisibility(View.VISIBLE);
                DeleteAccount_SettingsPage.setVisibility(View.VISIBLE);
                Back_SettingsPage.setVisibility(View.VISIBLE);
                ChangeAlarm_SettingsPage.setVisibility(View.VISIBLE);

                AreYouSure_SettingsPage.setVisibility(View.GONE);
                Yes_SettingsPage.setVisibility(View.GONE);
                No_SettingsPage.setVisibility(View.GONE);
            }
        });


        Yes_SettingsPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth = FirebaseAuth.getInstance();
                FirebaseUser user = auth.getCurrentUser();
                if (user == null) {
                    finish();
                }
                String uid = user.getUid();

                // Step 1: Delete the user from Firebase Authentication
                AuthCredential credential = EmailAuthProvider.getCredential(Gmail, Password);

                Objects.requireNonNull(auth.getCurrentUser()).reauthenticate(credential).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        user.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(getApplicationContext(), "Account deleted", Toast.LENGTH_SHORT).show();
                                FirebaseDatabase.getInstance().getReference().child(uid).removeValue();
                                auth.signOut();
                                Intent intent = new Intent(SettingsPage.this, AldrosEnterPage.class);
                                startActivity(intent);
                                editor.putString("rememberMe", "false");
                                editor.putString("Gmail", "");
                                editor.putString("Password", "");
                                editor.putString("back", "");
                                editor.putString("firebaseUserUid", "");
                                editor.apply();
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });

            }
        });

        /*
        Yes_SettingsPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth= FirebaseAuth.getInstance();

                AuthCredential credential = EmailAuthProvider.getCredential(Gmail, Password);
                Objects.requireNonNull(auth.getCurrentUser()).reauthenticate(credential).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        FirebaseDatabase.getInstance().getReference(auth.getCurrentUser().getUid()).removeValue();
                        Objects.requireNonNull(auth.getCurrentUser()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                editor.putString("rememberMe","false");
                                editor.putString("Gmail","");
                                editor.putString("Password","");
                                //editor.putString("goToPopaye","false");
                                //editor.putString("DisplayName","");
                                //editor.putString("fromEnterOrLoginPage", "false");
                                editor.putString("back","");
                                //editor.putString("dailySituations","0");
                                editor.putString("firebaseUserUid","");
                                //editor.putString("Alarm","warning");
                                editor.apply();

                                //
                                //auth.signOut();
                                //
                                Toast.makeText(getApplicationContext(),"Account deleted", Toast.LENGTH_SHORT).show();
                                //GlobalUser.setUser(null);
                                Intent intent=new Intent(SettingsPage.this,AldrosEnterPage.class);
                                startActivity(intent);
                                auth.signOut();
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(SettingsPage.this, "Error-Changed were not applied: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        });
        */



        ChangeDisplayName_SettingsPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SettingsPage.this,ChangeDisplayNamePage.class);
                startActivity(intent);
                finish();
            }
        });

    }
    //end of onCreate

    /**
     *  onBackPressed
     *  goes back to Popaye
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(SettingsPage.this, PopayePage.class);
        startActivity(intent);
    }
}