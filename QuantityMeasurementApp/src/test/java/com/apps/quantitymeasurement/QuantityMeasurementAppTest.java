package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    void testFeetEquality_SameValue(){
        QuantityMeasurementApp.Feet feet1=new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet feet2=new QuantityMeasurementApp.Feet(1.0);
        assertEquals(feet1,feet2);
    }
    @Test
    void testFeetEquality_DifferentValue(){
        QuantityMeasurementApp.Feet feet1=new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet feet2=new QuantityMeasurementApp.Feet(2.0);
        assertNotEquals(feet1,feet2);
    }
    @Test
    void testFeetEquality_NullComparison(){
        QuantityMeasurementApp.Feet feet1=new QuantityMeasurementApp.Feet(1.0);
        assertNotEquals(feet1,null);
    }
    void testFeetEquality_DifferentClass(){

    }

    @Test
    void testFeetEquality_SameReference(){
        QuantityMeasurementApp.Feet f1=new QuantityMeasurementApp.Feet(1.0);
        assertEquals(f1,f1);
    }

    @Test
    void testInchesEquality_SameValue(){
        QuantityMeasurementApp.Inches i1=new QuantityMeasurementApp.Inches(1.0);
        QuantityMeasurementApp.Inches i2= new QuantityMeasurementApp.Inches(1.0);
        assertEquals(i1,i2);

    }
    @Test
    void testInchesEquality_DifferentValue(){
        QuantityMeasurementApp.Inches i1=new QuantityMeasurementApp.Inches(1.0);
        QuantityMeasurementApp.Inches i2= new QuantityMeasurementApp.Inches(2.0);
        assertNotEquals(i1,i2);
    }
    @Test
    void testInchesEquality_NullComparison(){
        QuantityMeasurementApp.Inches i1=new QuantityMeasurementApp.Inches(1.0);
        assertNotEquals(i1,null);
    }

    @Test
    void testInchesEquality_DifferentClass(){
        QuantityMeasurementApp.Inches inches= new QuantityMeasurementApp.Inches(1.0);
        QuantityMeasurementApp.Feet feet=new QuantityMeasurementApp.Feet(1.0);
        assertNotEquals(inches,feet);
    }
    @Test
    void testInchesEquality_SameReference(){
        QuantityMeasurementApp.Inches inches=new QuantityMeasurementApp.Inches(1.0);
        assertEquals(inches,inches);
    }
    @Test
    void testFeetToInchesEquality(){
        QuantityMeasurementApp.Inches i1=new QuantityMeasurementApp.Inches(12.0);
        QuantityMeasurementApp.Feet f1=new QuantityMeasurementApp.Feet(1.0);
        assertEquals(f1.toInches(),i1.toInches());
    }
    @Test
    void testFeetAndInches_NotEqual(){
        QuantityMeasurementApp.Inches i1=new QuantityMeasurementApp.Inches(13.0);
        QuantityMeasurementApp.Feet f1=new QuantityMeasurementApp.Feet(1.0);
        assertNotEquals(f1.toInches(),i1.toInches());
    }
    @Test
    void testStaticFeetEqualityMethod(){
        assertTrue(QuantityMeasurementApp.demonstrateFeetEquality());
    }

    @Test
    void testStaticInchesEqualityMethod(){
        assertTrue(QuantityMeasurementApp.demonstrateInchesEquality());
    }
    @Test
    void testStaticFeetInchesEqualityMethod(){
        assertTrue(QuantityMeasurementApp.demonstrateFeetInchesEquality());
    }

}


