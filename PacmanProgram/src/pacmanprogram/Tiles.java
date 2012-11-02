package pacmanprogram;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import pacmanprogram.Tile.Type;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author stavy92
 */
public class Tiles {
    
    ArrayList<Tile> deadTiles = new ArrayList<Tile>();
    ArrayList<Tile> legalTiles = new ArrayList<Tile>();
    
    public Tiles(){
        //top wall
        int i;
        for(i=0;i<MazeDimensions.MAZE_WIDTH;i+=Tile.WIDTH){
            deadTiles.add(new Tile(i,0,Type.dead));
        }
        //bottom wall
        int j;
        for(j=0;j<MazeDimensions.MAZE_WIDTH;j+=Tile.WIDTH){
            deadTiles.add(new Tile(j,560,Type.dead));
        }
        
        
    }

    public void drawdeadSpace(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLUE);
        int i;
        for(i=0;i<deadTiles.size();i++){
            g2.fill(new Rectangle.Double(deadTiles.get(i).getXL(),deadTiles.get(i).getYU(),Tile.WIDTH,Tile.HEIGHT));
        }
    }
    
    public void drawLegalSpace(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        int i;
        for(i=0;i<legalTiles.size();i++){
            g2.fill(new Rectangle.Double(legalTiles.get(i).getXL(),legalTiles.get(i).getYU(),Tile.WIDTH,Tile.HEIGHT));
        }
    }
    
    public Tile getTileOccupied(double[] center){
        Tile empty = new Tile(600,600,Type.dead);
        boolean xTrue = false, yTrue = false;
        int i;
        for(i=0; i<legalTiles.size();i++){
            if((center[0] > legalTiles.get(i).getXL()) && (center[0] < legalTiles.get(i).getXR())){
                xTrue = true;
            }
            if((center[1] > legalTiles.get(i).getYU()) && (center[1] < legalTiles.get(i).getYD())){
                yTrue = true;
            }
            if(xTrue && yTrue){
                return legalTiles.get(i);
            }
            xTrue = false; 
            yTrue = false;  
        }
        
        return empty;
    }
}
