package bgu.spl.mics.application.services;

import bgu.spl.mics.MicroService;
import bgu.spl.mics.application.messages.DetectObjectsEvent;
import bgu.spl.mics.application.messages.TickBroadcast;
import bgu.spl.mics.application.messages.TerminatedBroadcast;
import bgu.spl.mics.application.objects.DetectedObject;

import java.util.List;

/**
 * CameraService represents a camera in the system, responsible for sending DetectObjectsEvent
 * messages based on the detected objects at specific time intervals.
 */
public class CameraService extends MicroService {

    private final int id; // Camera ID
    private final int frequency; // Time interval at which the camera operates (ticks)
    private final List<DetectedObject> detectedObjects; // List of detected objects
    private int currentTick;

    /**
     * Constructor for CameraService.
     *
     * @param id               The ID of the camera.
     * @param frequency        The frequency of the camera (in ticks).
     * @param detectedObjects  The list of detected objects.
     */
    public CameraService(int id, int frequency, List<DetectedObject> detectedObjects) {
        super("CameraService-" + id);
        this.id = id;
        this.frequency = frequency;
        this.detectedObjects = detectedObjects;
        this.currentTick = 0;
    }

    /**
     * Initializes the CameraService. Subscribes to TickBroadcast and sends DetectObjectsEvent
     * messages at the specified frequency.
     */
    @Override
    protected void initialize() {
        // Subscribe to TickBroadcast
        subscribeBroadcast(TickBroadcast.class, tick -> {
            currentTick = tick.getCurrentTick();

            // Send DetectObjectsEvent if the tick matches the camera's frequency
            if (currentTick % frequency == 0) {
                sendEvent(new DetectObjectsEvent(currentTick, getDetectedObjectIds()));
                System.out.println(getName() + " sent DetectObjectsEvent at tick: " + currentTick);
            }
        });

        // Subscribe to TerminatedBroadcast
        subscribeBroadcast(TerminatedBroadcast.class, terminated -> {
            System.out.println(getName() + " received TerminatedBroadcast. Terminating...");
            terminate();
        });
    }

    /**
     * Retrieves the IDs of the detected objects at the current tick.
     *
     * @return A list of detected object IDs.
     */
    private List<String> getDetectedObjectIds() {
        // In a real implementation, filter detected objects based on the current tick.
        return detectedObjects.stream().map(DetectedObject::getId).toList();
    }
}