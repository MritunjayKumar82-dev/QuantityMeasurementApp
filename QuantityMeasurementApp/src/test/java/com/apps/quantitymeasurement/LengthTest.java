package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LengthTest {
    @Test
    void givenFeetAndInches_shouldReturnEqual(){
        assertEquals(new Length(1.0,LengthUnit.FEET),new Length(12.0,LengthUnit.INCHES));
    }
    @Test
    void givenYardAndFeet_shouldReturnEqual(){
        assertEquals(new Length(1.0,LengthUnit.YARDS),new Length(3.0,LengthUnit.FEET));
    }
    @Test
    void givenYardAndInches_shouldReturnEqual(){
        assertEquals(new Length(1.0,LengthUnit.YARDS),new Length(36.0,LengthUnit.INCHES));

    }
    @Test
    void givenCentimeterAndInches_shouldReturnEqual(){
        assertEquals(new Length(1.0,LengthUnit.CENTIMETERS),new Length(0.393701,LengthUnit.INCHES));

    }
    @Test
    void givenSameCentimeters_shouldReturnEqual(){
        assertEquals(new Length(2.0,LengthUnit.CENTIMETERS),new Length(2.0,LengthUnit.CENTIMETERS));

    }
    @Test
    void givenDifferentLengths_shouldReturnNotEqual(){
        assertNotEquals(new Length(1.0,LengthUnit.FEET),new Length(1.0,LengthUnit.INCHES));
    }
    @Test
    void givenNullComparison_shouldReturnFalse(){
        assertFalse(new Length(1.0,LengthUnit.FEET).compare(null));

    }

}
