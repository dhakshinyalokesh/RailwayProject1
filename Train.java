import java.util.ArrayDeque;

public class Train {

    private String trainId;
    private String trainName;
    private int totalSeats;
    private int availableSeats;

    // Platform-related information
    private int arrivalTime;
    private int departureTime;
    private int trainLength;
    private int trainPriority;

    private ArrayDeque<Passenger> waitingList =
            new ArrayDeque<>();

    // Constructor
    public Train(String trainId, String trainName,
                 int totalSeats,
                 int arrivalTime,
                 int departureTime,
                 int trainLength,
                 int trainPriority) {

        this.trainId = trainId;
        this.trainName = trainName;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;

        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.trainLength = trainLength;
        this.trainPriority = trainPriority;
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

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getDepartureTime() {
        return departureTime;
    }

    public int getTrainLength() {
        return trainLength;
    }

    public int getTrainPriority() {
        return trainPriority;
    }

    public ArrayDeque<Passenger> getWaitingList() {
        return waitingList;
    }

    public boolean bookTicket(Passenger passenger) {

        if (availableSeats > 0) {

            availableSeats--;

            return true;
        }

        waitingList.add(passenger);

        return false;
    }

    public Passenger cancelTicket() {

        availableSeats++;

        if (!waitingList.isEmpty()) {

            Passenger promotedPassenger =
                    waitingList.poll();

            availableSeats--;

            return promotedPassenger;
        }

        return null;
    }
}