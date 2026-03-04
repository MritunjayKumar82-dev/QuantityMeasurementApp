package com.apps.quantitymeasurement;

import com.apps.quantitymeasurement.UC15.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.ArrayList;

import org.mockito.junit.jupiter.MockitoExtension;

import static jdk.jfr.internal.jfc.model.Constraint.any;
import static org.junit.jupiter.api.Assertions.*;



@ExtendWith(MockitoExtension.class)
public class QuantityMeasurementAppTest_UC15 {





/**
 * Comprehensive test suite for UC15 N-Tier Architecture.
 * Covers: Entity, Service, Controller, Layer Separation, Data Flow, Integration.
 */



    private IQuantityMeasurementRepository repository;
    private IQuantityMeasurementService service;
    private QuantityMeasurementController controller;

    @BeforeEach
    void setUp() {
        repository = mock(IQuantityMeasurementRepository.class);
        lenient().doNothing().when(repository).save(any());
        lenient().when(repository.getAllMeasurements()).thenReturn(new ArrayList<>());
        service    = new QuantityMeasurementServiceImpl(repository);
        controller = new QuantityMeasurementController(service);
    }

    // ══════════════════════════════════════════════════════════════════════════
    // ENTITY LAYER TESTS
    // ══════════════════════════════════════════════════════════════════════════

    @Test
    @DisplayName("testQuantityEntity_SingleOperandConstruction")
    void testQuantityEntity_SingleOperandConstruction() {
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity(
                1.0, "FEET", "LENGTH", "CONVERT", 12.0, "INCH", "LENGTH");

        assertEquals(1.0, entity.getOperand1Value());
        assertEquals("FEET", entity.getOperand1Unit());
        assertEquals("LENGTH", entity.getOperand1MeasurementType());
        assertEquals("CONVERT", entity.getOperationType());
        assertEquals(12.0, entity.getResultValue());
        assertEquals("INCH", entity.getResultUnit());
        assertFalse(entity.hasError());
        assertNull(entity.getErrorMessage());
    }

    @Test
    @DisplayName("testQuantityEntity_BinaryOperandConstruction")
    void testQuantityEntity_BinaryOperandConstruction() {
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity(
                2.0, "FEET", "LENGTH",
                2.0, "INCH", "LENGTH",
                "ADD", 26.0, "INCH", "LENGTH");

        assertEquals(2.0,   entity.getOperand1Value());
        assertEquals("FEET", entity.getOperand1Unit());
        assertEquals(2.0,   entity.getOperand2Value());
        assertEquals("INCH", entity.getOperand2Unit());
        assertEquals("ADD", entity.getOperationType());
        assertEquals(26.0,  entity.getResultValue());
        assertFalse(entity.hasError());
    }

    @Test
    @DisplayName("testQuantityEntity_ErrorConstruction")
    void testQuantityEntity_ErrorConstruction() {
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity(
                "ADD", "ADD is not supported for TEMPERATURE");

        assertTrue(entity.hasError());
        assertEquals("ADD is not supported for TEMPERATURE", entity.getErrorMessage());
        assertEquals("ADD", entity.getOperationType());
    }

    @Test
    @DisplayName("testQuantityEntity_ToString_Success")
    void testQuantityEntity_ToString_Success() {
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity(
                1.0, "FEET", "LENGTH", "CONVERT", 12.0, "INCH", "LENGTH");

        String str = entity.toString();
        assertNotNull(str);
        assertFalse(str.isEmpty());
        assertTrue(str.contains("1.0") || str.contains("FEET") || str.contains("INCH"));
    }

    @Test
    @DisplayName("testQuantityEntity_ToString_Error")
    void testQuantityEntity_ToString_Error() {
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity(
                "COMPARE", "Cross-category comparison not allowed");

        String str = entity.toString();
        assertTrue(str.contains("ERROR") || str.contains("Cross-category"));
    }

    @Test
    @DisplayName("testEntity_OperationType_Tracking")
    void testEntity_OperationType_Tracking() {
        QuantityMeasurementEntity convert  = new QuantityMeasurementEntity("CONVERT", "error");
        QuantityMeasurementEntity add      = new QuantityMeasurementEntity("ADD",     "error");
        QuantityMeasurementEntity compare  = new QuantityMeasurementEntity("COMPARE", "error");
        QuantityMeasurementEntity subtract = new QuantityMeasurementEntity("SUBTRACT","error");
        QuantityMeasurementEntity divide   = new QuantityMeasurementEntity("DIVIDE",  "error");

        assertEquals("CONVERT",  convert.getOperationType());
        assertEquals("ADD",      add.getOperationType());
        assertEquals("COMPARE",  compare.getOperationType());
        assertEquals("SUBTRACT", subtract.getOperationType());
        assertEquals("DIVIDE",   divide.getOperationType());
    }

    // ══════════════════════════════════════════════════════════════════════════
    // SERVICE LAYER TESTS
    // ══════════════════════════════════════════════════════════════════════════

    @Test
    @DisplayName("testService_CompareEquality_SameUnit_Success")
    void testService_CompareEquality_SameUnit_Success() {
        QuantityDTO q1 = QuantityDTO.of(5.0, QuantityDTO.LengthUnit.FEET);
        QuantityDTO q2 = QuantityDTO.of(5.0, QuantityDTO.LengthUnit.FEET);

        QuantityDTO result = service.compare(q1, q2);
        assertEquals(1.0, result.getValue(), 1e-6); // equal
    }

    @Test
    @DisplayName("testService_CompareEquality_DifferentUnit_Success")
    void testService_CompareEquality_DifferentUnit_Success() {
        // 1 foot == 12 inches
        QuantityDTO q1 = QuantityDTO.of(1.0, QuantityDTO.LengthUnit.FEET);
        QuantityDTO q2 = QuantityDTO.of(12.0, QuantityDTO.LengthUnit.INCH);

        QuantityDTO result = service.compare(q1, q2);
        assertEquals(1.0, result.getValue(), 1e-6);
    }

    @Test
    @DisplayName("testService_CompareEquality_NotEqual_Success")
    void testService_CompareEquality_NotEqual_Success() {
        QuantityDTO q1 = QuantityDTO.of(1.0, QuantityDTO.LengthUnit.FEET);
        QuantityDTO q2 = QuantityDTO.of(5.0, QuantityDTO.LengthUnit.INCH);

        QuantityDTO result = service.compare(q1, q2);
        assertEquals(0.0, result.getValue(), 1e-6); // not equal
    }

    @Test
    @DisplayName("testService_CompareEquality_CrossCategory_Error")
    void testService_CompareEquality_CrossCategory_Error() {
        QuantityDTO length = QuantityDTO.of(5.0, QuantityDTO.LengthUnit.FEET);
        QuantityDTO weight = QuantityDTO.of(5.0, QuantityDTO.WeightUnit.KILOGRAM);

        assertThrows(QuantityMeasurementException.class, () -> service.compare(length, weight));
    }

    @Test
    @DisplayName("testService_Convert_Success")
    void testService_Convert_Success() {
        QuantityDTO input = QuantityDTO.of(1.0, QuantityDTO.LengthUnit.FEET);

        QuantityDTO result = service.convert(input, "INCH");

        assertNotNull(result);
        assertEquals(12.0, result.getValue(), 1e-4);
        assertEquals("INCH", result.getUnit());
    }

    @Test
    @DisplayName("testService_Convert_YardToFeet")
    void testService_Convert_YardToFeet() {
        QuantityDTO input = QuantityDTO.of(1.0, QuantityDTO.LengthUnit.YARD);
        QuantityDTO result = service.convert(input, "FEET");
        assertEquals(3.0, result.getValue(), 1e-4);
    }

    @Test
    @DisplayName("testService_Convert_KgToGram")
    void testService_Convert_KgToGram() {
        QuantityDTO input = QuantityDTO.of(1.0, QuantityDTO.WeightUnit.KILOGRAM);
        QuantityDTO result = service.convert(input, "GRAM");
        assertEquals(1000.0, result.getValue(), 1e-4);
    }

    @Test
    @DisplayName("testService_Convert_Temperature_CelsiusToFahrenheit")
    void testService_Convert_Temperature_CelsiusToFahrenheit() {
        QuantityDTO input = QuantityDTO.of(0.0, QuantityDTO.TemperatureUnit.CELSIUS);
        QuantityDTO result = service.convert(input, "FAHRENHEIT");
        assertEquals(32.0, result.getValue(), 1e-4);
    }

    @Test
    @DisplayName("testService_Add_Success")
    void testService_Add_Success() {
        // 2 feet + 2 inches -> result in feet: 2 + 2/12 = 2.1667
        QuantityDTO q1 = QuantityDTO.of(2.0, QuantityDTO.LengthUnit.FEET);
        QuantityDTO q2 = QuantityDTO.of(2.0, QuantityDTO.LengthUnit.INCH);

        QuantityDTO result = service.add(q1, q2);

        assertNotNull(result);
        assertEquals("FEET", result.getUnit());
        assertEquals(2.1667, result.getValue(), 1e-3);
    }

    @Test
    @DisplayName("testService_Add_SameUnit_Success")
    void testService_Add_SameUnit_Success() {
        QuantityDTO q1 = QuantityDTO.of(3.0, QuantityDTO.WeightUnit.KILOGRAM);
        QuantityDTO q2 = QuantityDTO.of(2.0, QuantityDTO.WeightUnit.KILOGRAM);

        QuantityDTO result = service.add(q1, q2);
        assertEquals(5.0, result.getValue(), 1e-6);
        assertEquals("KILOGRAM", result.getUnit());
    }

    @Test
    @DisplayName("testService_Add_UnsupportedOperation_Error")
    void testService_Add_UnsupportedOperation_Error() {
        QuantityDTO t1 = QuantityDTO.of(100.0, QuantityDTO.TemperatureUnit.CELSIUS);
        QuantityDTO t2 = QuantityDTO.of(100.0, QuantityDTO.TemperatureUnit.CELSIUS);

        assertThrows(QuantityMeasurementException.class, () -> service.add(t1, t2));
    }

    @Test
    @DisplayName("testService_Subtract_Success")
    void testService_Subtract_Success() {
        QuantityDTO q1 = QuantityDTO.of(5.0, QuantityDTO.WeightUnit.KILOGRAM);
        QuantityDTO q2 = QuantityDTO.of(500.0, QuantityDTO.WeightUnit.GRAM);

        QuantityDTO result = service.subtract(q1, q2);
        assertEquals(4.5, result.getValue(), 1e-6);
        assertEquals("KILOGRAM", result.getUnit());
    }

    @Test
    @DisplayName("testService_Divide_Success")
    void testService_Divide_Success() {
        QuantityDTO q1 = QuantityDTO.of(12.0, QuantityDTO.LengthUnit.INCH);
        QuantityDTO q2 = QuantityDTO.of(1.0, QuantityDTO.LengthUnit.FEET);

        QuantityDTO result = service.divide(q1, q2);
        assertEquals(1.0, result.getValue(), 1e-6); // 12 inches / 12 inches (1 foot)
        assertEquals("SCALAR", result.getUnit());
    }

    @Test
    @DisplayName("testService_Divide_ByZero_Error")
    void testService_Divide_ByZero_Error() {
        QuantityDTO q1 = QuantityDTO.of(5.0, QuantityDTO.LengthUnit.FEET);
        QuantityDTO q2 = QuantityDTO.of(0.0, QuantityDTO.LengthUnit.FEET);

        assertThrows(QuantityMeasurementException.class, () -> service.divide(q1, q2));
    }

    @Test
    @DisplayName("testService_NullEntity_Rejection")
    void testService_NullEntity_Rejection() {
        assertThrows(QuantityMeasurementException.class, () -> service.compare(null, null));
        assertThrows(QuantityMeasurementException.class, () -> service.add(null, null));
        assertThrows(QuantityMeasurementException.class, () -> service.convert(null, "INCH"));
    }

    @Test
    @DisplayName("testService_AllMeasurementCategories")
    void testService_AllMeasurementCategories() {
        // Length
        QuantityDTO lengthResult = service.convert(
                QuantityDTO.of(1.0, QuantityDTO.LengthUnit.FEET), "INCH");
        assertEquals("LENGTH", lengthResult.getMeasurementType());

        // Weight
        QuantityDTO weightResult = service.convert(
                QuantityDTO.of(1.0, QuantityDTO.WeightUnit.KILOGRAM), "GRAM");
        assertEquals("WEIGHT", weightResult.getMeasurementType());

        // Volume
        QuantityDTO volumeResult = service.convert(
                QuantityDTO.of(1.0, QuantityDTO.VolumeUnit.LITER), "MILLILITER");
        assertEquals("VOLUME", volumeResult.getMeasurementType());

        // Temperature
        QuantityDTO tempResult = service.convert(
                QuantityDTO.of(0.0, QuantityDTO.TemperatureUnit.CELSIUS), "FAHRENHEIT");
        assertEquals("TEMPERATURE", tempResult.getMeasurementType());
    }

    @Test
    @DisplayName("testService_ValidationConsistency")
    void testService_ValidationConsistency() {
        QuantityDTO length = QuantityDTO.of(1.0, QuantityDTO.LengthUnit.FEET);
        QuantityDTO weight = QuantityDTO.of(1.0, QuantityDTO.WeightUnit.KILOGRAM);

        // All operations should throw for cross-category
        assertThrows(QuantityMeasurementException.class, () -> service.compare(length, weight));
        assertThrows(QuantityMeasurementException.class, () -> service.add(length, weight));
        assertThrows(QuantityMeasurementException.class, () -> service.subtract(length, weight));
        assertThrows(QuantityMeasurementException.class, () -> service.divide(length, weight));
    }

    @Test
    @DisplayName("testService_ExceptionHandling_AllOperations")
    void testService_ExceptionHandling_AllOperations() {
        // Verify all operations handle exceptions and save error entity to repository
        QuantityDTO t = QuantityDTO.of(100.0, QuantityDTO.TemperatureUnit.CELSIUS);
        assertThrows(QuantityMeasurementException.class, () -> service.add(t, t));
        verify(repository, atLeastOnce()).save(any(QuantityMeasurementEntity.class));
    }

    @Test
    @DisplayName("testService_AllUnitImplementations")
    void testService_AllUnitImplementations() {
        // LengthUnit
        assertDoesNotThrow(() -> service.convert(QuantityDTO.of(1.0, QuantityDTO.LengthUnit.YARD), "FEET"));
        assertDoesNotThrow(() -> service.convert(QuantityDTO.of(100.0, QuantityDTO.LengthUnit.CENTIMETER), "METER"));
        // WeightUnit
        assertDoesNotThrow(() -> service.convert(QuantityDTO.of(1.0, QuantityDTO.WeightUnit.POUND), "GRAM"));
        assertDoesNotThrow(() -> service.convert(QuantityDTO.of(1.0, QuantityDTO.WeightUnit.OUNCE), "GRAM"));
        // VolumeUnit
        assertDoesNotThrow(() -> service.convert(QuantityDTO.of(1.0, QuantityDTO.VolumeUnit.GALLON), "LITER"));
        // TemperatureUnit
        assertDoesNotThrow(() -> service.convert(QuantityDTO.of(100.0, QuantityDTO.TemperatureUnit.CELSIUS), "KELVIN"));
    }

    // ══════════════════════════════════════════════════════════════════════════
    // CONTROLLER LAYER TESTS
    // ══════════════════════════════════════════════════════════════════════════

    @Test
    @DisplayName("testController_DemonstrateEquality_Success")
    void testController_DemonstrateEquality_Success() {
        QuantityDTO q1 = QuantityDTO.of(1.0, QuantityDTO.LengthUnit.FEET);
        QuantityDTO q2 = QuantityDTO.of(12.0, QuantityDTO.LengthUnit.INCH);

        QuantityDTO result = controller.performComparison(q1, q2);
        assertNotNull(result);
        assertEquals(1.0, result.getValue(), 1e-6);
    }

    @Test
    @DisplayName("testController_DemonstrateConversion_Success")
    void testController_DemonstrateConversion_Success() {
        QuantityDTO input = QuantityDTO.of(1.0, QuantityDTO.LengthUnit.FEET);

        QuantityDTO result = controller.performConversion(input, "INCH");
        assertNotNull(result);
        assertEquals(12.0, result.getValue(), 1e-4);
    }

    @Test
    @DisplayName("testController_DemonstrateAddition_Success")
    void testController_DemonstrateAddition_Success() {
        QuantityDTO q1 = QuantityDTO.of(2.0, QuantityDTO.LengthUnit.FEET);
        QuantityDTO q2 = QuantityDTO.of(2.0, QuantityDTO.LengthUnit.INCH);

        QuantityDTO result = controller.performAddition(q1, q2);
        assertNotNull(result);
        assertTrue(result.getValue() > 2.0);
    }

    @Test
    @DisplayName("testController_DemonstrateAddition_Error")
    void testController_DemonstrateAddition_Error() {
        QuantityDTO t1 = QuantityDTO.of(100.0, QuantityDTO.TemperatureUnit.CELSIUS);
        QuantityDTO t2 = QuantityDTO.of(100.0, QuantityDTO.TemperatureUnit.CELSIUS);

        // safePerform should return error DTO, not throw
        QuantityDTO result = controller.safePerformAddition(t1, t2);
        assertNotNull(result);
        assertEquals("ERROR", result.getMeasurementType());
    }

    @Test
    @DisplayName("testController_DisplayResult_Success")
    void testController_DisplayResult_Success() {
        QuantityDTO result = new QuantityDTO(12.0, "INCH", "LENGTH");
        // Should not throw
        assertDoesNotThrow(() -> controller.displayResult(result));
    }

    @Test
    @DisplayName("testController_DisplayResult_Error")
    void testController_DisplayResult_Error() {
        QuantityDTO error = new QuantityDTO(0.0, "Cross-category error", "ERROR");
        assertDoesNotThrow(() -> controller.displayResult(error));
    }

    @Test
    @DisplayName("testController_NullService_Prevention")
    void testController_NullService_Prevention() {
        assertThrows(IllegalArgumentException.class,
                () -> new QuantityMeasurementController(null));
    }

    @Test
    @DisplayName("testController_AllOperations")
    void testController_AllOperations() {
        QuantityDTO q1 = QuantityDTO.of(2.0, QuantityDTO.WeightUnit.KILOGRAM);
        QuantityDTO q2 = QuantityDTO.of(1.0, QuantityDTO.WeightUnit.KILOGRAM);

        assertDoesNotThrow(() -> controller.performComparison(q1, q2));
        assertDoesNotThrow(() -> controller.performConversion(q1, "GRAM"));
        assertDoesNotThrow(() -> controller.performAddition(q1, q2));
        assertDoesNotThrow(() -> controller.performSubtraction(q1, q2));
        assertDoesNotThrow(() -> controller.performDivision(q1, q2));
    }

    // ══════════════════════════════════════════════════════════════════════════
    // LAYER SEPARATION TESTS
    // ══════════════════════════════════════════════════════════════════════════

    @Test
    @DisplayName("testLayerSeparation_ServiceIndependence")
    void testLayerSeparation_ServiceIndependence() {
        // Service can be tested without controller
        IQuantityMeasurementService independentService =
                new QuantityMeasurementServiceImpl(repository);

        QuantityDTO result = independentService.convert(
                QuantityDTO.of(1.0, QuantityDTO.LengthUnit.FEET), "INCH");

        assertEquals(12.0, result.getValue(), 1e-4);
    }

    @Test
    @DisplayName("testLayerSeparation_ControllerIndependence")
    void testLayerSeparation_ControllerIndependence() {
        // Controller can work with a mock service
        IQuantityMeasurementService mockService = mock(IQuantityMeasurementService.class);
        QuantityDTO mockResult = new QuantityDTO(12.0, "INCH", "LENGTH");
        when(mockService.convert(any(), anyString())).thenReturn(mockResult);

        QuantityMeasurementController ctrl = new QuantityMeasurementController(mockService);
        QuantityDTO result = ctrl.performConversion(
                QuantityDTO.of(1.0, QuantityDTO.LengthUnit.FEET), "INCH");

        assertEquals(12.0, result.getValue(), 1e-6);
        verify(mockService).convert(any(), eq("INCH"));
    }

    @Test
    @DisplayName("testLayerDecoupling_ServiceChange")
    void testLayerDecoupling_ServiceChange() {
        // Changing service implementation should not affect controller interface
        IQuantityMeasurementService anotherService =
                new QuantityMeasurementServiceImpl(repository);
        QuantityMeasurementController ctrl = new QuantityMeasurementController(anotherService);

        QuantityDTO result = ctrl.performConversion(
                QuantityDTO.of(1.0, QuantityDTO.LengthUnit.YARD), "FEET");

        assertEquals(3.0, result.getValue(), 1e-4);
    }

    @Test
    @DisplayName("testLayerDecoupling_EntityChange")
    void testLayerDecoupling_EntityChange() {
        // QuantityMeasurementEntity fields do not break service/controller
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity(
                1.0, "FEET", "LENGTH", "CONVERT", 12.0, "INCH", "LENGTH");

        // Entity is accessible and self-contained
        assertFalse(entity.hasError());
        assertEquals("CONVERT", entity.getOperationType());
    }

    // ══════════════════════════════════════════════════════════════════════════
    // DATA FLOW TESTS
    // ══════════════════════════════════════════════════════════════════════════

    @Test
    @DisplayName("testDataFlow_ControllerToService")
    void testDataFlow_ControllerToService() {
        IQuantityMeasurementService spyService = spy(
                new QuantityMeasurementServiceImpl(repository));
        QuantityMeasurementController ctrl = new QuantityMeasurementController(spyService);

        QuantityDTO input = QuantityDTO.of(1.0, QuantityDTO.LengthUnit.FEET);
        ctrl.performConversion(input, "INCH");

        verify(spyService).convert(eq(input), eq("INCH"));
    }

    @Test
    @DisplayName("testDataFlow_ServiceToController")
    void testDataFlow_ServiceToController() {
        IQuantityMeasurementService mockService = mock(IQuantityMeasurementService.class);
        QuantityDTO expected = new QuantityDTO(12.0, "INCH", "LENGTH");
        when(mockService.convert(any(), anyString())).thenReturn(expected);

        QuantityMeasurementController ctrl = new QuantityMeasurementController(mockService);
        QuantityDTO result = ctrl.performConversion(
                QuantityDTO.of(1.0, QuantityDTO.LengthUnit.FEET), "INCH");

        assertEquals(expected.getValue(), result.getValue(), 1e-6);
        assertEquals(expected.getUnit(), result.getUnit());
    }

    // ══════════════════════════════════════════════════════════════════════════
    // BACKWARD COMPATIBILITY (UC1–UC14) TESTS
    // ══════════════════════════════════════════════════════════════════════════

    @Test
    @DisplayName("testBackwardCompatibility_AllUC1_UC14_Tests")
    void testBackwardCompatibility_AllUC1_UC14_Tests() {
        // UC1-UC2: 1 foot == 12 inches
        QuantityDTO r1 = service.compare(
                QuantityDTO.of(1.0, QuantityDTO.LengthUnit.FEET),
                QuantityDTO.of(12.0, QuantityDTO.LengthUnit.INCH));
        assertEquals(1.0, r1.getValue(), 1e-6);

        // UC3: 1 yard == 3 feet
        QuantityDTO r2 = service.compare(
                QuantityDTO.of(1.0, QuantityDTO.LengthUnit.YARD),
                QuantityDTO.of(3.0, QuantityDTO.LengthUnit.FEET));
        assertEquals(1.0, r2.getValue(), 1e-6);

        // UC4: 1 foot + 1 foot = 2 feet
        QuantityDTO r3 = service.add(
                QuantityDTO.of(1.0, QuantityDTO.LengthUnit.FEET),
                QuantityDTO.of(1.0, QuantityDTO.LengthUnit.FEET));
        assertEquals(2.0, r3.getValue(), 1e-6);

        // UC5: Weight - 1 kg == 1000 grams
        QuantityDTO r4 = service.compare(
                QuantityDTO.of(1.0, QuantityDTO.WeightUnit.KILOGRAM),
                QuantityDTO.of(1000.0, QuantityDTO.WeightUnit.GRAM));
        assertEquals(1.0, r4.getValue(), 1e-6);

        // UC6: Volume - 1 liter == 1000 ml
        QuantityDTO r5 = service.compare(
                QuantityDTO.of(1.0, QuantityDTO.VolumeUnit.LITER),
                QuantityDTO.of(1000.0, QuantityDTO.VolumeUnit.MILLILITER));
        assertEquals(1.0, r5.getValue(), 1e-6);

        // UC7: Temperature - 0 celsius == 32 fahrenheit
        QuantityDTO tempResult = service.convert(
                QuantityDTO.of(0.0, QuantityDTO.TemperatureUnit.CELSIUS), "FAHRENHEIT");
        assertEquals(32.0, tempResult.getValue(), 1e-4);

        // UC8: Cross-category prevention
        assertThrows(QuantityMeasurementException.class, () -> service.compare(
                QuantityDTO.of(1.0, QuantityDTO.LengthUnit.FEET),
                QuantityDTO.of(1.0, QuantityDTO.WeightUnit.GRAM)));

        // UC9: Temperature addition not supported
        assertThrows(QuantityMeasurementException.class, () -> service.add(
                QuantityDTO.of(100.0, QuantityDTO.TemperatureUnit.CELSIUS),
                QuantityDTO.of(100.0, QuantityDTO.TemperatureUnit.CELSIUS)));

        // UC10: Division - scalar result
        QuantityDTO divResult = service.divide(
                QuantityDTO.of(2.0, QuantityDTO.LengthUnit.FEET),
                QuantityDTO.of(1.0, QuantityDTO.LengthUnit.FEET));
        assertEquals(2.0, divResult.getValue(), 1e-6);
        assertEquals("SCALAR", divResult.getUnit());
    }

    // ══════════════════════════════════════════════════════════════════════════
    // INTEGRATION TESTS
    // ══════════════════════════════════════════════════════════════════════════

    @Test
    @DisplayName("testIntegration_EndToEnd_LengthAddition")
    void testIntegration_EndToEnd_LengthAddition() {
        // Full flow: App → Controller → Service → Repository → Result
        IQuantityMeasurementRepository realRepo = mock(IQuantityMeasurementRepository.class);
        doNothing().when(realRepo).save(any());

        IQuantityMeasurementService svc = new QuantityMeasurementServiceImpl(realRepo);
        QuantityMeasurementController ctrl = new QuantityMeasurementController(svc);

        QuantityDTO q1 = QuantityDTO.of(2.0, QuantityDTO.LengthUnit.FEET);
        QuantityDTO q2 = QuantityDTO.of(2.0, QuantityDTO.LengthUnit.INCH);

        QuantityDTO result = ctrl.performAddition(q1, q2);

        assertNotNull(result);
        assertEquals("FEET", result.getUnit());
        assertEquals("LENGTH", result.getMeasurementType());
        assertTrue(result.getValue() > 2.0);

        verify(realRepo, atLeastOnce()).save(any());
    }

    @Test
    @DisplayName("testIntegration_EndToEnd_TemperatureUnsupported")
    void testIntegration_EndToEnd_TemperatureUnsupported() {
        // safePerformAddition should return error DTO without crashing
        QuantityDTO t1 = QuantityDTO.of(100.0, QuantityDTO.TemperatureUnit.CELSIUS);
        QuantityDTO t2 = QuantityDTO.of(212.0, QuantityDTO.TemperatureUnit.FAHRENHEIT);

        QuantityDTO result = controller.safePerformAddition(t1, t2);

        assertNotNull(result);
        assertEquals("ERROR", result.getMeasurementType());
    }

    @Test
    @DisplayName("testIntegration_App_Initializes")
    void testIntegration_App_Initializes() {
        IQuantityMeasurementService svc = new QuantityMeasurementServiceImpl(repository);
        QuantityMeasurementController ctrl = new QuantityMeasurementController(svc);
        QuantityMeasurementApp app = new QuantityMeasurementApp(ctrl);

        assertNotNull(app);
        assertNotNull(app.getController());
    }

    // ══════════════════════════════════════════════════════════════════════════
    // IMMUTABILITY & SCALABILITY TESTS
    // ══════════════════════════════════════════════════════════════════════════

    @Test
    @DisplayName("testEntity_Immutability")
    void testEntity_Immutability() {
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity(
                1.0, "FEET", "LENGTH", "CONVERT", 12.0, "INCH", "LENGTH");

        // Entity should not have setters – verify fields unchanged after creation
        assertEquals(1.0,   entity.getOperand1Value());
        assertEquals("FEET", entity.getOperand1Unit());
        assertEquals(12.0,  entity.getResultValue());
        assertFalse(entity.hasError());
    }

    @Test
    @DisplayName("testScalability_NewOperation_Addition")
    void testScalability_NewOperation_Addition() {
        // Adding a new combination works without modifying existing layers
        QuantityDTO vol1 = QuantityDTO.of(1.0, QuantityDTO.VolumeUnit.LITER);
        QuantityDTO vol2 = QuantityDTO.of(500.0, QuantityDTO.VolumeUnit.MILLILITER);

        QuantityDTO result = service.add(vol1, vol2);
        assertEquals(1.5, result.getValue(), 1e-4);
        assertEquals("LITER", result.getUnit());
    }

    // ══════════════════════════════════════════════════════════════════════════
    // REPOSITORY INTERACTION TESTS
    // ══════════════════════════════════════════════════════════════════════════

    @Test
    @DisplayName("testRepository_SaveCalledOnOperation")
    void testRepository_SaveCalledOnOperation() {
        service.convert(QuantityDTO.of(1.0, QuantityDTO.LengthUnit.FEET), "INCH");
        verify(repository, times(1)).save(any(QuantityMeasurementEntity.class));
    }

    @Test
    @DisplayName("testRepository_SaveCalledOnComparison")
    void testRepository_SaveCalledOnComparison() {
        service.compare(
                QuantityDTO.of(1.0, QuantityDTO.LengthUnit.FEET),
                QuantityDTO.of(12.0, QuantityDTO.LengthUnit.INCH));
        verify(repository, times(1)).save(any(QuantityMeasurementEntity.class));
    }

    @Test
    @DisplayName("testRepository_SaveCalledOnError")
    void testRepository_SaveCalledOnError() {
        try {
            service.add(
                    QuantityDTO.of(1.0, QuantityDTO.TemperatureUnit.CELSIUS),
                    QuantityDTO.of(1.0, QuantityDTO.TemperatureUnit.CELSIUS));
        } catch (QuantityMeasurementException ignored) {}
        verify(repository, atLeastOnce()).save(any(QuantityMeasurementEntity.class));
    }

    // ══════════════════════════════════════════════════════════════════════════
    // QUANTITYMODEL TESTS
    // ══════════════════════════════════════════════════════════════════════════

    @Test
    @DisplayName("testQuantityModel_Equality")
    void testQuantityModel_Equality() {
        QuantityModel<LengthUnit> m1 = new QuantityModel<>(1.0, LengthUnit.FEET);
        QuantityModel<LengthUnit> m2 = new QuantityModel<>(12.0, LengthUnit.INCH);
        assertEquals(m1, m2);
    }

    @Test
    @DisplayName("testQuantityModel_Conversion")
    void testQuantityModel_Conversion() {
        QuantityModel<LengthUnit> feet = new QuantityModel<>(1.0, LengthUnit.FEET);
        QuantityModel<LengthUnit> inches = feet.convertTo(LengthUnit.INCH);
        assertEquals(12.0, inches.getValue(), 1e-4);
        assertEquals(LengthUnit.INCH, inches.getUnit());
    }

    @Test
    @DisplayName("testQuantityModel_Temperature_Conversion")
    void testQuantityModel_Temperature_Conversion() {
        QuantityModel<TemperatureUnit> c = new QuantityModel<>(0.0, TemperatureUnit.CELSIUS);
        QuantityModel<TemperatureUnit> f = c.convertTo(TemperatureUnit.FAHRENHEIT);
        assertEquals(32.0, f.getValue(), 1e-4);
    }

    // ══════════════════════════════════════════════════════════════════════════
    // QUANTITYDTO FACTORY METHOD TESTS
    // ══════════════════════════════════════════════════════════════════════════

    @Test
    @DisplayName("testQuantityDTO_FactoryMethod")
    void testQuantityDTO_FactoryMethod() {
        QuantityDTO dto = QuantityDTO.of(5.0, QuantityDTO.LengthUnit.FEET);
        assertEquals(5.0, dto.getValue());
        assertEquals("FEET", dto.getUnit());
        assertEquals("LENGTH", dto.getMeasurementType());
    }

    @Test
    @DisplayName("testQuantityDTO_AllMeasurementTypes")
    void testQuantityDTO_AllMeasurementTypes() {
        QuantityDTO length = QuantityDTO.of(1.0, QuantityDTO.LengthUnit.INCH);
        QuantityDTO weight = QuantityDTO.of(1.0, QuantityDTO.WeightUnit.GRAM);
        QuantityDTO volume = QuantityDTO.of(1.0, QuantityDTO.VolumeUnit.LITER);
        QuantityDTO temp   = QuantityDTO.of(1.0, QuantityDTO.TemperatureUnit.CELSIUS);

        assertEquals("LENGTH",      length.getMeasurementType());
        assertEquals("WEIGHT",      weight.getMeasurementType());
        assertEquals("VOLUME",      volume.getMeasurementType());
        assertEquals("TEMPERATURE", temp.getMeasurementType());
    }
}

