/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanprogram;

import java.awt.event.ActionEvent;

/**
 *
 * @author Kevin
 */
public class PositionVelocityGetter {
    
    PacmanControl pacControl;
    GhostControl blinkyControl;
    GhostControl pinkyControl;
    GhostControl inkyControl;
    GhostControl clydeControl;
    double[] pacmanPos = new double[2]; 
    double[] pacmanVel = new double[2]; 
    double[] blinkyPos = new double[2];
    
    
    public PositionVelocityGetter(PacmanControl pacControl, GhostControl blinkyControl, GhostControl pinkyControl, 
                                                                    GhostControl inkyControl, GhostControl clydeControl){
        this.pacControl=pacControl;
        this.blinkyControl=blinkyControl;
        this.pinkyControl=pinkyControl;
        this.inkyControl=inkyControl;
        this.clydeControl=clydeControl;
    }
    
    
    
    public void refresh(ActionEvent e){
        pacControl.getPosition(pacmanPos);
        pacControl.getVelocity(pacmanVel);
        blinkyControl.getPosition(blinkyPos);
        
        blinkyControl.givePacmanPos(pacmanPos);
        blinkyControl.givePacmanVel(pacmanVel);
        
        pinkyControl.givePacmanPos(pacmanPos);
        pinkyControl.givePacmanVel(pacmanVel);
        
        inkyControl.givePacmanPos(pacmanPos);
        inkyControl.givePacmanVel(pacmanVel);
        
        clydeControl.givePacmanPos(pacmanPos);
        clydeControl.givePacmanVel(pacmanVel);
        
        inkyControl.giveBlinkyPos(blinkyPos);
    }
    
    
    
    
    
}
