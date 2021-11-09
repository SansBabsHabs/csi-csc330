package edu.cuny.csi.csc330.parkinglot;

import edu.cuny.csi.csc330.parkinglot.event.ParkingLotEvent;

import java.util.concurrent.Flow;

public class ParkingCapacityIndicator implements Flow.Subscriber<ParkingLotEvent> {

    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(ParkingLotEvent item) {
        System.out.println(item);
    }

    @Override
    public void onError(Throwable throwable) {}

    @Override
    public void onComplete() {
        if (subscription != null) {
            subscription.cancel();
        }
    }
}
