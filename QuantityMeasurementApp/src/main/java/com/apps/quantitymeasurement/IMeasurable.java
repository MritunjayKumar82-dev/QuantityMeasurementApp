package com.apps.quantitymeasurement;

public interface IMeasurable {

    double getConversionFactor();
    default double convertToBaseUnit(double value){return value * getConversionFactor();}
    default double convertFromBaseUnit(double baseValue){return  baseValue/getConversionFactor();}

    String getUnitName();

    //For UC14
    SupportsArithmetic supportsArithmetic = ()->true;
    default boolean supportsArithmetic(){
        return supportsArithmetic.isSupported();
    }
    default void validateOperationSupport(String operation){

    }
}
