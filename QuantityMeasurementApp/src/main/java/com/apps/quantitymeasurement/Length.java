package com.apps.quantitymeasurement;

import java.util.Objects;

public class Length {
    private final double value;
    private final LengthUnit unit;

    public Length(double value, LengthUnit unit) {
        this.value = value;
        this.unit = unit;
    }
    private double convertToBaseUnit(){
        return value * unit.getConversionFactor();
    }
    public boolean compare(Length other){
        return  Double.compare(this.convertToBaseUnit(),other.convertToBaseUnit())==0;
    }
    @Override
    public boolean equals(Object obj){
        if(this==obj) return true;
        if(obj == null || getClass() !=obj.getClass()) return false;
        Length other=(Length) obj;
        return Double.compare(this.convertToBaseUnit(),other.convertToBaseUnit())==0;
    }
    @Override
    public int hashCode(){
        return Objects.hash(convertToBaseUnit());
    }
}
