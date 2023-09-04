package com.example.location_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    LocationManager locationManager;
    double latitude = 0.0;
    double logtitute = 0.0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            return;
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,0, (LocationListener) this);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        latitude = location.getLatitude();
        logtitute = location.getLongitude();
    }


    public void openMap(View view) {

        Uri myLocation = Uri.parse("geo:6.0329,80.2168 ?z=15");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW,myLocation);
        startActivity(mapIntent);
    }

    public void Current_location(View view) {

        Toast.makeText(this, String.valueOf(logtitute), Toast.LENGTH_SHORT).show();
        Uri myLocation = Uri.parse("geo:" +latitude +","+ logtitute + "?z=10");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, myLocation);
        startActivity(mapIntent);
    }
}