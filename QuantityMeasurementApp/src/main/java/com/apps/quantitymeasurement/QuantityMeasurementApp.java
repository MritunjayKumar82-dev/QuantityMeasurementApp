package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {
    public static void main(String[] args) {
        System.out.println(demonstrateLengthEquality( new Length(1.0,LengthUnit.FEET),new Length(12.0,LengthUnit.INCHES)));
        System.out.println(demonstrateLengthEquality( new Length(1.0,LengthUnit.YARDS),new Length(36.0,LengthUnit.INCHES)));
        System.out.println(demonstrateLengthEquality( new Length(1.0,LengthUnit.YARDS),new Length(3.0,LengthUnit.FEET)));
        System.out.println(demonstrateLengthEquality( new Length(1.0,LengthUnit.CENTIMETERS),new Length(0.393701,LengthUnit.INCHES)));
        System.out.println(demonstrateLengthEquality( new Length(2.0,LengthUnit.CENTIMETERS),new Length(2.0,LengthUnit.CENTIMETERS)));

    }
    public static boolean demonstrateLengthEquality(Length l1, Length l2){
        return l1.equals(l2);
    }
}





