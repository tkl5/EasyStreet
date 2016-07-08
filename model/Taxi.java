/*
 * TCSS 305 - Spring 2016 
 * Assignment 3 - Easy Street
 */

package model;

import java.util.Map;

/**
 * Child class for the Taxi vehicle.
 *
 * @author Tim Liu
 * @version 25 April 2016
 */
public class Taxi extends AbstractVehicle implements Vehicle {

    /**
     * Taxi re-spawn time after death.
     */
    private static final int DEATH_TIME = 10;

    /**
     * Individual Taxi class constructor. Calls the super 
     * constructor, passes up the death time and vehicle name.
     * 
     * @param theX places the x-coordinate
     * @param theY places the y-coordinate
     * @param theDir Direction Taxi would like to go
     */
    public Taxi(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, DEATH_TIME, "Taxi");

    }


    /**
     * Taxis can only travel through STREET, CROSSWALK, and LIGHT terrain types.
     * Taxis only stop at RED traffic lights. It does not turn to avoid the light.
     * Taxis ignore all CROSSWALK lights.
     *  
     * @param theTerrain the Terrain status of the Taxi.
     * @param theLight the Light status.
     * @return boolean true or false if Taxi can travel through the terrain type.
     */
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean taxiCanPass = false;

        if (theTerrain == Terrain.LIGHT && theLight == Light.RED) {
            taxiCanPass = false;

        } else if (theTerrain == Terrain.STREET || theTerrain == Terrain.CROSSWALK
                 || theTerrain == Terrain.LIGHT) {

            taxiCanPass = true;
        }

        return taxiCanPass;
    }


    /**
     * Taxis always prefer to go straight. If it cannot go straight, it turns left.
     * If the Taxi cannot turn left, it turns right.
     * As a last resort, it reverses direction.
     * 
     * @param theNeighbors The adjacent terrain to the Taxi.
     * @return Direction the Direction the Taxi prefers to go.
     */
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        Direction taxiDir = getDirection();

        if (theNeighbors.get(taxiDir) == Terrain.STREET
            || theNeighbors.get(taxiDir) == Terrain.CROSSWALK
            || theNeighbors.get(taxiDir) == Terrain.LIGHT) {
            taxiDir = getDirection();

        } else if (canTurn(theNeighbors)) {

            if (theNeighbors.get(taxiDir.left()) != Terrain.STREET
                && theNeighbors.get(taxiDir.left()) != Terrain.CROSSWALK
                && theNeighbors.get(taxiDir.left()) != Terrain.LIGHT) {
                taxiDir = taxiDir.right();

            }
            taxiDir = taxiDir.left();

        } else {
            taxiDir = taxiDir.reverse();

        }
        return taxiDir;

    }

    /**
     * Helper method for taxi to see if conditions are met for it to turn left
     * or right.
     * 
     * @param theNeighbors map set
     * @return true or false
     */
    private boolean canTurn(final Map<Direction, Terrain> theNeighbors) {
        final Direction taxiDir = getDirection();

        return theNeighbors.get(taxiDir.right()) == Terrain.STREET
               || theNeighbors.get(taxiDir.left()) == Terrain.STREET
               || theNeighbors.get(taxiDir.right()) == Terrain.CROSSWALK
               || theNeighbors.get(taxiDir.left()) == Terrain.CROSSWALK
               || theNeighbors.get(taxiDir.right()) == Terrain.LIGHT
               || theNeighbors.get(taxiDir.left()) == Terrain.LIGHT;

    }

}
