package com.apps.quantitymeasurement.UC15;

/**
 * Enum representing volume units with their conversion factors to a base unit (milliliters).
 */
public enum VolumeUnit implements IMeasurable {
    MILLILITER(1.0),
    LITER(1000.0),
    GALLON(3785.41);

    private final double conversionFactor;

    VolumeUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    @Override
    public double getConversionFactor() {
        return conversionFactor;
    }

    @Override
    public String getMeasurementType() {
        return "VOLUME";
    }

    @Override
    public IMeasurable getUnitByName(String unitName) {
        return VolumeUnit.valueOf(unitName.toUpperCase());
    }
}
