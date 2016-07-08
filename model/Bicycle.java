/*
 * TCSS 305 - Spring 2016 Assignment 3 - Easy Street
 */

package model;

import java.util.Map;

/**
 * Child class for the Bicycle vehicle.
 *
 * @author Tim Liu
 * @version 25 April 2016
 */

public class Bicycle extends AbstractVehicle implements Vehicle {
    /**
     * Bicycle re-spawn time after death.
     */
    private static final int DEATH_TIME = 30;

    /**
     * Individual Bicycle class constructor. Calls the super 
     * constructor, passes up the death time and vehicle name.
     * 
     * @param theX places the x-coordinate
     * @param theY places the y-coordinate
     * @param theDir Direction Bicycle would like to go
     */
    public Bicycle(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, DEATH_TIME, "Bicycle");

    }

    /**
     * Bicycle can only travel on STREET, CROSSWALK, or TRAIL terrain types.
     * Bicycles ignore GREEN lights.
     * Bicycles stop for all YELLOW or RED lights.
     * 
     * @param theTerrain The terrain status of bicycle.
     * @param theLight the Light status.
     * @return boolean true or false if Bicycal can pass.
     */
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean bikeCanPass = false;

        if (theTerrain == Terrain.LIGHT && theLight != Light.GREEN) {
            bikeCanPass = false;
      
        } else if (theTerrain == Terrain.CROSSWALK && theLight != Light.GREEN) {
            bikeCanPass = false;
        } else if (theTerrain == Terrain.TRAIL || theTerrain == Terrain.STREET 
                        || theTerrain == Terrain.CROSSWALK || theTerrain == Terrain.LIGHT) {
            bikeCanPass = true;
        }
        return bikeCanPass;

    }

    /**
     * Bicycle always prefers to go on the TRAIL.
     * If there is a TRAIL next to the Bicycle, it always turns and travels in that direction.
     * If there is no TRAIL straight, left or right of the Bicycle, 
     * it will prefer to travel straight on a STREET.
     * If it cannot move straight ahead, it turns left. If it cannot turn left, it turns right.
     * As a last resort, the Bicycle will reverse.
     * If stopped at a light, it stays still unless there's a trail to the left/right.
     * 
     * @param theNeighbors Adjacent terrain to the Bicycle.
     * @return Direction the Bicycle prefers to go.
     */
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        Direction bikeDir = getDirection();

        if (theNeighbors.get(bikeDir) == Terrain.STREET
            || theNeighbors.get(bikeDir) == Terrain.LIGHT
            || theNeighbors.get(bikeDir) == Terrain.CROSSWALK) {

            bikeDir = toTrail(theNeighbors);

        } else if (theNeighbors.get(bikeDir.right()) == Terrain.STREET
                 || theNeighbors.get(bikeDir.left()) == Terrain.STREET
                 || theNeighbors.get(bikeDir.right()) == Terrain.CROSSWALK
                 || theNeighbors.get(bikeDir.left()) == Terrain.CROSSWALK) {
           
            bikeDir = bikeDir.left();
            
        } else if (theNeighbors.get(bikeDir) == Terrain.STREET
                 && theNeighbors.get(bikeDir) != Terrain.CROSSWALK) {
            
            bikeDir = bikeDir.right();

        }

        return bikeDir;
    }

    /**
     * Helper method for bike to prioritize turning towards trails.
     * 
     * @param theNeighbors map set
     * @return bikeDir preferred bike direction
     */
    private Direction toTrail(final Map<Direction, Terrain> theNeighbors) {
        Direction bikeDir = getDirection();

        if (theNeighbors.get(bikeDir.right()) == Terrain.TRAIL) {
            bikeDir = bikeDir.right();

        } else if (theNeighbors.get(bikeDir.left()) == Terrain.TRAIL) {
            bikeDir = bikeDir.left();
        }
        return bikeDir;

    }

}
