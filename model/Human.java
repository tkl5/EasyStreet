/*
 * TCSS 305 - Spring 2016 Assignment 3 - Easy Street
 */

package model;

import java.util.Map;

/**
 * Child class for the Human vehicle.
 * 
 * @author Tim Liu
 * @version 25 April 2016
 */
public final class Human extends AbstractVehicle implements Vehicle {

    /**
     * Human re-spawn time after death.
     */
    private static final int DEATH_TIME = 50;

    /**
     * Individual human class constructor. Calls the super 
     * constructor, passes up the death time and vehicle name.
     * 
     * @param theX places the x-coordinate
     * @param theY places the y-coordinate
     * @param theDir the Direction human would like to go
     */
    public Human(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, DEATH_TIME, "Human");
    }


    /**
     * Humans can only travel on GRASS and CROSSWALKS.
     * Humans only stop at CROSSWALKS when the LIGHT is GREEN.
     * Humans ignore all other traffic lights.
     * 
     * @param theTerrain the Terrain status of the Human.
     * @param theLight the Light status.
     * @return boolean true or false if Human can pass.
     */
    public  boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean humanCanPass = false;
        if (theTerrain == Terrain.CROSSWALK && theLight == Light.GREEN) {
            humanCanPass = false;

        } else if (theTerrain == Terrain.GRASS || theTerrain == Terrain.CROSSWALK) {
            humanCanPass = true;
        }

        return humanCanPass;

    }

    /**
     * Humans randomly select to go left, right, or forward. 
     * Humans only reverse if it can no longer move in any of the three directions.
     * Humans always prefer to travel on CROSSWALKS. 
     * If a human is near a CROSSWALK, it will always turn and face that direction.
     * 
     * @param theNeighbors Adjacent terrain to the Human.
     * @return Direction the human decides to go.
     */
    public  Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        Direction randHumanDir = Direction.random();
        randHumanDir = toCrossWalk(theNeighbors);
        if (canPass(Terrain.GRASS, Light.YELLOW)) {
            
            if (canHumanReverse(theNeighbors)) {
                randHumanDir = getDirection().reverse();

            } else {
                while (theNeighbors.get(randHumanDir) != Terrain.GRASS
                       && theNeighbors.get(randHumanDir) != Terrain.CROSSWALK
                       || randHumanDir == getDirection().reverse()) {
                    randHumanDir = Direction.random();
                }
            }
        }

        return randHumanDir;
    }

    /**
     * Helper method for human to prioritize turning towards cross-walks.
     * 
     * @param theNeighbors map set.
     * @return humanDir preferred human direction.
     */
    private Direction toCrossWalk(final Map<Direction, Terrain> theNeighbors) {
        final Direction humanDir = getDirection();
        Direction randHumanDir = Direction.random();

        if (theNeighbors.get(humanDir.right()) == Terrain.CROSSWALK) {
            randHumanDir = humanDir.right();
        } else if (theNeighbors.get(humanDir.left()) == Terrain.CROSSWALK) {
            randHumanDir = humanDir.left();
        } else if (theNeighbors.get(humanDir) == Terrain.CROSSWALK) {
            randHumanDir = getDirection();
        } 
        return randHumanDir;
    }

    /**
     * Helper method that checks if surrounding terrain options (left, right, forward)
     * are illegal, it triggers the human to reverse in direction.
     * 
     * @param theNeighbors map set of adjacent terrain.
     * @return boolean true or false.
     */
    private boolean canHumanReverse(final Map<Direction, Terrain> theNeighbors) {
        final Direction humanDir = getDirection();
        
        return theNeighbors.get(getDirection()) != Terrain.GRASS
               && theNeighbors.get(humanDir.right()) != Terrain.GRASS
               && theNeighbors.get(humanDir.left()) != Terrain.GRASS
               && theNeighbors.get(humanDir) != Terrain.CROSSWALK
               && theNeighbors.get(humanDir.right()) != Terrain.CROSSWALK
               && theNeighbors.get(humanDir.left()) != Terrain.CROSSWALK;
    }

}
