package bgu.spl.mics.application.objects;
import java.util.List;

/**
 * LiDarWorkerTracker is responsible for managing a LiDAR worker.
 * It processes DetectObjectsEvents and generates TrackedObjectsEvents by using data from the LiDarDataBase.
 * Each worker tracks objects and sends observations to the FusionSlam service.
 */
public class LiDarWorkerTracker {

	    private int id;
	    private int frequency;
	    private STATUS status;
	    private List<TrackedObject> lastTrackedObjects;

	    public LiDarWorkerTracker(int id, int frequency, STATUS status) {
	        this.id = id;
	        this.frequency = frequency;
	        this.status = status;
	        this.lastTrackedObjects = null;
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

	    public List<TrackedObject> getLastTrackedObjects() {
	        return lastTrackedObjects;
	    }

	    public void setStatus(STATUS status) {
	        this.status = status;
	    }

	    @Override
	    public String toString() {
	        return "LiDAR{" +
	                "id=" + id +
	                ", frequency=" + frequency +
	                ", status=" + status +
	                ", lastTrackedObjects=" + lastTrackedObjects +
	                '}';
	    }
	}

