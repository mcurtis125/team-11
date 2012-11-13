/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanprogram;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;

/**
 *
 * @author Kevin
 */

//this class is used to generate the map and modify the map
//all tiles, path, dots, energizer are implemented by this class

//also, the action of eating dots and energizer are also implemented in this class

public class Walls{
    
    ArrayList<Tiles> tiles = new ArrayList<Tiles>();
    
//this is the map file of our maze
//it is an array with 36*28(the dimension of the maze) numbers
//each number represents different tile components as follows:
//0=wall;1=path(empty);2=dot;3=energizer
//varing this will change the displayed maze map

    int[] map = {
    			
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
    			0,0,0,0,0,0,2,0,0,1,0,1,1,1,1,1,1,0,1,0,0,2,0,0,0,0,0,0,
    			1,1,1,1,1,1,2,1,1,1,0,1,1,1,1,1,1,0,1,1,1,2,1,1,1,1,1,1,
    			0,0,0,0,0,0,2,0,0,1,0,1,1,1,1,1,1,0,1,0,0,2,0,0,0,0,0,0,
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

    
//the following method will implement the above map into an arraylist
//this arraylist is declared in Tiles.java
    
    public Walls(){
        int a; 
    	int b; 
    	for (a=0;a<36;a++){  //this is the x-axis size of the map
    	  for (b=0;b<28;b++){	//this is the y-axis size of the map
                tiles.add(new Tiles(16*b,16*a,16,16,map[b+28*(a)]));
    	  };
    	};
    }
   
    
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
    
    public int getType(int index){
        return map[index];
    }
    
    public int getIndex(double xCoord, double yCoord){
        int col, row;
        col = (int)(xCoord+8)/16;
        row = (int)(yCoord+8)/16;
        return col+28*row;
    }
    
    public void changeType(int index, int originalType, int type){
        if(map[index]==originalType){
            map[index]=type;
            tiles.get(index).setType(type);
        }


    } 
    
    public int[] getMap(){
        return map;
    }
     
    public ArrayList<Tiles> getWallCoords(){
        ArrayList<Tiles> walls = new ArrayList<Tiles>();;
        int i;
        for(i=0;i<tiles.size();i++){
            if (tiles.get(i).getType()==0){
                walls.add(tiles.get(i));
            }
        }
        return walls;
    }
}