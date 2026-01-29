package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FeetCodeTest {
    @Test
    void testFeetEquality_SameValue(){
        FeetCode.Feet feet1=new FeetCode.Feet(1.0);
        FeetCode.Feet feet2=new FeetCode.Feet(1.0);
        Assertions.assertEquals(feet1, feet2);
    }
    @Test
    void testFeetEquality_DifferentValue(){
        FeetCode.Feet feet1=new FeetCode.Feet(1.0);
        FeetCode.Feet feet2=new FeetCode.Feet(2.0);
        assertNotEquals(feet1, feet2);
    }
    @Test
    void testFeetEquality_NullComparison(){
        FeetCode.Feet feet1=new FeetCode.Feet(1.0);
        assertNotEquals(null, feet1);
    }
    @Test
    void testFeetEquality_differentType(){
        FeetCode.Feet feet1=new FeetCode.Feet(1.0);
        assertNotEquals("1.0", feet1);
    }

    @Test
    void testFeetEquality_SameReference(){
        FeetCode.Feet feet1=new FeetCode.Feet(1.0);
        Assertions.assertEquals(feet1, feet1);
    }
    @Test
    void testConstructor_NullValue_ShouldThrowException(){
        Exception exception=assertThrows(IllegalArgumentException.class,()->new FeetCode.Feet(null));
       assertEquals("Feet Value cannot be null",exception.getMessage());
    }
    @Test
    void testHashCode_Consistency(){
        FeetCode.Feet f1=new FeetCode.Feet( 1.0);
        FeetCode.Feet f2=new FeetCode.Feet(1.0);
        assertEquals(f1.hashCode(),f2.hashCode());
    }



}
