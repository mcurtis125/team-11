/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanprogram;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.util.Timer;


/**
 *
 * @author stavy92
 */
class Level {
    LevelSpecs levelSpecs = new LevelSpecs();
    private int currentLevel = 1;
    private int dotsEaten = 0;
    private int dotsRemaining = 244;
//    private Timer lastDotTimer = new Timer();
    private double timerLimit;
    private double pacSpeedRatio;
    private double pacDotSpeedRatio;
    private double pacFrightSpeedRatio;
    private double pacFrightDotSpeedRatio;
    private double ghostSpeedRatio;
    private double ghostFrightSpeedRatio;
    private double ghostTunSpeedRatio;
    private int frightTime;
    private int flashNumber;
    
    
    Pacman pacman = new Pacman();
    Ghost blinky = new Ghost();
    Ghost pinky = new Ghost();
    Ghost inky = new Ghost();
    Ghost clyde = new Ghost();
    BonusSymbols bonus1 = levelSpecs.getBonusSymbol(currentLevel);
    BonusSymbols bonus2 = levelSpecs.getBonusSymbol(currentLevel);
    
    
    
    public Level(Pacman pacman, Ghost blinky, Ghost inky, Ghost pinky, Ghost clyde) {
        this.pacman = pacman;
        this.blinky = blinky;
        this.inky = inky;
        this.pinky = pinky;
        this.clyde = clyde;
        updateSpecifications(); 
        assignSpeeds();
    }
    
    public void changeLevel(){
        currentLevel++;
        updateSpecifications();
        assignSpeeds();
    }
    
    private void updateSpecifications(){
        this.pacSpeedRatio = levelSpecs.getPacSpeedRatio(currentLevel);
        this.pacDotSpeedRatio = levelSpecs.getPacDotSpeedRatio(currentLevel);
        this.pacFrightSpeedRatio = levelSpecs.getPacFrightSpeedRatio(currentLevel);
        this.pacFrightDotSpeedRatio = levelSpecs.getPacFrightDotSpeedRatio(currentLevel);
        this.ghostSpeedRatio = levelSpecs.getGhostSpeedRatio(currentLevel);
        this.ghostFrightSpeedRatio = levelSpecs.getGhostFrightSpeedRatio(currentLevel);
        this.ghostTunSpeedRatio = levelSpecs.getGhostTunSpeedRatio(currentLevel);
        this.frightTime = levelSpecs.getFrightTime(currentLevel);
        this.flashNumber = levelSpecs.getFlashNumber(currentLevel);    
    }

    private void assignSpeeds() {
        this.pacman.setSpeeds(this.pacSpeedRatio, this.pacDotSpeedRatio, this.pacFrightSpeedRatio, this.pacFrightDotSpeedRatio);
        this.blinky.setSpeeds(this.ghostSpeedRatio, this.ghostTunSpeedRatio, this.ghostFrightSpeedRatio);
        this.pinky.setSpeeds(this.ghostSpeedRatio, this.ghostTunSpeedRatio, this.ghostFrightSpeedRatio);
        this.inky.setSpeeds(this.ghostSpeedRatio, this.ghostTunSpeedRatio, this.ghostFrightSpeedRatio);
        this.clyde.setSpeeds(this.ghostSpeedRatio, this.ghostTunSpeedRatio, this.ghostFrightSpeedRatio);
    }
    
    public void drawBonus(Graphics g) {
//        if(dotsEaten>=BonusSymbols.APPEAR_TIME_DOTS_EATEN[0]){
            bonus1.show(g);
//        }
//        else if(dotsEaten>=BonusSymbols.APPEAR_TIME_DOTS_EATEN[1]){
            bonus2.show(g);
//        }
    }
    
    private void resetTimer(){
        
    }

    void refresh(ActionEvent ae) {
        //update dotsEaten and dotsRemaining
    }
    
   
    
}
