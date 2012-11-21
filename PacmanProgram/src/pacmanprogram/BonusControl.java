/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanprogram;

import java.awt.Graphics;
import pacmanprogram.BonusSymbol.Name;
import pacmanprogram.TimerControl;

/**
 *
 * @author stavy92
 */
public class BonusControl {
    
    private boolean showBonus;
    private boolean bonusGone;
    private int bonusEaten;
    BonusSymbol bonus;
    private double bonusTimer;
    
    private Pacman pacman;
    
    public BonusControl(Name bonusSymbol, Pacman pacman){
        showBonus = false;
        bonusGone = false;
        bonusEaten = 0;
        bonus = new BonusSymbol(bonusSymbol);
        
        this.pacman = pacman;
    }
    
    public void updateBonusSymbols(int dotCount){
        if(!showBonus){
            if((dotCount==BonusSymbol.APPEAR_TIME_DOTS_EATEN[1]) || (dotCount==BonusSymbol.APPEAR_TIME_DOTS_EATEN[0])){
                showBonus = true;
                bonusGone = false;
                startBonusTimer();
            }
        }
        else{
            if(pacEatingBonus()){
                eatBonus();
            }
            else if(TimerControl.timeCheck(getBonusTimer(), 9.5)){
                eraseBonus();
            }
        } 
    }
     
    public void drawBonus(Graphics g) {
        if(showBonus && !bonusGone){
            bonus.show(g);
        }      
    }
    
    
    private void eraseBonus(){
        showBonus = false;
        bonusGone = true;
    }
    
    private void eatBonus(){
        showBonus = false;
        bonusGone = true;
        bonusEaten++;
    }
    
    private boolean pacEatingBonus(){
        return (TimerControl.positionCheck(pacman.getX(),220) && TimerControl.positionCheck(pacman.getY(), 320));
    }
    
    
    private void startBonusTimer() {
        bonusTimer = System.currentTimeMillis();
    }
    
    private double getBonusTimer() {
        if(bonusTimer == 0){
            return 0;
        }
        return ((System.currentTimeMillis()-bonusTimer)/1000);
    }
    
    
    
    
}
