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
    
    
    Pacman pacman;
    Ghost blinky;
    Ghost pinky;
    Ghost inky;
    Ghost clyde;
    Walls walls;
    BonusSymbols bonus1;
    BonusSymbols bonus2;
    
    
    public Level(Pacman pacman, Ghost blinky, Ghost inky, Ghost pinky, Ghost clyde, Walls walls) {
        this.pacman = pacman;
        this.blinky = blinky;
        this.inky = inky;
        this.pinky = pinky;
        this.clyde = clyde;
        this.walls = walls;
        bonus1 = new BonusSymbols(levelSpecs.getBonusSymbol(currentLevel));
        bonus2 = new BonusSymbols(levelSpecs.getBonusSymbol(currentLevel));
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
        if(dotsEaten>=BonusSymbols.APPEAR_TIME_DOTS_EATEN[1]){
            bonus2.show(g);
            //start appear timer
        }
        else if(dotsEaten>=BonusSymbols.APPEAR_TIME_DOTS_EATEN[0]){
            bonus1.show(g);
            //start appear timer
        }
    }
    
    private void resetTimer(){
        
    }

    void refresh(ActionEvent ae) {
        int[] map = walls.getMap();
        int dotCounter = 0;
        int i;
        for(i=0; i<map.length; i++){
            if(map[i] > 1){
                dotCounter++;
            }
        }
        dotsRemaining = dotCounter;
        dotsEaten = 244-dotsRemaining;
        if(pacman.getCurrentTileIndex()==573){
            bonus1.erase(ae);
            bonus2.erase(ae);
        }
    }
    
   
    
}