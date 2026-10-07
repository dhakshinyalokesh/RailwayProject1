public class PlatformAllocation {

    private String trainId;
    private int platformId;
    private int startTime;
    private int endTime;

    public PlatformAllocation(String trainId,
                              int platformId,
                              int startTime,
                              int endTime) {

        this.trainId = trainId;
        this.platformId = platformId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getTrainId() {
        return trainId;
    }

    public int getPlatformId() {
        return platformId;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public boolean overlaps(int newStart, int newEnd) {

        return newStart < endTime
                && newEnd > startTime;
    }
}