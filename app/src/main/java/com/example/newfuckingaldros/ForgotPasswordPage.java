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
import com.google.firebase.auth.FirebaseAuth;
/**
 *  ForgotPasswordPage
 *  activity for resetting your password
 */
public class ForgotPasswordPage extends AppCompatActivity {
    public static final String SHARED_PREFS = "sharedPrefs";

    TextView ForgotPasswordPage;
    TextInputEditText Gmail_ForgotPasswordPage;
    Button SendVerificationGmail_ForgotPasswordPage;
    ImageView Back_ForgotPasswordPage;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_page);
        ForgotPasswordPage=(TextView)findViewById(R.id.ForgotPasswordPage);
        Gmail_ForgotPasswordPage=(TextInputEditText) findViewById(R.id.Gmail_ForgotPasswordPage);
        SendVerificationGmail_ForgotPasswordPage=(Button)findViewById(R.id.SendVerificationGmail_ForgotPasswordPage);
        Back_ForgotPasswordPage=(ImageView)findViewById(R.id.Back_ForgotPasswordPage);


        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        SendVerificationGmail_ForgotPasswordPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                auth= FirebaseAuth.getInstance();

                String Gmail = String.valueOf(Gmail_ForgotPasswordPage.getText());
                if(TextUtils.isEmpty(Gmail)){
                    Gmail_ForgotPasswordPage.setError("please enter Gmail");
                    Gmail_ForgotPasswordPage.requestFocus();
                    return;
                }

                auth.sendPasswordResetEmail(Gmail).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getApplicationContext(),"reset Password link has been sent to your Gmail", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(ForgotPasswordPage.this,LoginPage.class);
                        startActivity(intent);
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"ERROR : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        Back_ForgotPasswordPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    //end of onCreate
}