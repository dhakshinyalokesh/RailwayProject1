public class PlatformRequest {

    private Train train;
    private int requestType;
    private int arrivalOrder;

    public PlatformRequest(Train train,
                           int requestType,
                           int arrivalOrder) {

        this.train = train;
        this.requestType = requestType;
        this.arrivalOrder = arrivalOrder;
    }

    public Train getTrain() {
        return train;
    }

    public int getRequestType() {
        return requestType;
    }

    public int getArrivalOrder() {
        return arrivalOrder;
    }

    public int getPriority() {

        if (requestType == 1) {
            return 1;       // Emergency
        }

        if (requestType == 2) {
            return 2;       // Connection
        }

        return 3;           // Scheduled
    }
}