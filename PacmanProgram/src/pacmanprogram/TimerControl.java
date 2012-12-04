/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanprogram;

/**
 * Checks if any 2 times or positions are close enough to be considered equal.
 * @author stavy92
 */
public class TimerControl {
    
    private static final double TIME_TOLERANCE = 0.008140;
    private static final double POSITION_TOLERANCE = 6;
    
    /**
     * Checks if num1 equals num2 with a tolerance.
     * @param num1
     * @param num2
     * @return 
     */
    public static boolean timeCheck(double num1, double num2){
        return ((num1 > (num2-TIME_TOLERANCE)) && (num1 < (num2+TIME_TOLERANCE)));
    }
   
    /**
     * Checks if pos1 equals pos2 with a tolerance.
     * @param num1
     * @param num2
     * @return 
     */
    public static boolean positionCheck(double pos1, double pos2){
        return ((pos1 > (pos2-POSITION_TOLERANCE)) && (pos1 < (pos2+POSITION_TOLERANCE)));
    }
    
}

