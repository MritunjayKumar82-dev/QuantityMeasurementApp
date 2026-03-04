package com.apps.quantitymeasurement.UC15;

/**
 * IMeasurable interface defines the contract for measurable units.
 * Supports conversion factor retrieval, measurement type, and unit resolution by name.
 */
public interface IMeasurable {

    /**
     * Returns the conversion factor to the base unit.
     */
    double getConversionFactor();

    /**
     * Returns the offset for conversion (used for temperature).
     */
    default double getOffset() {
        return 0.0;
    }

    /**
     * Returns the measurement type category (e.g., "LENGTH", "WEIGHT", etc.)
     */
    String getMeasurementType();

    /**
     * Returns the IMeasurable unit instance matching the given unit name.
     */
    IMeasurable getUnitByName(String unitName);

    /**
     * Returns true if this unit supports addition/subtraction arithmetic.
     */
    default boolean supportsArithmetic() {
        return true;
    }
}
