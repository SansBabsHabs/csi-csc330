package edu.cuny.csi.csc330.parkinglot;

import edu.cuny.csi.csc330.parkinglot.car.Vehicle;

public class ParkingSpot {
    private Vehicle vehicle;

    public boolean isEmpty() {
        return vehicle == null;
    }

    public ParkingSpot parkVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        return this;
    }

    public Vehicle removeVehicle() {
        Vehicle vehicle = this.vehicle;
        this.vehicle = null;
        return vehicle;
    }
}
