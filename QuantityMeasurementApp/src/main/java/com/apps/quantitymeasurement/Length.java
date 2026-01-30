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
        return Math.round(value * unit.getConversionFactor() * 100.0)/100.0;
    }
    public boolean compare(Length other){
        if(other == null) return false;
        return Double.compare(this.convertToBaseUnit(),other.convertToBaseUnit())==0;
    }
    @Override
    public boolean equals(Object obj){
        if (this==obj) return true;
        if (!(obj instanceof Length other)) return false;
        return compare(other);
    }
    @Override
    public int hashCode(){
        return Objects.hash(convertToBaseUnit());
    }
}
