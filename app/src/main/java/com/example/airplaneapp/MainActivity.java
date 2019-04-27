package com.example.airplaneapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.lib3.AirPlane;
import com.example.lib3.airbus;
import com.example.lib3.blackbird;
import com.example.lib3.boeing;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import android.location.LocationManager;
import android.location.Location;
import android.content.Context;
import android.content.ContextWrapper;
import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.widget.Button;
import android.widget.Switch;
import android.widget.ToggleButton;


public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_FINE_LOCATION = 88;
    private boolean canAccessFineLocation = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView output = findViewById(R.id.outputWindow);
        final ImageButton boeing = findViewById(R.id.boeingImage);
        final ImageButton blackbird = findViewById(R.id.blackBird);
        final ImageButton airbus = findViewById(R.id.airBus);
        canAccessFineLocation =
                checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
        if (!canAccessFineLocation) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_FINE_LOCATION);


        //currLat, currLong;
        LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        double currLong = location.getLongitude();
        double currLat = location.getLatitude();

        //

        boeing.setOnClickListener(v -> {
            output.setText("Boeing 777");
            //
        });
        blackbird.setOnClickListener(v -> {
            output.setText("Blackbird");
        });
        airbus.setOnClickListener(v -> {
            output.setText("Airbus A320");
        });
    }

    void startAPIcall(String url) {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }


    protected void processData(final String jsonResult) {
        String result = "https://api.darksky.net/forecast/6592c435eba5f3dcc7d9f16a18aea781/42.3601,-71.0589";

    }
}
