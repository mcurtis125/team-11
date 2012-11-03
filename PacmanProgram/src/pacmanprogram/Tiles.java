/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanprogram;

/**
 *
 * @author Kevin
 */
public class Tiles {
    
    private double xCoordinate, yCoordinate, width, height;
    private int type;
    
    public Tiles(double xCoordinate,double yCoordinate, double width, double height, int type){
        this.xCoordinate=xCoordinate;
        this.yCoordinate=yCoordinate;
        this.width=width;
        this.height=height;
        this.type=type;
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
    
    public int getType(){
        return type;
    }
    
}