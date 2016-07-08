/*
 * TCSS 305 - Spring 2016 Assignment 3 - Easy Street
 */

package model;

/**
 * The abstract parent class that all vehicle sub-classes inherit from.
 * Implements the Vehicle interface.
 * 
 * @author Timothy Liu
 * @version 25 April 2016
 *
 */
public abstract class AbstractVehicle implements Vehicle {

    /**
     * Vehicle's X location.
     */
    private int myX;

    /**
     * Vehicle's Y location.
     */
    private int myY;

    /**
     * Vehicle's direction.
     */
    private Direction myDir;

    /**
     * Vehicle starting direction.
     */
    private final Direction myOriginalDir;

    /**
     * Vehicle's starting X position.
     */
    private final int myOriginalX;

    /**
     * Vehicle's starting Y position.
     */
    private final int myOriginalY;

    /**
     * Death timer for vehicle, counts down while vehicle is dead. Value is 0
     * when vehicle is alive.
     */
    private int myDeathTimer;

    /**
     * Gives the amount of time the vehicle is dead for.
     */
    private final int myDeathTime;

    /**
     * Gives the vehicle's name.
     */
    private final String myVehicleName;

    /**
     * Constructor that initializes fields.
     * 
     * @param theX Vehicle's X location.
     * @param theY Vehicle's Y location.
     * @param theDir Vehicle's direction.
     * @param theDeathTimer Death timer on vehicle.
     * @param theVehicleName the String vehicle name.
     */
    protected AbstractVehicle(final int theX, final int theY, final Direction theDir,
                              final int theDeathTimer, final String theVehicleName) {
        myOriginalX = theX;
        myOriginalY = theY;

        myX = theX;
        myY = theY;

        myDir = theDir;
        myOriginalDir = theDir;

        myDeathTimer = theDeathTimer;
        myDeathTime = theDeathTimer;

        myVehicleName = theVehicleName;

    }

    /**
     * Checks if two vehicles have collided. The smaller death time vehicle
     * always loses against the longer death time vehicle.
     * 
     * @param theOther the other vehicle object
     */
    public final void collide(final Vehicle theOther) {
        if ((isAlive() && theOther.isAlive()) && (theOther.getDeathTime() < myDeathTime)) {
            myDeathTimer = 0;
        }
    }

    /**
     * Returns the death time.
     * 
     * @return myDeathTime the death time
     */
    public final int getDeathTime() {
        return myDeathTime;
    }

    /**
     * Returns the image file name that the GUI will use to draw that vehicle
     * object onto the map.
     * 
     * @return String the name of the image file, alive or dead
     */
    public final String getImageFileName() {
        final StringBuilder sb = new StringBuilder(toString());
        if (isAlive()) {
            sb.append(".gif");
        } else {
            sb.append("_dead.gif");
        }
        return sb.toString().toLowerCase();
    }

    /**
     * Gets the direction the vehicle object is facing.
     * 
     * @return Direction current direction
     */
    public final Direction getDirection() {
        return myDir;
    }

    /**
     * Gets the x-coordinate.
     * 
     * @return x-point of vehicle
     */
    public final int getX() {
        return myX;
    }

    /**
     * Gets the y-coordinate.
     * 
     * @return y-point of vehicle
     */
    public final int getY() {
        return myY;
    }

    /**
     * Checks to see if vehicle object is alive.
     * 
     * @return boolean true or false
     */
    public final boolean isAlive() {
        return myDeathTimer == myDeathTime;
    }

    /**
     * A command called by the GUI for each turn, allows dead vehicles to keep
     * track of respective death timers. Once the vehicle re-spawns, it gets set
     * to a random direction.
     */
    public final void poke() {

        if (isAlive()) {
            myDir = Direction.random();
        } else {
            myDeathTimer++;
        }
    }

    /**
     * A command to reset all vehicles to their original positions and
     * direction.
     */
    public final void reset() {
        myX = myOriginalX;
        myY = myOriginalY;
        myDeathTimer = myDeathTime;
        myDir = myOriginalDir;
    }

    /**
     * Sets the direction after vehicle is alive or is reset.
     * 
     * @param theDir The current direction of moving vehicle
     */
    public final void setDirection(final Direction theDir) {
        myDir = theDir;
    }

    /**
     * Sets the vehicle x-coordinate.
     * 
     * @param theX The given x location
     */
    public final void setX(final int theX) {
        myX = theX;

    }

    /**
     * Sets the vehicle y-coordinate.
     * 
     * @param theY The given y location
     */
    public final void setY(final int theY) {
        myY = theY;
    }

    /**
     * Returns the string representation of the vehicle name.
     * 
     * @return the String vehicle name
     */
    @Override
    public final String toString() {
        return myVehicleName;
    }

}
