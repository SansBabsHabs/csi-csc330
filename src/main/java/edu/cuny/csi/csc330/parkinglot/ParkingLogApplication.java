package edu.cuny.csi.csc330.parkinglot;

import edu.cuny.csi.csc330.parkinglot.car.ElectricCarImpl;
import edu.cuny.csi.csc330.parkinglot.event.ParkingLotEvent;
import edu.cuny.csi.csc330.parkinglot.office.Office;
import edu.cuny.csi.csc330.parkinglot.office.Receipt;
import edu.cuny.csi.csc330.parkinglot.util.TimeProvider;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.SubmissionPublisher;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ParkingLogApplication {

    public static void main(String[] args) throws Exception {

        TimeProvider timeProvider = new TimeProvider();

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        shutdownHook(() -> executorService.shutdown());

        ScheduledExecutorService powerGridSchedulersForElectricParkingSpots =
                Executors.newScheduledThreadPool(3);
        shutdownHook(() -> powerGridSchedulersForElectricParkingSpots.shutdown());

        Flow.Subscriber<ParkingLotEvent> parkingCapacityIndicator = new ParkingCapacityIndicator();

        SubmissionPublisher<ParkingLotEvent> parkingEventEmitter =
                new SubmissionPublisher<>(executorService, 10);

        parkingEventEmitter.subscribe(parkingCapacityIndicator);

        Office office = new Office();
        office.setTimeProvider(timeProvider);

        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setParkingEventEmitter(parkingEventEmitter);
        parkingLot.setOffice(office);

        IntStream.range(0, 20)
                .forEach(
                        (x) -> {
                            ElectricCarParkingSpot electricCarParkingSpot =
                                    new ElectricCarParkingSpot();
                            electricCarParkingSpot.setPowerGridScheduler(
                                    powerGridSchedulersForElectricParkingSpots);
                            parkingLot.addParkingSpot(electricCarParkingSpot);
                        });

        System.out.println(parkingLot);

        List<Receipt> collect =
                IntStream.range(0, 10)
                        .mapToObj(
                                i ->
                                        parkingLot.parkCar(
                                                new ElectricCarImpl(String.valueOf(i), 5000, i)))
                        .collect(Collectors.toList());

        Thread.sleep(50000);
        for (Receipt receipt : collect) {

            parkingLot.getCar(office.pay(receipt));
        }

        System.out.println("SI Corner Parking Lot is open for business");
    }

    private static void shutdownHook(Runnable shutdownCmd) {
        Runtime.getRuntime().addShutdownHook(new Thread(shutdownCmd));
    }
}
