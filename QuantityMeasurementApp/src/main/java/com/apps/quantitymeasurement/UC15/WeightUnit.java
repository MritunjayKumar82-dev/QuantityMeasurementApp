package com.apps.quantitymeasurement.UC15;

/**
 * Enum representing weight units with their conversion factors to a base unit (grams).
 */
public enum WeightUnit implements IMeasurable {
    GRAM(1.0),
    KILOGRAM(1000.0),
    POUND(453.592),
    OUNCE(28.3495);

    private final double conversionFactor;

    WeightUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    @Override
    public double getConversionFactor() {
        return conversionFactor;
    }

    @Override
    public String getMeasurementType() {
        return "WEIGHT";
    }

    @Override
    public IMeasurable getUnitByName(String unitName) {
        return WeightUnit.valueOf(unitName.toUpperCase());
    }
}
