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
 *
 * @author stavy92
 */
class BonusSymbol {
    public static final double BONUS_X_POS = 220;
    public static final double BONUS_Y_POS = 320;
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

    public void show(Graphics g) {
        //bonus coords: x[216,232], y[320,336]
        Graphics2D bonus = (Graphics2D) g;
        switch (name){
            case cherry:
                GeneralPath stems = new GeneralPath(GeneralPath.WIND_EVEN_ODD,3);
                stems.moveTo(BONUS_X_POS+1,BONUS_Y_POS+8);
                stems.lineTo(BONUS_X_POS+4, BONUS_Y_POS+1);
                stems.lineTo(BONUS_X_POS+7,BONUS_Y_POS+8);
                bonus.setColor(Color.GREEN);
                bonus.fill(stems);
                bonus.setColor(color);
                bonus.fill(new Ellipse2D.Double(BONUS_X_POS-3,BONUS_Y_POS+8,8,8));
                bonus.fill(new Ellipse2D.Double(BONUS_X_POS+3,BONUS_Y_POS+8,8,8));
                break;
            case strawberry:
                double[] strawbPointsX = {BONUS_X_POS-2,BONUS_X_POS+4,BONUS_X_POS+10};
                double[] strawbPointsY = {BONUS_Y_POS+4,BONUS_Y_POS+14,BONUS_Y_POS+4};
                double[] leaf1PointsX = {BONUS_X_POS+1,BONUS_X_POS-2,BONUS_X_POS+4};
                double[] leaf2PointsX = {BONUS_X_POS+7,BONUS_X_POS+4,BONUS_X_POS+10};
                double[] leaf1PointsY = {BONUS_Y_POS+1,BONUS_Y_POS+4,BONUS_Y_POS+4};
                double[] leaf2PointsY = {BONUS_Y_POS+1,BONUS_Y_POS+4,BONUS_Y_POS+4};
                double[][] seedCoords = {{BONUS_X_POS+2,BONUS_Y_POS+5.5},{BONUS_X_POS+4,BONUS_Y_POS+5.5},{BONUS_X_POS+6,BONUS_Y_POS+5.5},{BONUS_X_POS+3,BONUS_Y_POS+7.5},{BONUS_X_POS+5,BONUS_Y_POS+7.5},{BONUS_X_POS+4,BONUS_Y_POS+9.5}};
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
                bonus.fill(new Ellipse2D.Double(BONUS_X_POS-1,BONUS_Y_POS+2,12,12));
                break;
            case apple:
                bonus.setColor(color);
                bonus.fill(new Ellipse2D.Double(BONUS_X_POS-2,BONUS_Y_POS+2,12,12));
                bonus.setColor(Color.GREEN);
                bonus.fill(new Rectangle.Double(BONUS_X_POS+3,BONUS_Y_POS+1,2,2));
                break;
            case melon:
                bonus.setColor(color);
                bonus.fill(new Ellipse2D.Double(BONUS_X_POS-1,BONUS_Y_POS+2,12,12));
                break;
            case tulip:
                bonus.setColor(Color.GREEN);
                bonus.fill(new Rectangle.Double(BONUS_X_POS+3,BONUS_Y_POS+6,2,8));
                bonus.setColor(color);
                bonus.fill(new Ellipse2D.Double(BONUS_X_POS,BONUS_Y_POS+1,8,8));
                bonus.setColor(Color.BLACK);
                bonus.fill(buildTriangle(new double[] {BONUS_X_POS+2,BONUS_X_POS+4,BONUS_X_POS+6}, new double[] {BONUS_Y_POS+1,BONUS_Y_POS+5,BONUS_Y_POS+1}));
                break;
            case bell:
                bonus.setColor(color);
                bonus.fill(new RoundRectangle2D.Double(BONUS_X_POS-0.5,BONUS_Y_POS+1,9,10,10,10));
                bonus.fill(buildTriangle(new double[] {BONUS_X_POS-0.5,BONUS_X_POS-3,BONUS_X_POS+3}, new double[] {BONUS_Y_POS+6,BONUS_Y_POS+12,BONUS_Y_POS+12}));
                bonus.fill(buildTriangle(new double[] {BONUS_X_POS+8.5,BONUS_X_POS+4,BONUS_X_POS+11}, new double[] {BONUS_Y_POS+6,BONUS_Y_POS+12,BONUS_Y_POS+12}));
                bonus.fill(new Rectangle.Double(BONUS_X_POS+3,BONUS_Y_POS+10,2,4));
                break;
            case key:
                bonus.setColor(color);
                bonus.fill(new Ellipse2D.Double(BONUS_X_POS-4,BONUS_Y_POS+5,7,6));
                bonus.fill(new Rectangle.Double(BONUS_X_POS+2,BONUS_Y_POS+7,10,2.5));
                bonus.fill(new Rectangle.Double(BONUS_X_POS+7,BONUS_Y_POS+9,2,3));
                bonus.fill(new Rectangle.Double(BONUS_X_POS+10,BONUS_Y_POS+9,2,3));
                break;
        }
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
    
    public int getPoints(){
        return points;
    }
}
