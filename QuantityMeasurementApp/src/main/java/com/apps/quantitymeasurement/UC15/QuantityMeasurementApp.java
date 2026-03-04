package com.apps.quantitymeasurement.UC15;

/**
 * Application entry point for the Quantity Measurement system (UC15).
 * Implements Factory + Facade patterns.
 * Initialises the repository, service, and controller layers via dependency injection.
 */
public class QuantityMeasurementApp {

    private final QuantityMeasurementController controller;

    public QuantityMeasurementApp() {
        IQuantityMeasurementRepository repository = QuantityMeasurementCacheRepository.getInstance();
        IQuantityMeasurementService service       = new QuantityMeasurementServiceImpl(repository);
        this.controller = new QuantityMeasurementController(service);
    }

    /** For testing: allow injection of a custom controller */
    public QuantityMeasurementApp(QuantityMeasurementController controller) {
        this.controller = controller;
    }

    public QuantityMeasurementController getController() {
        return controller;
    }

    // ─── Demonstration methods ────────────────────────────────────────────────

    private void demonstrateLengthEquality() {
        System.out.println("\n=== Example 1: Length Equality Demonstration ===");
        QuantityDTO feet1 = QuantityDTO.of(1.0, QuantityDTO.LengthUnit.FEET);
        QuantityDTO inch12 = QuantityDTO.of(12.0, QuantityDTO.LengthUnit.INCH);
        controller.safePerformComparison(feet1, inch12);

        QuantityDTO feet2 = QuantityDTO.of(2.0, QuantityDTO.LengthUnit.FEET);
        QuantityDTO yard1 = QuantityDTO.of(1.0, QuantityDTO.LengthUnit.YARD);
        controller.safePerformComparison(feet2, yard1);
    }

    private void demonstrateTemperatureAddition() {
        System.out.println("\n=== Example 2: Temperature Addition Attempt ===");
        QuantityDTO t1 = QuantityDTO.of(100.0, QuantityDTO.TemperatureUnit.CELSIUS);
        QuantityDTO t2 = QuantityDTO.of(212.0, QuantityDTO.TemperatureUnit.FAHRENHEIT);
        controller.safePerformAddition(t1, t2);
    }

    private void demonstrateCrossCategoryPrevention() {
        System.out.println("\n=== Example 3: Cross-Category Operation Prevention ===");
        QuantityDTO length = QuantityDTO.of(5.0, QuantityDTO.LengthUnit.FEET);
        QuantityDTO weight = QuantityDTO.of(5.0, QuantityDTO.WeightUnit.KILOGRAM);
        controller.safePerformComparison(length, weight);
    }

    private void demonstrateLengthAddition() {
        System.out.println("\n=== Length Addition: 2 feet + 2 inches ===");
        QuantityDTO feet = QuantityDTO.of(2.0, QuantityDTO.LengthUnit.FEET);
        QuantityDTO inch = QuantityDTO.of(2.0, QuantityDTO.LengthUnit.INCH);
        controller.safePerformAddition(feet, inch);
    }

    private void demonstrateWeightConversion() {
        System.out.println("\n=== Weight Conversion: 1 kg -> grams ===");
        QuantityDTO kg = QuantityDTO.of(1.0, QuantityDTO.WeightUnit.KILOGRAM);
        controller.safePerformConversion(kg, "GRAM");
    }

    // ─── main ────────────────────────────────────────────────────────────────

    public static void main(String[] args) {
        QuantityMeasurementApp app = new QuantityMeasurementApp();
        app.demonstrateLengthEquality();
        app.demonstrateTemperatureAddition();
        app.demonstrateCrossCategoryPrevention();
        app.demonstrateLengthAddition();
        app.demonstrateWeightConversion();
    }
}
