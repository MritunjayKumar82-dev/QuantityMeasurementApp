package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public static Length convert(double value,LengthUnit fromUnit,LengthUnit toUnit){
        return new Length(value,fromUnit).convertTo(toUnit);
    }
    public static Length convert(Length length,LengthUnit toUnit){
        return length.convertTo(toUnit);
    }

    /*public static boolean demonstrateLengthEquality(Length l1,Length l2){
        return l1.equals(l2);
    }*/
    public static boolean demonstrateLengthEquality(Length l1,Length l2){
        if(l1==null||l2==null){
            return false;
        }
        return l1.equals(l2);
    }

    /*public static Length demonstrateLengthComparison(double value,LengthUnit fromUnit,LengthUnit toUnit) {
        return new Length(value, fromUnit).convertTo(toUnit);
    }*/
    public static boolean demonstrateLengthComparison(Length l1,Length l2) {
        if(l1 == null || l2 == null){
            return false;
        }
        return l1.compare(l2);
    }
    /* public static Length demonstrateLengthConversion(Length l1,LengthUnit l2){
         return l1.convertTo(l2);
     }*/
    public static Length demonstrateLengthConversion(Length l1,LengthUnit l2){
        if(l1==null)
            throw new IllegalArgumentException("Length Cannot be Null");

        if(l2==null)
            throw new IllegalArgumentException("Target unit Cannot be Null");

        return l1.convertTo(l2);
    }
    /*public static boolean demonstrateLengthComparision(Length l1,Length l2){
        return l1.equals(l2);
    }*/
    public static boolean demonstrateLengthComparision(Length l1,Length l2){
        if(l1 == null || l2 == null)
            return false;
        return l1.compare(l2);
    }
    public static Length demonstrateLengthAddition(Length l1,Length l2,LengthUnit targetUnit){
        if(l1==null||l2==null)
            throw new IllegalArgumentException("Length Input Cannot be Null");
        if(targetUnit==null)
            throw new IllegalArgumentException("Traget Unit cannot be null");
        return l1.addLength(l2,targetUnit);
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

        /*
         ***************Below Output is for UC6******************
         */
        System.out.println(new Length(1.0,LengthUnit.FEET).add(new Length(2.0,LengthUnit.FEET)));
        System.out.println(new Length(1.0,LengthUnit.FEET).add(new Length(12.0,LengthUnit.INCHES)));
        System.out.println(new Length(1.0,LengthUnit.YARDS).add(new Length(3.0,LengthUnit.FEET)));
        System.out.println(new Length(2.54,LengthUnit.CENTIMETERS).add(new Length(1.0,LengthUnit.INCHES)));
        System.out.println(new Length(12.0,LengthUnit.INCHES).add(new Length(1.0,LengthUnit.FEET)));
        System.out.println(new Length(1.0,LengthUnit.YARDS).add(new Length(3.0,LengthUnit.FEET)));
        System.out.println(new Length(36.0,LengthUnit.INCHES).add(new Length(1.0,LengthUnit.YARDS)));
        System.out.println(new Length(2.54,LengthUnit.CENTIMETERS).add(new Length(1.0,LengthUnit.INCHES)));
        System.out.println(new Length(5.0,LengthUnit.FEET).add(new Length(0.0,LengthUnit.INCHES)));
        System.out.println(new Length(5.0,LengthUnit.FEET).add(new Length(-2.0,LengthUnit.FEET)));

        /*
         ***************Below Output is for UC7******************
         */
        Length l1=new Length(3.0,LengthUnit.FEET);
        Length l2=new Length(12.0,LengthUnit.INCHES);
        Length result= demonstrateLengthAddition(l1,l2,LengthUnit.FEET);
        System.out.println("result---------->"+result);
    }

}
