/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanprogram;

/**
 *
 * @author stavy92
 */
public class Characters {
    
    Pacman pacman;
    Ghost blinky;
    Ghost pinky;
    Ghost inky;
    Ghost clyde;
    
    public Characters(Pacman pacman, Ghost blinky, Ghost pinky, Ghost inky, Ghost clyde){
        this.pacman = pacman;
        this.blinky = blinky;
        this.inky = inky;
        this.pinky = pinky;
        this.clyde = clyde;
    }
    
    public void setMode(int elroyMode, int mode){
        blinky.setMode(elroyMode);
        inky.setMode(mode);
        pinky.setMode(mode);
        clyde.setMode(mode);
        pacman.setMode(mode);
    }
    
    public void resetPosition(){
        pacman.resetPosition();
        blinky.resetPosition();
        pinky.resetPosition();
        inky.resetPosition();
        clyde.resetPosition();
    }
    
}
