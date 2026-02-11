package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuantityMeasurementAppTest {
    @Test
    public void testFeetEquality(){
        assertTrue(new Length(1.0,LengthUnit.FEET).equals(new Length(12.0,LengthUnit.INCHES)));
    }
    @Test
    public void testInchesEquality(){
        assertTrue(new Length(12.0,LengthUnit.INCHES).equals(new Length(1.0,LengthUnit.FEET)));
    }
    @Test
    public void testFeetInchesComparison(){
        assertTrue(new Length(1.0,LengthUnit.FEET).equals(new Length(12.0,LengthUnit.INCHES)));

    }
    @Test
    public void testreflexiveSymmetricAndTransitiveProperty(){
        Length feet=new Length(1.0,LengthUnit.FEET);
        Length inches=new Length(12.0,LengthUnit.INCHES);
        Length yards=new Length(1.0/3.0,LengthUnit.YARDS);
        assertTrue(feet.equals(feet));
        assertTrue(feet.equals(inches));
        assertTrue(inches.equals(feet));
        assertTrue(feet.equals(inches));
        assertTrue(inches.equals(yards));
        assertTrue(feet.equals(yards));
    }
    @Test
    public void referenceEqualitySameObject(){
        Length length=new Length(1.0,LengthUnit.FEET);
        assertTrue(length.equals(length));
    }
    @Test
    public void equalsReturnsFalseForNull(){
        Length length=new Length(1.0,LengthUnit.FEET);
        assertFalse(length.equals(null));
    }

    @Test
    public void threeFeetEqualsOneYard(){
        assertTrue(new Length(3.0,LengthUnit.FEET).equals(new Length(1.0,LengthUnit.YARDS)));

    }


    @Test
    public void testMultipleFeetComparison(){
        Length result=QuantityMeasurementApp.demonstrateLengthConversion(new Length(3.0,LengthUnit.FEET),LengthUnit.YARDS);
        assertEquals(new Length(1.0,LengthUnit.YARDS),result);
    }
    @Test
    public void yardEquals36Inches(){
        assertEquals(new Length(1.0,LengthUnit.YARDS),new Length(36.0,LengthUnit.INCHES));
    }
    @Test
    public void testEqualsReturnsFalseForNull(){
        assertFalse(new Length(1.0,LengthUnit.FEET).equals(null));
    }

    @Test
    public void testNullUnit(){
        assertThrows(IllegalArgumentException.class,()->new Length(1.0,null));
    }
    // UC6 Test cases
    @Test
    public void testMultiFeetComparision(){
        Length l1=new Length(2.0,LengthUnit.FEET);
        Length l2=new Length(2.0,LengthUnit.FEET);
        assertTrue((l1.equals(l2)));
    }
    @Test
    public void centimeterEquals39Point3701Inches(){
        Length cm=new Length(1.0,LengthUnit.CENTIMETERS);
        Length inches=new Length(0.393701,LengthUnit.INCHES);
        assertTrue((cm.equals(inches)));
    }
    @Test
    public void testFeetEqualsOneYard(){
        Length feet=new Length(3.0,LengthUnit.FEET);
        Length yard=new Length(1.0,LengthUnit.YARDS);
        assertTrue((feet.equals(yard)));
    }
    @Test
    public void thirtyPoint48CmEqualsOneFoot(){
        Length cm=new Length(30.48,LengthUnit.CENTIMETERS);
        Length feet=new Length(1.0,LengthUnit.FEET);
        assertTrue((cm.equals(feet)));
    }
    @Test
    public void reflexiveSymmetricAndTransitiveProperty(){
        Length l1=new Length(1.0,LengthUnit.FEET);
        Length l2=new Length(12.0,LengthUnit.INCHES);
        Length l3=new Length(30.48,LengthUnit.CENTIMETERS);
        assertTrue((l1.equals(l2)));
        assertTrue((l2.equals(l3)));
        assertTrue((l1.equals(l3)));
    }
    @Test
    public void convertYardsToInchesUsingOverloadMethod(){
        Length yards=new Length(1.0,LengthUnit.YARDS);
        Length inches=yards.convertTo(LengthUnit.INCHES);
        assertEquals(36.0,inches.getValue());

    }

    /*test case for UC7*/
    @Test
    public void addFeetAndInchesReturnFeet(){
        Length length1=new Length(3.0,LengthUnit.FEET);
        Length length2=new Length(12.0,LengthUnit.INCHES);
        Length result=QuantityMeasurementApp.demonstrateLengthAddition(length1,length2,LengthUnit.FEET);
        assertTrue((result.equals(new Length(4.0,LengthUnit.FEET))));
    }
    @Test
    public void addFeetAndInchesReturnInches(){
        Length length1=new Length(1.0,LengthUnit.FEET);
        Length length2=new Length(12.0,LengthUnit.INCHES);
        Length result=QuantityMeasurementApp.demonstrateLengthAddition(length1,length2,LengthUnit.INCHES);
        assertTrue((result.equals(new Length(24.0,LengthUnit.INCHES))));

    }
    @Test
    public void addFeetAndCentimetersReturnFeet(){
        Length length1=new Length(1.0,LengthUnit.FEET);
        Length length2=new Length(30.48,LengthUnit.CENTIMETERS);
        Length result=QuantityMeasurementApp.demonstrateLengthAddition(length1,length2,LengthUnit.FEET);
        assertTrue((result.equals(new Length(2.0,LengthUnit.FEET))));

    }
    @Test
    public void addInchesAndFeetReturnInches(){
        Length length1=new Length(12.0,LengthUnit.INCHES);
        Length length2=new Length(1.0,LengthUnit.FEET);
        Length result=QuantityMeasurementApp.demonstrateLengthAddition(length1,length2,LengthUnit.INCHES);
        assertTrue((result.equals(new Length(24.0,LengthUnit.INCHES))));

    }
    @Test
    public void addFeetAndFeetReturnFeet(){
        Length length1=new Length(2.0,LengthUnit.FEET);
        Length length2=new Length(3.0,LengthUnit.FEET);
        Length result=QuantityMeasurementApp.demonstrateLengthAddition(length1,length2,LengthUnit.FEET);
        assertTrue((result.equals(new Length(5.0,LengthUnit.FEET))));

    }
    @Test
    public void testInchesInequality(){
        Length result=QuantityMeasurementApp.demonstrateLengthConversion(new Length(3.0,LengthUnit.FEET),LengthUnit.YARDS);
        assertNotEquals(new Length(12.0,LengthUnit.INCHES),new Length(2.0,LengthUnit.FEET));
    }
    @Test
    public void testCrossUnitInequality(){
        assertNotEquals(new Length(1.0,LengthUnit.FEET),new Length(1.0,LengthUnit.YARDS));
    }
    @Test
    public void tsetReflexiveSymmetricAndTransitiveProperty(){
       Length oneFoot=new Length(1.0,LengthUnit.FEET);
       Length twelveInches=new Length(12.0,LengthUnit.INCHES);
       Length oneThirdYard=new Length(0.333333,LengthUnit.YARDS);
       assertEquals(oneFoot,oneFoot);
       assertEquals(oneFoot,twelveInches);
       assertEquals(twelveInches,oneFoot);
       assertEquals(oneFoot,oneThirdYard);
    }
    @Test
    public void differentValuesSameUnitNotEqual(){
        assertNotEquals(new Length(1.0,LengthUnit.FEET),new Length(2.0,LengthUnit.FEET));
    }
    @Test
    public void testThreeFeetEqualsOneYard(){
        assertEquals(new Length(3.0,LengthUnit.FEET),new Length(1.0,LengthUnit.YARDS));
    }
    @Test
    public void testThirtySixInchesEqualsOneYard(){
        assertEquals(new Length(36.0,LengthUnit.INCHES),new Length(1.0,LengthUnit.YARDS));
    }
    @Test
    public void testCentimeterEqualsInchesWithinEpsilon(){
        assertEquals(new Length(2.54,LengthUnit.CENTIMETERS),new Length(1.0,LengthUnit.INCHES));
    }
    @Test
    public void testConvertFeetToInches(){
        Length result=new Length(1.0,LengthUnit.FEET).convertTo(LengthUnit.INCHES);
        assertEquals(new Length(12.0,LengthUnit.INCHES),result);
    }
    @Test
    public void testConvertYardsToInches(){
        Length result=new Length(1.0,LengthUnit.YARDS).convertTo(LengthUnit.INCHES);
        assertEquals(new Length(36.0,LengthUnit.INCHES),result);
    }
    @Test
    public void testConvertCentimetersToInches(){
        Length result=new Length(2.54,LengthUnit.CENTIMETERS).convertTo(LengthUnit.INCHES);
        assertEquals(new Length(1.0,LengthUnit.INCHES),result);
    }
    @Test
    public void testAddFeetAndInchesWithTargetFeet(){
        Length result=new Length(1.0,LengthUnit.FEET).add(new Length(12.0,LengthUnit.INCHES),LengthUnit.FEET);
        assertEquals(new Length(2.0,LengthUnit.FEET),result);
    }
    @Test
    public void testAddFeetAndInchesWithTargetInches(){
        Length result=new Length(1.0,LengthUnit.FEET).add(new Length(12.0,LengthUnit.INCHES),LengthUnit.INCHES);
        assertEquals(new Length(24.0,LengthUnit.INCHES),result);
    }
    @Test
    public void testAddFeetAndInchesUsingOverloadedMethod(){
        Length result=new Length(5.0,LengthUnit.FEET).add(new Length(0.0,LengthUnit.INCHES),LengthUnit.FEET);
        assertEquals(new Length(5.0,LengthUnit.FEET),result);
    }
    @Test
    public void testConvertFeetToBaseUnit(){
        assertEquals(144.0,LengthUnit.FEET.convertToBaseUnit(12.0));
    }
    @Test
    public void testConvertInchesToBaseUnit(){
        assertEquals(12.0,LengthUnit.INCHES.convertToBaseUnit(12.0));
    }
     //UC9 Test code is below
     @Test
     public void kilogramEquals1000Grams(){
         Weight oneKilogram=new Weight(1.0,WeightUnit.KILOGRAM);
         Weight thousandGrams=new Weight(1000.0,WeightUnit.GRAM);
         assertEquals(oneKilogram,thousandGrams);
     }
     @Test
    public void poundEquals453Point592Grams(){
        Weight onePound=new Weight(1.0,WeightUnit.POUND);
        Weight grams=new Weight(453.592,WeightUnit.GRAM);
        assertEquals(onePound,grams);

     }
     @Test
     void tonneEquals1000000Grams(){
        Weight oneTonne=new Weight(1.0,WeightUnit.KILOGRAM);
        Weight grams=new Weight(1000,WeightUnit.GRAM);
        assertEquals(oneTonne,grams);
     }
     @Test
    void kilogramNotEqualToPound(){
        Weight oneKilogram=new Weight(1.0,WeightUnit.KILOGRAM);
        Weight onePound=new Weight(1.0,WeightUnit.POUND);
        assertNotEquals(oneKilogram,onePound);

     }
     @Test
     void additionOfWeightEqualsExpected(){
         Weight weight1=new Weight(1.0,WeightUnit.KILOGRAM);
         Weight weight2=new Weight(500,WeightUnit.GRAM);
         Weight result=weight1.add(weight2,WeightUnit.GRAM);
         assertEquals(new Weight(1500,WeightUnit.GRAM),result);

     }

    @Test
    void testFeetInchesComparision(){
        Length feet=new Length(1,LengthUnit.FEET);
        Length inches=new Length(12,LengthUnit.INCHES);
        assertEquals(feet,inches);

    }
// Below test case for UC10
@Test
void testIMeasurableInterface_LengthUnitImplementation(){
    LengthUnit unit=LengthUnit.FEET;
    assertTrue(unit instanceof IMeasurable);
    assertEquals(12.0,unit.getConversionFactor());
    assertEquals(12.0,unit.convertToBaseUnit(1.0));
    assertEquals(1.0,unit.convertFromBaseUnit(12.0));
}
    @Test
    void testIMeasurableInterface_WeightUnitImplementation(){
        WeightUnit unit=WeightUnit.KILOGRAM;
        assertTrue(unit instanceof IMeasurable);
        assertEquals(1000.0,unit.getConversionFactor());
        assertEquals(1000.0,unit.convertToBaseUnit(1.0));
        assertEquals(1.0,unit.convertFromBaseUnit(1000.0));
    }
    @Test
    void testIMeasurableInterface_ConsistentBehavior(){
        LengthUnit unit=LengthUnit.FEET;
        WeightUnit kg=WeightUnit.KILOGRAM;
        assertNotNull(unit.getConversionFactor());
        assertNotNull(kg.getConversionFactor());
    }
    /*@Test
    void testGenericQuantity_LengthOperaions_Equality(){
        //Quantity<LengthUnit> length1=new Q
    }*/



}


