package com.apps.quantitymeasurement.UC15;

import java.io.Serializable;

/**
 * Data Transfer Object (DTO) for holding quantity measurement input/output data.
 * Contains value, unit string, and measurementType string.
 * Self-contained with inner IMeasurableUnit interface and unit enums.
 */
public class QuantityDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private double value;
    private String unit;
    private String measurementType;

    public QuantityDTO() {}

    public QuantityDTO(double value, String unit, String measurementType) {
        this.value = value;
        this.unit = unit;
        this.measurementType = measurementType;
    }

    public double getValue() { return value; }
    public void setValue(double value) { this.value = value; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }

    public String getMeasurementType() { return measurementType; }
    public void setMeasurementType(String measurementType) { this.measurementType = measurementType; }

    @Override
    public String toString() {
        return "QuantityDTO{value=" + value + ", unit='" + unit + "', measurementType='" + measurementType + "'}";
    }

    // ─── Inner interface for DTO-side unit representation ───────────────────

    /**
     * Inner interface to represent measurable units within the DTO layer.
     * Separate from the application-level IMeasurable to keep DTO self-contained.
     */
    public interface IMeasurableUnit {
        String getUnitName();
        String getMeasurementType();
    }

    // ─── Inner enums implementing IMeasurableUnit ────────────────────────────

    public enum LengthUnit implements IMeasurableUnit {
        FEET, INCH, YARD, CENTIMETER, METER;

        @Override public String getUnitName() { return name(); }
        @Override public String getMeasurementType() { return "LENGTH"; }
    }

    public enum WeightUnit implements IMeasurableUnit {
        GRAM, KILOGRAM, POUND, OUNCE;

        @Override public String getUnitName() { return name(); }
        @Override public String getMeasurementType() { return "WEIGHT"; }
    }

    public enum VolumeUnit implements IMeasurableUnit {
        MILLILITER, LITER, GALLON;

        @Override public String getUnitName() { return name(); }
        @Override public String getMeasurementType() { return "VOLUME"; }
    }

    public enum TemperatureUnit implements IMeasurableUnit {
        CELSIUS, FAHRENHEIT, KELVIN;

        @Override public String getUnitName() { return name(); }
        @Override public String getMeasurementType() { return "TEMPERATURE"; }
    }

    /**
     * Factory method: build a QuantityDTO from a typed IMeasurableUnit enum constant.
     */
    public static QuantityDTO of(double value, IMeasurableUnit unit) {
        return new QuantityDTO(value, unit.getUnitName(), unit.getMeasurementType());
    }
}
