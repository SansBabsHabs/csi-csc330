package edu.cuny.csi.csc330.parkinglot;

import edu.cuny.csi.csc330.parkinglot.util.TimeProvider;

public class ParkingLogApplication {

    public static void main(String[] args) {
        TimeProvider timeProvider = new TimeProvider();

        Office office = new Office();
        office.setTimeProvider(timeProvider);

        ParkingLot parkingLot = new ParkingLot();
        // call parkingLot.setOffice(office);

    }
}
