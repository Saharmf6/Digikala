package org.aut.digikala;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends RuntimePermissionsActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    Button btnSend, btnDest;
    boolean destClicked = false;
    Marker destMarker;
    LatLng destLatLng;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        email = getIntent().getStringExtra("email");
        btnDest = findViewById(R.id.btnDest);
        btnSend =findViewById(R.id.btnSend);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Uni and move the camera
        LatLng Uni = new LatLng(35.703881, 51.405689);
        //mMap.addMarker(new MarkerOptions().position(Uni).title("Marker in Tehran"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Uni, 5));

        ////////////////////////////////GETTING MY LOCATION
        MapsActivity.super.requestAppPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                LOCATION_PERMISSION_REQUEST_CODE);




        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"سفارش ارسال شد",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MapsActivity.this, MainActivity.class);
                intent.putExtra("email", email);
                startActivity(intent);

            }
        });


        btnDest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destClicked = true;
            }
        });

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                if(destClicked) {
                    if (destLatLng != null)
                        destMarker.remove();
                    destMarker = mMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)));
                    destLatLng = latLng;
                }
            }
        });
    }


    public boolean checkPlayServices(){
        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int result = googleAPI.isGooglePlayServicesAvailable(this);
        if(result == ConnectionResult.SUCCESS)
            return true;
        else
        {
            if (googleAPI.isUserResolvableError(result))
                googleAPI.getErrorDialog(this, result, 9000).show();
            return false;
        }
    }
    public boolean checkMapsIsReady(GoogleMap mMap)
    {
        if (mMap == null){
            Toast.makeText(this, "Map is not ready yet", Toast.LENGTH_LONG).show();
            return false;
        }
        else
            return true;
    }
    @Override
    public void onPermissionsGranted(int requestCode) {
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE)
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                return;
            }
        mMap.setMyLocationEnabled(true);

    }
    @Override
    public void onPermissionsDeny(int requestCode) {
        finish();

    }
}
