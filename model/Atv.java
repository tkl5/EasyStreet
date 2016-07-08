/*
 * TCSS 305 - Spring 2016 Assignment 3 - Easy Street
 */

package model;

import java.util.Map;

/**
 * Child class for the ATV vehicle.
 * 
 * @author Tim Liu
 * @version 25 April 2016
 */
public class Atv extends AbstractVehicle implements Vehicle {

    /**
     * ATV re-spawn time after death.
     */
    private static final int DEATH_TIME = 20;

    /**
     * Individual ATV class constructor. Calls the super 
     * constructor, passes up the death time and vehicle name.
     * 
     * @param theX places the x-coordinate
     * @param theY places the y-coordinate
     * @param theDir Direction ATV would like to go
     */
    public Atv(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, DEATH_TIME, "Atv");

    }

    /**
     * ATVs can travel on any terrain except WALLs.
     * 
     * @param theTerrain the Terrain status of ATV.
     * @param theLight the Light status.
     * @return boolean true or false if ATC can pass.
     */
    public boolean canPass(final Terrain theTerrain, final Light theLight) {

        return !(theTerrain == Terrain.WALL);
    }

    /**
     * ATVs randomly select to go straight, turn left, or turn right. ATVs never
     * reverse direction. ATV's drive through all traffic lights and cross-walk
     * lights without stopping.
     * 
     * @param theNeighbors The adjacent terrain to the ATV.
     * @return Direction the ATV would prefer to go.
     */
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        Direction atvRandDir = Direction.random();

        if (canPass(Terrain.STREET, Light.GREEN)) {
            while (theNeighbors.get(atvRandDir) == Terrain.WALL
                   || atvRandDir == getDirection().reverse()) {
                atvRandDir = Direction.random();
            }

        } else {
            atvRandDir = getDirection().reverse();
        }
        return atvRandDir;

    }

}
