/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanprogram;

import java.awt.Graphics;
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
//    private double timerLimit;
    private double pacSpeedRatio;
    private double pacDotSpeedRatio;
    private double pacFrightSpeedRatio;
    private double pacFrightDotSpeedRatio;
    private double ghostSpeedRatio;
    private double ghostFrightSpeedRatio;
    private double ghostTunSpeedRatio;
    private int frightTime;
    private int flashNumber;
    
    LevelSpecs specs = new LevelSpecs();
//    Pacman pacman = new Pacman(new Tiles());
//    Ghost blinky = new Ghost();
//    Ghost pinky = new Ghost();
//    Ghost inky = new Ghost();
//    Ghost clyde = new Ghost();
//    Dots dots = new Dots();
//    Energizers energizers = new Energizers();
    BonusSymbols bonus1 = specs.getBonusSymbol(currentLevel);
    BonusSymbols bonus2 = specs.getBonusSymbol(currentLevel);
    
    
    
    public Level(/*Pacman pacman, Ghost blinky, Ghost inky, Ghost pinky, Ghost clyde, Dots dots, Energizers energizers*/) {
//        this.pacman = pacman;
//        this.blinky = blinky;
//        this.inky = inky;
//        this.pinky = pinky;
//        this.dots = dots;
//        this.energizers = energizers;
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
    
    public void changeLevel(){
        currentLevel++;
        updateSpecifications();
    }
    
    private void updateSpecifications(){
        
    }

    void drawBonus(Graphics g) {
        if(dotsEaten>=BonusSymbols.APPEAR_TIME_DOTS_EATEN[0]){
            bonus1.drawBonus(g);
        }
        else if(dotsEaten>=BonusSymbols.APPEAR_TIME_DOTS_EATEN[1]){
            bonus2.drawBonus(g);
        }
    }
    
    private void resetTimer(){
        
    }
    
}
