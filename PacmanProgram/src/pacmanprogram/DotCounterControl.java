/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanprogram;

import java.util.ArrayList;

/**
 * Controls all the dot counters in the game: 3 personal counters, Blinky's Elroy counter, the global counter, and the last dot timer.
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
    ArrayList<DotCounter> personalDotCounters= new ArrayList<DotCounter>();
    DotCounter globalDotCounter;
    DotCounter blinkyDotCounter;
    private double lastDotTimer;
    private double lastDotTimerLimit;
    private int[] elroyLimits;
    private int[] personalCounterLimits;
    private int elroyMode;
    
    
    public DotCounterControl(Characters characters, Maze maze, ModeControl modeControl, LevelSpecs levelSpecs, int level){
        currentLevel = level;
        this.characters = characters;
        this.maze = maze;
        this.modeControl = modeControl;
        this.levelSpecs = levelSpecs;
        personalDotCounters.add(0,characters.pinky.dotCounter);
        personalDotCounters.add(1,characters.inky.dotCounter);
        personalDotCounters.add(2,characters.clyde.dotCounter);
        globalDotCounter = new DotCounter(DotCounter.Type.global, maze);
        blinkyDotCounter = characters.blinky.dotCounter;
       
    }
       
    /**
     * Updates Blinky's Elroy Dot Counter. 
     * If it's enabled and one the limits is reached, Blinky turns into Elroy1 or Elroy2.
     * If it's disabled it waits for Clyde to leave the pen before being enabled again.
     */
    public void updateElroyCounter(){
        if(blinkyDotCounter.getStatus() == DotCounter.Status.enabled){
            if(blinkyDotCounter.checkIfLimitReached()){
                System.out.println("Elroy Limit Reached: ELROY ON");
                modeControl.cruiseElroyOn();
                if(elroyMode<2){
                    elroyMode++;
                    characters.blinky.setElroySpeed(elroyMode);
                    blinkyDotCounter.setLimit(elroyLimits[elroyMode]);
                }
                if(elroyMode==2){
                    blinkyDotCounter.deactivateCounter();
                    characters.blinky.setElroySpeed(elroyMode-1);
                    blinkyDotCounter.setLimit(elroyLimits[elroyMode-1]);
                }
//                System.out.println(elroyMode);
            }
        }
        else if(blinkyDotCounter.getStatus() == DotCounter.Status.disabled){
            if(characters.clyde.isInPen()==false){
//                System.out.println("Elroy counter enabled");
                elroyMode++;
                blinkyDotCounter.enableCounter();
            }
        }
        
        
    }
    
    /**
     * Updates the last dot timer. 
     * If the time limit is reached, the most preferred ghost in the pen leaves and the timer is restarted.
     */
    public void updateLastDotTimer(){
            int i;
                for(i=0;i<characters.ghosts.size();i++){
                if(characters.ghosts.get(i).isInPen()){
                    if(TimerControl.timeCheck(getLastDotTimer(),lastDotTimerLimit)){
//                        System.out.println("LastDotTimer limit: Most preferred ghost leaves");
                        characters.ghosts.get(i).ghostControl.setLeavePenTrue();
                        startLastDotTimer();
                    }
                }
            }
    }
     
    /**
     * Resets all 3 personal counters, Blinky's Elroy Counter and the global counter.
     */
    public void resetCounters(){
        blinkyDotCounter.resetCounter();
        elroyMode = 0;
        characters.pinky.dotCounter.resetCounter();
        characters.inky.dotCounter.resetCounter();
        characters.clyde.dotCounter.resetCounter();
        globalDotCounter.resetCounter();
    }
    
    /**
     * Resets the statuses of all the dot counters.
     */
    public void resetStatuses(){
        blinkyDotCounter.resetStatus();
        characters.pinky.dotCounter.resetStatus();
        characters.inky.dotCounter.resetStatus();
        characters.clyde.dotCounter.resetStatus();
        globalDotCounter.resetStatus();
    }
    
    /**
     * Updates all the counters when a dot is eaten.
     * -The last dot timer is restarted
     * -The personal and global counters are updated
     * -The Elroy counter is incremented
     */
    public void updateDotCounters(){
        startLastDotTimer();
        updatePersonalCounters();
        updateGlobalCounter();
        blinkyDotCounter.increaseCounter();
    }

    /**
     * Updates counters when a life is lost.
     * Disables all the ghosts' counters and activates and resets the global counter. Restats the last dot timer.
     */
    public void resetLevel(){
        disablePersonalCounters();
        globalDotCounter.resetCounter();
        globalDotCounter.activateCounter();
//        System.out.println("Elroy counter disabled");
        blinkyDotCounter.disableCounter();
        elroyMode--;
        startLastDotTimer();
    }

    /**
     * Resets counters for a new level.
     */
    public void changeLevel() {
        updateSpecifications();
        setCounterLimits();
        resetStatuses();
        resetCounters();
        startLastDotTimer();
    }
    
    /**
     * Resets counters for a new game.
     */
    public void newGame(){
        updateSpecifications();
        setCounterLimits();
        resetCounters();
        resetStatuses();
        startLastDotTimer();
    }
      
    //Gets the specifications for the current level
    private void updateSpecifications(){
        lastDotTimerLimit = levelSpecs.getLastDotTimerLimit(currentLevel);
        elroyLimits = levelSpecs.getElroyDotsLeft(currentLevel);
        personalCounterLimits = levelSpecs.getPersonalCounterLimits(currentLevel);
        
//        System.out.println(personalCounterLimits[0] + "  " + personalCounterLimits[1]+ "  " + personalCounterLimits[2]);
    }
    
    //Sets all the counter limits  
    private void setCounterLimits(){
        blinkyDotCounter.setLimit(elroyLimits[0]);
        int i;
        for(i=0;i<personalDotCounters.size();i++){
            personalDotCounters.get(i).setLimit(personalCounterLimits[i]);
        }
    }
    
    /*
     * If it's activated, increment counter whenever a dot is eaten.
     * Each ghost leaves the pen when his respective limit has been reached.
     * If Clyde is in the pen when the count is 32, the global counter is deactivated and the personal counters are re-enabled.
     */
    private void updateGlobalCounter(){
        if(globalDotCounter.getStatus() == DotCounter.Status.activated){
            globalDotCounter.increaseCounter();
            if(globalDotCounter.getCount() == GLOBAL_PINKY_LIMIT){
//                System.out.println("Pinky leaves");
                characters.ghosts.get(0).ghostControl.setLeavePenTrue();
            }
            else if(globalDotCounter.getCount() == GLOBAL_INKY_LIMIT){
//                System.out.println("Inky leaves");
                characters.ghosts.get(1).ghostControl.setLeavePenTrue();
            }
            else if(globalDotCounter.getCount() == GLOBAL_CLYDE_LIMIT && characters.clyde.isInPen()){
//                System.out.println("deactivate global, enable personal");
                globalDotCounter.resetCounter();
                globalDotCounter.deactivateCounter();
                enablePersonalCounters();
            }
        }
    }
     
    /*
     * Unless the counter is disabled, activate counter when the ghost enters the pen.
     * If it's activated, increase the counter whenever a dot is eaten.
     * Make ghost leave the pen when his limit is reached.
     * Deactivate counter once the ghost leaves the pen.
     */
    private void updatePersonalCounters(){
        int i;
        for(i=0;i<personalDotCounters.size();i++){
            if(personalDotCounters.get(i).getStatus() == DotCounter.Status.enabled || personalDotCounters.get(i).getStatus() == DotCounter.Status.deactivated){
                if(characters.ghosts.get(i).isInPen()){
//                    System.out.println("Activate Personal Counter "+i);
                    personalDotCounters.get(i).activateCounter();
                }
            }
            else if(personalDotCounters.get(i).getStatus() == DotCounter.Status.activated && characters.ghosts.get(i).isInPen()==false){
                personalDotCounters.get(i).deactivateCounter();
            }
            if(personalDotCounters.get(i).getStatus() == DotCounter.Status.activated){
                
                if(personalDotCounters.get(i).checkIfLimitReached()){
//                    System.out.println("Ghost Limit Reached  "+i);
//                    System.out.println(personalDotCounters.get(i).getCount());
                    characters.ghosts.get(i).ghostControl.setLeavePenTrue();
                }
                else{
                    personalDotCounters.get(i).increaseCounter();
//                    System.out.println("Personal Counter "+i+" : "+personalDotCounters.get(i).getCount());
                    break;
                }
            }
        }
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
}
