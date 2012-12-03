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
    private int dotsRemaining = 240;
    private int energizersRemaining = 4;
    private int totalDotsRemaining = 244;
    private int dotsEaten = 0;
    private int energizersEaten = 0;
    private int totalDotsEaten = 0;
    private int prevDotsRemaining = 244;
    private boolean wasDotEaten = false;

    private boolean wasLifeLost;
    
    private int ghostsEaten;
    private int ghostScore;
    
    private double reverseDirectionTime;
    
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
    
  
    public Level(Characters characters, Maze maze) {
        this.maze = maze;
        this.characters = characters;
        collision= new CollisionChecker(characters);       
    }
    
    public void startGame(int level){
        currentLevel = level;
        ghostScore = 0;
        characters.pacman.resetLives();
        characters.resetPosition();
        maze.resetMaze();
        updateSpecifications();
        assignSpeeds();
        modeControl.newGame();
        dotCounterControl.newGame();
        bonusControl.newGame();
        startTimer();
        System.out.println("Game start");
    }
    
    private void updateSpecifications(){
        reverseDirectionTime = levelSpecs.getReverseDirectionTime(currentLevel);
        this.modeControl = new ModeControl(characters, maze, levelSpecs, currentLevel);
        this.bonusControl = new BonusControl(characters.pacman, levelSpecs, currentLevel);
        this.dotCounterControl = new DotCounterControl(characters, maze, modeControl, levelSpecs, currentLevel);
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
        characters.blinky.setElroySpeed(0);
        characters.pinky.assignSpeeds(ghostSpeedRatio, elroy1SpeedRatio, elroy2SpeedRatio, ghostTunSpeedRatio, ghostFrightSpeedRatio);
        characters.inky.assignSpeeds(ghostSpeedRatio, elroy1SpeedRatio, elroy2SpeedRatio, ghostTunSpeedRatio, ghostFrightSpeedRatio);
        characters.clyde.assignSpeeds(ghostSpeedRatio, elroy1SpeedRatio, elroy2SpeedRatio, ghostTunSpeedRatio, ghostFrightSpeedRatio);
    }

    public void refresh(ActionEvent ae) {
        updateModes();
        checkCollision();
        checkReverseDirection();
        updateDotCount();
        updateDotCounters();
        bonusControl.updateBonusSymbols(totalDotsEaten);
    }

    private void resetLevel(){
        System.out.println("Resetting Level");
        characters.resetPosition();
        modeControl.reset();
        dotCounterControl.resetLevel();
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
                    ghostsEaten++;
                    ghostScore += (int) Math.pow(2, ghostsEaten)*100;
//                    System.out.println("Eaten");
                    Thread.sleep(500);
                    ghostControl=collision.pacmanGhostCollisionCheck();
                    ghostControl.setFrightenedAndCaughtTrue();
                    ghostControl.becomeNonExistent();
                }
            }
            catch(Exception e){}
        }
    }
    
    public boolean checkGameOver(){
        if(characters.pacman.getLives() == 0){
            try{
                System.out.println("GAME OVER");
                Thread.sleep(5000);
                return true;
            }
            catch(Exception e){}
        }  
        return false;
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
//            System.out.println(totalDotsEaten);
            wasDotEaten = true;
        }
        else{
            wasDotEaten = false;
        }
    } 
    
    private void checkReverseDirection(){
        if(TimerControl.timeCheck(getTimer(), reverseDirectionTime)){
            modeControl.reverseDirection();
        }
    }
    
    private void updateModes(){
        modeControl.updateModes();
        if(modeControl.getMode()==1||modeControl.getMode()==2){
            ghostsEaten = 0;
        }
    }
    
    public boolean checkLevelChange(){
        if(totalDotsRemaining == 0){
            try{
                Thread.sleep(1500);
            }
            catch(Exception e){}
            System.out.println(""+currentLevel);
            return true;
        }
        return false;
    }
    
    public void changeLevel(){
        currentLevel++;
        ghostScore = 0;
        wasLifeLost = false;
        maze.resetMaze();
        characters.resetPosition();
        updateSpecifications();
        modeControl.newLevel();
        dotCounterControl.changeLevel();
        bonusControl.changeLevel();
        assignSpeeds();
        startTimer();
    }    

    private void updateDotCounters(){
        if(wasDotEaten){
            dotCounterControl.updateDotCounters();
        }
        dotCounterControl.updateLastDotTimer();
        dotCounterControl.updateElroyCounter();
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
    
    public int getDotsEaten(){
        return dotsEaten;
    }
    
    public int getEnergizersEaten(){
        return energizersEaten;
    }
    
    public int getGhostScore(){
        return ghostScore;
    }


}
