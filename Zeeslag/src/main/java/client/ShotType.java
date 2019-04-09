/*
 * Sea Battle Start project.
 */
package client;

/**
 * Indicate the result of a shot.
 * @author Nico Kuijpers
 */
public enum ShotType {
    MISSED,   // Shot missed
    HIT,      // Shot hit
    SUNK,     // Ship sunk
    ALLSUNK;  // All ships sunk
}
