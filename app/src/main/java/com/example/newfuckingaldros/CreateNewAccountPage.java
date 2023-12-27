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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
/**
 *  CreateNewAccountPage
 *  activity to create new accounts
 */
public class CreateNewAccountPage extends AppCompatActivity {
    public static final String SHARED_PREFS = "sharedPrefs";

    TextView CreateNewUserPage;
    TextInputEditText EnterGmailAddress_CreateNewUserPage;
    TextInputEditText EnterDisplayName_CreateNewUserPage;
    TextInputEditText CreatePassword_CreateNewUserPage;
    TextInputEditText VerifyPassword_CreateNewUserPage;
    Button CreateUser_CreateNewUserPage;
    CheckBox RememberMe_CreateNewUserPage;
    ImageView Back_CreateNewUserPage;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_account);

        CreateNewUserPage=(TextView)findViewById(R.id.CreateNewUserPage);
        EnterGmailAddress_CreateNewUserPage=(TextInputEditText)findViewById(R.id.EnterGmailAddress_CreateNewUserPage);
        EnterDisplayName_CreateNewUserPage=(TextInputEditText)findViewById(R.id.EnterDisplayName_CreateNewUserPage);
        CreatePassword_CreateNewUserPage=(TextInputEditText)findViewById(R.id.CreatePassword_CreateNewUserPage);
        VerifyPassword_CreateNewUserPage=(TextInputEditText)findViewById(R.id.VerifyPassword_CreateNewUserPage);
        CreateUser_CreateNewUserPage=(Button)findViewById(R.id.CreateUser_CreateNewUserPage);
        RememberMe_CreateNewUserPage=(CheckBox) findViewById(R.id.RememberMe_CreateNewUserPage);
        Back_CreateNewUserPage = (ImageView)findViewById(R.id.Back_CreateNewUserPage);

        auth= FirebaseAuth.getInstance();


        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        CreateUser_CreateNewUserPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth = FirebaseAuth.getInstance();

                String DisplayName, Password, Gmail, VerifyPassword;
                Gmail = String.valueOf(EnterGmailAddress_CreateNewUserPage.getText());
                DisplayName = String.valueOf(EnterDisplayName_CreateNewUserPage.getText());
                Password = String.valueOf(CreatePassword_CreateNewUserPage.getText());
                VerifyPassword = String.valueOf(VerifyPassword_CreateNewUserPage.getText());

                if (TextUtils.isEmpty(Gmail)) {
                    EnterGmailAddress_CreateNewUserPage.setError("please enter Gmail");
                    EnterGmailAddress_CreateNewUserPage.requestFocus();
                    return;

                }
                if (TextUtils.isEmpty(DisplayName)) {
                    EnterDisplayName_CreateNewUserPage.setError("please enter Display name");
                    EnterDisplayName_CreateNewUserPage.requestFocus();
                    return;
                } else if (DisplayName.length() > 15 ) {
                    EnterDisplayName_CreateNewUserPage.setError("DisplayName shouldn't be more than 15 characters");
                    EnterDisplayName_CreateNewUserPage.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(Password)) {
                    CreatePassword_CreateNewUserPage.setError("please enter Password");
                    CreatePassword_CreateNewUserPage.requestFocus();
                    return;

                }
                if (TextUtils.isEmpty(VerifyPassword)) {
                    VerifyPassword_CreateNewUserPage.setError("please Verify Password");
                    VerifyPassword_CreateNewUserPage.requestFocus();
                    return;

                }
                if (VerifyPassword.equals(Password)) {
                    auth.createUserWithEmailAndPassword(Gmail, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                editor.putString("Gmail",Gmail);
                                editor.putString("Password",Password);
                                editor.apply();

                                User myUser = new User(auth.getCurrentUser(), Gmail,Password);
                                myUser.setDisplayName(DisplayName);
                                GlobalUser.setUser(myUser);
                                GlobalUser.getUser().setDidAcceptedTerms(false);
                                GlobalUser.getUser().setMyAlarm("warning");

                                GlobalUser.getUser().setDailySituations(0);

                                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                // Create a SimpleDateFormat object with your desired format
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);

                                // Get the current date and time
                                Date currentDate = new Date();

                                // Format the date and time
                                String formattedDate = dateFormat.format(currentDate);
                                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                                FirebaseDatabase.getInstance().getReference(auth.getCurrentUser().getUid()).child("UserData").child("lastSituationsReset").setValue(formattedDate);

                                editor.putString("firebaseUserUid",auth.getCurrentUser().getUid());
                                editor.apply();

                                Intent intent = new Intent(CreateNewAccountPage.this, ClickLinkCreateAccountPage.class);
                                startActivity(intent);
                                Toast.makeText(CreateNewAccountPage.this, "please verify your account via gmail", Toast.LENGTH_SHORT).show();

                                auth.signInWithEmailAndPassword(Gmail, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        Objects.requireNonNull(auth.getCurrentUser()).sendEmailVerification();
                                        if (RememberMe_CreateNewUserPage.isChecked()) {
                                            editor.putString("RememberMe","true");
                                            editor.apply();
                                        }
                                    }
                                });
                            }
                        }
                    }) .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            if ( e.getMessage().equals("The given password is invalid. [ Password should be at least 6 characters ]")){
                                Toast.makeText(getApplicationContext(),"[ Password should be at least 6 characters ]" , Toast.LENGTH_SHORT).show();
                            }
                            else if ( e.getMessage().equals("The email address is already in use by another account.")){
                                Toast.makeText(getApplicationContext(),"The email address is already in use by another account." , Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(CreateNewAccountPage.this, LoginPage.class);
                                startActivity(intent);
                                Toast.makeText(getApplicationContext(),"please log in" , Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(getApplicationContext(),"Error: " + e.getMessage() , Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else {
                    VerifyPassword_CreateNewUserPage.setError("wrong password verification");
                    VerifyPassword_CreateNewUserPage.requestFocus();
                    Toast.makeText(CreateNewAccountPage.this, "wrong password verification", Toast.LENGTH_SHORT).show();
                }

            }
        });

        Back_CreateNewUserPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    //end of onCreate()
}
