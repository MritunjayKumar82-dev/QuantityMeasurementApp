package com.apps.quantitymeasurement.UC15;

import java.io.Serializable;

/**
 * Entity class for persisting quantity measurement operations (operands, type, result, errors).
 * Implements Serializable for disk persistence via the cache repository.
 * Designed for immutability through constructor-only initialization.
 */
public class QuantityMeasurementEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Double operand1Value;
    private String operand1Unit;
    private String operand1MeasurementType;

    private Double operand2Value;
    private String operand2Unit;
    private String operand2MeasurementType;

    private String operationType;

    private Double resultValue;
    private String resultUnit;
    private String resultMeasurementType;

    private boolean error;
    private String errorMessage;

    // ─── Constructor for single-operand operations (e.g., conversion) ────────
    public QuantityMeasurementEntity(Double operand1Value, String operand1Unit,
                                     String operand1MeasurementType, String operationType,
                                     Double resultValue, String resultUnit, String resultMeasurementType) {
        this.operand1Value = operand1Value;
        this.operand1Unit = operand1Unit;
        this.operand1MeasurementType = operand1MeasurementType;
        this.operationType = operationType;
        this.resultValue = resultValue;
        this.resultUnit = resultUnit;
        this.resultMeasurementType = resultMeasurementType;
        this.error = false;
        this.errorMessage = null;
    }

    // ─── Constructor for binary operations (e.g., add, subtract, compare) ────
    public QuantityMeasurementEntity(Double operand1Value, String operand1Unit, String operand1MeasurementType,
                                     Double operand2Value, String operand2Unit, String operand2MeasurementType,
                                     String operationType,
                                     Double resultValue, String resultUnit, String resultMeasurementType) {
        this.operand1Value = operand1Value;
        this.operand1Unit = operand1Unit;
        this.operand1MeasurementType = operand1MeasurementType;
        this.operand2Value = operand2Value;
        this.operand2Unit = operand2Unit;
        this.operand2MeasurementType = operand2MeasurementType;
        this.operationType = operationType;
        this.resultValue = resultValue;
        this.resultUnit = resultUnit;
        this.resultMeasurementType = resultMeasurementType;
        this.error = false;
        this.errorMessage = null;
    }

    // ─── Constructor for error scenarios ──────────────────────────────────────
    public QuantityMeasurementEntity(String operationType, String errorMessage) {
        this.operationType = operationType;
        this.error = true;
        this.errorMessage = errorMessage;
    }

    // ─── Getters ──────────────────────────────────────────────────────────────
    public Double getOperand1Value() { return operand1Value; }
    public String getOperand1Unit() { return operand1Unit; }
    public String getOperand1MeasurementType() { return operand1MeasurementType; }
    public Double getOperand2Value() { return operand2Value; }
    public String getOperand2Unit() { return operand2Unit; }
    public String getOperand2MeasurementType() { return operand2MeasurementType; }
    public String getOperationType() { return operationType; }
    public Double getResultValue() { return resultValue; }
    public String getResultUnit() { return resultUnit; }
    public String getResultMeasurementType() { return resultMeasurementType; }
    public boolean hasError() { return error; }
    public String getErrorMessage() { return errorMessage; }

    @Override
    public String toString() {
        if (error) {
            return "QuantityMeasurementEntity{operation='" + operationType + "', ERROR: " + errorMessage + "}";
        }
        if (operand2Value != null) {
            return "QuantityMeasurementEntity{" + operand1Value + " " + operand1Unit
                    + " " + operationType + " " + operand2Value + " " + operand2Unit
                    + " = " + resultValue + " " + resultUnit + "}";
        }
        return "QuantityMeasurementEntity{" + operand1Value + " " + operand1Unit
                + " -> " + resultValue + " " + resultUnit + "}";
    }
}
