package pacmanprogram;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author stavy92
 */
public class Tile {
    public enum Type{dead, legal};
    static final double HEIGHT = 16;
    static final double WIDTH = 16;
    private Type type;
    private boolean isOccuppied;
    private int numberOfExits;
    private double xLeft, xRight, yUp, yDown;
    
    public Tile(double xL, double yU, Type type){
        this.xLeft = xL;
        this.xRight = xL+WIDTH;
        this.yUp = yU;
        this.yDown = yU+HEIGHT;
        this.type = type;
    }

    
    public void getCoordinates(double[] position){
        position[0] = xLeft;
        position[1] = yUp;
    }
    
    public double getXL(){
        return xLeft;
    }
    
    public double getXR(){
        return xRight;
    }
    
    public double getYU(){
        return yUp;
    }
    
    public double getYD(){
        return yDown;
    }
    
}
