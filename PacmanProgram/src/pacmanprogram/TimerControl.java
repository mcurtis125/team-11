/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanprogram;

/**
 *
 * @author stavy92
 */
public class TimerControl {
    
    private static final double TIME_TOLERANCE = 0.008146;
    private static final double POSITION_TOLERANCE = 6;
    
    
    public static boolean timeCheck(double num1, double num2){
        return ((num1 > (num2-TIME_TOLERANCE)) && (num1 < (num2+TIME_TOLERANCE)));
    }
   
    public static boolean positionCheck(double pos1, double pos2){
        return ((pos1 > (pos2-POSITION_TOLERANCE)) && (pos1 < (pos2+POSITION_TOLERANCE)));
    }
    
}

