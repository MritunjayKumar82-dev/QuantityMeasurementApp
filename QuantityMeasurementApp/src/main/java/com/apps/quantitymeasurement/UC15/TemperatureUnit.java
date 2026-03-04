package com.apps.quantitymeasurement.UC15;

/**
 * Enum representing temperature units.
 * Uses conversion factor + offset model (relative to Celsius as base).
 * toBase:  value * factor + offset  => Celsius
 * fromBase: (celsius - offset) / factor  => value in this unit
 */
public enum TemperatureUnit implements IMeasurable {
    CELSIUS(1.0, 0.0),
    FAHRENHEIT(5.0 / 9.0, -32.0),
    KELVIN(1.0, -273.15);

    private final double conversionFactor;
    private final double offset;

    TemperatureUnit(double conversionFactor, double offset) {
        this.conversionFactor = conversionFactor;
        this.offset = offset;
    }

    @Override
    public double getConversionFactor() {
        return conversionFactor;
    }

    @Override
    public double getOffset() {
        return offset;
    }

    @Override
    public String getMeasurementType() {
        return "TEMPERATURE";
    }

    @Override
    public boolean supportsArithmetic() {
        return false;
    }

    @Override
    public IMeasurable getUnitByName(String unitName) {
        return TemperatureUnit.valueOf(unitName.toUpperCase());
    }
}
