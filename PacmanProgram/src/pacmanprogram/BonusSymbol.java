/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanprogram;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.geom.Ellipse2D;

/**
 *
 * @author stavy92
 */
class BonusSymbol {
    public enum Name{cherry,strawberry,orange,apple,melon,tulip,bell,key};
    private Name name;
    private int points;
    Color color = Color.BLACK;
    static final double[] APPEAR_TIME_DOTS_EATEN = {70,170};
    private boolean isEaten = false;
    private boolean isErased = false;
    private boolean wasDrawn = false;
    
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
                color = Color.YELLOW;
                break;
            case key:
                points = 5000;
                color = Color.WHITE;
                break;
        }
                
    }

    public void show(Graphics g) {
        Graphics2D bonus = (Graphics2D) g;
        bonus.setColor(color);
        bonus.fill(new Ellipse2D.Double(221,324,10,10));
    }
    
}
