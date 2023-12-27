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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import java.util.Objects;

/**
 *  ClickLinkCreateAccountPage
 *  activity to wait for the user while he verify his account
 */
public class ClickLinkCreateAccountPage extends AppCompatActivity {
    public static final String SHARED_PREFS = "sharedPrefs";

    TextView ClickLinkCreateAccountPage;
    Button pressMe_ClickLinkCreateAccountPage;
    Button DidntGotLinkOnGmail_ClickLinkCreateAccountPage;
    ImageView Back_ClickLinkCreateAccountPage;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_link_create_account_page);
        ClickLinkCreateAccountPage=(TextView)findViewById(R.id.ClickLinkCreateAccountPage);
        pressMe_ClickLinkCreateAccountPage =(Button)findViewById(R.id.pressMe_ClickLinkCreateAccountPage);
        DidntGotLinkOnGmail_ClickLinkCreateAccountPage =(Button)findViewById(R.id.DidntGotLinkOnGmail_ClickLinkCreateAccountPage);
        Back_ClickLinkCreateAccountPage=(ImageView) findViewById(R.id.Back_ClickLinkCreateAccountPage);

        auth= FirebaseAuth.getInstance();


        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();


        pressMe_ClickLinkCreateAccountPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signInWithEmailAndPassword(GlobalUser.getUser().getGmail(), GlobalUser.getUser().getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful() && Objects.requireNonNull(auth.getCurrentUser()).isEmailVerified()) {
                            editor.putString("back","Click");
                            editor.apply();

                            Toast.makeText(getApplicationContext(),"gmail verified", Toast.LENGTH_SHORT).show();

                            Intent intent=new Intent(ClickLinkCreateAccountPage.this,AllowPermissionsPage.class);
                            startActivity(intent);
                            finish();
                        } else if (!Objects.requireNonNull(auth.getCurrentUser()).isEmailVerified()) {
                            Toast.makeText(getApplicationContext(),"please verify your gmail", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(ClickLinkCreateAccountPage.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ClickLinkCreateAccountPage.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        DidntGotLinkOnGmail_ClickLinkCreateAccountPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signInWithEmailAndPassword(GlobalUser.getUser().getGmail(), GlobalUser.getUser().getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Intent intent=new Intent(ClickLinkCreateAccountPage.this, UpdateGmailPage.class);
                        startActivity(intent);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ClickLinkCreateAccountPage.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        Back_ClickLinkCreateAccountPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    //end of onCreate()
}

