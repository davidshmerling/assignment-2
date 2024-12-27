package bgu.spl.mics.application.objects;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the fusion of sensor data for simultaneous localization and mapping (SLAM).
 * Combines data from multiple sensors (e.g., LiDAR, camera) to build and update a global map.
 * Implements the Singleton pattern to ensure a single instance of FusionSlam exists.
 */


public class FusionSlam {

    // Fields
    private List<LandMark> landmarks;
    private List<Pose> poses;

    // Singleton instance holder
    private static class FusionSlamHolder {
        private static final FusionSlam instance = new FusionSlam();
    }

    // Private constructor
    private FusionSlam() {
        this.landmarks = new ArrayList<>();
        this.poses = new ArrayList<>();
    }

    public static FusionSlam getInstance() {
        return FusionSlamHolder.instance;
    }

    public void addLandmark(LandMark landmark) {
        this.landmarks.add(landmark);
    }

    public List<LandMark> getLandmarks() {
        return landmarks;
    }

    public void addPose(Pose pose) {
        this.poses.add(pose);
    }

    public List<Pose> getPoses() {
        return poses;
    }
}
