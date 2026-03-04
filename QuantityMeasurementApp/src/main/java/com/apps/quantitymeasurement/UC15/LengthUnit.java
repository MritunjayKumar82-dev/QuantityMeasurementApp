package com.apps.quantitymeasurement.UC15;

/**
 * Enum representing length units with their conversion factors to a base unit (inches).
 */
public enum LengthUnit implements IMeasurable {
    FEET(12.0),
    INCH(1.0),
    YARD(36.0),
    CENTIMETER(0.393701),
    METER(39.3701);

    private final double conversionFactor;

    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    @Override
    public double getConversionFactor() {
        return conversionFactor;
    }

    @Override
    public String getMeasurementType() {
        return "LENGTH";
    }

    @Override
    public IMeasurable getUnitByName(String unitName) {
        return LengthUnit.valueOf(unitName.toUpperCase());
    }
}
