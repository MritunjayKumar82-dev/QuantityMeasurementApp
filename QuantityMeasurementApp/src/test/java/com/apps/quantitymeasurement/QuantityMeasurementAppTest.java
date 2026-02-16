package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuantityMeasurementAppTest {
    @Test
    public void testFeetEquality() {
        assertTrue(new Length(1.0, LengthUnit.FEET).equals(new Length(12.0, LengthUnit.INCHES)));
    }

    @Test
    public void testInchesEquality() {
        assertTrue(new Length(12.0, LengthUnit.INCHES).equals(new Length(1.0, LengthUnit.FEET)));
    }

    @Test
    public void testFeetInchesComparison() {
        assertTrue(new Length(1.0, LengthUnit.FEET).equals(new Length(12.0, LengthUnit.INCHES)));

    }

    @Test
    public void testreflexiveSymmetricAndTransitiveProperty() {
        Length feet = new Length(1.0, LengthUnit.FEET);
        Length inches = new Length(12.0, LengthUnit.INCHES);
        Length yards = new Length(1.0 / 3.0, LengthUnit.YARDS);
        assertTrue(feet.equals(feet));
        assertTrue(feet.equals(inches));
        assertTrue(inches.equals(feet));
        assertTrue(feet.equals(inches));
        assertTrue(inches.equals(yards));
        assertTrue(feet.equals(yards));
    }

    @Test
    public void referenceEqualitySameObject() {
        Length length = new Length(1.0, LengthUnit.FEET);
        assertTrue(length.equals(length));
    }

    @Test
    public void equalsReturnsFalseForNull() {
        Length length = new Length(1.0, LengthUnit.FEET);
        assertFalse(length.equals(null));
    }

    @Test
    public void threeFeetEqualsOneYard() {
        assertTrue(new Length(3.0, LengthUnit.FEET).equals(new Length(1.0, LengthUnit.YARDS)));

    }


    @Test
    public void testMultipleFeetComparison() {
        Length result = QuantityMeasurementApp.demonstrateLengthConversion(new Length(3.0, LengthUnit.FEET), LengthUnit.YARDS);
        assertEquals(new Length(1.0, LengthUnit.YARDS), result);
    }

    @Test
    public void yardEquals36Inches() {
        assertEquals(new Length(1.0, LengthUnit.YARDS), new Length(36.0, LengthUnit.INCHES));
    }

    @Test
    public void testEqualsReturnsFalseForNull() {
        assertFalse(new Length(1.0, LengthUnit.FEET).equals(null));
    }

    @Test
    public void testNullUnit() {
        assertThrows(IllegalArgumentException.class, () -> new Length(1.0, null));
    }

    // UC6 Test cases
    @Test
    public void testMultiFeetComparision() {
        Length l1 = new Length(2.0, LengthUnit.FEET);
        Length l2 = new Length(2.0, LengthUnit.FEET);
        assertTrue((l1.equals(l2)));
    }

    @Test
    public void centimeterEquals39Point3701Inches() {
        Length cm = new Length(1.0, LengthUnit.CENTIMETERS);
        Length inches = new Length(0.393701, LengthUnit.INCHES);
        assertTrue((cm.equals(inches)));
    }

    @Test
    public void testFeetEqualsOneYard() {
        Length feet = new Length(3.0, LengthUnit.FEET);
        Length yard = new Length(1.0, LengthUnit.YARDS);
        assertTrue((feet.equals(yard)));
    }

    @Test
    public void thirtyPoint48CmEqualsOneFoot() {
        Length cm = new Length(30.48, LengthUnit.CENTIMETERS);
        Length feet = new Length(1.0, LengthUnit.FEET);
        assertTrue((cm.equals(feet)));
    }

    @Test
    public void reflexiveSymmetricAndTransitiveProperty() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);
        Length l3 = new Length(30.48, LengthUnit.CENTIMETERS);
        assertTrue((l1.equals(l2)));
        assertTrue((l2.equals(l3)));
        assertTrue((l1.equals(l3)));
    }

    @Test
    public void convertYardsToInchesUsingOverloadMethod() {
        Length yards = new Length(1.0, LengthUnit.YARDS);
        Length inches = yards.convertTo(LengthUnit.INCHES);
        assertEquals(36.0, inches.getValue());

    }

    /*test case for UC7*/
    @Test
    public void addFeetAndInchesReturnFeet() {
        Length length1 = new Length(3.0, LengthUnit.FEET);
        Length length2 = new Length(12.0, LengthUnit.INCHES);
        Length result = QuantityMeasurementApp.demonstrateLengthAddition(length1, length2, LengthUnit.FEET);
        assertTrue((result.equals(new Length(4.0, LengthUnit.FEET))));
    }

    @Test
    public void addFeetAndInchesReturnInches() {
        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(12.0, LengthUnit.INCHES);
        Length result = QuantityMeasurementApp.demonstrateLengthAddition(length1, length2, LengthUnit.INCHES);
        assertTrue((result.equals(new Length(24.0, LengthUnit.INCHES))));

    }

    @Test
    public void addFeetAndCentimetersReturnFeet() {
        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(30.48, LengthUnit.CENTIMETERS);
        Length result = QuantityMeasurementApp.demonstrateLengthAddition(length1, length2, LengthUnit.FEET);
        assertTrue((result.equals(new Length(2.0, LengthUnit.FEET))));

    }

    @Test
    public void addInchesAndFeetReturnInches() {
        Length length1 = new Length(12.0, LengthUnit.INCHES);
        Length length2 = new Length(1.0, LengthUnit.FEET);
        Length result = QuantityMeasurementApp.demonstrateLengthAddition(length1, length2, LengthUnit.INCHES);
        assertTrue((result.equals(new Length(24.0, LengthUnit.INCHES))));

    }

    @Test
    public void addFeetAndFeetReturnFeet() {
        Length length1 = new Length(2.0, LengthUnit.FEET);
        Length length2 = new Length(3.0, LengthUnit.FEET);
        Length result = QuantityMeasurementApp.demonstrateLengthAddition(length1, length2, LengthUnit.FEET);
        assertTrue((result.equals(new Length(5.0, LengthUnit.FEET))));

    }

    @Test
    public void testInchesInequality() {
        Length result = QuantityMeasurementApp.demonstrateLengthConversion(new Length(3.0, LengthUnit.FEET), LengthUnit.YARDS);
        assertNotEquals(new Length(12.0, LengthUnit.INCHES), new Length(2.0, LengthUnit.FEET));
    }

    @Test
    public void testCrossUnitInequality() {
        assertNotEquals(new Length(1.0, LengthUnit.FEET), new Length(1.0, LengthUnit.YARDS));
    }

    @Test
    public void tsetReflexiveSymmetricAndTransitiveProperty() {
        Length oneFoot = new Length(1.0, LengthUnit.FEET);
        Length twelveInches = new Length(12.0, LengthUnit.INCHES);
        Length oneThirdYard = new Length(0.333333, LengthUnit.YARDS);
        assertEquals(oneFoot, oneFoot);
        assertEquals(oneFoot, twelveInches);
        assertEquals(twelveInches, oneFoot);
        assertEquals(oneFoot, oneThirdYard);
    }

    @Test
    public void differentValuesSameUnitNotEqual() {
        assertNotEquals(new Length(1.0, LengthUnit.FEET), new Length(2.0, LengthUnit.FEET));
    }

    @Test
    public void testThreeFeetEqualsOneYard() {
        assertEquals(new Length(3.0, LengthUnit.FEET), new Length(1.0, LengthUnit.YARDS));
    }

    @Test
    public void testThirtySixInchesEqualsOneYard() {
        assertEquals(new Length(36.0, LengthUnit.INCHES), new Length(1.0, LengthUnit.YARDS));
    }

    @Test
    public void testCentimeterEqualsInchesWithinEpsilon() {
        assertEquals(new Length(2.54, LengthUnit.CENTIMETERS), new Length(1.0, LengthUnit.INCHES));
    }

    @Test
    public void testConvertFeetToInches() {
        Length result = new Length(1.0, LengthUnit.FEET).convertTo(LengthUnit.INCHES);
        assertEquals(new Length(12.0, LengthUnit.INCHES), result);
    }

    @Test
    public void testConvertYardsToInches() {
        Length result = new Length(1.0, LengthUnit.YARDS).convertTo(LengthUnit.INCHES);
        assertEquals(new Length(36.0, LengthUnit.INCHES), result);
    }

    @Test
    public void testConvertCentimetersToInches() {
        Length result = new Length(2.54, LengthUnit.CENTIMETERS).convertTo(LengthUnit.INCHES);
        assertEquals(new Length(1.0, LengthUnit.INCHES), result);
    }

    @Test
    public void testAddFeetAndInchesWithTargetFeet() {
        Length result = new Length(1.0, LengthUnit.FEET).add(new Length(12.0, LengthUnit.INCHES), LengthUnit.FEET);
        assertEquals(new Length(2.0, LengthUnit.FEET), result);
    }

    @Test
    public void testAddFeetAndInchesWithTargetInches() {
        Length result = new Length(1.0, LengthUnit.FEET).add(new Length(12.0, LengthUnit.INCHES), LengthUnit.INCHES);
        assertEquals(new Length(24.0, LengthUnit.INCHES), result);
    }

    @Test
    public void testAddFeetAndInchesUsingOverloadedMethod() {
        Length result = new Length(5.0, LengthUnit.FEET).add(new Length(0.0, LengthUnit.INCHES), LengthUnit.FEET);
        assertEquals(new Length(5.0, LengthUnit.FEET), result);
    }

    @Test
    public void testConvertFeetToBaseUnit() {
        assertEquals(144.0, LengthUnit.FEET.convertToBaseUnit(12.0));
    }

    @Test
    public void testConvertInchesToBaseUnit() {
        assertEquals(12.0, LengthUnit.INCHES.convertToBaseUnit(12.0));
    }

    //UC9 Test code is below
    @Test
    public void kilogramEquals1000Grams() {
        Weight oneKilogram = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight thousandGrams = new Weight(1000.0, WeightUnit.GRAM);
        assertEquals(oneKilogram, thousandGrams);
    }

    @Test
    public void poundEquals453Point592Grams() {
        Weight onePound = new Weight(1.0, WeightUnit.POUND);
        Weight grams = new Weight(453.592, WeightUnit.GRAM);
        assertEquals(onePound, grams);

    }

    @Test
    void tonneEquals1000000Grams() {
        Weight oneTonne = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight grams = new Weight(1000, WeightUnit.GRAM);
        assertEquals(oneTonne, grams);
    }

    @Test
    void kilogramNotEqualToPound() {
        Weight oneKilogram = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight onePound = new Weight(1.0, WeightUnit.POUND);
        assertNotEquals(oneKilogram, onePound);

    }

    @Test
    void additionOfWeightEqualsExpected() {
        Weight weight1 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight weight2 = new Weight(500, WeightUnit.GRAM);
        Weight result = weight1.add(weight2, WeightUnit.GRAM);
        assertEquals(new Weight(1500, WeightUnit.GRAM), result);

    }

    @Test
    void testFeetInchesComparision() {
        Length feet = new Length(1, LengthUnit.FEET);
        Length inches = new Length(12, LengthUnit.INCHES);
        assertEquals(feet, inches);

    }

    // Below test case for UC10
    @Test
    void testIMeasurableInterface_LengthUnitImplementation() {
        LengthUnit unit = LengthUnit.FEET;
        assertTrue(unit instanceof IMeasurable);
        assertEquals(12.0, unit.getConversionFactor());
        assertEquals(12.0, unit.convertToBaseUnit(1.0));
        assertEquals(1.0, unit.convertFromBaseUnit(12.0));
    }

    @Test
    void testIMeasurableInterface_WeightUnitImplementation() {
        WeightUnit unit = WeightUnit.KILOGRAM;
        assertTrue(unit instanceof IMeasurable);
        assertEquals(1000.0, unit.getConversionFactor());
        assertEquals(1000.0, unit.convertToBaseUnit(1.0));
        assertEquals(1.0, unit.convertFromBaseUnit(1000.0));
    }

    @Test
    void testIMeasurableInterface_ConsistentBehavior() {
        LengthUnit unit = LengthUnit.FEET;
        WeightUnit kg = WeightUnit.KILOGRAM;
        assertNotNull(unit.getConversionFactor());
        assertNotNull(kg.getConversionFactor());
    }

    @Test
    void testGenericQuantity_LengthOperaions_Equality() {
        Quantity<LengthUnit> length1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> length2 = new Quantity<>(12.0, LengthUnit.INCHES);
        assertTrue(length1.equals(length2));
    }

    @Test
    void testGenericQuantity_WeightOperaions_Equality() {
        Quantity<WeightUnit> weight1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> weight2 = new Quantity<>(1000.0, WeightUnit.GRAM);
        assertTrue(weight1.equals(weight2));
    }

    @Test
    void testGenericQuantity_LengthOperations_Conversion() {
        Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> result = length.convertTo(LengthUnit.INCHES);
        assertEquals(12.0, result.getValue());
        assertEquals(LengthUnit.INCHES, result.getUnit());
    }

    @Test
//7
    void testGenericQuantity_WeightOperations_Conversion() {
        Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> result = weight.convertTo(WeightUnit.GRAM);
        assertEquals(1000.0, result.getValue());
        assertEquals(WeightUnit.GRAM, result.getUnit());
    }

    @Test
//8
    void testGenericQuantity_LengthOperations_Addition() {
        Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> inches = new Quantity<>(12.0, LengthUnit.INCHES);
        Quantity<LengthUnit> result = feet.add(inches);
        assertEquals(2.0, result.getValue());
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
//9
    void testGenericQuantity_WeightOperations_Addition() {
        Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> grams = new Quantity<>(1000.0, WeightUnit.GRAM);
        Quantity<WeightUnit> result = kg.add(grams);
        assertEquals(2.0, result.getValue());
        assertEquals(WeightUnit.KILOGRAM, result.getUnit());
    }

    @Test
//10
    void testCrossCategoryPrevention_LengthVsWeight() {
        Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        assertFalse(length.equals(weight));
    }

    @Test
//11
    void testCrossCategoryPrevention_CompilerTypeSafety() {
        Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        assertTrue(true);
    }

    @Test
//12
    void testGenericQuantity_ConstructorValidation_NullUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Quantity<>(1.0, null);
        });
    }

    @Test
//13
    void testGenericQuantity_ConstructorValidation_InvalidValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Quantity<>(Double.NaN, LengthUnit.FEET);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Quantity<>(Double.POSITIVE_INFINITY, LengthUnit.FEET);
        });
    }

    @Test
//14
    void testGenericQuantity_Conversion_AllUnitCombinations() {
        Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
        assertEquals(12.0, feet.convertTo(LengthUnit.INCHES).getValue());
        assertEquals(0.3333333, feet.convertTo(LengthUnit.YARDS).getValue(), 0.0001);
        Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        assertEquals(1000, kg.convertTo(WeightUnit.GRAM).getValue());
        assertEquals(2.2046226218, kg.convertTo(WeightUnit.POUND).getValue(), 0.01);
    }

    @Test
//15
    void testGenericQuantity_Addition_AllUnitCombinations() {
        Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> inches = new Quantity<>(12.0, LengthUnit.INCHES);
        Quantity<LengthUnit> resultLength = feet.add(inches).convertTo(LengthUnit.FEET);
        assertEquals(2.0, resultLength.getValue(), 0.0001);

        Quantity<WeightUnit> grams = new Quantity<>(1000.0, WeightUnit.GRAM);
        Quantity<WeightUnit> kg = new Quantity<>(12.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> resultWeight = kg.add(grams).convertTo(WeightUnit.KILOGRAM);
        assertEquals(2.0, resultLength.getValue(), 0.0001);

    }

    @Test
//16
    void testBackwardCompatibility_AllUC1ThroughUC9Tests() {
        Quantity<LengthUnit> oneFoot = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> twelveInches = new Quantity<>(12.0, LengthUnit.INCHES);
        assertTrue(oneFoot.equals(twelveInches));

        assertEquals(12.0, oneFoot.convertTo(LengthUnit.INCHES).getValue(), 0.0001);
        Quantity<LengthUnit> twoFeet = oneFoot.add(twelveInches);
        assertEquals(2.0, twoFeet.getValue(), 0.0001);
    }

    @Test
//17
    void testQuantityMeasurementApp_SimplifiedDemonstration_Equality() {
        Quantity<LengthUnit> length1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> length2 = new Quantity<>(12.0, LengthUnit.INCHES);
        Quantity<WeightUnit> weight1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> weight2 = new Quantity<>(1000.0, WeightUnit.GRAM);

        assertTrue(length1.equals(length2));
        assertTrue(weight1.equals(weight2));

    }

    @Test
//18
    void testQuantityMeasurementApp_SimplifiedDemonstration_Conversion() {
        Quantity<LengthUnit> length1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<WeightUnit> weight1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        assertEquals(12.0, length1.convertTo(LengthUnit.INCHES).getValue(), 0.0001);
        assertEquals(1000.0, weight1.convertTo(WeightUnit.GRAM).getValue(), 0.0001);

    }
    //19
    @Test
    void testQuantityMeasurementApp_SimplifiedDemonstration_Addition() {
        Quantity<LengthUnit> lengthResult = new Quantity<>(1.0, LengthUnit.FEET).add(new Quantity<>(12.0,LengthUnit.INCHES));
        assertEquals(2.0,lengthResult.getValue(),0.0001);
        Quantity<WeightUnit> weightResult = new Quantity<>(1.0, WeightUnit.KILOGRAM).add(new Quantity<>(1000.0,WeightUnit.GRAM));
        assertEquals(2.0,weightResult.getValue(),0.0001);

    }
    //20
    @Test
    void testTypeWildcard_FlexibleSignature() {
        Quantity<LengthUnit> length=new Quantity<>(12.0,LengthUnit.FEET);
        Quantity<WeightUnit> weight=new Quantity<>(1000.0,WeightUnit.GRAM);
        assertEquals(12.0,getValue(length));
        assertEquals(1000.0,getValue(weight));
    }
    private  double getValue(Quantity<?> quantity){
        return quantity.getValue();
    }

    //UC 11 Test cases
    public static <U extends IMeasurable> Quantity<U> demonstrateConversion(Quantity<U> quantity,U targetUnit){
        double value=quantity.convertToo(targetUnit);
        return new Quantity<>(value,targetUnit);
    }
    public static <U extends IMeasurable> Quantity<U> demonstrateAddition(Quantity<U> quantity1,Quantity<U> quantity2) {
        return quantity1.add(quantity2);
    }
    public static <U extends IMeasurable> Quantity<U> demonstrateAddition(Quantity<U> quantity1,Quantity<U> quantity2,U targetUnit) {
        return quantity1.add(quantity2);
    }
    @Test
    void testEquality_LitreToLitre_SameValue(){
        Quantity<VolumeUnit> VolumeInLitres1 = new Quantity<>(1, VolumeUnit.LITRE);
        Quantity<VolumeUnit> VolumeInLitres2 = new Quantity<>(1, VolumeUnit.LITRE);
        assertTrue(VolumeInLitres1.equals(VolumeInLitres2));
    }
    @Test
    void testEquality_LitreToLitre_DifferentValue(){
        Quantity<VolumeUnit> VolumeInLitres1 = new Quantity<>(1, VolumeUnit.LITRE);
        Quantity<VolumeUnit> VolumeInLitres2 = new Quantity<>(2, VolumeUnit.LITRE);
        assertFalse(VolumeInLitres1.equals(VolumeInLitres2));
    }
    @Test
    void testEquality_LitreToMillilitre_EquivalentValue(){
        Quantity<VolumeUnit> VolumeInLitres = new Quantity<>(1, VolumeUnit.LITRE);
        Quantity<VolumeUnit> VolumeInMillilitres = new Quantity<>(1000, VolumeUnit.MILLILITRE);
        assertTrue(VolumeInLitres.equals(VolumeInMillilitres));
    }
    @Test
    void testEquality_MillilitreToLitre_EquivalentValue(){
        Quantity<VolumeUnit> VolumeInLitres = new Quantity<>(1, VolumeUnit.LITRE);
        Quantity<VolumeUnit> VolumeInMillilitres = new Quantity<>(1000, VolumeUnit.MILLILITRE);
        assertTrue(VolumeInMillilitres.equals(VolumeInLitres));
    }
    @Test
    void testEquality_LitreToGallon_EquivalentValue(){
        Quantity<VolumeUnit> VolumeInLitres = new Quantity<>(1, VolumeUnit.LITRE);
        Quantity<VolumeUnit> VolumeInGallon = new Quantity<>(0.264172, VolumeUnit.GALLON);
        assertTrue(VolumeInLitres.equals(VolumeInGallon));
    }
    @Test
    void testEquality_GallonToLitre_EquivalentValue(){
        Quantity<VolumeUnit> VolumeInGallon = new Quantity<>(1, VolumeUnit.GALLON);
        Quantity<VolumeUnit> VolumeInLitres = new Quantity<>(3.78541, VolumeUnit.LITRE);
        assertTrue(VolumeInGallon.equals(VolumeInLitres));
    }
    @Test
    void testEquality_VolumeVsLength_Incompatible(){
        Quantity<VolumeUnit> VolumeInLitre = new Quantity<>(1, VolumeUnit.LITRE);
        Quantity<LengthUnit> LengthInFeet = new Quantity<>(1, LengthUnit.FEET);
        assertFalse(VolumeInLitre.equals(LengthInFeet));
    }
    @Test
    void testEquality_VolumeVsWeight_Incompatible(){
        Quantity<VolumeUnit> VolumeInLitre = new Quantity<>(1, VolumeUnit.LITRE);
        Quantity<WeightUnit> WeightInKilogram = new Quantity<>(1, WeightUnit.KILOGRAM);
        assertFalse(VolumeInLitre.equals(WeightInKilogram));
    }
    @Test
    void testEquality_NullComparison(){
        Quantity<VolumeUnit> VolumeInLitre = new Quantity<>(1, VolumeUnit.LITRE);
        assertFalse(VolumeInLitre.equals(null));
    }
    @Test
    void testEquality_SameReference(){
        Quantity<VolumeUnit> VolumeInLitre = new Quantity<>(1, VolumeUnit.LITRE);
        assertTrue(VolumeInLitre.equals(VolumeInLitre));
    }
    @Test
    void testEquality_NullUnit(){
        assertThrows(IllegalArgumentException.class, () ->{
            Quantity<VolumeUnit> VolumeInLitre = new Quantity<>(1, null);
        });
    }
    @Test
    void testEquality_TransitiveProperty(){
        Quantity<VolumeUnit> VolumeInLitre = new Quantity<>(1, VolumeUnit.LITRE);
        Quantity<VolumeUnit> VolumeInMillilitres = new Quantity<>(1000, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> VolumeInLitre1 = new Quantity<>(1, VolumeUnit.LITRE);
        assertTrue(VolumeInLitre.equals(VolumeInMillilitres));
        assertTrue(VolumeInMillilitres.equals(VolumeInLitre1));
        assertTrue(VolumeInLitre.equals(VolumeInLitre1));
    }
    @Test
    void testEquality_ZeroValue(){
        Quantity<VolumeUnit> VolumeInLitre = new Quantity<>(0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> VolumeInMillilitres = new Quantity<>(0, VolumeUnit.MILLILITRE);
        assertTrue(VolumeInLitre.equals(VolumeInMillilitres));
    }
    @Test
    void testEquality_NegativeVolume(){
        Quantity<VolumeUnit> VolumeInLitre = new Quantity<>(0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> VolumeInMillilitres = new Quantity<>(0, VolumeUnit.MILLILITRE);
        assertTrue(VolumeInLitre.equals(VolumeInMillilitres));
    }
    @Test
    void testEquality_LargeVolumeValue(){
        Quantity<VolumeUnit> VolumeInLitre = new Quantity<>(1000, VolumeUnit.LITRE);
        Quantity<VolumeUnit> VolumeInMillilitres = new Quantity<>(1000000, VolumeUnit.MILLILITRE);
        assertTrue(VolumeInLitre.equals(VolumeInMillilitres));
    }
    @Test
    void testEquality_SmallVolumeValue(){
        Quantity<VolumeUnit> VolumeInLitre = new Quantity<>(0.001, VolumeUnit.LITRE);
        Quantity<VolumeUnit> VolumeInMillilitres = new Quantity<>(1, VolumeUnit.MILLILITRE);
        assertTrue(VolumeInLitre.equals(VolumeInMillilitres));
    }
    @Test
    void testConversion_LitreToMillilitre(){
        Quantity<VolumeUnit> VolumeInLitre = new Quantity<>(1.0, VolumeUnit.LITRE);
        assertEquals(new Quantity<>(1000, VolumeUnit.MILLILITRE),demonstrateConversion(VolumeInLitre, VolumeUnit.MILLILITRE));
        assertEquals(new Quantity<>(1000, VolumeUnit.MILLILITRE),demonstrateConversion(VolumeInLitre, VolumeUnit.MILLILITRE));

    }
    @Test
    void testConversion_MillilitreToLitre(){
        Quantity<VolumeUnit> VolumeInMillilitres = new Quantity<>(1000, VolumeUnit.MILLILITRE);
        assertEquals(new Quantity<>(1, VolumeUnit.LITRE),demonstrateConversion(VolumeInMillilitres, VolumeUnit.LITRE));
    }
    @Test
    void testConversion_GallonToLitre(){
        Quantity<VolumeUnit> VolumeInGallon = new Quantity<>(1.0, VolumeUnit.GALLON);
        assertEquals(new Quantity<>(3.78541, VolumeUnit.LITRE),demonstrateConversion(VolumeInGallon, VolumeUnit.LITRE));
    }
    @Test
    void testConversion_LitreToGallon(){
        Quantity<VolumeUnit> VolumeInLitre = new Quantity<>(3.78541, VolumeUnit.LITRE);
        assertEquals(new Quantity<>(1, VolumeUnit.GALLON),demonstrateConversion(VolumeInLitre, VolumeUnit.GALLON));
    }

    @Test
    void testConversion_SameUnit(){
        Quantity<VolumeUnit> VolumeInLitre = new Quantity<>(5, VolumeUnit.LITRE);
        assertEquals(new Quantity<>(5, VolumeUnit.LITRE),demonstrateConversion(VolumeInLitre, VolumeUnit.LITRE));
    }
    @Test
    void testConversion_ZeroValue(){
        Quantity<VolumeUnit> VolumeInLitre = new Quantity<>(0, VolumeUnit.LITRE);
        assertEquals(new Quantity<>(0, VolumeUnit.MILLILITRE),demonstrateConversion(VolumeInLitre, VolumeUnit.MILLILITRE));
    }
    @Test
    void testConversion_NegativeValue(){
        Quantity<VolumeUnit> VolumeInLitre = new Quantity<>(-1.0, VolumeUnit.LITRE);
        assertEquals(new Quantity<>(-1000.0, VolumeUnit.MILLILITRE),demonstrateConversion(VolumeInLitre, VolumeUnit.MILLILITRE));
    }
    @Test
    void testConversion_RoundTrip(){
        Quantity<VolumeUnit> VolumeInLitre = new Quantity<>(1.5, VolumeUnit.LITRE);
        Quantity<VolumeUnit> VolumeInMillilitre = demonstrateConversion(VolumeInLitre,VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> convertToLitre = demonstrateConversion(VolumeInMillilitre,VolumeUnit.MILLILITRE);
        assertEquals(VolumeInLitre,convertToLitre);
    }
    @Test
    void testAddition_SameUnit_LitrePlusLitre(){
        Quantity<VolumeUnit> value1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> value2 = new Quantity<>(2.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> sumOfValue = demonstrateAddition(value1,value2);
        assertEquals(new Quantity<>(3.0, VolumeUnit.LITRE), sumOfValue);
    }
    @Test
    void testAddition_SameUnit_MillilitrePlusMillilitre(){
        Quantity<VolumeUnit> value1 = new Quantity<>(500.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> value2 = new Quantity<>(500.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> sumOfValue = demonstrateAddition(value1,value2);
        assertEquals(new Quantity<>(1000.0, VolumeUnit.MILLILITRE), sumOfValue);
    }
    @Test
    void testAddition_SameUnit_LitrePlusMillilitre(){
        Quantity<VolumeUnit> value1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> value2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> sumOfValue = demonstrateAddition(value1,value2);
        assertEquals(new Quantity<>(2.0, VolumeUnit.LITRE), sumOfValue);
    }
    @Test
    void testAddition_SameUnit_MillilitrePlusLitre(){
        Quantity<VolumeUnit> value1 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> value2 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> sumOfValue = demonstrateAddition(value1,value2);
        assertEquals(new Quantity<>(2000.0, VolumeUnit.MILLILITRE), sumOfValue);
    }
    @Test
    void testAddition_CrossUnit_GallonPlusLitre(){
        Quantity<VolumeUnit> value1 = new Quantity<>(1.0, VolumeUnit.GALLON);
        Quantity<VolumeUnit> value2 = new Quantity<>(3.78541, VolumeUnit.LITRE);
        Quantity<VolumeUnit> sumOfValue = demonstrateAddition(value1,value2);
        assertEquals(new Quantity<>(2.0, VolumeUnit.GALLON), sumOfValue);
    }
    @Test
    void testAddition_ExplicitTargetUnit_Litre(){
        Quantity<VolumeUnit> value1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> value2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> sumOfValue = demonstrateAddition(value1,value2,VolumeUnit.LITRE);
        assertEquals(new Quantity<>(2.0, VolumeUnit.LITRE), sumOfValue);
    }
    @Test
    void testAddition_ExplicitTargetUnit_Millilitre(){
        Quantity<VolumeUnit> value1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> value2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> sumOfValue = demonstrateAddition(value1,value2,VolumeUnit.MILLILITRE);
        assertEquals(new Quantity<>(2000.0, VolumeUnit.MILLILITRE), sumOfValue);
    }
    @Test
    void testAddition_ExplicitTargetUnit_Gallon(){
        Quantity<VolumeUnit> value1 = new Quantity<>(3.78541, VolumeUnit.LITRE);
        Quantity<VolumeUnit> value2 = new Quantity<>(3.78541, VolumeUnit.LITRE);
        Quantity<VolumeUnit> sumOfValue = demonstrateAddition(value1,value2,VolumeUnit.GALLON);
        assertEquals(new Quantity<>(2.0, VolumeUnit.GALLON), sumOfValue);
    }
    @Test
    void testAddition_Commutativity(){
        Quantity<VolumeUnit> value1 = new Quantity<>(1, VolumeUnit.LITRE);
        Quantity<VolumeUnit> value2 = new Quantity<>(1000, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> sumOfValue1 = demonstrateAddition(value1,value2);
        Quantity<VolumeUnit> sumOfValue2 = demonstrateAddition(value2,value1);
        assertEquals(sumOfValue1,sumOfValue2);
    }
    @Test
    void testAddition_WithZero(){
        Quantity<VolumeUnit> value1 = new Quantity<>(5, VolumeUnit.LITRE);
        Quantity<VolumeUnit> value2 = new Quantity<>(0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> sumOfValue1 = demonstrateAddition(value1,value2);
        assertEquals(value1,sumOfValue1);
    }
    @Test
    void testAddition_NegativeValues(){
        Quantity<VolumeUnit> value1 = new Quantity<>(5, VolumeUnit.LITRE);
        Quantity<VolumeUnit> value2 = new Quantity<>(-2000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> sumOfValue1 = demonstrateAddition(value1,value2);
        assertEquals(new Quantity<>(3, VolumeUnit.LITRE),sumOfValue1);
    }
    @Test
    void testAddition_LargeValues(){
        Quantity<VolumeUnit> value1 = new Quantity<>(1e6, VolumeUnit.LITRE);
        Quantity<VolumeUnit> value2 = new Quantity<>(1e6, VolumeUnit.LITRE);
        Quantity<VolumeUnit> sumOfValue1 = demonstrateAddition(value1,value2);
        assertEquals(new Quantity<>(2e6, VolumeUnit.LITRE),sumOfValue1);
    }
    @Test
    void testAddition_SmallValues(){
        Quantity<VolumeUnit> value1 = new Quantity<>(0.001, VolumeUnit.LITRE);
        Quantity<VolumeUnit> value2 = new Quantity<>(0.002, VolumeUnit.LITRE);
        Quantity<VolumeUnit> sumOfValue1 = demonstrateAddition(value1,value2);
        assertEquals(new Quantity<>(0.003, VolumeUnit.LITRE),sumOfValue1);
    }
    @Test
    void testVolumeUnitEnum_LitreConstant(){
        assertEquals(1.0,VolumeUnit.LITRE.getConversionFactor());
    }
    @Test
    void testVolumeUnitEnum_MillilitreConstant(){
        assertEquals(0.001,VolumeUnit.MILLILITRE.getConversionFactor());
    }
    @Test
    void testVolumeUnitEnum_GallonConstant(){
        assertEquals(3.78541,VolumeUnit.GALLON.getConversionFactor());
    }
    @Test
    void testConvertToBaseUnit_LitreToLitre(){
        assertEquals(5.0,VolumeUnit.LITRE.convertToBaseUnit(5.0));
    }
    @Test
    void testConvertToBaseUnit_MillilitreToLitre(){
        assertEquals(1.0,VolumeUnit.MILLILITRE.convertToBaseUnit(1000.0));
    }
    @Test
    void testConvertToBaseUnit_GallonToLitre(){
        assertEquals(3.78541,VolumeUnit.GALLON.convertToBaseUnit(1.0));
    }
    @Test
    void testConvertFromBaseUnit_LitreToLitre(){
        assertEquals(2.0,VolumeUnit.LITRE.convertFromBaseUnit(2.0));
    }
    @Test
    void testConvertFromBaseUnit_LitreToMillilitre(){
        assertEquals(1000.0,VolumeUnit.MILLILITRE.convertFromBaseUnit(1.0));
    }
    @Test
    void testConvertFromBaseUnit_LitreToGallon(){
        assertEquals(1.0,VolumeUnit.GALLON.convertFromBaseUnit(3.78541));
    }
}


