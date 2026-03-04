package com.apps.quantitymeasurement.UC15;



/**
 * Service interface defining contract for quantity measurement operations.
 * Accepts QuantityDTO input and returns QuantityDTO output.
 */
public interface IQuantityMeasurementService {

    /**
     * Converts a quantity to the specified target unit.
     *
     * @param input      the quantity to convert
     * @param targetUnit the target unit name (e.g., "INCH", "METER")
     * @return QuantityDTO with converted value and target unit
     */
    QuantityDTO convert(QuantityDTO input, String targetUnit);

    /**
     * Compares two quantities for equality (cross-unit, same category).
     *
     * @return QuantityDTO with result value 1.0 (equal) or 0.0 (not equal),
     *         or error DTO if cross-category
     */
    QuantityDTO compare(QuantityDTO input1, QuantityDTO input2);

    /**
     * Adds two quantities, returning result in unit of input1.
     *
     * @return QuantityDTO with sum, or error DTO if unsupported
     */
    QuantityDTO add(QuantityDTO input1, QuantityDTO input2);

    /**
     * Subtracts input2 from input1, returning result in unit of input1.
     *
     * @return QuantityDTO with difference, or error DTO if unsupported
     */
    QuantityDTO subtract(QuantityDTO input1, QuantityDTO input2);

    /**
     * Divides input1 by input2, returning a dimensionless scalar DTO.
     *
     * @return QuantityDTO with scalar ratio, or error DTO if division by zero
     */
    QuantityDTO divide(QuantityDTO input1, QuantityDTO input2);
}
