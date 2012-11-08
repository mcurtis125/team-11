/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanprogram;

import java.awt.event.ActionEvent;
import pacmanprogram.Ghost.Name;

/**
 *
 * @author stavy92
 */
class GhostControl {
    private double x,y;
    Ghost ghost;
    Name name;
    
    public GhostControl(Ghost ghost){
        this.ghost = ghost;
        this.name = ghost.getName();
        switch(name){
            case Blinky:
                x=217;
                y=224;
                break;
            case Pinky:
                x=217;
                y=272;
                break;
            case Inky:
                x=184;
                y=272;
                break;
            case Clyde:
                x=248;
                y=272;
                break;    
           
        }
    }

    void refresh(ActionEvent e) {
        
    }
    
    public void getPosition(double[] pos){
        pos[0] = x;
        pos[1] = y;
    }
    
    public void frightened(){
        
    }
    
    public void scatter(){
        
    }
    
    public void chase(){
        
    }
}
