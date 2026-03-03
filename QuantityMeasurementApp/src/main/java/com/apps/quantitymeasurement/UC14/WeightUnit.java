package com.apps.quantitymeasurement.UC14;

public enum WeightUnit implements IMeasurable {
    KILOGRAM(1000.0),
    GRAM(1.0),
    POUND(453.592);

    private final double conversionFactor;

    WeightUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }
    public double toBase(double value){
        return value * conversionFactor;
    }
    public double fromBase(double baseValue){
        return baseValue / conversionFactor;
    }

    @Override
    public double getConversionFactor() {
        return conversionFactor;
    }

    @Override
    public String getUnitName() {
        return "";
    }
}
