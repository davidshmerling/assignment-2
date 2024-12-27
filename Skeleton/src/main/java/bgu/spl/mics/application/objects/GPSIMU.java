package bgu.spl.mics.application.objects;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the robot's GPS and IMU system.
 * Provides information about the robot's position and movement.
 */
public class GPSIMU {
    
    
        // Fields
        private int currentTick;
        private STATUS status;
        private List<Pose> poseList;
    
        // Constructor
        public GPSIMU() {
            this.currentTick = 0;
            this.status = STATUS.UP;
            this.poseList = new ArrayList<>();
        }
    
        public int getCurrentTick() {
            return currentTick;
        }
    
        public void setCurrentTick(int currentTick) {
            this.currentTick = currentTick;
        }
    
        public STATUS getStatus() {
            return status;
        }
    
        public void setStatus(STATUS status) {
            this.status = status;
        }
    
        public List<Pose> getPoseList() {
            return poseList;
        }
    
        public void addPose(Pose pose) {
            this.poseList.add(pose);
        }
    }
    