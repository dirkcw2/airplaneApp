package com.example.airplaneapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.toolbox.JsonObjectRequest;
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
import java.util.List;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_FINE_LOCATION = 88;
    private boolean canAccessFineLocation = false;
    double currLong;
    double currLat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView output = findViewById(R.id.outputWindow);
        final ImageButton boeing = findViewById(R.id.boeingImage);
        final ImageButton blackbird = findViewById(R.id.blackBird);
        final ImageButton airbus = findViewById(R.id.airBus);
        final TextView input = findViewById(R.id.textView);

        canAccessFineLocation =
                checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
        if (!canAccessFineLocation) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_FINE_LOCATION);
        }




        LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        Location myLocation = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        currLong = myLocation.getLongitude();
        currLat = myLocation.getLatitude();

        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                currLong = location.getLongitude();
                currLat = location.getLatitude();
            }

            @Override
            public void onStatusChanged(String provider,
                                        int status,
                                        Bundle extras) {
                Log.d("tag", "changed");
            }

            @Override
            public void onProviderEnabled(String provider) {
                Log.d("tag", "changed");
            }

            @Override
            public void onProviderDisabled(String provider) {
                Log.d("tag", "changed");
            }

        };

        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10, locationListener);


        String url = "https://api.darksky.net/forecast/6592c435eba5f3dcc7d9f16a18aea781/" + Double.toString(currLat) + "," + Double.toString(currLong);


        //startAPIcall(url);

        boeing.setOnClickListener(v -> {
            startAPIcall(url);
            double mass = getInput();
            boeing boeing777 = new boeing(outputData, mass);
            String distance = boeing777.trackDistance(boeing777.takeoffVel());
            output.setText(distance);
        });
        blackbird.setOnClickListener(v -> {
            startAPIcall(url);
            double mass = getInput();
            blackbird sr71blackbird = new blackbird(outputData, mass);
            String distance = sr71blackbird.trackDistance(sr71blackbird.takeoffVel());
            output.setText(distance);
        });
        airbus.setOnClickListener(v -> {
            startAPIcall(url);
            double mass = getInput();
            airbus a320 = new airbus(outputData, mass);
            String distance = a320.trackDistance(a320.takeoffVel());
            output.setText(distance);
        });
    }

    public double getInput() {
        final TextView input = findViewById(R.id.textView);
        double mass = new Double(input.getText().toString());
        return mass;
    }

    public double[] outputData = new double[2];
    //System.out.println(output[1]);

    public void startAPIcall(String url) {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                        // Display the first 500 characters of the response string.
                        JsonParser parser = new JsonParser();
                        JsonObject result = parser.parse(response).getAsJsonObject();
                        double pressure = result.getAsJsonObject("currently").get("pressure").getAsDouble();
                        outputData[0] = pressure;
                        double temperature = result.getAsJsonObject("currently").get("temperature").getAsDouble();
                        outputData[1] = temperature;

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
