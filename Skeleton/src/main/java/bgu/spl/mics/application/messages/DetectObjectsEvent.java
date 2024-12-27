package bgu.spl.mics.application.messages;

import bgu.spl.mics.Event;
import java.util.List;


public class DetectObjectsEvent implements Event<List<String>> {

    private final int time; // The time when the objects were detected
    private final List<String> detectedObjects; // List of detected object IDs

    public DetectObjectsEvent(int time, List<String> detectedObjects) {
        this.time = time;
        this.detectedObjects = detectedObjects;
    }
   
    public int getTime() {
        return time;
    }

    public List<String> getDetectedObjects() {
        return detectedObjects;
    }
}
