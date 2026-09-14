public class Platform {

    private int platformId;
    private int platformLength;
    private boolean occupied;

    public Platform(int platformId, int platformLength) {

        this.platformId = platformId;
        this.platformLength = platformLength;
        this.occupied = false;
    }

    public int getPlatformId() {
        return platformId;
    }

    public int getPlatformLength() {
        return platformLength;
    }

    public boolean isOccupied() {
        return occupied;
    }
}