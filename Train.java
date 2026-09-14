import java.util.ArrayDeque;

public class Train {

    private String trainId;
    private String trainName;
    private int totalSeats;
    private int availableSeats;

    private ArrayDeque<Passenger> waitingList =
            new ArrayDeque<>();

    public Train(String trainId, String trainName, int totalSeats) {

        this.trainId = trainId;
        this.trainName = trainName;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
    }

    public String getTrainId() {
        return trainId;
    }

    public String getTrainName() {
        return trainName;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public ArrayDeque<Passenger> getWaitingList() {
        return waitingList;
    }

    // Book ticket
    public boolean bookTicket(Passenger passenger) {

        if (availableSeats > 0) {

            availableSeats--;

            return true;
        }

        waitingList.add(passenger);

        return false;
    }

    // Cancel ticket
    public Passenger cancelTicket() {

        availableSeats++;

        // Promote first waiting passenger
        if (!waitingList.isEmpty()) {

            Passenger promotedPassenger =
                    waitingList.poll();

            availableSeats--;

            return promotedPassenger;
        }

        return null;
    }
}