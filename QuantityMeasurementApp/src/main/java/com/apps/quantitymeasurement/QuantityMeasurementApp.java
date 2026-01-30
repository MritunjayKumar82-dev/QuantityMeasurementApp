package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public static class Feet {
        private final double value;
        public Feet(double value) {
            this.value = value;
        }
        public double toInches(){
            return value * 12;
        }

        @Override
        public boolean equals(Object obj){
            if(this==obj)
                return true;
            if(obj==null || getClass() !=obj.getClass()) return false;
            Feet feet= (Feet) obj;
            return Double.compare(feet.value,value) ==0;
        }
    }

    public static class Inches{
        private final double value;
        public Inches(double value) {
            this.value = value;
        }
        public double toInches(){
            return value;
        }
        @Override
        public boolean equals(Object obj){
            if(this==obj)
                return true;
            if(obj==null || getClass() !=obj.getClass()) return false;
            Inches inches= (Inches) obj;
            return Double.compare(inches.value,value) ==0;
        }
    }

    public static boolean demonstrateFeetEquality(){
        Feet feet1=new Feet(1.0);
        Feet feet2=new Feet(1.0);
        return feet1.equals(feet2);
    }

    public static boolean demonstrateInchesEquality(){
        Inches inches1=new Inches(1.0);
        Inches inches2=new Inches(1.0);
        return inches1.equals(inches2);

    }
    public static boolean demonstrateFeetInchesEquality(){
        Feet feet=new Feet(1.0);
        Inches inches=new Inches(12.0);
        return Double.compare(feet.toInches(),inches.toInches())==0;

    }

    public static void main(String[] args) {
        System.out.println("Feet Equality: "+demonstrateFeetEquality());
        System.out.println("Inches Equality: "+demonstrateInchesEquality());
        System.out.println("Feet & Inches Equality: "+demonstrateFeetInchesEquality());
    }
}

