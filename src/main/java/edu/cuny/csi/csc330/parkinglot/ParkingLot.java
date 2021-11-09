package edu.cuny.csi.csc330.parkinglot;

import edu.cuny.csi.csc330.parkinglot.car.Vehicle;
import edu.cuny.csi.csc330.parkinglot.event.ParkingLotEvent;
import edu.cuny.csi.csc330.parkinglot.event.ParkingSpotEvent;
import edu.cuny.csi.csc330.parkinglot.office.Invoice;
import edu.cuny.csi.csc330.parkinglot.office.Office;
import edu.cuny.csi.csc330.parkinglot.office.Receipt;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.SubmissionPublisher;

public class ParkingLot {
    private final Map<String, ParkingSpot> carMap;
    private final Queue<ParkingSpot> parkingSpots;
    private SubmissionPublisher<ParkingLotEvent> parkingEventEmitter;
    private Office office;
    public ParkingLot() {
        carMap = new HashMap<>();
        parkingSpots = new LinkedList<>();
    }

    // function to generate a random string of length n
    static String getString(int n) {

        // chose a Character random from this String
        String getString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "01234567890123456789";

        // create StringBuffer size of AlphaNumericString
        StringBuilder builtString = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            // generate a random number
            int index = (int) (getString.length() * Math.random());

            // add chars to builtString iteratively
            builtString.append(getString.charAt(index));
        }

        return builtString.toString();
    }

    @Override
    public String toString() {
        return "ParkingLot{" + "parkingSpots=" + parkingSpots.size() + '}';
    }

    public void addParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpots.add(parkingSpot);
    }

    public Receipt parkCar(Vehicle vehicle) {

        if (this.parkingSpots.isEmpty()) {
            throw new IllegalArgumentException("parking lot full");
        }
        ParkingSpot poll = this.parkingSpots.poll();

        Receipt receipt = office.createReceipt(vehicle.getPlateNumber());
        carMap.put(vehicle.getPlateNumber(), poll.parkVehicle(vehicle));

        this.parkingEventEmitter.submit(ParkingSpotEvent.IN);
        return receipt;
    }

    public Vehicle getCar(Invoice invoice) {
        ParkingSpot spot = carMap.remove(invoice.getCarId());

        Vehicle vehicle = spot.removeVehicle();
        parkingSpots.add(spot);
        this.parkingEventEmitter.submit(ParkingSpotEvent.OUT);
        return vehicle;
    }

    public void setParkingEventEmitter(SubmissionPublisher<ParkingLotEvent> parkingEventEmitter) {
        this.parkingEventEmitter = parkingEventEmitter;
    }

    public void setOffice(Office office) {
        this.office = office;
    }
}
