/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanprogram;

import java.awt.Graphics;

/**
 *
 * @author stavy92
 */
class BonusSymbols {
    
    public enum Name{cherry,strawberry,peach,apple,grape,galaxian,bell,key};
    private Name name;
    private int points;
    static final double[] APPEAR_TIME_DOTS_EATEN = {70,170};
    private boolean isEaten = false;
    
    public BonusSymbols(Name name){
        this.name = name;
        switch (name){
            case cherry:
                points = 100;
                break;
            case strawberry:
                points = 300;
                break;
            case peach:
                points = 500;
                break;
            case apple:
                points = 700;
                break;
            case grape:
                points = 1000;
                break;
            case galaxian:
                points = 2000;
                break;
            case bell:
                points = 3000;
                break;
            case key:
                points = 5000;
                break;
        }
                
    }

    public void drawBonus(Graphics g) {
        if(isEaten){
            return;
        }
        else{
            
        }
    }
    
    public void isEaten(boolean isEaten){
        this.isEaten = isEaten;
    }
    
}
