/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanprogram;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Timer;
import pacmanprogram.DotCounter.Status;
import pacmanprogram.DotCounter.Type;


/**
 *
 * @author stavy92
 */
class Level {
    
    private static final double TIME_TOLERANCE = 0.00815;
    private static final double POSITION_TOLERANCE = 6;
    
    //level specs
    LevelSpecs levelSpecs = new LevelSpecs();
      
    Maze maze;
    Characters characters;
    CollisionChecker collision;
    ModeControl modeControl;
    DotCounterControl dotCounterControl;
    BonusControl bonusControl;
    
    private int currentLevel;
    
    //dot counters
    private int dotsRemaining;
    private int energizersRemaining;
    private int totalDotsRemaining;
    private int dotsEaten;
    private int energizersEaten;
    private int totalDotsEaten;
    private int prevDotsRemaining;
    private boolean wasDotEaten;
    
    //TEMPORARY
    private int totdots=0;
    
    private boolean wasLifeLost;
    
    private int ghostsEaten;
    
    
    //speeds
    private double pacSpeedRatio;
    private double pacDotSpeedRatio;
    private double pacFrightSpeedRatio;
    private double pacFrightDotSpeedRatio;
    private double ghostSpeedRatio;
    private double ghostFrightSpeedRatio;
    private double ghostTunSpeedRatio;   
    private double elroy1SpeedRatio;
    private double elroy2SpeedRatio;
     
    //timer
    private double timer;
    
  
    public Level(Characters characters, Maze maze, int level) {
        currentLevel = level;
        this.maze = maze;
        this.characters = characters;
        collision= new CollisionChecker(characters);
        this.modeControl = new ModeControl(characters, maze, levelSpecs, currentLevel);
        this.bonusControl = new BonusControl(characters.pacman, levelSpecs, currentLevel);
        this.dotCounterControl = new DotCounterControl(characters, maze, modeControl, levelSpecs, currentLevel);
        
        //speeds and timers
        updateSpecifications();
        
        assignSpeeds();
        startTimer();
        
        System.out.println("Game start");
        
        
    }
    
    private void updateSpecifications(){
        
        pacSpeedRatio = levelSpecs.getPacSpeedRatio(currentLevel);
        pacDotSpeedRatio = levelSpecs.getPacDotSpeedRatio(currentLevel);
        pacFrightSpeedRatio = levelSpecs.getPacFrightSpeedRatio(currentLevel);
        pacFrightDotSpeedRatio = levelSpecs.getPacFrightDotSpeedRatio(currentLevel);
        ghostSpeedRatio = levelSpecs.getGhostSpeedRatio(currentLevel);
        ghostFrightSpeedRatio = levelSpecs.getGhostFrightSpeedRatio(currentLevel);
        ghostTunSpeedRatio = levelSpecs.getGhostTunSpeedRatio(currentLevel);
        elroy1SpeedRatio = levelSpecs.getElroy1SpeedRatio(currentLevel);
        elroy2SpeedRatio = levelSpecs.getElroy2SpeedRatio(currentLevel);
        
        
    }
   
        
    private void assignSpeeds() {
        characters.pacman.assignSpeeds(pacSpeedRatio, pacDotSpeedRatio, pacFrightSpeedRatio, pacFrightDotSpeedRatio);
        characters.blinky.assignSpeeds(ghostSpeedRatio, elroy1SpeedRatio, elroy2SpeedRatio, ghostTunSpeedRatio, ghostFrightSpeedRatio);
        characters.blinky.setElroySpeed(1);
        characters.pinky.assignSpeeds(ghostSpeedRatio, elroy1SpeedRatio, elroy2SpeedRatio, ghostTunSpeedRatio, ghostFrightSpeedRatio);
        characters.inky.assignSpeeds(ghostSpeedRatio, elroy1SpeedRatio, elroy2SpeedRatio, ghostTunSpeedRatio, ghostFrightSpeedRatio);
        characters.clyde.assignSpeeds(ghostSpeedRatio, elroy1SpeedRatio, elroy2SpeedRatio, ghostTunSpeedRatio, ghostFrightSpeedRatio);
    }

    public void refresh(ActionEvent ae) {
        modeControl.updateModes();
        checkCollision();
        checkGameOver();
        updateDotCount();
        checkLevelChange();
        updateDotCounters();
        bonusControl.updateBonusSymbols(totalDotsEaten);
    }

    private void resetLevel(){
        System.out.println("Resetting Level");
        characters.resetPosition();
        modeControl.resetLevel();
        dotCounterControl.resetLevel();
    }
    
    private void restartGame(){
        System.out.println("Restarting game");
        currentLevel = 1;
        characters.pacman.resetLives();
        maze.resetMaze();
        updateSpecifications();
        assignSpeeds();
        modeControl.restartGame();
        dotCounterControl.restartGame();
        bonusControl.newGame();
        startTimer();
        resetLevel();           
    }
    
    private void checkCollision(){
        GhostControl ghostControl;
        if(collision.pacmanGhostCollisionCheck()!=null){
            try{ 
                if(modeControl.getMode()==1||modeControl.getMode()==2){
                    
                    Thread.sleep(1500);
                    System.out.println("LOSE LIFE");
                    resetLevel();
                    characters.pacman.loseLife();
                    wasLifeLost = true;
                }
                else if(modeControl.getMode()==3){
                    System.out.println("Eaten");
//                    ghostsEaten++;  doesnt work right now because this is called many times: pacmanGhostCollisionCheck()
                    ghostControl=collision.pacmanGhostCollisionCheck();
                    ghostControl.goToPen();
                }
            }
            catch(Exception e){}
        }
    }
    
    private void checkGameOver(){
        if(characters.pacman.getLives() == 0){
            try{
                System.out.println("GAME OVER");
                Thread.sleep(5000);
                restartGame();
            }
            catch(Exception e){}
        }  
    }
    
    
    private void updateDotCount(){
        prevDotsRemaining = totalDotsRemaining;
        
        int[] map = maze.getMap();
        int dots = 0;
        int energizers = 0;
        int i;
        for(i=0; i<map.length; i++){
            if(map[i] == 2){
                dots++;
            }
            else if(map[i] == 3){
                energizers++;
            }
        }
        
        dotsRemaining = dots;
        energizersRemaining = energizers;
        totalDotsRemaining = dotsRemaining+energizersRemaining;
        dotsEaten = 240-dotsRemaining;
        energizersEaten = 4-energizersRemaining; 
        totalDotsEaten = dotsEaten+energizersEaten;
        
        if(prevDotsRemaining>totalDotsRemaining){
            wasDotEaten = true;
            totdots++;
        }
        else{
            wasDotEaten = false;
        }
    } 
    
    
    private void checkLevelChange(){
        if(totalDotsRemaining == 0){
            try{
                Thread.sleep(1500);
            }
            catch(Exception e){}
            changeLevel();
            System.out.println(""+currentLevel);
        }
    }
    
    public void changeLevel(){
        currentLevel++;
        wasLifeLost = false;
        maze.resetMaze();
        updateSpecifications();
        dotCounterControl.changeLevel();
        bonusControl.changeLevel();
        assignSpeeds();
        startTimer();
        resetLevel();
    }    

    private void updateDotCounters(){
        dotCounterControl.updateLastDotTimer();
        if(wasDotEaten){
            dotCounterControl.updateDotCounters();
        }
    }
    
    public int getLevel(){
        return currentLevel;
    }
    
    
    
    private void startTimer() {
        timer = System.currentTimeMillis();
    }
    
    private double getTimer(){
        if(timer == 0){
            return 0;
        }
        return (System.currentTimeMillis()-timer)/1000;
    }


    public void drawBonus(Graphics g){
       bonusControl.drawBonus(g); 
    }
    
    
    public int getDots(){
        return totdots;
    }
    

}
