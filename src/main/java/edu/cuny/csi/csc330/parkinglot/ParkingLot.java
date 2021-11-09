package edu.cuny.csi.csc330.parkinglot;

import edu.cuny.csi.csc330.parkinglot.car.Vehicle;
import edu.cuny.csi.csc330.parkinglot.event.ParkingLotEvent;
import edu.cuny.csi.csc330.parkinglot.event.ParkingSpotEvent;
import edu.cuny.csi.csc330.parkinglot.office.Invoice;
import edu.cuny.csi.csc330.parkinglot.office.Office;
import edu.cuny.csi.csc330.parkinglot.office.Receipt;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.SubmissionPublisher;

public class ParkingLot {

    private final Map<String, Vehicle> carMap;
    private SubmissionPublisher<ParkingLotEvent> parkingEventEmitter;
    private Office office;

    public ParkingLot() {
        carMap = new HashMap<>();
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

    public Receipt parkCar(Vehicle vehicle) {

        Receipt receipt = office.createReceipt(vehicle.getPlateNumber());
        carMap.put(vehicle.getPlateNumber(), vehicle);

        this.parkingEventEmitter.submit(ParkingSpotEvent.IN);
        return receipt;
    }

    public Vehicle getCar(Invoice invoice) {
        Vehicle parkedCar = carMap.remove(invoice.getCarId());
        if (parkedCar == null) {
            return null;
        }

        this.parkingEventEmitter.submit(ParkingSpotEvent.OUT);
        return parkedCar;
    }

    public void setParkingEventEmitter(SubmissionPublisher<ParkingLotEvent> parkingEventEmitter) {
        this.parkingEventEmitter = parkingEventEmitter;
    }

    public void setOffice(Office office) {
        this.office = office;
    }
}
