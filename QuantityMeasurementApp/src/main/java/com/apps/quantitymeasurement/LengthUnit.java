package com.apps.quantitymeasurement;

public enum LengthUnit implements IMeasurable{
    FEET(12.0),
    INCHES(1.0),
    YARDS(36.0),
    CENTIMETERS(0.393701);

    private final double conversionFactor;

    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }

    public double convertToBaseUnit(double value){
        return round(value*conversionFactor);
    }
    public double convertFromBaseUnit(double baseValue){
        return round(baseValue/conversionFactor);

    }
    private static double round(double value){
        return Math.round((value*100.0)/100.0);
    }


}
