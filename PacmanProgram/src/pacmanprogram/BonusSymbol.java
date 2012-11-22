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
                stems.moveTo(221,328);
                stems.lineTo(224, 321);
                stems.lineTo(227,328);
                bonus.setColor(Color.GREEN);
                bonus.fill(stems);
                bonus.setColor(color);
                bonus.fill(new Ellipse2D.Double(217,328,8,8));
                bonus.fill(new Ellipse2D.Double(223,328,8,8));
                break;
            case strawberry:
                double[] strawbPointsX = {218,224,230};
                double[] strawbPointsY = {324,334,324};
                double[] leaf1PointsX = {221,218,224};
                double[] leaf2PointsX = {227,224,230};
                double[] leaf1PointsY = {321,324,324};
                double[] leaf2PointsY = {321,324,324};
                double[][] seedCoords = {{222,325.5},{224,325.5},{226,325.5},{223,327.5},{225,327.5},{224,329.5}};
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
                bonus.fill(new Ellipse2D.Double(219,322,12,12));
                break;
            case apple:
                bonus.setColor(color);
                bonus.fill(new Ellipse2D.Double(218,322,12,12));
                bonus.setColor(Color.GREEN);
                bonus.fill(new Rectangle.Double(223,321,2,2));
                break;
            case melon:
                bonus.setColor(color);
                bonus.fill(new Ellipse2D.Double(219,322,12,12));
                break;
            case tulip:
                bonus.setColor(Color.GREEN);
                bonus.fill(new Rectangle.Double(223,326,2,8));
                bonus.setColor(color);
                bonus.fill(new Ellipse2D.Double(220,321,8,8));
                bonus.setColor(Color.BLACK);
                bonus.fill(buildTriangle(new double[] {222,224,226}, new double[] {321,325,321}));
                break;
            case bell:
                bonus.setColor(color);
                bonus.fill(new RoundRectangle2D.Double(219.5,321,9,10,10,10));
                bonus.fill(buildTriangle(new double[] {219.5,217,223}, new double[] {326,332,332}));
                bonus.fill(buildTriangle(new double[] {228.5,224,231}, new double[] {326,332,332}));
                bonus.fill(new Rectangle.Double(223,330,2,4));
                break;
            case key:
                bonus.setColor(color);
                bonus.fill(new Ellipse2D.Double(216,325,7,6));
                bonus.fill(new Rectangle.Double(222,327,10,2.5));
                bonus.fill(new Rectangle.Double(227,329,2,3));
                bonus.fill(new Rectangle.Double(230,329,2,3));
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
    
}
