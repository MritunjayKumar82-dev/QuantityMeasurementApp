package com.apps.quantitymeasurement;

import java.lang.reflect.WildcardType;

public class QuantityMeasurementApp {

    /* public static Length convert(double value,LengthUnit fromUnit,LengthUnit toUnit){
         return new Length(value,fromUnit).convertTo(toUnit);
     }
     public static Length convert(Length length,LengthUnit toUnit){
         return length.convertTo(toUnit);
     }

     *//*public static boolean demonstrateLengthEquality(Length l1,Length l2){
        return l1.equals(l2);
    }*//*
    public static boolean demonstrateLengthEquality(Length l1,Length l2){
        if(l1==null||l2==null){
            return false;
        }
        return l1.equals(l2);
    }

    *//*public static Length demonstrateLengthComparison(double value,LengthUnit fromUnit,LengthUnit toUnit) {
        return new Length(value, fromUnit).convertTo(toUnit);
    }*//*
    public static boolean demonstrateLengthComparison(Length l1,Length l2) {
        if(l1 == null || l2 == null){
            return false;
        }
        return l1.compare(l2);
    }
    *//* public static Length demonstrateLengthConversion(Length l1,LengthUnit l2){
         return l1.convertTo(l2);
     }*//*
    public static Length demonstrateLengthConversion(Length l1,LengthUnit l2){
        if(l1==null)
            throw new IllegalArgumentException("Length Cannot be Null");

        if(l2==null)
            throw new IllegalArgumentException("Target unit Cannot be Null");

        return l1.convertTo(l2);
    }
    *//*public static boolean demonstrateLengthComparision(Length l1,Length l2){
        return l1.equals(l2);
    }*//*
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
        *//*
     ***************Below Output is for UC5******************
     *//*
        System.out.println(convert(1.0,LengthUnit.FEET,LengthUnit.INCHES));
        System.out.println(convert(3.0,LengthUnit.YARDS,LengthUnit.FEET));
        System.out.println(convert(36.0,LengthUnit.INCHES,LengthUnit.YARDS));
        System.out.println(convert(1.0,LengthUnit.CENTIMETERS,LengthUnit.INCHES));
        System.out.println(convert(0.0,LengthUnit.FEET,LengthUnit.INCHES));

        *//*
     ***************Below Output is for UC6******************
     *//*
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
*/
    /*
     ***************Below Output is for UC8******************
     */
/*        Length l1=new Length(3.0,LengthUnit.FEET);
        Length l2=new Length(12.0,LengthUnit.INCHES);
        Length result= demonstrateLengthAddition(l1,l2,LengthUnit.FEET);*/
    public static boolean demonstrateLengthEquality(Length l1,Length l2){
        return l1.equals(l2);
    }

    public static boolean demonstrateLengthComparison(double value1,LengthUnit unit1,double value2,LengthUnit unit2) {
        Length length1=new Length(value1,unit1);
        Length length2=new Length(value2,unit2);
        return length1.equals(length2);
    }
    public static Length demonstrateLengthConversion(double value,LengthUnit fromUnit,LengthUnit toUnit){
        Length length=new Length(value,fromUnit);
        return length.convertTo(toUnit);
    }
    public static Length demonstrateLengthConversion(Length length,LengthUnit toUnit){

        return length.convertTo(toUnit);
    }
    public static Length demonstrateLengthAddition(Length l1,Length l2,LengthUnit targetUnit){

        return l1.add(l2,targetUnit);
    }
    public static boolean demonstrateWeightEquality(Weight w1,Weight w2){
        return w1.equals(w2);
    }
    public static boolean demonstrateWeightComparision(double val1,WeightUnit unit1,double val2,WeightUnit unit2){
        Weight w1=new Weight(val1,unit1);
        Weight w2=new Weight(val2,unit2);
        return w1.equals(w2);
    }
    public static Weight demonstrateWeightConversion(double value,WeightUnit toUnit,WeightUnit fromUnit){
        Weight weight=new Weight(value,fromUnit);
        return weight.convertTo(toUnit);
    }
    public static Quantity<WeightUnit> demonstrateWeightConversion(Quantity<WeightUnit> weight,WeightUnit targetUnit){
        return weight.convertTo(targetUnit);
    }
    public static Weight demonstrateWeightConversion(Weight weight,WeightUnit toUnit){
        return weight.convertTo(toUnit);
    }
    public static Weight demonstrateWeightAddition(Weight w1,Weight w2){
        return w1.add(w2);

    }
    public static Weight demonstrateWeightAddition(Weight w1,Weight w2,WeightUnit targetUnit){
        return w1.add(w2,targetUnit);
    }
    public static Quantity<WeightUnit> demonstrateWeightAddition(Quantity<WeightUnit> w1,Quantity<WeightUnit> w2,WeightUnit targetUnit){
        Quantity<WeightUnit> sum=w1.add(w2);
        return sum.convertTo(targetUnit);
    }

    public static void main(String[] args) {
        System.out.println(new Length(1.0,LengthUnit.FEET).convertTo(LengthUnit.INCHES));
        System.out.println(new Length(1.0,LengthUnit.FEET).add(new Length(12.0,LengthUnit.INCHES),LengthUnit.FEET));

        System.out.println(new Length(36.0,LengthUnit.INCHES).equals(new Length(1.0,LengthUnit.YARDS)));
        System.out.println(new Length(1.0,LengthUnit.YARDS).add(new Length(3.0,LengthUnit.FEET),LengthUnit.YARDS));
        System.out.println(new Length(2.54,LengthUnit.CENTIMETERS).convertTo(LengthUnit.INCHES));
        System.out.println(new Length(5.0,LengthUnit.FEET).add(new Length(0.0,LengthUnit.INCHES),LengthUnit.FEET));
        System.out.println(LengthUnit.FEET.convertToBaseUnit(12.0));
        System.out.println(new Length(12.0,LengthUnit.INCHES).convertTo(LengthUnit.FEET));

        //UC9
        System.out.println(demonstrateWeightEquality(new Weight(1.0,WeightUnit.KILOGRAM),new Weight(1.0,WeightUnit.KILOGRAM)));
        System.out.println(demonstrateWeightEquality(new Weight(1.0,WeightUnit.KILOGRAM),new Weight(1000.0,WeightUnit.GRAM)));
        System.out.println(demonstrateWeightEquality(new Weight(2.0,WeightUnit.POUND),new Weight(2.0,WeightUnit.POUND)));
        System.out.println(demonstrateWeightEquality(new Weight(1.0,WeightUnit.KILOGRAM),new Weight(2.20462,WeightUnit.POUND)));
        System.out.println(demonstrateWeightEquality(new Weight(500.0,WeightUnit.GRAM),new Weight(0.5,WeightUnit.KILOGRAM)));

        System.out.println(demonstrateWeightConversion(new Weight(1.0,WeightUnit.KILOGRAM),WeightUnit.GRAM));
        System.out.println(demonstrateWeightAddition(new Weight(1.0,WeightUnit.KILOGRAM),new Weight(500.0,WeightUnit.GRAM),WeightUnit.KILOGRAM));

        //UC10
        /*Length Comparision*/

        Quantity<LengthUnit> lengthFeet=new Quantity<>(10.0,LengthUnit.FEET);
        Quantity<LengthUnit> lengthInches=new Quantity<>(120.0,LengthUnit.INCHES);
        boolean isLengthEqual=lengthFeet.equals(lengthInches);
        System.out.println("Are lengths equal ? " + isLengthEqual);

        Quantity<WeightUnit> weightKilograms= new Quantity<>(1.0,WeightUnit.KILOGRAM);
        Quantity<WeightUnit> weightGrams=new Quantity<>(1000.0,WeightUnit.GRAM);

        boolean isWeightEqual=weightKilograms.equals(weightGrams);
        System.out.println("Are weights equal ? " + isWeightEqual);

        Quantity<LengthUnit> convertedLength=lengthFeet.convertTo(LengthUnit.INCHES);
        System.out.println("10 feet in inches: " + convertedLength.getValue()+ " -- "+convertedLength.getUnit());

        Quantity<WeightUnit> convertedWeight=weightGrams.convertTo(WeightUnit.KILOGRAM);
        System.out.println("1000 grams in kilograms: " + convertedWeight.getValue() + " -- "+convertedWeight.getUnit());

        Quantity<LengthUnit> totalLength=lengthFeet.add(lengthInches);
        System.out.println("Total Length in Feet: " + totalLength.getValue());

        Quantity<WeightUnit> weightPounds=new Quantity<>(2.0,WeightUnit.POUND);
        System.out.println("1000 grams in kilograms: " + convertedWeight.getValue()+ " -- "+convertedWeight.getUnit());
        Quantity<WeightUnit> totalWeight=weightKilograms.add(weightPounds);
        System.out.println("Total weight in kilograms: "+totalWeight.getValue()+ " -- "+totalWeight.getUnit());

        Quantity<WeightUnit> sumWeightInGrams=demonstrateWeightAddition(weightKilograms,weightPounds,WeightUnit.GRAM);
        System.out.println("1000 grams in kilograms: " + convertedWeight.getValue()+ " -- "+convertedWeight.getUnit());

    }

}
