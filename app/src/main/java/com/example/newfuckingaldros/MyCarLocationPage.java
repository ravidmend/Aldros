package com.example.newfuckingaldros;

import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.widget.ImageView;
import com.google.firebase.auth.FirebaseAuth;
import android.content.Intent;
import android.net.Uri;

/**
 *  MyCarLocationPage
 *  activity to save your car location and direct you to it
 */
public class MyCarLocationPage extends AppCompatActivity {

    private static final int PERMISSIONS_FINE_LOCATION = 1;
    private static final int PERMISSIONS_COARSE_LOCATION = 5;

    Button UpdateCarLocation_MyCarLocationPage;
    Button DeleteCarLocation_MyCarLocationPage;
    Button DirectMeToCarInMaps_MyCarLocationPage;
    TextView Text_MyCarLocationPage;
    TextView Location_MyCarLocationPage;
    TextView LocationDetails_MyCarLocationPage;
    TextView Address_MyCarLocationPage;
    TextView AddressDetails_MyCarLocationPage;
    ImageView Pic_MyCarLocationPage;
    ImageView CopyAddress_MyCarLocationPage;
    ImageView CopyLocation_MyCarLocationPage;
    ImageView Back_MyCarLocationPage;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_car_location_page);
        auth= FirebaseAuth.getInstance();

        Text_MyCarLocationPage=(TextView) findViewById(R.id.Text_MyCarLocationPage);
        Location_MyCarLocationPage=(TextView)findViewById(R.id.Location_MyCarLocationPage);
        LocationDetails_MyCarLocationPage=(TextView)findViewById(R.id.LocationDetails_MyCarLocationPage);
        Address_MyCarLocationPage=(TextView)findViewById(R.id.Address_MyCarLocationPage);
        AddressDetails_MyCarLocationPage=(TextView)findViewById(R.id.AddressDetails_MyCarLocationPage);
        UpdateCarLocation_MyCarLocationPage=(Button)findViewById(R.id.UpdateCarLocation_MyCarLocationPage);
        DeleteCarLocation_MyCarLocationPage=(Button)findViewById(R.id.DeleteCarLocation_MyCarLocationPage);
        DirectMeToCarInMaps_MyCarLocationPage =(Button)findViewById(R.id.DirectMeToCarInMaps_MyCarLocationPage);
        DirectMeToCarInMaps_MyCarLocationPage.setBackgroundColor(ContextCompat.getColor(this,R.color.lighterteal));
        Pic_MyCarLocationPage=(ImageView) findViewById(R.id.Pic_MyCarLocationPage);
        CopyAddress_MyCarLocationPage=(ImageView) findViewById(R.id.CopyAddress_MyCarLocationPage);
        CopyLocation_MyCarLocationPage=(ImageView) findViewById(R.id.CopyLocation_MyCarLocationPage);
        Back_MyCarLocationPage=(ImageView) findViewById(R.id.Back_MyCarLocationPage);
        UpdateCarLocation_MyCarLocationPage.setBackgroundColor(ContextCompat.getColor(this,R.color.teal_700));
        DeleteCarLocation_MyCarLocationPage.setBackgroundColor(ContextCompat.getColor(this,R.color.red));

        if ((GlobalUser.getUser().getCarLatitude() != 0.0 ) && (GlobalUser.getUser().getCarLongitude() != 0.0)) {
            LocationDetails_MyCarLocationPage.setText(GlobalUser.getUser().getCarLatitude() + ", " + GlobalUser.getUser().getCarLongitude());
            AddressDetails_MyCarLocationPage.setText(GlobalUser.getUser().getCarAddress());
        }
         else {
            LocationDetails_MyCarLocationPage.setVisibility(View.GONE);
            AddressDetails_MyCarLocationPage.setVisibility(View.GONE);
            DirectMeToCarInMaps_MyCarLocationPage.setVisibility(View.GONE);
        }

        Back_MyCarLocationPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        UpdateCarLocation_MyCarLocationPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalUser.getUser().setMyCarLocation(GlobalUser.getUser().getCurrentLocation());
                LocationDetails_MyCarLocationPage.setVisibility(View.VISIBLE);
                LocationDetails_MyCarLocationPage.setText(GlobalUser.getUser().getCarLatitude() + ", " + GlobalUser.getUser().getCarLongitude());

                GlobalUser.getUser().setCarAddress(GlobalUser.getUser().getUserAddress());
                AddressDetails_MyCarLocationPage.setVisibility(View.VISIBLE);
                AddressDetails_MyCarLocationPage.setText(GlobalUser.getUser().getCarAddress());

                DirectMeToCarInMaps_MyCarLocationPage.setVisibility(View.VISIBLE);

            }
        });

        CopyAddress_MyCarLocationPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(LocationDetails_MyCarLocationPage.getVisibility() == View.VISIBLE) {
                    copyAddress(v);
                }
                else {
                    Toast.makeText(getApplicationContext(),"what you want to copy bruh?", Toast.LENGTH_SHORT).show();
                }
            }
        });

        CopyLocation_MyCarLocationPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CopyLocation_MyCarLocationPage.getVisibility() == View.VISIBLE) {
                    copyLocation(v);
                }
                else {
                    Toast.makeText(getApplicationContext(),"what you want to copy bruh?", Toast.LENGTH_SHORT).show();
                }
            }
        });

        DeleteCarLocation_MyCarLocationPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocationDetails_MyCarLocationPage.setVisibility(View.GONE);
                GlobalUser.getUser().setCarLatitude(0.0);
                GlobalUser.getUser().setCarLongitude(0.0);

                AddressDetails_MyCarLocationPage.setVisibility(View.GONE);
                GlobalUser.getUser().setCarAddress("");

                DirectMeToCarInMaps_MyCarLocationPage.setVisibility(View.GONE);

            }
        });


        DirectMeToCarInMaps_MyCarLocationPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q="+GlobalUser.getUser().getCarLatitude() + "," + GlobalUser.getUser().getCarLongitude()+"&mode=w"));
                intent.setPackage("com.google.android.apps.maps");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(),"problemos", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    //end of onCreate

    /**
     *  copyAddress
     *  copy the car address
     */
    public void copyAddress(View view) {
        String text = AddressDetails_MyCarLocationPage.getText().toString();

        // Get the clipboard manager
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

        // Create a ClipData object to hold the text to copy
        android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", text);

        // Set the ClipData on the clipboard
        clipboard.setPrimaryClip(clip);

        // Show a toast message to indicate that text has been copied
        Toast.makeText(this, "Address copied to clipboard", Toast.LENGTH_SHORT).show();
    }
    /**
     *  copyLocation
     *  copy the car location
     */
    public void copyLocation(View view) {
        String text = LocationDetails_MyCarLocationPage.getText().toString();

        // Get the clipboard manager
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

        // Create a ClipData object to hold the text to copy
        android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", text);

        // Set the ClipData on the clipboard
        clipboard.setPrimaryClip(clip);

        // Show a toast message to indicate that text has been copied
        Toast.makeText(this, "Location copied to clipboard", Toast.LENGTH_SHORT).show();
    }
    /**
     *  onBackPressed
     *  goes back to Popaye
     */
    @Override
    public void onBackPressed() {
        // Do nothing or handle it as per your requirements
        // For example, you can show a message to the user or perform a specific action
        Intent intent=new Intent(MyCarLocationPage.this,PopayePage.class);
        startActivity(intent);
    }
}