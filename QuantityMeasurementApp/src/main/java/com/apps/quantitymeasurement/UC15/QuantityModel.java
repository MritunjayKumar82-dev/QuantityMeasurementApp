package com.apps.quantitymeasurement.UC15;

/**
 * Generic internal model class for representing a quantity with its unit.
 * Used within the service layer for all operations.
 *
 * @param <U> type of the unit, must implement IMeasurable
 */
public class QuantityModel<U extends IMeasurable> {

    private final double value;
    private final U unit;

    public QuantityModel(double value, U unit) {
        this.value = value;
        this.unit = unit;
    }

    public double getValue() { return value; }
    public U getUnit() { return unit; }

    /**
     * Convert this quantity to the target unit, returning a new QuantityModel.
     */
    @SuppressWarnings("unchecked")
    public QuantityModel<U> convertTo(U targetUnit) {
        double baseValue = toBaseValue();
        double convertedValue;
        if (unit instanceof TemperatureUnit) {
            // Temperature: base is Celsius
            // toBase: (value + offset) * factor
            // fromBase: celsius / factor - offset
            convertedValue = baseValue / targetUnit.getConversionFactor() - targetUnit.getOffset();
        } else {
            convertedValue = baseValue / targetUnit.getConversionFactor();
        }
        return new QuantityModel<>(convertedValue, targetUnit);
    }

    /**
     * Convert value to base unit value.
     */
    public double toBaseValue() {
        if (unit instanceof TemperatureUnit) {
            return (value + unit.getOffset()) * unit.getConversionFactor();
        }
        return value * unit.getConversionFactor();
    }

    /**
     * Check equality by comparing base values.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuantityModel<?> other)) return false;
        // Only compare if same measurement type
        if (!this.unit.getMeasurementType().equals(other.unit.getMeasurementType())) return false;
        double delta = Math.abs(this.toBaseValue() - other.toBaseValue());
        return delta < 1e-6;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(toBaseValue());
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}
