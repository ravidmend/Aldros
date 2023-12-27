package com.example.newfuckingaldros;

import android.content.SharedPreferences;
import android.location.Location;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;import androidx.annotation.NonNull;
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
import java.util.Objects;

/**
 *  User
 *  class for saving the user data locally
 */
public class User {
    private FirebaseUser firebaseUser;
    private DatabaseReference databaseReference;
    private DatabaseReference databaseReferenceUserData;
    private String gmail;
    private String password;
    private String displayName;
    private boolean didAcceptTerms;
    private String userType;
    private boolean isActive = true;
    private boolean shouldNotify;
    private Location currentLocation;
    private double latitude;
    private double longitude;
    private double speed;
    private double acceleration;

    private Location myCarLocation;
    private double carLatitude;
    private double carLongitude;

    private String myAlarm;

    private String userAddress;
    private String carAddress;

    private int dailySituations;


    public User(FirebaseUser firebaseUser, String gmail, String password) {
        this.firebaseUser = firebaseUser;
        this.databaseReference = FirebaseDatabase.getInstance().getReference(firebaseUser.getUid());
        this.databaseReferenceUserData = FirebaseDatabase.getInstance().getReference(firebaseUser.getUid()).child("UserData");
        this.gmail = gmail;
        this.password = password;
        this.userType = "walker";
        databaseReference.child("should_notify").setValue(0);
        databaseReference.child("user_type").setValue("walker");

    }
    public User() {

    }

    public FirebaseUser getFirebaseUser() {
        return (this.firebaseUser);
    }

    public void setDatabaseReference(DatabaseReference databaseReference) {
        this.databaseReference = databaseReference;
    }

    public void setDatabaseReferenceUserData(DatabaseReference databaseReferenceUserData) {
        this.databaseReferenceUserData = databaseReferenceUserData;
    }

    public String getGmail() {
        return (this.gmail);
    }
    public void setGmail(String gmail) {
        this.gmail = gmail;
    } //todo


    public String getPassword() {
        return (this.password);
    }
    public void setPassword(String password) {
        this.password = password; ///todo
    }


    public String getDisplayName() {
        return (this.displayName);
    }
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
        databaseReferenceUserData.child("displayName").setValue(displayName);
    }


    public boolean getDidAcceptedTerms() {
        return (this.didAcceptTerms);
    }
    public void setDidAcceptedTerms(boolean did) {
        this.didAcceptTerms = did;
        databaseReferenceUserData.child("didAcceptTerms").setValue(did);
    }


    public String getUserType() {
        return (this.userType);
    }
    public void setUserType(String type) {
        this.userType = type;
        databaseReference.child("user_type").setValue(type);
    }


    public void setActive() {
        this.isActive = true;
        databaseReference.child("isActive").setValue(true);
    }
    public void setNotActive() {
        this.isActive = false;
        databaseReference.child("isActive").setValue(false);
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }
    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
        this.latitude=currentLocation.getLatitude();
        this.longitude=currentLocation.getLongitude();
        this.speed=currentLocation.getSpeed();
        databaseReference.child("latitude").setValue(this.latitude);
        databaseReference.child("longitude").setValue(this.longitude);
        databaseReference.child("velocity").setValue(this.speed);

    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getSpeed() {
        return speed;
    }


    public double getAcceleration() {
        return acceleration;
    }
    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
        databaseReference.child("acceleration").setValue(this.acceleration);
    }


    public Location getMyCarLocation() {
        return myCarLocation;
    }
    public void setMyCarLocation(Location myCarLocation) {
        this.myCarLocation = myCarLocation;
        this.carLatitude = myCarLocation.getLatitude();
        this.carLongitude=myCarLocation.getLongitude();
        databaseReferenceUserData.child("carLatitude").setValue(this.carLatitude);
        databaseReferenceUserData.child("carLongitude").setValue(this.carLongitude);
    }

    public double getCarLatitude() {

        return carLatitude;
    }

    public double getCarLongitude() {
        return carLongitude;
    }

    public void setCarLatitude(double carLatitude) {
        this.carLatitude = carLatitude;
        databaseReferenceUserData.child("carLatitude").setValue(this.carLatitude);
    }

    public void setCarLongitude(double carLongitude) {
        this.carLongitude = carLongitude;
        databaseReferenceUserData.child("carLongitude").setValue(this.carLongitude);
    }

    public String getMyAlarm() {
        return myAlarm;
    }
    public void setMyAlarm(String myAlarm) {
        this.myAlarm = myAlarm;
        databaseReferenceUserData.child("myAlarm").setValue(this.myAlarm);
    }


    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
        databaseReferenceUserData.child("userAddress").setValue(userAddress);
    }

    public String getCarAddress() {
        return carAddress;
    }

    public void setCarAddress(String carAddress) {
        this.carAddress = carAddress;
        databaseReferenceUserData.child("carAddress").setValue(carAddress);
    }

    public void setUser(FirebaseUser firebaseUser, String gmail, String displayName, String password) {
        this.firebaseUser = firebaseUser;
        this.gmail = gmail;
        this.displayName = displayName;
        this.password = password;
    }

    public int getDailySituations() {
        return dailySituations;
    }


    public void setDailySituations(int dailySituations) {
        this.dailySituations = dailySituations;
        databaseReferenceUserData.child("dailySituations").setValue(dailySituations);

    }

    public void addDailySituations() {
        this.dailySituations = this.dailySituations +1;
        databaseReferenceUserData.child("dailySituations").setValue(dailySituations);
    }
    public void resetDailySituations() {
        this.dailySituations = 0;
        databaseReferenceUserData.child("dailySituations").setValue(dailySituations);
    }
}
