package com.apps.quantitymeasurement;

import com.sun.jdi.Value;

public class Quantity<U extends IMeasurable> {
    private double value;
    private U unit;
    public Quantity(double value,U unit){
        if (unit==null){
            throw new IllegalArgumentException("Unit cannot be null");
        }
        if(Double.isNaN(value) || Double.isInfinite(value)){
            throw new IllegalArgumentException("Invalid value");
        }
        this.value=value;
        this.unit=unit;
    }
    public U getUnit() {
        return unit;
    }

    public double getValue() {
        return value;
    }

    public Quantity<U> convertTo(U targetUnit){
        if (!unit.getClass().equals((targetUnit.getClass()))){
            throw  new IllegalArgumentException("Incompatible units");
        }
        double baseValue=unit.convertToBaseUnit(value);
        double convertedValue=targetUnit.convertFromBaseUnit(baseValue);
        return new Quantity<>(convertedValue,targetUnit);
    }
    public <U extends IMeasurable> double convertToo(U targetUnit){
        if (!unit.getClass().equals((targetUnit.getClass()))){
            throw  new IllegalArgumentException("Incompatible units");
        }
        double ValueInBaseUnit=unit.convertToBaseUnit(value);
        return targetUnit.convertFromBaseUnit(ValueInBaseUnit);

    }

    public Quantity<U> add(Quantity<U> other){
        if (!unit.getClass().equals(other.unit.getClass())){
            throw  new IllegalArgumentException("Cannot add incompatible quantities");
        }
        double baseValue1=unit.convertToBaseUnit(value);
        double baseValue2=other.unit.convertToBaseUnit(other.value);
        double sumBase=baseValue1+baseValue2;
        double resultValue=this.unit.convertFromBaseUnit(sumBase);
        if(!Double.isFinite(resultValue)){
            throw new RuntimeException("Invalid numeric result");
        }
        return new Quantity<>(resultValue,this.unit);

    }
    public Quantity<U> add(Quantity<U> other,U targetUnit){
        return add(new Quantity<>(value,unit),other,targetUnit);

    }

    private Quantity<U> add(Quantity<U> quantity1, Quantity<U> quantity2, U targetUnit) {
       /* if(!(quantity1.unit.getClass().equals(targetUnit.getClass()))||!(quantity2.unit.getClass().equals(targetUnit.getClass()))){
            throw new IllegalArgumentException();
        }

        double valueInBaseUnit1=quantity1.unit.convertToBaseUnit(quantity1.value);
        double valueInBaseUnit2=quantity2.unit.convertToBaseUnit(quantity2.value);
        double sumInBaseUnit=valueInBaseUnit1+valueInBaseUnit2;
        double targetValue=sumInBaseUnit/targetUnit.getConversionFactor();
        targetValue=Math.round(targetValue*100)/100.0;*/
        quantity1.validateArithmeticOperands(quantity2,targetUnit,true);
        double targetValue=quantity1.performArithmetic(quantity2,targetUnit,ArithmeticOperation.SUBTRACT);
        return new Quantity<>(targetValue,targetUnit);
    }
    private void validateArithmeticOperands(Quantity<U> other, U targetUnit, boolean targetUnitRequired) {

        // Check null units
        if (unit == null || other == null || other.unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

        // Check same unit type
        if (!unit.getClass().equals(other.unit.getClass())) {
            throw new IllegalArgumentException("Unit types do not match");
        }

        // Check finite values
        if (!Double.isFinite(value) || !Double.isFinite(other.value)) {
            throw new RuntimeException("Value is infinite or NaN");
        }

        // Check target unit requirement
        if (targetUnitRequired && targetUnit == null) {
            throw new NullPointerException("Target unit is required but null");
        }
    }
    private double performArithmetic(Quantity<U> other, U targetUnit, ArithmeticOperation operation) {

        // Convert both values to base unit
        double valueInBaseUnit1 = unit.convertToBaseUnit(value);
        double valueInBaseUnit2 = other.unit.convertToBaseUnit(other.value);

        double result;

        // Perform operation using enum
        result = operation.compute(valueInBaseUnit1, valueInBaseUnit2);

        // If division, return directly (already in base unit ratio)
        if (operation == ArithmeticOperation.DIVIDE) {
            return result;
        }

        // Convert result to target unit
        result = result / targetUnit.getConversionFactor();

        // Round to 2 decimal places
        result = Math.round(result * 100) / 100.0;

        return result;
    }

    public boolean equals(Object obj){
        if (this==obj) return true;
        if(!(obj instanceof Quantity<?>)) return false;

        Quantity<?> other= (Quantity<?>) obj;

        if (!unit.getClass().equals(other.unit.getClass())) return false;
        double baseValue1=unit.convertToBaseUnit(value);
        double baseValue2=((IMeasurable)other.unit).convertToBaseUnit(other.value);
//        return Double.compare(baseValue1,baseValue2)==0;
        return  Math.abs(baseValue1-baseValue2)<0.001;
    }

    // For UC12
    public Quantity<U> subtract(Quantity<U> other){
        return subtract(new Quantity<>(value,unit),other,unit);
    }
    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
        return subtract(new Quantity<>(value, unit), other, targetUnit);
    }

    private Quantity<U> subtract(Quantity<U> quantity1,
                                 Quantity<U> quantity2,
                                 U targetUnit) {

        if (!(quantity1.unit.getClass().equals(targetUnit.getClass())) ||
                !(quantity2.unit.getClass().equals(targetUnit.getClass())))
        {
            throw new IllegalArgumentException();
        }

        if (!Double.isFinite(quantity1.value) ||
                !Double.isFinite(quantity2.value)) {
            throw new RuntimeException("Is infinite or NaN");
        }

        double valueInBaseUnit1 =
                quantity1.unit.convertToBaseUnit(quantity1.value);

        double valueInBaseUnit2 =
                quantity2.unit.convertToBaseUnit(quantity2.value);

        double differenceInBaseUnit =
                valueInBaseUnit1 - valueInBaseUnit2;

        // Converting to specified unit
        double targetValue =
                differenceInBaseUnit / targetUnit.getConversionFactor();

        targetValue = Math.round(targetValue * 100) / 100.0;

        return new Quantity<>(targetValue, targetUnit);
    }
    public double divide(Quantity<U> other) {

        if (!(unit.getClass().equals(other.unit.getClass()))) {
            throw new IllegalArgumentException();
        }

        if (!Double.isFinite(value) || !Double.isFinite(other.value)) {
            throw new RuntimeException("Is infinite or NaN");
        }

        if (other.value == 0) {
            throw new ArithmeticException();
        }

        double valueInBaseUnit1 =
                unit.convertToBaseUnit(value);

        double valueInBaseUnit2 =
                other.unit.convertToBaseUnit(other.value);

        return valueInBaseUnit1 / valueInBaseUnit2;


    }
    public double dividee(Quantity<U> other) {

        validateArithmeticOperands(other,null,false);
        return performArithmetic(other,null,ArithmeticOperation.DIVIDE);

    }
    @Override
    public String toString() {
        return "Quantity{" +
                "value=" + value +
                ", unit=" + unit +
                '}';
    }
}
