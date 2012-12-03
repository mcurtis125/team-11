/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanprogram;

import java.util.ArrayList;

/**
 * All the characters of the Pacman game: Pacman and the Ghosts Blinky, Pinky, Inky and Clyde.
 * @author stavy92
 */
public class Characters {
    
    Pacman pacman;
    Ghost blinky;
    Ghost pinky;
    Ghost inky;
    Ghost clyde;
    
    ArrayList<Ghost> ghosts = new ArrayList<Ghost>();
    
    public Characters(Pacman pacman, Ghost blinky, Ghost pinky, Ghost inky, Ghost clyde){
        this.pacman = pacman;
        this.blinky = blinky;
        this.inky = inky;
        this.pinky = pinky;
        this.clyde = clyde;
        ghosts.add(pinky);
        ghosts.add(inky);
        ghosts.add(clyde);
        ghosts.add(blinky);
    }
    
    /**
     * Sets the modes of all the characters.
     * @param elroyMode Blinky's mode.
     * @param mode Everyone else's mode.
     */
    public void setMode(int elroyMode, int mode){
        blinky.setMode(elroyMode);
        inky.setMode(mode);
        pinky.setMode(mode);
        clyde.setMode(mode);
        pacman.setMode(mode);
    }
    
    /**
     * Resets characters to their starting position.
     */
    public void resetPosition(){
        pacman.resetPosition();
        blinky.resetPosition();
        pinky.resetPosition();
        inky.resetPosition();
        clyde.resetPosition();
    }
    
}
