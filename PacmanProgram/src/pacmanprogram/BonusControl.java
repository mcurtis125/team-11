/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanprogram;

import java.awt.Graphics;
import pacmanprogram.TimerControl;

/**
 * This class controls when and for how long the bonus symbols appear. 
 * @author stavy92
 * 
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
        bonus1 = levelSpecs.getBonusSymbol(currentLevel);
        bonus2 = levelSpecs.getBonusSymbol(currentLevel);
        
        this.pacman = pacman;
    }
    
    /**
     * 
     * Checks if it's time to show or erase the bonus symbols.
     * 
     * @param dotCount number of dots eaten used to check if it's time to show the bonus symbols.
     */
    
    public void updateBonusSymbols(int dotCount){
        if(bonus1Gone==false && dotCount==APPEAR_TIME_DOTS_EATEN[0]){
//            System.out.println("Show bonus 1");
            showBonus1 = true;
            startBonusTimer();
        }
        else if(bonus2Gone==false && dotCount==APPEAR_TIME_DOTS_EATEN[1]){
//            System.out.println("Show bonus 2");
            showBonus2 = true;
            startBonusTimer();
        }
        if((showBonus1 || showBonus2) && pacEatingBonus()){
            eraseBonus();
            eatBonus();
        }
        else if(getBonusTimer() >= APPEAR_DURATION){
            eraseBonus();
        }
    }
    
    /**
     * Shows the bonus symbols.
     * 
     * @param g 
     */
     
    public void drawBonus(Graphics g) {
        if(showBonus1){
            bonus1.show(g);
        } 
        else if(showBonus2){
            bonus2.show(g);
        }  
    }
    
    /**
     * Erases bonus symbols. 
     */
    
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
    
    /**
     * Increments number of bonus symbols eaten by Pacman.
     */
    private void eatBonus(){
        bonusEaten++;
    }
    
    /**
     * Checks if Pacman is currently eating a bonus symbol.
     * @return 
     */
    private boolean pacEatingBonus(){
        return (TimerControl.positionCheck(pacman.getX(),bonus1.getXPos()) && TimerControl.positionCheck(pacman.getY(), bonus1.getYPos()));
    }
    
    /**
     * Starts or resets the bonus timer.
     */
    private void startBonusTimer() {
        bonusTimer = System.currentTimeMillis();
    }
    
    /**
     * Helper method for updateBonusSymbols(). 
     * Timer used to check if it's time to erase bonus symbols.
     * @return current time of the bonus timer.
     */
    private double getBonusTimer() {
        if(bonusTimer == 0){
            return 0;
        }
        return ((System.currentTimeMillis()-bonusTimer)/1000);
    }
    
    /**
     * Resets class variables for a new level.
     */
    public void changeLevel(){
        showBonus1 = false;
        showBonus2 = false;
        bonus1Gone = false;
        bonus2Gone = false;
        bonusEaten = 0;
    }
    
    /**
     * Resets class variables for a new game.
     */
    public void newGame(){
        showBonus1 = false;
        showBonus2 = false;
        bonus1Gone = false;
        bonus2Gone = false;
        currentLevel=1;
        bonusEaten = 0;
    }
    
    /**
     * 
     * @return Number of bonuses eaten by Pacman in one level.
     */
    public int getBonusesEaten(){
        return bonusEaten;
    }
    
    
    
}