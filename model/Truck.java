/*
 * TCSS 305 - Spring 2016 
 * Assignment 3 - Easy Street
 */
package model;


import java.util.Map;

/**
 * Child class for the Truck vehicle.
 *
 * @author Tim Liu
 * @version 25 April 2016
 */
public class Truck extends AbstractVehicle implements Vehicle {

    /**
     * Individual truck class constructor. Calls the super 
     * constructor, passes up the death time and vehicle name.
     * 
     * @param theX places the x-coordinate.
     * @param theY places the y-coordinate.
     * @param theDir direction Truck would like to move.
     * 
     */
    public Truck(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, 0, "Truck");
    }
    
    /**
     * Checks if Truck can pass a certain type of Terrain. Trucks can travel through the
     * STREET, CROSSWALK, or LIGHT terrain type. Trucks will only stop at RED CROSSWALK lights.
     * 
     * @param theTerrain The terrain status of the Truck.
     * @param theLight Light status.
     * @return boolean true or false.
     */
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean truckCanPass = false;
        if (theTerrain == Terrain.CROSSWALK && theLight == Light.RED) {
            truckCanPass = false;

        } else if (theTerrain == Terrain.STREET || theTerrain == Terrain.LIGHT
                 || theTerrain == Terrain.CROSSWALK) {
            truckCanPass = true;
        }
        return truckCanPass;

    }
    
    /**
     * Trucks randomly select to go left, right, or forward. Trucks only reverse
     * if it can no longer move in any of the three directions.
     * 
     * @param theNeighbors Adjacent terrain to the Truck.
     * @return Direction the Truck decides to go.
     */
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        Direction randTruckDir = Direction.random();
        if (canPass(Terrain.STREET, Light.GREEN)) {

            if (canTruckReverse(theNeighbors)) {
                randTruckDir = getDirection().reverse();
                
            } else {
                while (theNeighbors.get(randTruckDir) != Terrain.STREET
                       && theNeighbors.get(randTruckDir) != Terrain.CROSSWALK
                       && theNeighbors.get(randTruckDir) != Terrain.LIGHT
                       || randTruckDir == getDirection().reverse()) {
                    randTruckDir = Direction.random();
                }
            }
        }

        return randTruckDir;

    }
    
    /**
     * Helper method for truck to see whether it can reverse.
     * 
     * @param theNeighbors the map set.
     * @return canReverse true or false if truck can reverse.
     */
    private boolean canTruckReverse(final Map<Direction, Terrain> theNeighbors) {
        final Direction truckDir = getDirection();
        
        return theNeighbors.get(getDirection()) != Terrain.STREET
                        && theNeighbors.get(truckDir) != Terrain.LIGHT
                        && theNeighbors.get(truckDir.right()) != Terrain.LIGHT
                        && theNeighbors.get(truckDir.left()) != Terrain.LIGHT
                        && theNeighbors.get(truckDir) != Terrain.CROSSWALK
                        && theNeighbors.get(truckDir.right()) != Terrain.CROSSWALK
                        && theNeighbors.get(truckDir.left()) != Terrain.CROSSWALK
                        && (theNeighbors.get(truckDir.right()) != Terrain.STREET)
                        && (theNeighbors.get(truckDir.left()) != Terrain.STREET);
    }
}
