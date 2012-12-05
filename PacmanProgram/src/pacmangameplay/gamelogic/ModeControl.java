/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmangameplay.gamelogic;

import pacmangameplay.mazedisplay.Maze;
import pacmangameplay.charactercontrol.Characters;
import pacmangameplay.gamelogic.DotCounter.Status;
import pacmangameplay.charactercontrol.Ghost.Name;

/**
 * Modes:
 * 1 scatter
 * 2 chase
 * 3 frightened
 * 4 elroy (used only by Blinky)
 *
 * @author stavy92
 */
public class ModeControl {
    
    Characters characters;
    Maze maze;
    LevelSpecs levelSpecs;
    
    int currentLevel;
    
    //mode control
    private int mode;
    private int elroyMode;
    private int prevMode;
    private int prevElroyMode;
    private boolean frightOn;
    private boolean elroyOn;
    
    //specs
    private double[] scatTimes;
    private double[] chaseTimes;
    private int frightTime;
    private int flashNumber;
    private double pauseTime;
    private double scatChaseTimer;
    private double frightTimer;
    
    
    public ModeControl(Characters characters, Maze maze, LevelSpecs levelSpecs, int level){
        this.currentLevel = level;
        this.characters = characters;
        this.maze = maze;
        this.levelSpecs = levelSpecs;
        
        updateSpecifications();
        elroyMode = 1;
        startScatChaseTimer();
        frightOn = false;
        elroyOn = false;
    }
    
    /**
     * Checks if timer limits have been reached to change to scatter or chase mode.
     * Checks if pacman is eating an energizer to change to frightened mode.
     * Checks if frightened mode timer has been reached to change back to previous mode.
     */
    public void updateModes(){
        
        //start fright mode when pacman eats energizers
        if(maze.getType(characters.pacman.getCurrentTileIndex())==3){
            maze.changeType(characters.pacman.getCurrentTileIndex(), 3, 1);
            System.out.println(getScatChaseTimer());
            pauseScatChaseTimer(frightTime);
//                System.out.println(getScatChaseTimer());
            prevMode = getMode();
            prevElroyMode = getElroyMode();
            frightMode();
            startFrightTimer();
            frightOn = true;
        }  
        if(!frightOn){
            //scatter mode at appropriate times
            if(isScatterTime()){
                System.out.println(getScatChaseTimer());
                scatterMode();
            }
            //chase mode at appropriate times
            else if(isChaseTime())  {
                System.out.println(getScatChaseTimer());
                chaseMode();
            }

        }
        else{
            if(getFrightTimer() > frightTime){
                frightTimer = 0;
                frightOn = false;
                mode = prevMode;
                if(prevElroyMode ==4){
                    cruiseElroyOn();
                }
                System.out.println(""+getScatChaseTimer());
//                System.out.println("CHANGING BACK");
                setMode();
//                System.out.println(""+mode);
            }
        } 
    }
    
    /**
     * Resets modes when a life is lost.
     */
    public void reset(){
        frightOn = false;
        scatterMode();
        startScatChaseTimer();
    }
    
    /**
     * Resets modes for a new level.
     */
    public void newLevel(){
        updateSpecifications();
        reset();
    }
    
    /**
     * Resets modes for a new game.
     */
    public void newGame(){
        updateSpecifications();
        reset();
    }
    
    /**
     * Returns the current mode.
     * @return mode as integer
     */
    public int getMode(){
        return mode;
    }
    
    /**
     * Returns current Elroy mode.
     * @return 1 or 2
     */
    public int getElroyMode(){
        return elroyMode;
    }
    
    /**
     * Turns Elroy Mode on for Blinky.
     */
    public void cruiseElroyOn(){
//        System.out.println("Cruise Elroy ON");
        elroyOn = true;
        elroyMode = 4;
    }
    
    /**
     * Turns Elroy Mode off for Blinky.
     */
    public void cruiseElroyOff(){
//        System.out.println("Cruise Elroy OFF");
        elroyOn = false;
        elroyMode = mode;
    }
    
    private void setMode(){
        if(elroyOn){
            characters.setMode(elroyMode, mode);
        }
        else{
            characters.setMode(mode, mode);
        }
    }

    private boolean isScatterTime(){
        return (ToleranceEquals.timeCheck(getScatChaseTimer(),scatTimes[0]) 
                || ToleranceEquals.timeCheck(getScatChaseTimer(),scatTimes[1]) 
                || ToleranceEquals.timeCheck(getScatChaseTimer(),scatTimes[2]));
    }
    
    private boolean isChaseTime(){
        return (ToleranceEquals.timeCheck(getScatChaseTimer(), chaseTimes[0]) 
                || ToleranceEquals.timeCheck(getScatChaseTimer(), chaseTimes[1]) 
                || ToleranceEquals.timeCheck(getScatChaseTimer(), chaseTimes[2]) 
                || ToleranceEquals.timeCheck(getScatChaseTimer(), chaseTimes[3]));
    }
    
    private void scatterMode(){
//        System.out.println("SCATTER MODE");
        mode = 1;
        setMode();
        
    }
    
    private void chaseMode(){
//        System.out.println("CHASE MODE");
        mode = 2;
        setMode();
    }
    
    private void frightMode(){
//        System.out.println("FRIGHTENED MODE");
        mode = 3;
        int i;
        for(i=0;i<characters.ghosts.size();i++){
            characters.ghosts.get(i).ghostControl.becomeBlue();
        }
        cruiseElroyOff();
        setMode();
    }
    
    private void startScatChaseTimer(){
        scatChaseTimer = System.currentTimeMillis();
    }
    
    private void pauseScatChaseTimer(double time){
        pauseTime += time;
    }
    
    private double getScatChaseTimer(){
        if(scatChaseTimer == 0){
            return 0;
        }
        return ((System.currentTimeMillis()-scatChaseTimer)/1000-pauseTime);
    }
    
    private void startFrightTimer(){
        frightTimer = System.currentTimeMillis();
    }
    
    private double getFrightTimer(){
        if(frightTimer == 0){
            return 0;
        }
        return (System.currentTimeMillis()-frightTimer)/1000;
    }

    private void updateSpecifications(){
        scatTimes = levelSpecs.getScatTimes(currentLevel);
        chaseTimes = levelSpecs.getChaseTimes(currentLevel);
        frightTime = levelSpecs.getFrightTime(currentLevel);
    }
}
