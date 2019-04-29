package com.example.lib3;
import java.util.List;
import java.util.ArrayList;

public abstract class AirPlane {
    protected String name;
    public double wingArea;
    public double aspectRatio;
    public double thrust;
    public double CL_Max;
    public double CL_G;
    public double CD0;
    public double e = 2.78;
    public double CR = 0.020;
    public double carryWeight;
    public double maxWeight;
    public double emptyWeight;
    public double g = 9.81;
    public double r = 8.314;
    public double p;
    public double t;

    public AirPlane(double[] array, double newCarryWeight) {
        carryWeight = newCarryWeight;
        p = array[0] * 6894.76;
        t = ((array[1] - 32) * (5 / 9)) + 273.15;
    }

    public AirPlane(Double setCarryWeight) {
        carryWeight = setCarryWeight;
    }
    public String getName() {
        return name;
    }




    //double airDensity = ((p * mr) / (r * t));



    public int trackDistance() {

        return 0;
    }
}
