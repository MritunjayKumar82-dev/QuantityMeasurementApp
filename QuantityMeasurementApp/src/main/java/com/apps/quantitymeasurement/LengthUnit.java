package com.apps.quantitymeasurement;

public enum LengthUnit {
    FEET(12.0),
    INCHES(1.0),
    YARDS(36.0),
    CENTIMETERS(0.393701);

    private final double toInchesFactor;

    LengthUnit(double toInchesFactor) {
        this.toInchesFactor = toInchesFactor;
    }

    public double convertToBaseUnit(double value){
        return round(value*toInchesFactor);
    }
    public double convertFromBaseUnit(double baseValue){
        return round(baseValue/toInchesFactor);

    }
    private static double round(double value){
        return Math.round((value*100.0)/100.0);
    }


}
