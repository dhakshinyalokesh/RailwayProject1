public class Reservation {

    private int reservationId;
    private Passenger passenger;
    private String trainId;
    private boolean active;

    public Reservation(int reservationId, Passenger passenger, String trainId) {

        this.reservationId = reservationId;
        this.passenger = passenger;
        this.trainId = trainId;
        this.active = true;
    }

    public int getReservationId() {
        return reservationId;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public String getTrainId() {
        return trainId;
    }

    public boolean isActive() {
        return active;
    }

    public void cancel() {
        active = false;
    }
}