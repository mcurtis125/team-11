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
    private static final int GLOBAL_PINKY_LIMIT = 7;
    private static final int GLOBAL_INKY_LIMIT = 17;
    private static final int GLOBAL_CLYDE_LIMIT = 32;
    int currentLevel;
    Characters characters;
    Maze maze;
    ModeControl modeControl;
    LevelSpecs levelSpecs;
    ArrayList<DotCounter> personalCounters= new ArrayList<DotCounter>();
    DotCounter globalDotCounter;
    DotCounter blinkyCounter;
    private double lastDotTimer;
    private double lastDotTimerLimit;
    private int elroyLimit1;
    private int elroyLimit2;
    private int[] personalCounterLimits;
    
    
    public DotCounterControl(Characters characters, Maze maze, ModeControl modeControl, LevelSpecs levelSpecs, int level){
        currentLevel = level;
        this.characters = characters;
        this.maze = maze;
        this.modeControl = modeControl;
        this.levelSpecs = levelSpecs;
        personalCounters.add(0,characters.pinky.dotCounter);
        personalCounters.add(1,characters.inky.dotCounter);
        personalCounters.add(2,characters.clyde.dotCounter);
        globalDotCounter = new DotCounter(DotCounter.Type.global, maze);
        blinkyCounter = characters.blinky.dotCounter;
        
        
        updateSpecifications();
        setCounterLimits();
    }
    
    private void updateSpecifications(){
        lastDotTimerLimit = levelSpecs.getLastDotTimerLimit(currentLevel);
        elroyLimit1 = levelSpecs.getElroy1DotsLeft(currentLevel);
        elroyLimit2 = levelSpecs.getElroy2DotsLeft(currentLevel);
        personalCounterLimits = levelSpecs.getPersonalCounterLimits(currentLevel);
    }
    
     
    private void setCounterLimits(){
        blinkyCounter.setLimit(elroyLimit1);
        int i;
        for(i=0;i<personalCounters.size();i++){
            personalCounters.get(i).setLimit(personalCounterLimits[i]);
        }
    }
            
    private void updateElroyCounter(){
        blinkyCounter.increaseCounter();
        if(blinkyCounter.checkIfLimitReached()){
            System.out.println("Elroy Limit Reached");
            modeControl.cruiseElroyOn();
            characters.blinky.setElroySpeed(2);
            blinkyCounter.setLimit(elroyLimit2);
        }
    }
    
    private void updatePersonalCounters(){
        int i;
        for(i=0;i<personalCounters.size();i++){
            if(personalCounters.get(i).getStatus() == DotCounter.Status.enabled || personalCounters.get(i).getStatus() == DotCounter.Status.deactivated){
                if(characters.ghosts.get(i).isInPen()){
                    System.out.println("Activate Personal Counter "+i);
                    personalCounters.get(i).activateCounter();
                }
            }
            else if(personalCounters.get(i).getStatus() == DotCounter.Status.activated && characters.ghosts.get(i).isInPen()==false){
                personalCounters.get(i).deactivateCounter();
            }
            if(personalCounters.get(i).getStatus() == DotCounter.Status.activated){
                
                if(personalCounters.get(i).checkIfLimitReached()){
                    System.out.println("Ghost Limit Reached  "+i);
                    characters.ghosts.get(i).ghostControl.setLeavePenTrue();
                }
                else{
                    personalCounters.get(i).increaseCounter();
//                    System.out.println("Personal Counter "+i+" : "+personalCounters.get(i).getCount());
                    break;
                }
            }
        }
    }
    
    private void updateGlobalCounter(){
        if(globalDotCounter.getStatus() == DotCounter.Status.activated){
            globalDotCounter.increaseCounter();
            if(globalDotCounter.getCount() == GLOBAL_PINKY_LIMIT){
                System.out.println("Pinky leaves");
                characters.ghosts.get(0).ghostControl.setLeavePenTrue();
            }
            else if(globalDotCounter.getCount() == GLOBAL_INKY_LIMIT){
                System.out.println("Inky leaves");
                characters.ghosts.get(1).ghostControl.setLeavePenTrue();
            }
            else if(globalDotCounter.getCount() == GLOBAL_CLYDE_LIMIT && characters.clyde.isInPen()){
                System.out.println("deactivate global, enable personal");
                globalDotCounter.resetCounter();
                globalDotCounter.deactivateCounter();
                enablePersonalCounters();
            }
        }
    }
    
    public void updateLastDotTimer(){
            int i;
                for(i=0;i<characters.ghosts.size();i++){
                if(characters.ghosts.get(i).isInPen()){
                    if(TimerControl.timeCheck(getLastDotTimer(),lastDotTimerLimit)){
                        System.out.println("LastDotTimer limit: Most preferred ghost leaves");
                        characters.ghosts.get(i).ghostControl.setLeavePenTrue();
                        startLastDotTimer();
                    }
                }
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
        startLastDotTimer();
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
    
    public void resetLevel(){
        disablePersonalCounters();
        globalDotCounter.resetCounter();
        globalDotCounter.activateCounter();
        startLastDotTimer();
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
