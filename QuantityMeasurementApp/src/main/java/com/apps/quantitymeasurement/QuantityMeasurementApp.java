package com.apps.quantitymeasurement;

import java.lang.reflect.WildcardType;

public class QuantityMeasurementApp {


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
    //For UC11
    public static <U extends IMeasurable> boolean demonstrateEquality(Quantity<U> quantity1, Quantity<U> quantity2){
        return quantity1.equals(quantity2);
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateConversion(Quantity<U> quantity, U targetUnit){
        double value = quantity.convertToo(targetUnit);
        return new Quantity<>(value, targetUnit);
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateAddition(Quantity<U> quantity1, Quantity<U> quantity2){
        return quantity1.add(quantity2);
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateAddition(Quantity<U> quantity1, Quantity<U> quantity2, U targetUnit){
        return quantity1.add(quantity2, targetUnit);
    }

    //For UC12

    public static <U extends IMeasurable> Quantity<U> demonstrateSubtraction(
            Quantity<U> quantity1,
            Quantity<U> quantity2) {

        return quantity1.subtract(quantity2);
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateSubtraction(
            Quantity<U> quantity1,
            Quantity<U> quantity2,
            U targetUnit) {

        return quantity1.subtract(quantity2, targetUnit);
    }

    public static <U extends IMeasurable> double demonstrateDivision(
            Quantity<U> quantity1,
            Quantity<U> quantity2) {

        return quantity1.divide(quantity2);
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
        /**Length Comparision**/

        Quantity<LengthUnit> lengthFeet=new Quantity<>(1.0,LengthUnit.FEET);
        Quantity<LengthUnit> lengthInches=new Quantity<>(12.0,LengthUnit.INCHES);
        boolean isLengthEqual=lengthFeet.equals(lengthInches);
        System.out.println("Are lengths equal ? " + isLengthEqual);

        Quantity<WeightUnit> weightKilograms= new Quantity<>(1.0,WeightUnit.KILOGRAM);
        Quantity<WeightUnit> weightGrams=new Quantity<>(1000.0,WeightUnit.GRAM);

        boolean isWeightEqual=weightKilograms.equals(weightGrams);
        System.out.println("Are weights equal ? " + isWeightEqual);

        Quantity<LengthUnit> convertedLength=lengthFeet.convertTo(LengthUnit.INCHES);
        System.out.println("1 feet in inches: " + convertedLength.getValue()+ " -- "+convertedLength.getUnit());

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
// For UC11
        //UC 11
        System.out.println(new Quantity<>(1.0, VolumeUnit.LITRE).equals(new Quantity<>(1.0, VolumeUnit.LITRE)));
        System.out.println(new Quantity<>(1.0, VolumeUnit.LITRE).equals(new Quantity<>(1000.0, VolumeUnit.MILLILITRE)));
        System.out.println(new Quantity<>(1.0, VolumeUnit.GALLON).equals(new Quantity<>(1.0, VolumeUnit.GALLON)));
        System.out.println(new Quantity<>(1.0, VolumeUnit.LITRE).equals(new Quantity<>(0.264172, VolumeUnit.GALLON)));
        System.out.println(new Quantity<>(500.0, VolumeUnit.MILLILITRE).equals(new Quantity<>(0.5, VolumeUnit.LITRE)));
        System.out.println(demonstrateEquality(new Quantity<>(3.78541, VolumeUnit.LITRE), new Quantity<>(1.0, VolumeUnit.GALLON)));

//UC11 Unit Conversion
        System.out.println(demonstrateConversion(new Quantity<>(1.0, VolumeUnit.LITRE), VolumeUnit.MILLILITRE));
        System.out.println(demonstrateConversion(new Quantity<>(2.0, VolumeUnit.GALLON), VolumeUnit.LITRE));
        System.out.println(demonstrateConversion(new Quantity<>(500.0, VolumeUnit.MILLILITRE), VolumeUnit.GALLON));
        System.out.println(demonstrateConversion(new Quantity<>(0.0, VolumeUnit.LITRE), VolumeUnit.MILLILITRE));
        System.out.println(demonstrateConversion(new Quantity<>(1.0, VolumeUnit.LITRE), VolumeUnit.LITRE));

//UC11 IMPLICIT target unit
        System.out.println(demonstrateAddition(new Quantity<>(1.0, VolumeUnit.LITRE),new Quantity<>(2.0, VolumeUnit.LITRE)));
        System.out.println(demonstrateAddition(new Quantity<>(1.0, VolumeUnit.LITRE),new Quantity<>(1000.0, VolumeUnit.MILLILITRE)));
        System.out.println(demonstrateAddition(new Quantity<>(500.0, VolumeUnit.MILLILITRE),new Quantity<>(0.5, VolumeUnit.LITRE)));
        System.out.println(demonstrateAddition(new Quantity<>(2.0, VolumeUnit.GALLON),new Quantity<>(3.78541, VolumeUnit.LITRE)));

//UC11 IMPLICIT target unit
        System.out.println(demonstrateAddition(new Quantity<>(1.0, VolumeUnit.LITRE),new Quantity<>(1000.0, VolumeUnit.MILLILITRE), VolumeUnit.MILLILITRE));
        System.out.println(demonstrateAddition(new Quantity<>(1.0, VolumeUnit.GALLON),new Quantity<>(3.78541, VolumeUnit.LITRE), VolumeUnit.GALLON));
        System.out.println(demonstrateAddition(new Quantity<>(500.0, VolumeUnit.MILLILITRE),new Quantity<>(1.0, VolumeUnit.LITRE), VolumeUnit.GALLON));
        System.out.println(demonstrateAddition(new Quantity<>(2.0, VolumeUnit.LITRE),new Quantity<>(4.0, VolumeUnit.GALLON), VolumeUnit.LITRE));

//Category Incompatibility
        System.out.println(new Quantity<>(1.0, VolumeUnit.LITRE).equals(new Quantity<>(1.0, LengthUnit.FEET)));
        System.out.println(new Quantity<>(1.0, VolumeUnit.LITRE).equals(new Quantity<>(1.0, WeightUnit.KILOGRAM)));

        // For UC12

        //UC12 Substraction with Implicit TargetUnit
        System.out.println(demonstrateSubtraction(new Quantity<>(10.0, LengthUnit.FEET),new Quantity<>(6.0, LengthUnit.INCHES)));
        System.out.println(demonstrateSubtraction(new Quantity<>(10.0, WeightUnit.KILOGRAM),new Quantity<>(5000.0, WeightUnit.GRAM)));
        System.out.println(demonstrateSubtraction(new Quantity<>(5.0, VolumeUnit.LITRE),new Quantity<>(500.0, VolumeUnit.MILLILITRE)));

        //UC12 Substraction with Explicit TargetUnit
        System.out.println(demonstrateSubtraction(new Quantity<>(10.0, LengthUnit.FEET),new Quantity<>(6.0, LengthUnit.INCHES), LengthUnit.INCHES));
        System.out.println(demonstrateSubtraction(new Quantity<>(10.0, WeightUnit.KILOGRAM),new Quantity<>(5000.0, WeightUnit.GRAM), WeightUnit.GRAM));
        System.out.println(demonstrateSubtraction(new Quantity<>(5.0, VolumeUnit.LITRE),new Quantity<>(2.0, VolumeUnit.LITRE), VolumeUnit.MILLILITRE));

        //UC12 Substraction Resulting in Negative Values
        System.out.println(demonstrateSubtraction(new Quantity<>(5.0, LengthUnit.FEET),new Quantity<>(10.0, LengthUnit.FEET)));
        System.out.println(demonstrateSubtraction(new Quantity<>(2.0, WeightUnit.KILOGRAM),new Quantity<>(5.0, WeightUnit.KILOGRAM)));

//UC12 Substraction Resulting in Zero
        System.out.println(demonstrateSubtraction(new Quantity<>(10.0, LengthUnit.FEET),new Quantity<>(120.0, LengthUnit.INCHES)));
        System.out.println(demonstrateSubtraction(new Quantity<>(1.0, VolumeUnit.LITRE),new Quantity<>(1000.0, VolumeUnit.MILLILITRE)));
//UC12 Division Operations
        System.out.println(demonstrateDivision(new Quantity<>(10.0, LengthUnit.FEET),new Quantity<>(2.0, LengthUnit.FEET)));
        System.out.println(demonstrateDivision(new Quantity<>(10.0, LengthUnit.FEET),new Quantity<>(5.0, LengthUnit.FEET)));
        System.out.println(demonstrateDivision(new Quantity<>(24.0, LengthUnit.INCHES),new Quantity<>(2.0, LengthUnit.FEET)));
        System.out.println(demonstrateDivision(new Quantity<>(10.0, WeightUnit.KILOGRAM),new Quantity<>(5.0, WeightUnit.KILOGRAM)));
        System.out.println(demonstrateDivision(new Quantity<>(5.0, VolumeUnit.LITRE),new Quantity<>(10.0, VolumeUnit.LITRE)));
//UC12 Division with Different Units (Same Category)
        System.out.println(demonstrateDivision(new Quantity<>(12.0, LengthUnit.INCHES),new Quantity<>(1.0, LengthUnit.FEET)));
        System.out.println(demonstrateDivision(new Quantity<>(2000.0, WeightUnit.GRAM),new Quantity<>(1.0, WeightUnit.KILOGRAM)));
        System.out.println(demonstrateDivision(new Quantity<>(1000.0, VolumeUnit.MILLILITRE),new Quantity<>(1.0, VolumeUnit.LITRE)));
//UC12 Error Cases
        try{
            new Quantity<>(10.0, LengthUnit.FEET).subtract(null);
        }catch (Exception ex){
            // ex.printStackTrace();
        }

        try{
            new Quantity<>(10.0, LengthUnit.FEET).divide(new Quantity<>(0.0, LengthUnit.FEET));
        }catch (Exception ex){
            // ex.printStackTrace();
        }

        try{
            demonstrateSubtraction(new Quantity<>(10.0, LengthUnit.FEET), new Quantity<>(5.0, WeightUnit.KILOGRAM));
        }catch (Exception ex){
            // ex.printStackTrace();
        }

        //UC14
        System.out.println("n=== Temperature Demonstration ===");

        //Equality Demonstration
        Quantity<TemperatureUnit> temp1 = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> temp2 = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);
        System.out.println("0 degree C equals 32 F "+ temp1.equals(temp2));

        System.out.println(new Quantity<>(212.0, TemperatureUnit.FAHRENHEIT).equals(new Quantity<>(100.0, TemperatureUnit.CELSIUS)));
        System.out.println(new Quantity<>(50.0, TemperatureUnit.CELSIUS).equals(new Quantity<>(122.0, TemperatureUnit.FAHRENHEIT)));

        //Conversion Demonstration
        Quantity<TemperatureUnit> celsius = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        double fahrenheit = celsius.convertToo(TemperatureUnit.FAHRENHEIT);
        System.out.println(fahrenheit);

        System.out.println(new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT).convertTo(TemperatureUnit.CELSIUS));
        System.out.println(new Quantity<>(-40.0, TemperatureUnit.CELSIUS).convertTo(TemperatureUnit.FAHRENHEIT));

        //Unsupported Operation Demonstration
        try{
            celsius.add(new Quantity<>(50.0, TemperatureUnit.CELSIUS));
        }catch (UnsupportedOperationException e){
            System.out.println("Cant add absolute Temperatures: "+ e.getMessage());
        }

        try{
            celsius.subtract(new Quantity<>(50.0, TemperatureUnit.CELSIUS));
        }catch (UnsupportedOperationException e){
            System.out.println("Cant add absolute Temperatures: "+ e.getMessage());
        }

        try{
            celsius.divide(new Quantity<>(50.0, TemperatureUnit.CELSIUS));
        }catch (UnsupportedOperationException e){
            System.out.println("Cant add absolute Temperatures: "+ e.getMessage());
        }

        System.out.println(celsius.equals(new Quantity<>(50.0, VolumeUnit.LITRE)));
    }
}
