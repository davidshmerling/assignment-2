package bgu.spl.mics.application.objects;

import java.util.ArrayList;
import java.util.List;

/**
     * Represents a camera sensor on the robot.
     * Responsible for detecting objects in the environment.
     */
    public class Camera {
        private int id;
        private int frequency;
        private STATUS status;
         private List<StampedDetectedObjects> detectedObjectsList; 
    
        public Camera(int id, int frequency,STATUS status){
            this.id = id;
            this.frequency = frequency;
            this.status = status;
            this.detectedObjectsList = new ArrayList<>(); 
        }
        
        public int getId() {
            return id;
        }
    
        public int getFrequency() {
            return frequency;
        }
    
        public STATUS getStatus() {
            return status;
        }
    
        public void setStatus(STATUS status) {
            this.status = status;
        }
        public void addDetectedObject(StampedDetectedObjects detectedObject) {
            this.detectedObjectsList.add(detectedObject);
        }
    
        @Override
        public String toString() {
            return "Camera{" +
                    "id=" + id +
                    ", frequency=" + frequency +
                    ", status=" + status +
                    '}';
        }	
    }
    
