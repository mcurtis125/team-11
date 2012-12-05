/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmangameplay.mazedisplay;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;

/**
 * This class displays and updates the maze.
 * 
 * The maze is an ArrayList<Tiles>.
 * 
 * The types of the tiles are in integer form:
 * 0 wall
 * 1 path
 * 2 dot
 * 3 energizer
 * 
 * @author Kevin
 */
public class Maze{
    
    ArrayList<Tile> tiles = new ArrayList<Tile>();
    public static final double sizeOfTiles=16;
    
    int[] maze = {
    			//0 wall
    			//1 path
    			//2 dot
    			//3 energizer
    			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
    			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
    			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
    			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
    			0,2,2,2,2,2,2,2,2,2,2,2,2,0,0,2,2,2,2,2,2,2,2,2,2,2,2,0,
    			0,2,0,0,0,0,2,0,0,0,0,0,2,0,0,2,0,0,0,0,0,2,0,0,0,0,2,0,
    			0,3,0,0,0,0,2,0,0,0,0,0,2,0,0,2,0,0,0,0,0,2,0,0,0,0,3,0,
    			0,2,0,0,0,0,2,0,0,0,0,0,2,0,0,2,0,0,0,0,0,2,0,0,0,0,2,0,
    			0,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,0,
    			0,2,0,0,0,0,2,0,0,2,0,0,0,0,0,0,0,0,2,0,0,2,0,0,0,0,2,0,
    			0,2,0,0,0,0,2,0,0,2,0,0,0,0,0,0,0,0,2,0,0,2,0,0,0,0,2,0,
    			0,2,2,2,2,2,2,0,0,2,2,2,2,0,0,2,2,2,2,0,0,2,2,2,2,2,2,0,
    			0,0,0,0,0,0,2,0,0,0,0,0,1,0,0,1,0,0,0,0,0,2,0,0,0,0,0,0,
    			0,0,0,0,0,0,2,0,0,0,0,0,1,0,0,1,0,0,0,0,0,2,0,0,0,0,0,0,
    			0,0,0,0,0,0,2,0,0,1,1,1,1,1,1,1,1,1,1,0,0,2,0,0,0,0,0,0,
    			0,0,0,0,0,0,2,0,0,1,0,0,0,1,1,0,0,0,1,0,0,2,0,0,0,0,0,0,
    			0,0,0,0,0,0,2,0,0,1,0,0,0,1,1,0,0,0,1,0,0,2,0,0,0,0,0,0,
    			1,1,1,1,1,1,2,1,1,1,0,0,1,1,1,1,0,0,1,1,1,2,1,1,1,1,1,1,
    			0,0,0,0,0,0,2,0,0,1,0,0,0,0,0,0,0,0,1,0,0,2,0,0,0,0,0,0,
    			0,0,0,0,0,0,2,0,0,1,0,0,0,0,0,0,0,0,1,0,0,2,0,0,0,0,0,0,
    			0,0,0,0,0,0,2,0,0,1,1,1,1,1,1,1,1,1,1,0,0,2,0,0,0,0,0,0,
    			0,0,0,0,0,0,2,0,0,1,0,0,0,0,0,0,0,0,1,0,0,2,0,0,0,0,0,0,
    			0,0,0,0,0,0,2,0,0,1,0,0,0,0,0,0,0,0,1,0,0,2,0,0,0,0,0,0,
    			0,2,2,2,2,2,2,2,2,2,2,2,2,0,0,2,2,2,2,2,2,2,2,2,2,2,2,0,
    			0,2,0,0,0,0,2,0,0,0,0,0,2,0,0,2,0,0,0,0,0,2,0,0,0,0,2,0,
    			0,2,0,0,0,0,2,0,0,0,0,0,2,0,0,2,0,0,0,0,0,2,0,0,0,0,2,0,
        		0,3,2,2,0,0,2,2,2,2,2,2,2,1,1,2,2,2,2,2,2,2,0,0,2,2,3,0,
        		0,0,0,2,0,0,2,0,0,2,0,0,0,0,0,0,0,0,2,0,0,2,0,0,2,0,0,0,
        		0,0,0,2,0,0,2,0,0,2,0,0,0,0,0,0,0,0,2,0,0,2,0,0,2,0,0,0,
        		0,2,2,2,2,2,2,0,0,2,2,2,2,0,0,2,2,2,2,0,0,2,2,2,2,2,2,0,
        		0,2,0,0,0,0,0,0,0,0,0,0,2,0,0,2,0,0,0,0,0,0,0,0,0,0,2,0,
        		0,2,0,0,0,0,0,0,0,0,0,0,2,0,0,2,0,0,0,0,0,0,0,0,0,0,2,0,
        		0,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,0,
        		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
        		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
        		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 			
    		}; 
    
    private int[] penTileIndexes = {430,431,432,433,434,435,436,437,458,459,460,461,462,463,464,465,486,487,488,489,490,491,492,493,514,515,516,517,518,519,520,521,542,543,544,545,546,547,548,549};
    private int[] tunnelTileIndexes = {476,477,478,479,480,481,498,499,500,501,502,503};

    public Maze(){
        int a; 
    	int b;
    	for (a=0;a<36;a++){
    	  for (b=0;b<28;b++){
                tiles.add(new Tile(sizeOfTiles*b,sizeOfTiles*a,sizeOfTiles,sizeOfTiles,maze[b+28*(a)]));
    	  };
    	};
    }
   
    /**
     * Draws the maze.
     * @param g 
     */
    public void draw(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        //g2.setColor(Color.BLUE);
     
        int loopCounter;
        
        for(loopCounter=0;loopCounter<tiles.size();loopCounter++){
        	if (tiles.get(loopCounter).getType()==0){
                    g2.setColor(Color.BLUE );
                    g2.fill(new Rectangle.Double(tiles.get(loopCounter).getXCoordinate(),
                        tiles.get(loopCounter).getYCoordinate(),tiles.get(loopCounter).getWidth(),
                        tiles.get(loopCounter).getHeight()));}
                else if (tiles.get(loopCounter).getType()==1){
                    g2.setColor(Color.BLACK );
                    g2.fill(new Ellipse2D.Double(tiles.get(loopCounter).getXCoordinate(),
                        tiles.get(loopCounter).getYCoordinate(),tiles.get(loopCounter).getWidth(),
                        tiles.get(loopCounter).getHeight()));
                }
        	else if (tiles.get(loopCounter).getType()==2){
                    g2.setColor(Color.LIGHT_GRAY );
                    g2.fill(new Ellipse2D.Double(tiles.get(loopCounter).getXCoordinate()+6.5,
                        tiles.get(loopCounter).getYCoordinate()+6.5,3,3));
                }
        	else if (tiles.get(loopCounter).getType()==3){
                    g2.setColor(Color.LIGHT_GRAY );
                    g2.fill(new Ellipse2D.Double(tiles.get(loopCounter).getXCoordinate()+1,
                        tiles.get(loopCounter).getYCoordinate()+1,14,14));
                }

        }
    }
    /**
     * Returns the indexes in the map array of the ghost pen.
     * @return array of tile indexes
     */
    public int[] getPenTileIndexes(){
        return penTileIndexes;
    }
    
    /**
     * Returns the indexes in the map array of the tunnel.
     * @return array of tile indexes
     */
    public int[] getTunnelTileIndexes(){
        return tunnelTileIndexes;
    }
    
    /**
     * Returns the tiles that are walls.
     * @return wall tiles
     */
    public ArrayList<Tile> getWallCoords(){
        ArrayList<Tile> walls = new ArrayList<Tile>();;
        int i;
        for(i=0;i<tiles.size();i++){
            if (tiles.get(i).getType()==0){
                walls.add(tiles.get(i));
            }
        }
        return walls;
    }
     
    /**
     * Returns the coordinates of the energizers.
     * @return array of coordinates
     */
    public double[][] getEnergizerCoords(){
        double[][] eCoords = {{16,96},{416,96},{16,416},{416,416}};
        return eCoords;
    } 
    
    /**
     * Returns the index of the tile at (xCoord, yCoord).
     * @param xCoord
     * @param yCoord
     * @return index
     */
    public int getIndex(double xCoord, double yCoord){
        int col, row;
        col = (int)(xCoord+8)/16;
        row = (int)(yCoord+8)/16;
        return col+28*row;
    }
    
    
    /**
     * Returns the type of the tile at index.
     * @param index
     * @return type as integer
     */
    public int getType(int index){
        return maze[index];
    }
   
    /**
     * Changes the type of the tile at index from its current type to type.
     * @param index index of the tile to be changed
     * @param originalType current type
     * @param type new type
     */
    public void changeType(int index, int originalType, int type){
        if(maze[index]==originalType){
            maze[index]=type;
            tiles.get(index).setType(type);
        }
    } 
    
    /**
     * Returns the whole map of the maze.
     * @return 
     */
    public int[] getMap(){
        return maze;
    }
    
    /**
     * Resets the maze.
     */
    public void resetMaze(){
        ArrayList<Tile> newTiles = new ArrayList<Tile>();
        int[] newMaze = {
    			//0 wall
    			//1 path
    			//2 dot
    			//3 energizer
    			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
    			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
    			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
    			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
    			0,2,2,2,2,2,2,2,2,2,2,2,2,0,0,2,2,2,2,2,2,2,2,2,2,2,2,0,
    			0,2,0,0,0,0,2,0,0,0,0,0,2,0,0,2,0,0,0,0,0,2,0,0,0,0,2,0,
    			0,3,0,0,0,0,2,0,0,0,0,0,2,0,0,2,0,0,0,0,0,2,0,0,0,0,3,0,
    			0,2,0,0,0,0,2,0,0,0,0,0,2,0,0,2,0,0,0,0,0,2,0,0,0,0,2,0,
    			0,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,0,
    			0,2,0,0,0,0,2,0,0,2,0,0,0,0,0,0,0,0,2,0,0,2,0,0,0,0,2,0,
    			0,2,0,0,0,0,2,0,0,2,0,0,0,0,0,0,0,0,2,0,0,2,0,0,0,0,2,0,
    			0,2,2,2,2,2,2,0,0,2,2,2,2,0,0,2,2,2,2,0,0,2,2,2,2,2,2,0,
    			0,0,0,0,0,0,2,0,0,0,0,0,1,0,0,1,0,0,0,0,0,2,0,0,0,0,0,0,
    			0,0,0,0,0,0,2,0,0,0,0,0,1,0,0,1,0,0,0,0,0,2,0,0,0,0,0,0,
    			0,0,0,0,0,0,2,0,0,1,1,1,1,1,1,1,1,1,1,0,0,2,0,0,0,0,0,0,
    			0,0,0,0,0,0,2,0,0,1,0,0,0,1,1,0,0,0,1,0,0,2,0,0,0,0,0,0,
    			0,0,0,0,0,0,2,0,0,1,0,0,0,1,1,0,0,0,1,0,0,2,0,0,0,0,0,0,
    			1,1,1,1,1,1,2,1,1,1,0,0,1,1,1,1,0,0,1,1,1,2,1,1,1,1,1,1,
    			0,0,0,0,0,0,2,0,0,1,0,0,0,0,0,0,0,0,1,0,0,2,0,0,0,0,0,0,
    			0,0,0,0,0,0,2,0,0,1,0,0,0,0,0,0,0,0,1,0,0,2,0,0,0,0,0,0,
    			0,0,0,0,0,0,2,0,0,1,1,1,1,1,1,1,1,1,1,0,0,2,0,0,0,0,0,0,
    			0,0,0,0,0,0,2,0,0,1,0,0,0,0,0,0,0,0,1,0,0,2,0,0,0,0,0,0,
    			0,0,0,0,0,0,2,0,0,1,0,0,0,0,0,0,0,0,1,0,0,2,0,0,0,0,0,0,
    			0,2,2,2,2,2,2,2,2,2,2,2,2,0,0,2,2,2,2,2,2,2,2,2,2,2,2,0,
    			0,2,0,0,0,0,2,0,0,0,0,0,2,0,0,2,0,0,0,0,0,2,0,0,0,0,2,0,
    			0,2,0,0,0,0,2,0,0,0,0,0,2,0,0,2,0,0,0,0,0,2,0,0,0,0,2,0,
        		0,3,2,2,0,0,2,2,2,2,2,2,2,1,1,2,2,2,2,2,2,2,0,0,2,2,3,0,
        		0,0,0,2,0,0,2,0,0,2,0,0,0,0,0,0,0,0,2,0,0,2,0,0,2,0,0,0,
        		0,0,0,2,0,0,2,0,0,2,0,0,0,0,0,0,0,0,2,0,0,2,0,0,2,0,0,0,
        		0,2,2,2,2,2,2,0,0,2,2,2,2,0,0,2,2,2,2,0,0,2,2,2,2,2,2,0,
        		0,2,0,0,0,0,0,0,0,0,0,0,2,0,0,2,0,0,0,0,0,0,0,0,0,0,2,0,
        		0,2,0,0,0,0,0,0,0,0,0,0,2,0,0,2,0,0,0,0,0,0,0,0,0,0,2,0,
        		0,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,0,
        		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
        		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
        		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 			
    		}; 
        int a; 
    	int b;
    	for (a=0;a<36;a++){
    	  for (b=0;b<28;b++){
                newTiles.add(new Tile(sizeOfTiles*b,sizeOfTiles*a,sizeOfTiles,sizeOfTiles,newMaze[b+28*(a)]));
    	  };
    	};
        maze = newMaze;
        tiles = newTiles;
    }
}
