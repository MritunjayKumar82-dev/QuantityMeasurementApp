package com.apps.quantitymeasurement.UC15;

/**
 * Controller layer for the Quantity Measurement system.
 * Orchestrates input/output, delegates all business logic to IQuantityMeasurementService.
 * Acts as a REST-ready Facade — perform* methods map to POST endpoints.
 */
public class QuantityMeasurementController {

    private final IQuantityMeasurementService service;

    public QuantityMeasurementController(IQuantityMeasurementService service) {
        if (service == null) throw new IllegalArgumentException("Service cannot be null");
        this.service = service;
    }

    // ─── API Methods ──────────────────────────────────────────────────────────

    /**
     * POST /api/quantity/compare
     */
    public QuantityDTO performComparison(QuantityDTO input1, QuantityDTO input2) {
        displayRequest("COMPARE", input1, input2);
        QuantityDTO result = service.compare(input1, input2);
        displayResult(result);
        return result;
    }

    /**
     * POST /api/quantity/convert
     */
    public QuantityDTO performConversion(QuantityDTO input, String targetUnit) {
        System.out.println("[CONVERT] " + input + " -> " + targetUnit);
        QuantityDTO result = service.convert(input, targetUnit);
        displayResult(result);
        return result;
    }

    /**
     * POST /api/quantity/add
     */
    public QuantityDTO performAddition(QuantityDTO input1, QuantityDTO input2) {
        displayRequest("ADD", input1, input2);
        QuantityDTO result = service.add(input1, input2);
        displayResult(result);
        return result;
    }

    /**
     * POST /api/quantity/subtract
     */
    public QuantityDTO performSubtraction(QuantityDTO input1, QuantityDTO input2) {
        displayRequest("SUBTRACT", input1, input2);
        QuantityDTO result = service.subtract(input1, input2);
        displayResult(result);
        return result;
    }

    /**
     * POST /api/quantity/divide
     */
    public QuantityDTO performDivision(QuantityDTO input1, QuantityDTO input2) {
        displayRequest("DIVIDE", input1, input2);
        QuantityDTO result = service.divide(input1, input2);
        displayResult(result);
        return result;
    }

    // ─── Safe perform (returns DTO with error flag instead of throwing) ────────

    public QuantityDTO safePerformComparison(QuantityDTO input1, QuantityDTO input2) {
        try {
            return performComparison(input1, input2);
        } catch (QuantityMeasurementException e) {
            return errorDTO("COMPARE", e.getMessage());
        }
    }

    public QuantityDTO safePerformConversion(QuantityDTO input, String targetUnit) {
        try {
            return performConversion(input, targetUnit);
        } catch (QuantityMeasurementException e) {
            return errorDTO("CONVERT", e.getMessage());
        }
    }

    public QuantityDTO safePerformAddition(QuantityDTO input1, QuantityDTO input2) {
        try {
            return performAddition(input1, input2);
        } catch (QuantityMeasurementException e) {
            return errorDTO("ADD", e.getMessage());
        }
    }

    public QuantityDTO safePerformSubtraction(QuantityDTO input1, QuantityDTO input2) {
        try {
            return performSubtraction(input1, input2);
        } catch (QuantityMeasurementException e) {
            return errorDTO("SUBTRACT", e.getMessage());
        }
    }

    public QuantityDTO safePerformDivision(QuantityDTO input1, QuantityDTO input2) {
        try {
            return performDivision(input1, input2);
        } catch (QuantityMeasurementException e) {
            return errorDTO("DIVIDE", e.getMessage());
        }
    }

    // ─── Display helpers ──────────────────────────────────────────────────────

    public void displayResult(QuantityDTO result) {
        if (result == null) {
            System.out.println("Result: null");
            return;
        }
        if ("ERROR".equals(result.getMeasurementType())) {
            System.out.println("ERROR: " + result.getUnit());
        } else {
            System.out.println("Result: " + result.getValue() + " " + result.getUnit()
                    + " [" + result.getMeasurementType() + "]");
        }
    }

    private void displayRequest(String operation, QuantityDTO input1, QuantityDTO input2) {
        System.out.println("[" + operation + "] " + input1 + " and " + input2);
    }

    private QuantityDTO errorDTO(String operation, String message) {
        QuantityDTO err = new QuantityDTO(0.0, message, "ERROR");
        System.out.println("[" + operation + "] ERROR: " + message);
        return err;
    }
}
