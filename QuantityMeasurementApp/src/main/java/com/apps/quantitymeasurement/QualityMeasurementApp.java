package com.apps.quantitymeasurement;

public class QualityMeasurementApp {

    public static void main(String[] args) {
        demonstrateFeetEquality();
        demonstrateInchesEquality();
        demonstrateFeetInchesComparison();
    }
    public static boolean demonstrateLengthEquality(Length l1,Length l2){
        return l1.equals(l2);
    }
    public static void demonstrateFeetEquality(){
        Length length1=new Length(1.0,LengthUnit.FEET);
        Length length2=new Length(1.0,LengthUnit.FEET);
        System.out.println("Feet equality: " + demonstrateLengthEquality(length1,length2));
    }
    public static void demonstrateInchesEquality(){
        Length length1=new Length(12.0,LengthUnit.INCH);
        Length length2=new Length(12.0,LengthUnit.INCH);
        System.out.println("Inches equality: " + demonstrateLengthEquality(length1,length2));

    }
    public static void demonstrateFeetInchesComparison(){
        Length length1=new Length(1.0,LengthUnit.FEET);
        Length length2=new Length(12.0,LengthUnit.INCH);
        System.out.println("FEET & INCHES Comparision: " + demonstrateLengthEquality(length1,length2));

    }
}





