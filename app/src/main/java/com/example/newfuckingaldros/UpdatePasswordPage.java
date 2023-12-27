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
 *  UpdatePasswordPage
 *  activity for update the password you sign in with
 */
public class UpdatePasswordPage extends AppCompatActivity {
    public static final String SHARED_PREFS = "sharedPrefs";

    TextView UpdatePasswordPage;
    TextView Note_UpdatePasswordPage;
    TextInputEditText EnterOldPassword_UpdatePasswordPage;
    TextInputEditText EnterNewPassword_UpdatePasswordPage;
    TextInputEditText VerifyNewPassword_UpdatePasswordPage;
    Button Continue_UpdatePasswordPage;
    Button ForgotPassword_UpdatePasswordPage;
    ImageView Back_UpdatePasswordPage;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password_page);
        UpdatePasswordPage =(TextView)findViewById(R.id.UpdatePasswordPage);
        Note_UpdatePasswordPage=(TextView)findViewById(R.id.Note_UpdatePasswordPage);
        EnterOldPassword_UpdatePasswordPage=(TextInputEditText)findViewById(R.id.EnterOldPassword_UpdatePasswordPage);
        EnterNewPassword_UpdatePasswordPage=(TextInputEditText)findViewById(R.id.EnterNewPassword_UpdatePasswordPage);
        VerifyNewPassword_UpdatePasswordPage=(TextInputEditText)findViewById(R.id.VerifyNewPassword_UpdatePasswordPage);
        Continue_UpdatePasswordPage=(Button)findViewById(R.id.Continue_UpdatePasswordPage);
        ForgotPassword_UpdatePasswordPage=(Button)findViewById(R.id.ForgotPassword_UpdatePasswordPage);
        Back_UpdatePasswordPage= (ImageView)findViewById(R.id.Back_UpdatePasswordPage);

        auth= FirebaseAuth.getInstance();

        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String Gmail = sharedPreferences.getString("Gmail", "");
        String Password = sharedPreferences.getString("Password", "");
        /*
        String back = sharedPreferences.getString("back", "");
        String rememberMe = sharedPreferences.getString("rememberMe", "");
        String fromEnterOrLoginPage = sharedPreferences.getString("fromEnterOrLoginPage", "");
        String goToPopaye = sharedPreferences.getString("goToPopaye","");
        String DisplayName = sharedPreferences.getString("DisplayName","");

         */
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////;

        Continue_UpdatePasswordPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth = FirebaseAuth.getInstance();

                String OldPassword, NewPassword,VerifyNewPassword;
                OldPassword = String.valueOf(EnterOldPassword_UpdatePasswordPage.getText());
                NewPassword = String.valueOf(EnterNewPassword_UpdatePasswordPage.getText());
                VerifyNewPassword = String.valueOf(VerifyNewPassword_UpdatePasswordPage.getText());

                if (TextUtils.isEmpty(OldPassword)) {
                    EnterOldPassword_UpdatePasswordPage.setError("please enter Old Password");
                    EnterOldPassword_UpdatePasswordPage.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(NewPassword)) {
                    EnterNewPassword_UpdatePasswordPage.setError("please enter New Password");
                    EnterNewPassword_UpdatePasswordPage.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(VerifyNewPassword)) {
                    VerifyNewPassword_UpdatePasswordPage.setError("please Verify New Password");
                    VerifyNewPassword_UpdatePasswordPage.requestFocus();
                    return;
                }
                if (VerifyNewPassword.equals(NewPassword)) {
                    if (OldPassword.equals(NewPassword)) {
                        EnterNewPassword_UpdatePasswordPage.setError("new and old password shouldn't be the same");
                        EnterNewPassword_UpdatePasswordPage.requestFocus();
                        Toast.makeText(UpdatePasswordPage.this, "new and old password shouldn't be the same", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (Password.equals(OldPassword)) {
                        AuthCredential credential = EmailAuthProvider.getCredential(Gmail, Password);
                        Objects.requireNonNull(auth.getCurrentUser()).reauthenticate(credential).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Objects.requireNonNull(auth.getCurrentUser()).updatePassword(NewPassword).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(UpdatePasswordPage.this, "password has been changed", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(UpdatePasswordPage.this, PopayePage.class);
                                        startActivity(intent);
                                        editor.putString("Password", NewPassword);
                                        editor.apply();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(UpdatePasswordPage.this, "Error-Changed were not applied: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
                    }
                    else {
                        EnterOldPassword_UpdatePasswordPage.setError("this is not the old Password");
                        EnterOldPassword_UpdatePasswordPage.requestFocus();
                        Toast.makeText(UpdatePasswordPage.this, "this is not the old Password", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                else {
                    VerifyNewPassword_UpdatePasswordPage.setError("wrong password verification");
                    VerifyNewPassword_UpdatePasswordPage.requestFocus();
                    Toast.makeText(UpdatePasswordPage.this, "wrong password verification", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ForgotPassword_UpdatePasswordPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdatePasswordPage.this, ForgotPasswordPage.class);
                startActivity(intent);
                /*
                editor.putString("goToPopaye","true");
                editor.apply();

                 */
            }
        });

        Back_UpdatePasswordPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    //end of onCreate
}
