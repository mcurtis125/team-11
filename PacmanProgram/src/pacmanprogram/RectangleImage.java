/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanprogram;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 *
 * @author Kevin
 */
public class RectangleImage extends JPanel{
   
    double initialX, initialY, sizeX, sizeY;
    Color rectangleColor;
    
    
    public RectangleImage(double initialX, double initialY, Color rectangleColor, double sizeX, double sizeY){
        this.initialX=initialX;
        this.initialY=initialY;
        this.sizeX=sizeX;
        this.sizeY=sizeY;
        this.rectangleColor=rectangleColor;
    }
   
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        Rectangle2D rect=new Rectangle.Double(initialX,initialY,sizeX,sizeY);
        g2.setColor(rectangleColor);
        g2.fill(rect);
        g2.setColor(rectangleColor);
        g2.draw(rect);
    }
    
    
    public Rectangle2D getRectangleImage(){
        Rectangle2D rect=new Rectangle.Double(initialX,initialY,sizeX,sizeY);
        return rect;
    }
    
    
    
}
