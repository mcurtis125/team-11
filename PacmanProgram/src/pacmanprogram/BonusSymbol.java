/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanprogram;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

/**
 * Contains all the information about a bonus symbol.
 * @author stavy92
 */
class BonusSymbol {
    
    private double xPos = 220;
    private double yPos = 320;
    public enum Name{cherry,strawberry,orange,apple,melon,tulip,bell,key};
    private Name name;
    private int points;
    Color color = Color.BLACK;
    
    public BonusSymbol(Name name){
        this.name = name;
        switch (name){
            case cherry:
                points = 100;
                color = Color.RED;
                break;
            case strawberry:
                points = 300;
                color = Color.RED;
                break;
            case orange:
                points = 500;
                color = Color.ORANGE;
                break;
            case apple:
                points = 700;
                color = Color.RED;
                break;
            case melon:
                points = 1000;
                color = Color.GREEN;
                break;
            case tulip:
                points = 2000;
                color = Color.PINK;
                break;
            case bell:
                points = 3000;
                color = Color.ORANGE;
                break;
            case key:
                points = 5000;
                color = Color.WHITE;
                break;
        }
                
    }

    /**
     * Paints the bonus symbols.
     * @param g 
     */
    public void show(Graphics g) {
        //bonus coords: x[216,232], y[320,336]
        Graphics2D bonus = (Graphics2D) g;
        switch (name){
            case cherry:
                GeneralPath stems = new GeneralPath(GeneralPath.WIND_EVEN_ODD,3);
                stems.moveTo(xPos+1,yPos+8);
                stems.lineTo(xPos+4, yPos+1);
                stems.lineTo(xPos+7,yPos+8);
                bonus.setColor(Color.GREEN);
                bonus.fill(stems);
                bonus.setColor(color);
                bonus.fill(new Ellipse2D.Double(xPos-3,yPos+8,8,8));
                bonus.fill(new Ellipse2D.Double(xPos+3,yPos+8,8,8));
                break;
            case strawberry:
                double[] strawbPointsX = {xPos-2,xPos+4,xPos+10};
                double[] strawbPointsY = {yPos+4,yPos+14,yPos+4};
                double[] leaf1PointsX = {xPos+1,xPos-2,xPos+4};
                double[] leaf2PointsX = {xPos+7,xPos+4,xPos+10};
                double[] leaf1PointsY = {yPos+1,yPos+4,yPos+4};
                double[] leaf2PointsY = {yPos+1,yPos+4,yPos+4};
                double[][] seedCoords = {{xPos+2,yPos+5.5},{xPos+4,yPos+5.5},{xPos+6,yPos+5.5},{xPos+3,yPos+7.5},{xPos+5,yPos+7.5},{xPos+4,yPos+9.5}};
                //seeds: size = 1
                GeneralPath strawb = buildTriangle(strawbPointsX,strawbPointsY);
                GeneralPath leaf1 = buildTriangle(leaf1PointsX,leaf1PointsY);
                GeneralPath leaf2 = buildTriangle(leaf2PointsX,leaf2PointsY);
                ArrayList<Rectangle.Double> seeds = new ArrayList<Rectangle.Double>();
                int j;
                for(j=0;j<seedCoords.length;j++){
                    seeds.add(new Rectangle.Double(seedCoords[j][0],seedCoords[j][1],1,1));   
                }
                bonus.setColor(color);
                bonus.fill(strawb);
                bonus.setColor(Color.GREEN);
                bonus.fill(leaf1);
                bonus.fill(leaf2);
                bonus.setColor(Color.BLACK);
                int i;
                for(i=0;i<seeds.size();i++){
                    bonus.fill(seeds.get(i));
                }
                break;
            case orange:
                bonus.setColor(color);
                bonus.fill(new Ellipse2D.Double(xPos-1,yPos+2,12,12));
                break;
            case apple:
                bonus.setColor(color);
                bonus.fill(new Ellipse2D.Double(xPos-2,yPos+2,12,12));
                bonus.setColor(Color.GREEN);
                bonus.fill(new Rectangle.Double(xPos+3,yPos+1,2,2));
                break;
            case melon:
                bonus.setColor(color);
                bonus.fill(new Ellipse2D.Double(xPos-1,yPos+2,12,12));
                break;
            case tulip:
                bonus.setColor(Color.GREEN);
                bonus.fill(new Rectangle.Double(xPos+3,yPos+6,2,8));
                bonus.setColor(color);
                bonus.fill(new Ellipse2D.Double(xPos,yPos+1,8,8));
                bonus.setColor(Color.BLACK);
                bonus.fill(buildTriangle(new double[] {xPos+2,xPos+4,xPos+6}, new double[] {yPos+1,yPos+5,yPos+1}));
                break;
            case bell:
                bonus.setColor(color);
                bonus.fill(new RoundRectangle2D.Double(xPos-0.5,yPos+1,9,10,10,10));
                bonus.fill(buildTriangle(new double[] {xPos-0.5,xPos-3,xPos+3}, new double[] {yPos+6,yPos+12,yPos+12}));
                bonus.fill(buildTriangle(new double[] {xPos+8.5,xPos+4,xPos+11}, new double[] {yPos+6,yPos+12,yPos+12}));
                bonus.fill(new Rectangle.Double(xPos+3,yPos+10,2,4));
                break;
            case key:
                bonus.setColor(color);
                bonus.fill(new Ellipse2D.Double(xPos-4,yPos+5,7,6));
                bonus.fill(new Rectangle.Double(xPos+2,yPos+7,10,2.5));
                bonus.fill(new Rectangle.Double(xPos+7,yPos+9,2,3));
                bonus.fill(new Rectangle.Double(xPos+10,yPos+9,2,3));
                break;
        }
    }
    
    /**
     * Returns the name of the bonus symbol.
     * @return Name of the bonus symbol. 
     */
    public Name getName(){
        return name;
    }
    
    /**
     * Returns the number of points the symbol is worth.
     * @return number of points
     */
    public int getPoints(){
        return points;
    }
    
    /**
     * Returns the x position of the bonus symbol.
     * @return x coordinate
     */
    public double getXPos(){
        return xPos;
    }
    
    /**
     * Returns the y position of the bonus symbol
     * @return y coordinate
     */
    public double getYPos(){
        return yPos;
    }
    
    /**
     * Sets the position of the bonus symbol to (x,y).
     * @param x
     * @param y 
     */
    public void setPosition(double x, double y){
        xPos = x;
        yPos = y;
    }
    
    private GeneralPath buildTriangle(double[] pointsX, double[] pointsY){
        GeneralPath triangle = new GeneralPath(GeneralPath.WIND_EVEN_ODD,pointsX.length);
        triangle.moveTo(pointsX[0], pointsY[0]);
        int i;
        for(i=1;i<pointsX.length;i++){
            triangle.lineTo(pointsX[i], pointsY[i]);
        }
        triangle.closePath();
        return triangle;
    }
}
