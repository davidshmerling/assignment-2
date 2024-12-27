package bgu.spl.mics.application.objects;

import java.util.List;

/**
 * Represents an object tracked by the LiDAR.
 * This object includes information about the tracked object's ID, description, 
 * time of tracking, and coordinates in the environment.
 */
public class TrackedObject {

	    private String id;   
	    private int time; 
	    private String description;   
	    private List<CloudPoint> coordinates; 

	    public TrackedObject(String id, int time, String description, List<CloudPoint> coordinates) {
	        this.id = id;
	        this.time = time;
	        this.description = description;
	        this.coordinates = coordinates;
	    }

	    public String getId() {
	        return id;
	    }

	    public int getTime() {
	        return time;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public List<CloudPoint> getCoordinates() {
	        return coordinates;
	    }

	    public void setId(String id) {
	        this.id = id;
	    }

	    public void setTime(int time) {
	        this.time = time;
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }

	    public void setCoordinates(List<CloudPoint> coordinates) {
	        this.coordinates = coordinates;
	    }
	    
	    @Override
	    public String toString() {
	        return "TrackedObject{" +
	                "id='" + id + '\'' +
	                ", time=" + time +
	                ", description='" + description + '\'' +
	                ", coordinates=" + coordinates +
	                '}';
	    }
	}
