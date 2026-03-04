package com.apps.quantitymeasurement.UC15;


/**
 * Core business logic implementation for quantity measurement operations.
 * Converts QuantityDTO → QuantityModel, performs operations, persists results,
 * and returns standardised QuantityDTO results.
 *
 * Follows SRP, OCP, and DI principles.
 */
public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    private final IQuantityMeasurementRepository repository;

    public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repository) {
        if (repository == null) throw new IllegalArgumentException("Repository cannot be null");
        this.repository = repository;
    }

    // ─── Convert ──────────────────────────────────────────────────────────────

    @Override
    public QuantityDTO convert(QuantityDTO input, String targetUnit) {
        validateNotNull(input, "Input QuantityDTO");
        validateNotNull(targetUnit, "Target unit");
        try {
            IMeasurable srcUnit  = resolveUnit(input.getMeasurementType(), input.getUnit());
            IMeasurable tgtUnit  = resolveUnit(input.getMeasurementType(), targetUnit);

            validateSameCategory(srcUnit, tgtUnit, "CONVERT");

            QuantityModel<IMeasurable> model = new QuantityModel<>(input.getValue(), srcUnit);
            QuantityModel<IMeasurable> converted = model.convertTo(tgtUnit);

            QuantityDTO result = new QuantityDTO(converted.getValue(),
                    targetUnit.toUpperCase(), tgtUnit.getMeasurementType());

            repository.save(new QuantityMeasurementEntity(
                    input.getValue(), input.getUnit(), input.getMeasurementType(),
                    "CONVERT",
                    converted.getValue(), targetUnit.toUpperCase(), tgtUnit.getMeasurementType()));

            return result;

        } catch (QuantityMeasurementException e) {
            repository.save(new QuantityMeasurementEntity("CONVERT", e.getMessage()));
            throw e;
        } catch (Exception e) {
            String msg = "Conversion failed: " + e.getMessage();
            repository.save(new QuantityMeasurementEntity("CONVERT", msg));
            throw new QuantityMeasurementException(msg, e);
        }
    }

    // ─── Compare ──────────────────────────────────────────────────────────────

    @Override
    public QuantityDTO compare(QuantityDTO input1, QuantityDTO input2) {
        validateNotNull(input1, "First QuantityDTO");
        validateNotNull(input2, "Second QuantityDTO");
        try {
            IMeasurable unit1 = resolveUnit(input1.getMeasurementType(), input1.getUnit());
            IMeasurable unit2 = resolveUnit(input2.getMeasurementType(), input2.getUnit());

            validateSameCategory(unit1, unit2, "COMPARE");

            QuantityModel<IMeasurable> model1 = new QuantityModel<>(input1.getValue(), unit1);
            QuantityModel<IMeasurable> model2 = new QuantityModel<>(input2.getValue(), unit2);

            boolean equal = model1.equals(model2);
            double resultVal = equal ? 1.0 : 0.0;

            QuantityDTO result = new QuantityDTO(resultVal, "BOOLEAN", "COMPARISON");

            repository.save(new QuantityMeasurementEntity(
                    input1.getValue(), input1.getUnit(), input1.getMeasurementType(),
                    input2.getValue(), input2.getUnit(), input2.getMeasurementType(),
                    "COMPARE", resultVal, "BOOLEAN", "COMPARISON"));

            return result;

        } catch (QuantityMeasurementException e) {
            repository.save(new QuantityMeasurementEntity("COMPARE", e.getMessage()));
            throw e;
        } catch (Exception e) {
            String msg = "Comparison failed: " + e.getMessage();
            repository.save(new QuantityMeasurementEntity("COMPARE", msg));
            throw new QuantityMeasurementException(msg, e);
        }
    }

    // ─── Add ──────────────────────────────────────────────────────────────────

    @Override
    public QuantityDTO add(QuantityDTO input1, QuantityDTO input2) {
        validateNotNull(input1, "First QuantityDTO");
        validateNotNull(input2, "Second QuantityDTO");
        try {
            IMeasurable unit1 = resolveUnit(input1.getMeasurementType(), input1.getUnit());
            IMeasurable unit2 = resolveUnit(input2.getMeasurementType(), input2.getUnit());

            validateSameCategory(unit1, unit2, "ADD");
            validateArithmetic(unit1, "ADD");

            QuantityModel<IMeasurable> model1 = new QuantityModel<>(input1.getValue(), unit1);
            QuantityModel<IMeasurable> model2 = new QuantityModel<>(input2.getValue(), unit2);

            // Convert both to base, add, express in unit1
            double baseSum = model1.toBaseValue() + model2.toBaseValue();
            double resultValue = baseSum / unit1.getConversionFactor();

            QuantityDTO result = new QuantityDTO(resultValue, input1.getUnit(), input1.getMeasurementType());

            repository.save(new QuantityMeasurementEntity(
                    input1.getValue(), input1.getUnit(), input1.getMeasurementType(),
                    input2.getValue(), input2.getUnit(), input2.getMeasurementType(),
                    "ADD", resultValue, input1.getUnit(), input1.getMeasurementType()));

            return result;

        } catch (QuantityMeasurementException e) {
            repository.save(new QuantityMeasurementEntity("ADD", e.getMessage()));
            throw e;
        } catch (Exception e) {
            String msg = "Addition failed: " + e.getMessage();
            repository.save(new QuantityMeasurementEntity("ADD", msg));
            throw new QuantityMeasurementException(msg, e);
        }
    }

    // ─── Subtract ─────────────────────────────────────────────────────────────

    @Override
    public QuantityDTO subtract(QuantityDTO input1, QuantityDTO input2) {
        validateNotNull(input1, "First QuantityDTO");
        validateNotNull(input2, "Second QuantityDTO");
        try {
            IMeasurable unit1 = resolveUnit(input1.getMeasurementType(), input1.getUnit());
            IMeasurable unit2 = resolveUnit(input2.getMeasurementType(), input2.getUnit());

            validateSameCategory(unit1, unit2, "SUBTRACT");
            validateArithmetic(unit1, "SUBTRACT");

            QuantityModel<IMeasurable> model1 = new QuantityModel<>(input1.getValue(), unit1);
            QuantityModel<IMeasurable> model2 = new QuantityModel<>(input2.getValue(), unit2);

            double baseDiff = model1.toBaseValue() - model2.toBaseValue();
            double resultValue = baseDiff / unit1.getConversionFactor();

            QuantityDTO result = new QuantityDTO(resultValue, input1.getUnit(), input1.getMeasurementType());

            repository.save(new QuantityMeasurementEntity(
                    input1.getValue(), input1.getUnit(), input1.getMeasurementType(),
                    input2.getValue(), input2.getUnit(), input2.getMeasurementType(),
                    "SUBTRACT", resultValue, input1.getUnit(), input1.getMeasurementType()));

            return result;

        } catch (QuantityMeasurementException e) {
            repository.save(new QuantityMeasurementEntity("SUBTRACT", e.getMessage()));
            throw e;
        } catch (Exception e) {
            String msg = "Subtraction failed: " + e.getMessage();
            repository.save(new QuantityMeasurementEntity("SUBTRACT", msg));
            throw new QuantityMeasurementException(msg, e);
        }
    }

    // ─── Divide ───────────────────────────────────────────────────────────────

    @Override
    public QuantityDTO divide(QuantityDTO input1, QuantityDTO input2) {
        validateNotNull(input1, "First QuantityDTO");
        validateNotNull(input2, "Second QuantityDTO");
        try {
            IMeasurable unit1 = resolveUnit(input1.getMeasurementType(), input1.getUnit());
            IMeasurable unit2 = resolveUnit(input2.getMeasurementType(), input2.getUnit());

            validateSameCategory(unit1, unit2, "DIVIDE");

            QuantityModel<IMeasurable> model1 = new QuantityModel<>(input1.getValue(), unit1);
            QuantityModel<IMeasurable> model2 = new QuantityModel<>(input2.getValue(), unit2);

            double base2 = model2.toBaseValue();
            if (Math.abs(base2) < 1e-10) {
                throw new QuantityMeasurementException("Division by zero is not allowed");
            }

            double ratio = model1.toBaseValue() / base2;
            QuantityDTO result = new QuantityDTO(ratio, "SCALAR", "DIMENSIONLESS");

            repository.save(new QuantityMeasurementEntity(
                    input1.getValue(), input1.getUnit(), input1.getMeasurementType(),
                    input2.getValue(), input2.getUnit(), input2.getMeasurementType(),
                    "DIVIDE", ratio, "SCALAR", "DIMENSIONLESS"));

            return result;

        } catch (QuantityMeasurementException e) {
            repository.save(new QuantityMeasurementEntity("DIVIDE", e.getMessage()));
            throw e;
        } catch (Exception e) {
            String msg = "Division failed: " + e.getMessage();
            repository.save(new QuantityMeasurementEntity("DIVIDE", msg));
            throw new QuantityMeasurementException(msg, e);
        }
    }

    // ─── Private helpers ──────────────────────────────────────────────────────

    /**
     * Resolve a unit string to an IMeasurable, guided by measurementType.
     */
    private IMeasurable resolveUnit(String measurementType, String unitName) {
        if (measurementType == null || unitName == null) {
            throw new QuantityMeasurementException("Measurement type and unit must not be null");
        }
        try {
            return switch (measurementType.toUpperCase()) {
                case "LENGTH"      -> LengthUnit.valueOf(unitName.toUpperCase());
                case "WEIGHT"      -> WeightUnit.valueOf(unitName.toUpperCase());
                case "VOLUME"      -> VolumeUnit.valueOf(unitName.toUpperCase());
                case "TEMPERATURE" -> TemperatureUnit.valueOf(unitName.toUpperCase());
                default -> throw new QuantityMeasurementException(
                        "Unknown measurement type: " + measurementType);
            };
        } catch (IllegalArgumentException e) {
            throw new QuantityMeasurementException(
                    "Unknown unit '" + unitName + "' for type '" + measurementType + "'", e);
        }
    }

    private void validateSameCategory(IMeasurable unit1, IMeasurable unit2, String operation) {
        if (!unit1.getMeasurementType().equals(unit2.getMeasurementType())) {
            throw new QuantityMeasurementException(
                    "Cannot perform " + operation + " on different measurement types: "
                            + unit1.getMeasurementType() + " and " + unit2.getMeasurementType());
        }
    }

    private void validateArithmetic(IMeasurable unit, String operation) {
        if (!unit.supportsArithmetic()) {
            throw new QuantityMeasurementException(
                    operation + " is not supported for measurement type: " + unit.getMeasurementType());
        }
    }

    private void validateNotNull(Object obj, String name) {
        if (obj == null) {
            throw new QuantityMeasurementException(name + " cannot be null");
        }
    }
}
