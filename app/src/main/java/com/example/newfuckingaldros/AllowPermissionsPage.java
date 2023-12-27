package com.example.newfuckingaldros;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.Manifest;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 *  AllowPermissionsPage
 *  activity to give the app the permissions needed
 */
public class AllowPermissionsPage extends AppCompatActivity {
    public static final String SHARED_PREFS = "sharedPrefs";

    TextView AllowPermissionsPage;
    TextView WeNeedGPS_AllowPermissionsPage;
    TextView TouchThePic_AllowPermissionsPage;
    Button GivePermission_AllowPermissionsPage;
    CheckBox CheckBox_AllowPermissionsPage;
    ImageView imageTerms_AllowPermissionsPage;
    ImageView Back_AllowPermissionsPage;

    ActivityResultLauncher<String []> mPermissionResultLauncher;

    FirebaseAuth auth;

    private boolean isCOARSE_LOCATIONPermissionGranted = false;
    private boolean isFINE_LOCATIONPermissionGranted = false;
    private boolean isLOCATION_EXTRA_COMMANDSPermissionGranted = false;
    private boolean isVIBRATEPermissionGranted = false;
    private boolean isBODY_SENSORSPermissionGranted = false;
    private boolean isACTIVITY_RECOGNITIONPermissionGranted = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allow_permissions_page);

        auth= FirebaseAuth.getInstance();

        AllowPermissionsPage=(TextView)findViewById(R.id.AllowPermissionsPage);
        WeNeedGPS_AllowPermissionsPage=(TextView)findViewById(R.id.WeNeedGPS_AllowPermissionsPage);
        TouchThePic_AllowPermissionsPage=(TextView)findViewById(R.id.TouchThePic_AllowPermissionsPage);
        GivePermission_AllowPermissionsPage=(Button)findViewById(R.id.GivePermission_AllowPermissionsPage);
        CheckBox_AllowPermissionsPage=(CheckBox)findViewById(R.id.CheckBox_AllowPermissionsPage);
        imageTerms_AllowPermissionsPage=(ImageView) findViewById(R.id.imageTerms_AllowPermissionsPage);
        Back_AllowPermissionsPage=(ImageView)findViewById(R.id.Back_AllowPermissionsPage);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        mPermissionResultLauncher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback<Map<String, Boolean>>() {
            @Override
            public void onActivityResult(Map<String, Boolean> result) {

            }
        });


        if (GlobalUser.getUser().getDidAcceptedTerms()) {
            CheckBox_AllowPermissionsPage.setVisibility(View.GONE);
        }
        requestPermission();

        GivePermission_AllowPermissionsPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!(isFINE_LOCATIONPermissionGranted && isLOCATION_EXTRA_COMMANDSPermissionGranted && isVIBRATEPermissionGranted  && isBODY_SENSORSPermissionGranted && isACTIVITY_RECOGNITIONPermissionGranted&&isCOARSE_LOCATIONPermissionGranted )){
                    requestPermission();
                    if(!( isFINE_LOCATIONPermissionGranted && isLOCATION_EXTRA_COMMANDSPermissionGranted && isVIBRATEPermissionGranted && isBODY_SENSORSPermissionGranted && isACTIVITY_RECOGNITIONPermissionGranted&&isCOARSE_LOCATIONPermissionGranted)){
                        Toast.makeText(getApplicationContext(),"We need FINE_GPS,Sensors & ACTIVITY RECOGNITION access to keep you safe", Toast.LENGTH_LONG).show();
                        openSettings(view);
                        return;
                    }
                }
                if (CheckBox_AllowPermissionsPage.isChecked() || GlobalUser.getUser().getDidAcceptedTerms()) {
                    GlobalUser.getUser().setDidAcceptedTerms(true);
                    Toast.makeText(getApplicationContext(),"Welcome to Aldros: " + GlobalUser.getUser().getDisplayName(), Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(AllowPermissionsPage.this,PopayePage.class);
                    startActivity(intent);
                } else {
                    CheckBox_AllowPermissionsPage.setError("please check");
                    Toast.makeText(getApplicationContext(),"please check the checkbox", Toast.LENGTH_SHORT).show();
                    CheckBox_AllowPermissionsPage.requestFocus();
                }
            }
        });

        CheckBox_AllowPermissionsPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        imageTerms_AllowPermissionsPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://docs.google.com/document/d/1A8J3_Cn3k2WSIIPdxjhTPmHa6OpmB8hodLAZ7DsETtg/edit?usp=sharing");
                startActivity(new Intent(Intent.ACTION_VIEW,uri));
            }
        });

        Back_AllowPermissionsPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    //end of onCreate()

    /**
     *  requestPermission()
     *  pops up request for a lot of permissions
     */
    private void requestPermission() {
        isCOARSE_LOCATIONPermissionGranted = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
        isFINE_LOCATIONPermissionGranted = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
        isLOCATION_EXTRA_COMMANDSPermissionGranted = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS) == PackageManager.PERMISSION_GRANTED;
        isVIBRATEPermissionGranted = ContextCompat.checkSelfPermission(this, Manifest.permission.VIBRATE) == PackageManager.PERMISSION_GRANTED;
        isBODY_SENSORSPermissionGranted = ContextCompat.checkSelfPermission(this, Manifest.permission.BODY_SENSORS) == PackageManager.PERMISSION_GRANTED;
        isACTIVITY_RECOGNITIONPermissionGranted=ContextCompat.checkSelfPermission(this, Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_GRANTED;

        List<String> permissionRequest = new ArrayList<String>();

        if(!isFINE_LOCATIONPermissionGranted){
            permissionRequest.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if(!isCOARSE_LOCATIONPermissionGranted){
            permissionRequest.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        }

        if(!isLOCATION_EXTRA_COMMANDSPermissionGranted){
            permissionRequest.add(Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS);
        }

        if(!isVIBRATEPermissionGranted){
            permissionRequest.add(Manifest.permission.VIBRATE);
        }
        if(!isBODY_SENSORSPermissionGranted){
            permissionRequest.add(Manifest.permission.BODY_SENSORS);
        }
        if(!isACTIVITY_RECOGNITIONPermissionGranted){
            permissionRequest.add(Manifest.permission.ACTIVITY_RECOGNITION);
        }

        if(!permissionRequest.isEmpty()) {
            mPermissionResultLauncher.launch(permissionRequest.toArray(new String[0]));
        }
        if (GlobalUser.getUser().getDidAcceptedTerms() && isFINE_LOCATIONPermissionGranted && isLOCATION_EXTRA_COMMANDSPermissionGranted && isVIBRATEPermissionGranted && isBODY_SENSORSPermissionGranted && isACTIVITY_RECOGNITIONPermissionGranted&&isCOARSE_LOCATIONPermissionGranted) {
            Toast.makeText(getApplicationContext(),"Already allowed permissions", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(AllowPermissionsPage.this,PopayePage.class);
            startActivity(intent);
        }
    }
    /**
     *  openSettings
     *  opens the app settings in the phone settings
     */
    private void openSettings(View view) {
        Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(android.net.Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }
}