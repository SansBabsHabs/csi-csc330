package edu.cuny.csi.csc330.parkinglot;

import edu.cuny.csi.csc330.parkinglot.util.TimeProvider;

import java.util.Arrays;
import java.util.*;

public class ParkingLot {
	
	
	public interface Vehicle {
		
		public boolean isElectric();
		public boolean isLong();
		public boolean isHandicap();
		
	}
	

	class RegularCar implements Vehicle {	
				
		
		public boolean isElectric() {
			
			return false;
		}
		
		public boolean isLong() {
			
			return false;
			
		}
		public boolean isHandicap() {
			
			return false;
		}
	
	}
	
	////////////////////////
	
	class LongCar implements Vehicle {	
				
		
		public boolean isElectric() {
			
			return false;
		}
		
		public boolean isLong() {
			
			return true;
			
		}
		public boolean isHandicap() {
			
			return false;
		}
	
	}	
	
	/////////////////////////
	
	class HandicapCar implements Vehicle {	
		
		
		public boolean isElectric() {
	
			return false;
		}

		public boolean isLong() {
	
			return false;
	
		}
		public boolean isHandicap() {
	
			return true;
		}

	}

	/////////////////////////
	
	// function to generate a random string of length n
	static String getString(int n) {
  
        // chose a Character random from this String
        String getString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "01234567890123456789";
  
        // create StringBuffer size of AlphaNumericString
        StringBuilder builtString = new StringBuilder(n);
  
        for (int i = 0; i < n; i++) {
        	// generate a random number
            int index = (int)(getString.length() * Math.random());
  
            // add chars to builtString iteratively
            builtString.append(getString.charAt(index));
        }
  
        return builtString.toString();
	}

	/////////////////////////

	public static void main(String[] args) {

		ParkingLot num = new ParkingLot();
	
		System.out.println(num.getString(6));
	
	}
}
