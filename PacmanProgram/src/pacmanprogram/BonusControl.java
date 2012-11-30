/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanprogram;

import java.awt.Graphics;
import pacmanprogram.TimerControl;

/**
 *
 * @author stavy92
 */
public class BonusControl {
    
    private static final int[] APPEAR_TIME_DOTS_EATEN = {70,170}; 
    private static final double APPEAR_DURATION = 9.5;
    
    
    LevelSpecs levelSpecs;
    
    private int currentLevel;
    
    private boolean showBonus1;
    private boolean showBonus2;
    private boolean bonus1Gone;
    private boolean bonus2Gone;
    private static int bonusEaten;
    BonusSymbol bonus1;
    BonusSymbol bonus2;
    private double bonusTimer;
    
    private Pacman pacman;
    
    
    public BonusControl(Pacman pacman, LevelSpecs levelSpecs, int level){
        currentLevel = level;
        this.levelSpecs = levelSpecs;
        showBonus1 = false;
        showBonus2 = false;
        bonus1Gone = false;
        bonus2Gone = false;
        bonusEaten = 0;
        bonus1 = new BonusSymbol(levelSpecs.getBonusSymbol(currentLevel));
        bonus2 = new BonusSymbol(levelSpecs.getBonusSymbol(currentLevel));
        
        this.pacman = pacman;
    }
    
    public void updateBonusSymbols(int dotCount){
        if(bonus1Gone==false && dotCount==APPEAR_TIME_DOTS_EATEN[0]){
            showBonus1 = true;
            startBonusTimer();
        }
        else if(bonus2Gone==false && dotCount==APPEAR_TIME_DOTS_EATEN[1]){
            showBonus2 = true;
            startBonusTimer();
        }
        if((showBonus1 || showBonus2) && pacEatingBonus()){
            eraseBonus();
            eatBonus();
        }
        else if(TimerControl.timeCheck(getBonusTimer(), APPEAR_DURATION)){
            eraseBonus();
        }
    }
     
    public void drawBonus(Graphics g) {
        if(showBonus1){
            bonus1.show(g);
        } 
        else if(showBonus2){
            bonus2.show(g);
        }  
    }
    
    private void eraseBonus(){
        if(showBonus1==true){
            showBonus1 = false;
            bonus1Gone = true;
        }
        else if(showBonus2==true){
            showBonus2 = false;
            bonus2Gone = true;
        }
    }
    
    private void eatBonus(){
        bonusEaten++;
    }
    
    private boolean pacEatingBonus(){
        return (TimerControl.positionCheck(pacman.getX(),bonus1.BONUS_X_POS) && TimerControl.positionCheck(pacman.getY(), bonus1.BONUS_Y_POS));
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
    
    public void changeLevel(){
        showBonus1 = false;
        showBonus2 = false;
        bonus1Gone = false;
        bonus2Gone = false;
        currentLevel++;
        bonusEaten = 0;
    }
    
    public void newGame(){
        showBonus1 = false;
        showBonus2 = false;
        bonus1Gone = false;
        bonus2Gone = false;
        currentLevel=1;
        bonusEaten = 0;
    }
    
    public int getBonusesEaten(){
        return bonusEaten;
    }
    
}