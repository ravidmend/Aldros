package com.example.newfuckingaldros;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import java.util.Objects;
/**
 *  UpdateGmailPage
 *  activity for update the email you sign in with
 */
public class UpdateGmailPage extends AppCompatActivity {
    public static final String SHARED_PREFS = "sharedPrefs";

    TextView UpdateGmailPage;
    TextInputEditText EnterGmail_UpdateGmailPage;
    TextInputEditText EnterPassword_UpdateGmailPage;
    TextInputEditText VerifyGmail_UpdateGmailPage;
    Button SendVerificationGmail_UpdateGmailPage;
    Button ForgotPassword_UpdateGmailPage;
    TextView Note_UpdateGmailPage;
    ImageView Back_UpdateGmailPage;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_gmail_page);
        UpdateGmailPage =(TextView)findViewById(R.id.UpdateGmailPage);
        Note_UpdateGmailPage=(TextView)findViewById(R.id.Note_UpdateGmailPage);
        EnterPassword_UpdateGmailPage = (TextInputEditText) findViewById(R.id.EnterPassword_UpdateGmailPage);
        EnterGmail_UpdateGmailPage=(TextInputEditText)findViewById(R.id.EnterGmail_UpdateGmailPage);
        VerifyGmail_UpdateGmailPage=(TextInputEditText)findViewById(R.id. VerifyGmail_UpdateGmailPage);
        SendVerificationGmail_UpdateGmailPage=(Button)findViewById(R.id.SendVerificationGmail_UpdateGmailPage);
        Back_UpdateGmailPage= (ImageView)findViewById(R.id.Back_UpdateGmailPage);
        ForgotPassword_UpdateGmailPage = (Button)findViewById(R.id.ForgotPassword_UpdateGmailPage );

        auth= FirebaseAuth.getInstance();

        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String Gmail = sharedPreferences.getString("Gmail", "");
        String Password = sharedPreferences.getString("Password", "");
        String back = sharedPreferences.getString("back", "");
        String rememberMe = sharedPreferences.getString("rememberMe", "");
        String fromEnterOrLoginPage = sharedPreferences.getString("fromEnterOrLoginPage", "");
        String goToPopaye = sharedPreferences.getString("goToPopaye","");
        String DisplayName = sharedPreferences.getString("DisplayName","");
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////;

        SendVerificationGmail_UpdateGmailPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth = FirebaseAuth.getInstance();

                String NewGmail, VerifyGmail, EnteredPassword;
                NewGmail = String.valueOf(EnterGmail_UpdateGmailPage.getText());
                VerifyGmail = String.valueOf(VerifyGmail_UpdateGmailPage.getText());
                EnteredPassword = String.valueOf(EnterPassword_UpdateGmailPage.getText());

                if (TextUtils.isEmpty(EnteredPassword)) {
                    EnterPassword_UpdateGmailPage.setError("please enter Password");
                    EnterPassword_UpdateGmailPage.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(NewGmail)) {
                    EnterGmail_UpdateGmailPage.setError("please enter Gmail");
                    EnterGmail_UpdateGmailPage.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(VerifyGmail)) {
                    VerifyGmail_UpdateGmailPage.setError("please Verify Password");
                    VerifyGmail_UpdateGmailPage.requestFocus();
                    return;
                }
                if (VerifyGmail.equals(NewGmail)) {
                    if(EnteredPassword.equals(Password)) {
                        AuthCredential credential = EmailAuthProvider.getCredential(Gmail, Password);
                        Objects.requireNonNull(auth.getCurrentUser()).reauthenticate(credential).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Objects.requireNonNull(auth.getCurrentUser()).updateEmail(NewGmail).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(UpdateGmailPage.this, "please verify your account via gmail", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(UpdateGmailPage.this, ClickLinkCreateAccountPage.class);
                                        startActivity(intent);
                                        editor.putString("Gmail", NewGmail);
                                        editor.apply();
                                        GlobalUser.getUser().setGmail(NewGmail);
                                        auth.getCurrentUser().sendEmailVerification();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(UpdateGmailPage.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
                    }
                    else {
                        VerifyGmail_UpdateGmailPage.setError("wrong Password");
                        VerifyGmail_UpdateGmailPage.requestFocus();
                        Toast.makeText(UpdateGmailPage.this, "wrong Password", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    VerifyGmail_UpdateGmailPage.setError("wrong Gmail verification");
                    VerifyGmail_UpdateGmailPage.requestFocus();
                    Toast.makeText(UpdateGmailPage.this, "wrong Gmail verification", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Back_UpdateGmailPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ForgotPassword_UpdateGmailPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateGmailPage.this, ForgotPasswordPage.class);
                startActivity(intent);
            }
        });
    }
    //end of onCreate
}
