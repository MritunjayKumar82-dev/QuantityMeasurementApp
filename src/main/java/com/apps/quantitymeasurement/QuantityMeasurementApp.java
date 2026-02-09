package com.apps.quantitymeasurement;

import java.util.Objects;

public class QuantityMeasurementApp {

    public static final class Feet{
        private final Double value;

        public Feet(Double value) {
            if(value == null){
                throw new IllegalArgumentException(("Feet Value cannot be null"));
            }
            this.value = value;
        }
        public Double getValue(){
            return value;
        }

        @Override
        public boolean equals(Object o) {

            if(this==o){return true;}

            if(o==null){return false;}

            if(this.getClass() != o.getClass()){return false;}

            Feet feet = (Feet) o;

            return Double.compare(this.value, feet.value) == 0;
        }


        @Override
        public int hashCode() {
            return Objects.hashCode(value);
        }
    }

    public static void main(String[] args) {
        Feet feet1=new Feet(1.0);
        Feet feet2=new Feet(1.0);
        boolean isEqual=feet1.equals(feet2);
        System.out.println("Are both Feet values equal ? :"+ isEqual);

    }
}





