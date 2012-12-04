/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanprogram;

/**
 * Types:
 * 0 wall
 * 1 path
 * 2 dot
 * 3 energizer
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
    
    /**
     * Returns x position of tile.
     * @return x coord
     */
    public double getXCoordinate(){
        return xCoordinate; 
    }
    
    /**
     * Returns y position of tile.
     * @return y coord
     */
    public double getYCoordinate(){
        return yCoordinate; 
    }
    
    /**
     * Returns width of tile.
     * @return width
     */
    public double getWidth(){
        return width; 
    }
    
    /**
     * Returns height of tile.
     * @return height
     */
    public double getHeight(){
        return height; 
    }
    
    /**
     * Returns type of tile.
     * @return type as integer
     */
    public int getType(){
        return type;
    }
    
    /**
     * Sets tile's type to type.
     * @param type type as integer
     */
    public void setType(int type){
        this.type = type;
    }
    
}