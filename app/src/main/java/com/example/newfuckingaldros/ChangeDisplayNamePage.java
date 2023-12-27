package com.example.newfuckingaldros;

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
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
/**
 *  ChangeDisplayNamePage
 *  activity to change the displayName of the user
 */
public class ChangeDisplayNamePage extends AppCompatActivity {
    public static final String SHARED_PREFS = "sharedPrefs";

    TextView ChangeDisplayNamePage;
    TextView Note_ChangeDisplayNamePage;
    TextInputEditText NewDisplayName_ChangeDisplayNamePage;
    TextInputEditText VerifyDisplayName_ChangeDisplayNamePage;
    Button Confirm_ChangeDisplayNamePage;
    ImageView Back_ChangeDisplayNamePage;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_display_name_page);

        ChangeDisplayNamePage =(TextView)findViewById(R.id.ChangeDisplayNamePage);
        Note_ChangeDisplayNamePage=(TextView)findViewById(R.id.Note_ChangeDisplayNamePage);
        NewDisplayName_ChangeDisplayNamePage=(TextInputEditText)findViewById(R.id.NewDisplayName_ChangeDisplayNamePage);
        VerifyDisplayName_ChangeDisplayNamePage=(TextInputEditText)findViewById(R.id.VerifyDisplayName_ChangeDisplayNamePage);
        Confirm_ChangeDisplayNamePage=(Button)findViewById(R.id.Confirm_ChangeDisplayNamePage);
        Back_ChangeDisplayNamePage= (ImageView)findViewById(R.id.Back_ChangeDisplayNamePage);

        auth= FirebaseAuth.getInstance();


        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();


        Confirm_ChangeDisplayNamePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth = FirebaseAuth.getInstance();

                String  NewDisplayName,VerifyNewDisplayName;

                NewDisplayName= String.valueOf(NewDisplayName_ChangeDisplayNamePage.getText());
                VerifyNewDisplayName = String.valueOf(VerifyDisplayName_ChangeDisplayNamePage.getText());

                if (TextUtils.isEmpty(NewDisplayName)) {
                    NewDisplayName_ChangeDisplayNamePage.setError("please enter your new DisplayName");
                    NewDisplayName_ChangeDisplayNamePage.requestFocus();
                    return;
                }
                else if (NewDisplayName.length() > 15 ) {
                    NewDisplayName_ChangeDisplayNamePage.setError("DisplayName shouldn't be more than 15 characters");
                    NewDisplayName_ChangeDisplayNamePage.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(VerifyNewDisplayName)) {
                    VerifyDisplayName_ChangeDisplayNamePage.setError("please verify your new DisplayName");
                    VerifyDisplayName_ChangeDisplayNamePage.requestFocus();
                    return;
                }
                if (NewDisplayName.equals(VerifyNewDisplayName)) {
                    if (NewDisplayName.equals(GlobalUser.getUser().getDisplayName())) {
                        NewDisplayName_ChangeDisplayNamePage.setError("new and old DisplayName shouldn't be the same");
                        NewDisplayName_ChangeDisplayNamePage.requestFocus();
                        Toast.makeText(ChangeDisplayNamePage.this, "new and old DisplayName shouldn't be the same", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    GlobalUser.getUser().setDisplayName(NewDisplayName);
                    Toast.makeText(ChangeDisplayNamePage.this, "DisplayName has been changed", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ChangeDisplayNamePage.this, PopayePage.class);
                    startActivity(intent);
                }
                else {
                    VerifyDisplayName_ChangeDisplayNamePage.setError("wrong DisplayName verification");
                    VerifyDisplayName_ChangeDisplayNamePage.requestFocus();
                    Toast.makeText(ChangeDisplayNamePage.this, "wrong DisplayName verification", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Back_ChangeDisplayNamePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    //end of onCreate()
}
