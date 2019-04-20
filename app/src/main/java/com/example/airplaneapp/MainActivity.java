package com.example.airplaneapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.lib3.AirPlane;
import com.example.lib3.airbus;
import com.example.lib3.blackbird;
import com.example.lib3.boeing;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView output = findViewById(R.id.outputWindow);
        final ImageButton boeing = findViewById(R.id.boeingImage);
        final ImageButton blackbird = findViewById(R.id.blackBird);
        final ImageButton airbus = findViewById(R.id.airBus);
        boeing.setOnClickListener(v -> {
            output.setText("Boeing 777");
        });
        blackbird.setOnClickListener(v -> {
            output.setText("Blackbird");
        });
        airbus.setOnClickListener(v -> {
            output.setText("Airbus 320");
        });


    }
}
