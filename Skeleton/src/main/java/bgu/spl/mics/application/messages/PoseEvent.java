package bgu.spl.mics.application.messages;

import bgu.spl.mics.Event;


public class PoseEvent implements Event<Void> {

    private final int time; // The time when the pose was measured
    private final double x; // The x-coordinate of the robot
    private final double y; // The y-coordinate of the robot
    private final double yaw; // The orientation angle of the robot

  
    public PoseEvent(int time, double x, double y, double yaw) {
        this.time = time;
        this.x = x;
        this.y = y;
        this.yaw = yaw;
    }

    public int getTime() {
        return time;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getYaw() {
        return yaw;
    }
}
