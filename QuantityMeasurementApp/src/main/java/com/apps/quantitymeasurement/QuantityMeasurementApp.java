package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public static Length convert(double value,LengthUnit fromUnit,LengthUnit toUnit){
        return new Length(value,fromUnit).convertTo(toUnit);
    }
    public static Length convert(Length length,LengthUnit toUnit){
        return length.convertTo(toUnit);
    }
    public static boolean demonstrateLengthEquality(Length l1,Length l2){
        return l1.equals(l2);
    }
    public static void main(String[] args) {
        System.out.println(demonstrateLengthEquality( new Length(1.0,LengthUnit.FEET),new Length(12.0,LengthUnit.INCHES)));
        System.out.println(demonstrateLengthEquality( new Length(1.0,LengthUnit.YARDS),new Length(36.0,LengthUnit.INCHES)));
        System.out.println(demonstrateLengthEquality( new Length(1.0,LengthUnit.YARDS),new Length(3.0,LengthUnit.FEET)));
        System.out.println(demonstrateLengthEquality( new Length(1.0,LengthUnit.CENTIMETERS),new Length(0.393701,LengthUnit.INCHES)));
        System.out.println(demonstrateLengthEquality( new Length(2.0,LengthUnit.CENTIMETERS),new Length(2.0,LengthUnit.CENTIMETERS)));
        /*
         ***************Below Output is for UC5******************
         */
        System.out.println(convert(1.0,LengthUnit.FEET,LengthUnit.INCHES));
        System.out.println(convert(3.0,LengthUnit.YARDS,LengthUnit.FEET));
        System.out.println(convert(36.0,LengthUnit.INCHES,LengthUnit.YARDS));
        System.out.println(convert(1.0,LengthUnit.CENTIMETERS,LengthUnit.INCHES));
        System.out.println(convert(0.0,LengthUnit.FEET,LengthUnit.INCHES));
    }

}
