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
    @Test
    public void differentValuesSameUnitNotEqual(){
        assertFalse(new Length(2.0,LengthUnit.FEET).equals(new Length(1.0,LengthUnit.FEET)));
    }
    @Test
    public void differentUnitsNotEqual(){
       assertFalse(new Length(1.0,LengthUnit.FEET).equals(new Length(10.0,LengthUnit.INCHES)));

    }
    @Test
    public void threeFeetEqualsOneYard(){
        assertTrue(new Length(3.0,LengthUnit.FEET).equals(new Length(1.0,LengthUnit.YARDS)));

    }
    @Test
    public void yardNotEqualToInches(){
        assertFalse(new Length(1.0,LengthUnit.YARDS).equals(new Length(1.0,LengthUnit.INCHES)));

    }
    @Test
    public void centimeterNotEqualToFeet(){
        assertFalse(new Length(30.48,LengthUnit.CENTIMETERS).equals(new Length(2.0,LengthUnit.INCHES)));

    }
    @Test
    public void centimeterNotEqualToInches(){
        assertFalse(new Length(2.0,LengthUnit.CENTIMETERS).equals(new Length(2.0,LengthUnit.INCHES)));

    }

}
