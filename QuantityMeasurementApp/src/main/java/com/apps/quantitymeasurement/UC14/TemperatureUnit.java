package com.apps.quantitymeasurement.UC14;

import java.util.function.Function;
@SuppressWarnings("java:S106")
public enum TemperatureUnit implements IMeasurable{
    CELSIUS(false),
    FAHRENHEIT(true);
    //final boolean isFahrenheit;
    TemperatureUnit(boolean isFahrenheit){

        if(isFahrenheit) {
            this.conversionValue = FAHRENHEIT_TO_CELSIUS;
        } else {
            this.conversionValue = CELSIUS_TO_CELSIUS;
        }
    }

    final Function<Double, Double>
            FAHRENHEIT_TO_CELSIUS =  (fahrenheit) -> (fahrenheit-32)*5/9;//NOSONAR
    final Function<Double,Double>
            CELSIUS_TO_CELSIUS = (celsius) -> celsius;
    Function<Double, Double> conversionValue;




    SupportsArithmetic supportsArithmetic = () -> false;

    @Override
    public double getConversionFactor() {
        return 1.0;
    }

    @Override
    public double convertToBaseUnit(double value) {
        return conversionValue.apply(value);
    }

    @Override
    public double convertFromBaseUnit(double baseValue) {
        return 0;
    }

    @Override
    public String getUnitName() {
        return this.name();
    }
    @Override
    public boolean supportsArithmetic(){
        return supportsArithmetic.isSupported();
    }

    @Override
    public void validateOperationSupport(String operation){
        if(!supportsArithmetic.isSupported()){
            String message = this.name() + " does not support "+ operation + " operation";
            throw new UnsupportedOperationException(message);
        }
    }

    public static void main(String[] args) {
        System.out.println(TemperatureUnit.FAHRENHEIT.conversionValue.apply(5.6));
        System.out.println("TemperatureUnit ENUM");
        for(TemperatureUnit unit: TemperatureUnit.values()){
            System.out.println(unit + " has conversion function to base unit "+  unit.conversionValue);
        }

        System.out.println("Does Temperature support arithmetic Operation? "+
                TemperatureUnit.CELSIUS.supportsArithmetic() + " for CELSIUS " +
                TemperatureUnit.FAHRENHEIT.supportsArithmetic() + " for Fahrenheit");
    }
}

