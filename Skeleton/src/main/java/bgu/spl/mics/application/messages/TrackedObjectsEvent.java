package bgu.spl.mics.application.messages;

import bgu.spl.mics.Event;
import bgu.spl.mics.application.objects.TrackedObject;
import java.util.List;


public class TrackedObjectsEvent implements Event<Void> {

    private final int time; // The time when the objects were tracked
    private final List<TrackedObject> trackedObjects; // List of tracked objects


    public TrackedObjectsEvent(int time, List<TrackedObject> trackedObjects) {
        this.time = time;
        this.trackedObjects = trackedObjects;
    }

    public int getTime() {
        return time;
    }

    List<TrackedObject> getTrackedObjects() {
        return trackedObjects;
    }
}