/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanprogram;

import java.util.ArrayList;

/**
 *
 * @author stavy92
 */
public class DotCounterControl {
    int currentLevel;
    Characters characters;
    Maze maze;
    ModeControl modeControl;
    LevelSpecs levelSpecs;
    ArrayList<Ghost> ghosts = new ArrayList<Ghost>();
    ArrayList<DotCounter> personalCounters= new ArrayList<DotCounter>();
    DotCounter globalDotCounter;
    DotCounter blinkyCounter;
    private double lastDotTimer;
    private double lastDotTimerLimit;
    private double[] lastDotTimerLimits = {4,4,4,4,3};
    private int elroyLimit1;
    private int elroyLimit2;
    private int[] personalCounterLimits;
    
    
    public DotCounterControl(Characters characters, Maze maze, ModeControl modeControl, LevelSpecs levelSpecs, int level){
        currentLevel = level;
        this.characters = characters;
        this.maze = maze;
        this.modeControl = modeControl;
        this.levelSpecs = levelSpecs;
        ghosts.add(characters.pinky);
        ghosts.add(characters.inky);
        ghosts.add(characters.clyde);
        ghosts.add(characters.blinky);
        personalCounters.add(characters.pinky.dotCounter);
        personalCounters.add(characters.inky.dotCounter);
        personalCounters.add(characters.clyde.dotCounter);
        globalDotCounter = new DotCounter(DotCounter.Type.global, maze);
        blinkyCounter = characters.blinky.dotCounter;
        
        
        updateSpecifications();
        setCounterLimits();
    }
     
    public void setCounterLimits(){
        blinkyCounter.setLimit(elroyLimit1);
        int i;
        for(i=0;i<personalCounters.size();i++){
            personalCounters.get(i).setLimit(personalCounterLimits[i]);
        }
    }
            
    private void updateElroyCounter(){
        blinkyCounter.increaseCounter();
        if(blinkyCounter.checkIfLimitReached()){
            modeControl.cruiseElroyOn();
            characters.blinky.setElroySpeed(2);
            blinkyCounter.setLimit(elroyLimit2);
        }
    }
    
    private void updatePersonalCounters(){
        int i;
        for(i=0;i<personalCounters.size();i++){
            if(personalCounters.get(i).getStatus() == DotCounter.Status.enabled || personalCounters.get(i).getStatus() == DotCounter.Status.deactivated){
                if(ghosts.get(i).isInPen()){
                    personalCounters.get(i).activateCounter();
                }
            }
            if(personalCounters.get(i).getStatus() == DotCounter.Status.activated){
                personalCounters.get(i).increaseCounter();
                if(personalCounters.get(i).checkIfLimitReached()){
                    personalCounters.get(i).deactivateCounter();
                    //leave pen
                    if(i != (personalCounters.size()-1)){
                        personalCounters.get(i+1).activateCounter();
                    }
                }
                else{
                    break;
                }
            }
        }
    }
    
    private void updateGlobalCounter(){
        if(globalDotCounter.getStatus() == DotCounter.Status.activated){
            globalDotCounter.increaseCounter();
            globalDotCounter.checkIfLimitReached();
            if(globalDotCounter.getCount() == 7){
                //pinky.leavePen();
            }
            else if(globalDotCounter.getCount() == 17){
                //inky.leavePen();
            }
            else if(globalDotCounter.getCount() == 32 && characters.clyde.isInPen()){
                globalDotCounter.resetCounter();
                globalDotCounter.deactivateCounter();
                enablePersonalCounters();
            }
        }
    }
    
    public void updateLastDotTimer(){
        if(TimerControl.timeCheck(getLastDotTimer(),lastDotTimerLimit)){
            startLastDotTimer();
            //most preferred ghost in pen-> deactivateCounter(); leavePen();
        }
    }
    
        
    public void resetCounters(){
        blinkyCounter.resetCounter();
        characters.pinky.dotCounter.resetCounter();
        characters.inky.dotCounter.resetCounter();
        characters.clyde.dotCounter.resetCounter();
        globalDotCounter.resetCounter();
    }
    
    public void resetStatuses(){
        blinkyCounter.resetStatus();
        characters.pinky.dotCounter.resetStatus();
        characters.inky.dotCounter.resetStatus();
        characters.clyde.dotCounter.resetStatus();
        globalDotCounter.resetStatus();
    }
    
    public void updateDotCounters(){
        updateElroyCounter();
        updatePersonalCounters();
        updateGlobalCounter();
    }
    
    private void enablePersonalCounters(){
        characters.pinky.dotCounter.enableCounter();
        characters.inky.dotCounter.enableCounter();
        characters.clyde.dotCounter.enableCounter();
    }
    
    private void disablePersonalCounters(){
        characters.pinky.dotCounter.disableCounter();
        characters.inky.dotCounter.disableCounter();
        characters.clyde.dotCounter.disableCounter();
    }
        
    private void startLastDotTimer(){
        lastDotTimer = System.currentTimeMillis();
    }
    
    private double getLastDotTimer(){
        if(lastDotTimer == 0){
            return 0;
        }
        return (System.currentTimeMillis()-lastDotTimer)/1000;
    }
    
    private void updateSpecifications(){
        lastDotTimerLimit = levelSpecs.getLastDotTimerLimit(currentLevel);
        elroyLimit1 = levelSpecs.getElroy1DotsLeft(currentLevel);
        elroyLimit2 = levelSpecs.getElroy2DotsLeft(currentLevel);
        personalCounterLimits = levelSpecs.getPersonalCounterLimits(currentLevel);
    }
    
    public void resetLevel(){
        disablePersonalCounters();
        globalDotCounter.resetCounter();
        globalDotCounter.activateCounter();
        blinkyCounter.deactivateCounter();
    }
    
    public void changeLevel() {
        currentLevel++;
        updateSpecifications();
        setCounterLimits();
        resetCounters();
        resetStatuses();
    }
    
    public void restartGame(){
        currentLevel = 1;
        updateSpecifications();
        setCounterLimits();
        resetCounters();
        resetStatuses();
    }
    
}
