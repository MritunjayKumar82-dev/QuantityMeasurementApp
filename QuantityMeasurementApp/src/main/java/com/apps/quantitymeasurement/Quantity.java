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

    public Quantity<U> add(Quantity<U> other){
        if (!unit.getClass().equals(other.unit.getClass())){
            throw  new IllegalArgumentException("Cannot add incompatible quantities");
        }
        double baseValue1=unit.convertToBaseUnit(value);
        double baseValue2=other.unit.convertToBaseUnit(other.value);
        double sumBase=baseValue1+baseValue2;
        double resultValue=unit.convertFromBaseUnit(sumBase);

        return new Quantity<>(resultValue,unit);

    }
    public boolean equals(Object obj){
        if (this==obj) return true;
        if(!(obj instanceof Quantity<?>)) return false;

        Quantity<?> other= (Quantity<?>) obj;

        if (!unit.getClass().equals(other.unit.getClass())) return false;
        double baseValue1=unit.convertToBaseUnit(value);
        double baseValue2=((IMeasurable)other.unit).convertToBaseUnit(other.value);
        return Double.compare(baseValue1,baseValue2)==0;
    }
}
