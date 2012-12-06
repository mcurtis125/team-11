/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmangameplay.charactercontrol;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import pacmangameplay.mazedisplay.Maze;

/**
 * Object of all the characters of the Pacman game: Pacman and the Ghosts Blinky, Pinky, Inky and Clyde.
 * @author stavy92
 */
public class Characters {
    
    public Pacman pacman;
    public Ghost blinky;
    public Ghost pinky;
    public Ghost inky;
    public Ghost clyde;
    
    public ArrayList<Ghost> ghosts = new ArrayList<Ghost>();
    
    public Characters(Maze maze){
        pacman = new Pacman(maze);
        blinky = new Ghost(Ghost.Name.Blinky, maze);
        pinky = new Ghost(Ghost.Name.Pinky, maze);
        inky = new Ghost(Ghost.Name.Inky, maze);
        clyde = new Ghost(Ghost.Name.Clyde, maze);
        ghosts.add(pinky);
        ghosts.add(inky);
        ghosts.add(clyde);
        ghosts.add(blinky);
    }
    
    /**
     * Draws the characters on the Game JPanel.
     * @param g 
     */
    public void draw(Graphics g){
        pacman.draw(g);
        blinky.draw(g);
        pinky.draw(g);
        inky.draw(g);
        clyde.draw(g);
    }
    
    /**
     * Updates all the information associated with all the characters.
     * @param ae 
     */
    public void refresh(ActionEvent ae){
        pacman.refresh(ae);
        blinky.refresh(ae);
        pinky.refresh(ae);
        inky.refresh(ae);
        clyde.refresh(ae);
    }
    
    /**
     * Sets the modes of all the characters.
     * @param elroyMode Blinky's mode when "Cruise Elroy" is on.
     * @param mode Pacman, Pinky, Inky and Clyde's mode.
     */
    public void setMode(int elroyMode, int mode){
        blinky.setMode(elroyMode);
        inky.setMode(mode);
        pinky.setMode(mode);
        clyde.setMode(mode);
        pacman.setMode(mode);
    }
    
    /**
     * Resets the characters to their starting position.
     */
    public void resetPosition(){
        pacman.resetPosition();
        blinky.resetPosition();
        pinky.resetPosition();
        inky.resetPosition();
        clyde.resetPosition();
    }
    
}
