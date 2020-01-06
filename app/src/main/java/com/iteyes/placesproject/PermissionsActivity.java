package com.iteyes.placesproject;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.google.firebase.iid.FirebaseInstanceId;
import com.inlocomedia.android.engagement.InLocoEngagement;
import com.inlocomedia.android.engagement.InLocoEngagementOptions;
import com.inlocomedia.android.engagement.request.FirebasePushProvider;
import com.inlocomedia.android.engagement.request.PushProvider;
import com.inlocomedia.android.location.InLoco;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissionsActivity extends AppCompatActivity {

    private static final int MY_ACCESS_FINE_RETURN_VALUE = 128;
    private static final int MY_ACCESS_COARSE_RETURN_VALUE = 123;
    //    private String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.ACCESS_WIFI_STATE, Manifest.permission.CHANGE_WIFI_STATE, Manifest.permission.ACTIVITY_RECOGNITION, Manifest.permission.RECEIVE_BOOT_COMPLETED};
    private GoogleMap map;
    private Marker marker;
    private Boolean toggle = true;
    RequestQueue queue;
    private Button btnGrant;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permissions);

        InLocoEngagementOptions options = InLocoEngagementOptions.getInstance(this);

        // The App ID you obtained in the dashboard
        options.setApplicationId(getString(R.string.in_loco_app_id));

        // Verbose mode; enables SDK logging, defaults to true.
        // Remember to set to false in production builds.
        options.setLogEnabled(true);

        //Initialize the SDK
        InLocoEngagement.init(this, options);
        InLocoEngagement.setUserId(this, "Unique_ID");

        String firebaseToken = FirebaseInstanceId.getInstance().getToken();

        if (firebaseToken != null && !firebaseToken.isEmpty()) {
            final PushProvider pushProvider = new FirebasePushProvider.Builder()
                    .setFirebaseToken(firebaseToken)
                    .build();
            InLocoEngagement.setPushProvider(this, pushProvider);
        }
//        getPermissions();
//        fireBaseConfig();

        if(ContextCompat.checkSelfPermission(PermissionsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            startActivity(new Intent(PermissionsActivity.this, MapActivity.class));
            finish();
            return;
        }

        boolean statusPermission = true;

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_ACCESS_FINE_RETURN_VALUE);

        } else {
            // Permission has already been granted
        }

        statusPermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED && statusPermission;

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // No explanation needed; request the permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, MY_ACCESS_COARSE_RETURN_VALUE);

        } else {
            // Permission has already been granted
        }

        statusPermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED && statusPermission;

        if(statusPermission){
            startActivity(new Intent(PermissionsActivity.this, MapActivity.class));
        } else{
            Toast.makeText(this, "Missing Location Permission", Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_ACCESS_COARSE_RETURN_VALUE:
            case MY_ACCESS_FINE_RETURN_VALUE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startActivity(new Intent(PermissionsActivity.this, MapActivity.class));
                } else {
                    Toast.makeText(this, "Missing Location Permission", Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }


}
