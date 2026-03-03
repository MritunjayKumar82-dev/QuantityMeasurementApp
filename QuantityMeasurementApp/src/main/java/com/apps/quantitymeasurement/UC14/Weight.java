package com.apps.quantitymeasurement.UC14;

import java.util.Objects;

import static java.lang.Math.round;

public class Weight {
    private final double value;
    private final WeightUnit unit;
    private static final double EPSILON=0.0001;

    public Weight(double value, WeightUnit unit) {
        if(unit==null) throw new IllegalArgumentException("Weight unit cannot be null");
        if(Double.isNaN(value) || Double.isInfinite(value)) throw new IllegalArgumentException("Invalid Weight Value");
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }
    public Weight convertTo(WeightUnit targetUnit){
        double baseValue=unit.toBase(value);
        double converted= targetUnit.fromBase(baseValue);
        return new Weight(round(converted),targetUnit);
    }
    public Weight add(Weight other){
        return add(other,this.unit);
    }
    public Weight add(Weight other,WeightUnit targetUnit){
        double sumBase=this.unit.toBase(this.value) + other.unit.toBase(other.value);
        double result=targetUnit.fromBase(sumBase);
        return new Weight(round(result),targetUnit);
    }
    @Override
    public boolean equals(Object obj) {
        if (this==obj) return true;
        if(obj==null||getClass() !=obj.getClass()) return false;
        Weight other= (Weight) obj;
        double base1=this.unit.toBase(this.value);
        double base2=other.unit.toBase(other.value);
        return Math.abs(base1-base2)<EPSILON;

    }
    @Override
    public int hashCode() {
        return Objects.hash(round(unit.toBase(value)));
    }
    private double round(double val){
        return Math.round(val*100.0)/100.0;
    }
    @Override
    public String toString() {
        return "Weight("+value+","+unit+")";
    }

}
