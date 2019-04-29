package com.example.lib3;
import java.util.List;
import java.util.ArrayList;

public class boeing {
    protected String name = "Boeing 777";
    public double wingArea = 427.8;
    public double aspectRatio = 8.68;
    public double thrust = 409000;
    public double CL_Max = 1.9;
    public double CL_G = 0.55;
    public double CD0 = 0.030;
    public double e = 0.77;
    public double CR = 0.020;
    public double carryWeight;
    public double maxWeight = 299370;
    public double emptyWeight = 135850;
    public double g = 9.81;
    public double r = 8.314;
    public double p;
    public double t;
    public double mr = 0.02897;
    public double CD = CD0 + ((CL_G * CL_G) / (3.14159 * e * aspectRatio));
    public double airDensity;
    public double totalWeight;

    public boeing(double[] array, double newCarryWeight) {
        carryWeight = newCarryWeight;
        totalWeight = emptyWeight + carryWeight;
        p = (array[0] / 1000) * 100000;
        t = ((array[1] - 32) * (5.0 / 9.0)) + 273.15;
        double air = ((p * mr) / (r * t));
        airDensity = air;
    }


    public double takeoffVel() {
        double vStall = Math.sqrt((2 * totalWeight * g) / (airDensity * wingArea * CL_Max));
        return 1.25 * vStall;

    }

    public String trackDistance(double takeoffVel) {
        if (totalWeight > maxWeight) {
            return "Overweight! Please enter a new weight";
        }
        double a = thrust / totalWeight;
        return Double.toString((takeoffVel * takeoffVel) / (2 * a));
    }











    /**
    public double trackDistance(double takeoffVel) {
        List<Double> t = new ArrayList<>();
        t.add(0.0);
        List<Double> vEst = new ArrayList<>();
        List<Double> v = new ArrayList<>();
        v.add(0.0);
        List<Double> dvDt = new ArrayList<>();
        List<Double> dvDtEst = new ArrayList<>();

        while (true) {
            double acc = (thrust / totalWeight) - ((CR / totalWeight) * ((totalWeight * g) - (0.5 * CL_G * airDensity * wingArea * (v.get(v.size() - 1) * v.get(v.size() - 1)))))
                    - (0.5 * CL_G * airDensity * (wingArea / totalWeight) * CD * (v.get(v.size() - 1) * v.get(v.size() - 1)));
            dvDt.add(acc);

            double vest = v.get(v.size() - 1) + (0.01 * dvDt.get(dvDt.size() - 1));
            vEst.add(vest);

            double accEst = (thrust / totalWeight) - ((CR / totalWeight) * ((totalWeight * g) - (0.5 * CL_G * airDensity * wingArea * (vEst.get(vEst.size() - 1) * vEst.get(vEst.size() - 1)))))
                    - (0.5 * CL_G * airDensity * (wingArea / totalWeight) * CD * (vEst.get(vEst.size() - 1) * vEst.get(vEst.size() - 1)));
            dvDtEst.add(accEst);

            double vTrue = v.get(v.size() - 1) + (0.01 * 0.5 * (dvDtEst.get(dvDtEst.size() - 1)) + dvDt.get(dvDt.size() - 1));

            if (vTrue > takeoffVel) {
                break;
            }
        }
        double sum = 0;
        for (double vel : v) {
            sum += vel;
        }
        return 0.01 * sum;
    }
    **/















}
