package com.apps.quantitymeasurement.UC14;

public class Length {
    private final double value;
    private final LengthUnit unit;
    private static final double EPSILON=0.0001;
    public Length(double value, LengthUnit unit) {

        if(unit==null)
            throw new IllegalArgumentException("LengthUnit cannot be null");
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    /*================================
        UC6 Add Operations
     ================================*/
    public Length add(Length other,LengthUnit targetUnit){
        if (other==null||targetUnit==null){
            throw new IllegalArgumentException("Arguments cannot be null");
        }
        double thisBase=this
                .unit.convertToBaseUnit(this.value);
        double otherBase=other.unit.convertToBaseUnit(other.value);
        double sumBase=thisBase + otherBase;
        double result =targetUnit.convertFromBaseUnit(sumBase);
        return new Length(round(result),targetUnit);
    }
    /*================================
        UC7 Add Operations
     ================================*/
    /*public Length addLength(Length length,LengthUnit targerUnit){
        if (length==null) throw new IllegalArgumentException("Target unit cannot be null");
        if (targerUnit==null) throw new IllegalArgumentException("Target unit cannot be null");
        double sumInBaseUnit=this.convertToBaseUnit()+ length.convertToBaseUnit();
        double convertedValue=sumInBaseUnit/targerUnit.getConversionFactor();
        return new Length(round(convertedValue),targerUnit);

    }*/

    /*private double convertToBaseUnit(){
        return round(value * unit.getConversionFactor() * 100.0)/100.0;
    }*/
    public Length convertTo(LengthUnit targetUnit){
        double baseValue=unit.convertToBaseUnit(value);
        double converted= targetUnit.convertFromBaseUnit(baseValue);
        return new Length(round(converted),targetUnit);
    }
    /*public boolean compare(Length other){
        if(other == null) return false;
        return Double.compare(this.convertToBaseUnit(),other.convertToBaseUnit())==0;
    }*/
    @Override
    public boolean equals(Object obj) {
        if (this==obj) return true;
        if(obj==null||getClass() !=obj.getClass()) return false;
        Length other= (Length) obj;
        double base1=this.unit.convertToBaseUnit(this.value);
        double base2=other.unit.convertToBaseUnit(other.value);
        double epsilon=0.0001;
        return Math.abs(base1-base2)<EPSILON;

    }
    @Override
    public int hashCode() {
        double baseValue=unit.convertToBaseUnit(value);
        return Double.hashCode(baseValue);
    }

    @Override
    public String toString() {
        return "Length("+value+","+unit+")";
    }

    private double round(double val){
        return Math.round(val*100.0)/100.0;
    }
}
