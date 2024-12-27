package bgu.spl.mics.application.objects;

/**
 * Holds statistical information about the system's operation.
 * This class aggregates metrics such as the runtime of the system,
 * the number of objects detected and tracked, and the number of landmarks identified.
 */
public class StatisticalFolder {

    private int systemRuntime; // The total runtime of the system, measured in ticks.
    private int numDetectedObjects; // The cumulative count of objects detected by all cameras.
    private int numTrackedObjects; // The cumulative count of objects tracked by all LiDAR workers.
    private int numLandmarks; // The total number of unique landmarks identified and mapped.

    public StatisticalFolder() {
        this.systemRuntime = 0;
        this.numDetectedObjects = 0;
        this.numTrackedObjects = 0;
        this.numLandmarks = 0;
    }

    public synchronized void incrementSystemRuntime(int ticks) {
        this.systemRuntime += ticks;
    }

    public synchronized void incrementDetectedObjects(int count) {
        this.numDetectedObjects += count;
    }

    public synchronized void incrementTrackedObjects(int count) {
        this.numTrackedObjects += count;
    }

    public synchronized void incrementLandmarks(int count) {
        this.numLandmarks += count;
    }

    public synchronized int getSystemRuntime() {
        return systemRuntime;
    }

    public synchronized int getNumDetectedObjects() {
        return numDetectedObjects;
    }

    public synchronized int getNumTrackedObjects() {
        return numTrackedObjects;
    }

    public synchronized int getNumLandmarks() {
        return numLandmarks;
    }

    @Override
    public String toString() {
        return "StatisticalFolder{" +
                "systemRuntime=" + systemRuntime +
                ", numDetectedObjects=" + numDetectedObjects +
                ", numTrackedObjects=" + numTrackedObjects +
                ", numLandmarks=" + numLandmarks +
                '}';
    }
}

