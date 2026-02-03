package com.apps.quantitymeasurement;
import java.util.Objects;
public class Length {
    private final double value;
    private final LengthUnit unit;
    public Length(double value, LengthUnit unit) {
        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid numeric Value");
        if(unit==null)
            throw new IllegalArgumentException("LengthUnit cannot be null");
        this.value = value;
        this.unit = unit;
    }
    /*================================
       UC6 Add Operations
    ================================*/
    public Length add(Length other){
        if(other == null){throw  new IllegalArgumentException("Other Length Cannot be null");}
        double sumInBaseUnit=this.convertToBaseUnit()+ other.convertToBaseUnit();
        double convertedValue=sumInBaseUnit/this.unit.getConversionFactor();
        return new Length(round(convertedValue),this.unit);
    }

    private double convertToBaseUnit(){
        return round(value * unit.getConversionFactor() * 100.0)/100.0;
    }
    public Length convertTo(LengthUnit targetUnit){
        if(targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
        double baseValue=convertToBaseUnit();
        double convertedValue=baseValue/ targetUnit.getConversionFactor();
        return new Length(round(convertedValue),targetUnit);
    }
    public boolean compare(Length other){
        if(other == null) return false;
        return Double.compare(this.convertToBaseUnit(),other.convertToBaseUnit())==0;
    }
    @Override
    public boolean equals(Object obj) {
        if (this==obj) return true;
        if(!(obj instanceof Length other)) return false;
        return compare(other);
    }
    @Override
    public int hashCode() {
        return Objects.hash(convertToBaseUnit());
    }
    @Override
    public String toString() {
        return String.format("%.2f %s",value,unit);
    }

    private double round(double val){
        return Math.round(val*100.0)/100.0;
    }
}
