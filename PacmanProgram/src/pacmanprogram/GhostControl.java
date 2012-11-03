/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanprogram;

import java.awt.event.ActionEvent;

/**
 *
 * @author stavy92
 */
class GhostControl {
    private double x,y;
    
    public GhostControl(Ghost ghost){
        
    }

    void refresh(ActionEvent e) {
        
    }
    
    public void getPosition(double[] pos){
        pos[0] = x;
        pos[1] = y;
    }
    
    
}
