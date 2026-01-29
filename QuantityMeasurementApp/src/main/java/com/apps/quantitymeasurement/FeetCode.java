package com.apps.quantitymeasurement;

import java.util.Objects;
import java.util.Scanner;

public class FeetCode {

    public static final class Feet{
        private final Double value;

        public Feet(Double value) {
            if(value == null){
                throw new IllegalArgumentException(("Feet Value cannot be null"));
            }
            this.value = value;
        }
        public Double getValue(){return value;}

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
        Scanner scanner=new Scanner(System.in);
        try {
            System.out.println("First Value is =>");
            double firstValue=scanner.nextDouble();
            System.out.println("Second Value is =>");
            double secondValue=scanner.nextDouble();
            Feet feet1 = new Feet(firstValue);
            Feet feet2 = new Feet(secondValue);
            boolean isEqual = feet1.equals((feet2));
            System.out.println("Are both Feet values equal? :" + isEqual);
        } catch (NumberFormatException e) {
            System.out.println("Invalid Input. pls enter the numeric values only.");
        }
        catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
        finally {
            scanner.close();
        }
    }
}





