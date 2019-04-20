package com.example.lib3;

public abstract class AirPlane {
    protected String name;
    public double wingArea;
    public double aspectRatio;
    public double thrust;
    public double CL_Max;
    public double CL_G;
    public double CD0;
    public double e;
    public double CR;
    public double carryWeight;
    public double Max_Weight;
    public AirPlane() {
        carryWeight = 0;
    }
    public AirPlane(Double setCarryWeight) {
        carryWeight = setCarryWeight;
    }
    public String getName() {
        return name;
    }
    public int trackDistance() {
        return 0;
    }
}
