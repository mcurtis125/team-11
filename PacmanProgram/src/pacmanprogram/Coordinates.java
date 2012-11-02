/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanprogram;

/**
 *
 * @author Kevin
 */
public class Coordinates {
    
    private double xCoordinate, yCoordinate, width, height;
    
    public Coordinates(double xCoordinate,double yCoordinate, double width, double height){
        this.xCoordinate=xCoordinate;
        this.yCoordinate=yCoordinate;
        this.width=width;
        this.height=height;
    }
    
    public double getXCoordinate(){
        return xCoordinate; 
    }
    
    
    public double getYCoordinate(){
        return yCoordinate; 
    }
    
    public double getWidth(){
        return width; 
    }
    
    public double getHeight(){
        return height; 
    }
    
    
}