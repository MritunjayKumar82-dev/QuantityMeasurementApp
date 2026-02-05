package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
  /*  @Test
    public void differentValuesSameUnitNotEqual(){
        assertFalse(new Length(2.0,LengthUnit.FEET).equals(new Length(1.0,LengthUnit.FEET)));
    }
    @Test
    public void differentUnitsNotEqual(){
        assertFalse(new Length(1.0,LengthUnit.FEET).equals(new Length(10.0,LengthUnit.INCHES)));

    }*/
    @Test
    public void threeFeetEqualsOneYard(){
        assertTrue(new Length(3.0,LengthUnit.FEET).equals(new Length(1.0,LengthUnit.YARDS)));

    }
    /*@Test
    public void yardNotEqualToInches(){
        assertFalse(new Length(1.0,LengthUnit.YARDS).equals(new Length(1.0,LengthUnit.INCHES)));

    }
    @Test
    public void centimeterNotEqualToFeet(){
        assertFalse(new Length(30.48,LengthUnit.CENTIMETERS).equals(new Length(2.0,LengthUnit.INCHES)));

    }*/
   /* @Test
    public void centimeterNotEqualToInches(){
        assertFalse(new Length(2.0,LengthUnit.CENTIMETERS).equals(new Length(2.0,LengthUnit.INCHES)));

    }

    @Test
    public void testInchesInEquality(){
        assertNotEquals(new Length(12.0,LengthUnit.INCHES),(new Length(24.0,LengthUnit.INCHES)));
    }*/
    /*@Test
    public void testCrossUnitInEquality(){
        assertNotEquals(new Length(1.0,LengthUnit.FEET),(new Length(1.0,LengthUnit.YARDS)));
    }*/
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
    /*@Test
    public void testInvalidNumericValue(){
        assertThrows(IllegalArgumentException.class,()->new Length(Double.NaN,LengthUnit.FEET));
    }*/
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
   /* @Test
    public void convertFeetToCentimeters(){
        Length feet=new Length(1.0,LengthUnit.FEET);
        Length cm=feet.convertTo(LengthUnit.CENTIMETERS);
        assertEquals(30.48,cm.getValue());

    }*/
    /*@Test
    public void addFeetAndInches(){
        Length length1=new Length(1.0,LengthUnit.FEET);
        Length length2=new Length(12.0,LengthUnit.INCHES);
        Length sum=length1.add(length2);
        Length expected=new Length(2.0,LengthUnit.FEET);
        assertTrue(sum.equals(expected));

    }
    @Test
    public void addFeetAndCentimeters(){
        Length length1=new Length(1.0,LengthUnit.FEET);
        Length length2=new Length(12.0,LengthUnit.INCHES);
        Length sum=length1.add(length2);
        Length expected=new Length(2.0,LengthUnit.FEET);
        assertTrue(sum.equals(expected));

    }*/
    /*@Test
    public void addInchesAndFeet(){
        Length feet=new Length(1.0,LengthUnit.FEET);
        Length inches=new Length(12.0,LengthUnit.INCHES);
        Length sum=inches.add(feet);
        Length expected=new Length(24.0,LengthUnit.INCHES);
        assertTrue(sum.equals(expected));

    }
    @Test
    public void addZeroInchesToFeet(){
        Length zeroInches=new Length(0.0,LengthUnit.INCHES);
        Length feet=new Length(12.0,LengthUnit.FEET);
        Length sum=feet.add(zeroInches);
        assertTrue(sum.equals(feet));

    }*/

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

}


