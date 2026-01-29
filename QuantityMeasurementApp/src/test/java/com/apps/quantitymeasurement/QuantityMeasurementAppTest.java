package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {
    @Test
    void testFeetEquality_SameValue(){
        QuantityMeasurementApp.Feet feet1=new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet feet2=new QuantityMeasurementApp.Feet(1.0);
        Assertions.assertEquals(feet1, feet2);
    }
    @Test
    void testFeetEquality_DifferentValue(){
        QuantityMeasurementApp.Feet feet1=new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet feet2=new QuantityMeasurementApp.Feet(2.0);
        assertNotEquals(feet1, feet2);
    }
    @Test
    void testFeetEquality_NullComparison(){
        QuantityMeasurementApp.Feet feet1=new QuantityMeasurementApp.Feet(1.0);
        assertNotEquals(null, feet1);
    }
    @Test
    void testFeetEquality_differentType(){
        QuantityMeasurementApp.Feet feet1=new QuantityMeasurementApp.Feet(1.0);
        assertNotEquals("1.0", feet1);
    }

    @Test
    void testFeetEquality_SameReference(){
        QuantityMeasurementApp.Feet feet1=new QuantityMeasurementApp.Feet(1.0);
        Assertions.assertEquals(feet1, feet1);
    }
    @Test
    void testConstructor_NullValue_ShouldThrowException(){
        Exception exception=assertThrows(IllegalArgumentException.class,()->new QuantityMeasurementApp.Feet(null));
       assertEquals("Feet Value cannot be null",exception.getMessage());
    }
    @Test
    void testHashCode_Consistency(){
        QuantityMeasurementApp.Feet f1=new QuantityMeasurementApp.Feet( 1.0);
        QuantityMeasurementApp.Feet f2=new QuantityMeasurementApp.Feet(1.0);
        assertEquals(f1.hashCode(),f2.hashCode());
    }



}
