/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanprogram;

import pacmanprogram.DotCounter.Status;
import pacmanprogram.Ghost.Name;

/**
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
        scatterMode();
        startScatChaseTimer();
        frightOn = false;
        elroyOn = false;
    }
    
    private void updateSpecifications(){
        scatTimes = levelSpecs.getScatTimes(currentLevel);
        chaseTimes = levelSpecs.getChaseTimes(currentLevel);
        frightTime = levelSpecs.getFrightTime(currentLevel);
        flashNumber = levelSpecs.getFlashNumber(currentLevel);
    }
    
    public void updateModes(){
        
        //start fright mode when pacman eats energizers
        if(pacEatingEnergizer()){
            if(maze.getType(characters.pacman.getCurrentTileIndex())==3){
                maze.changeType(characters.pacman.getCurrentTileIndex(), 3, 1);
                System.out.println(getScatChaseTimer());
                pauseScatChaseTimer(frightTime);
                System.out.println(getScatChaseTimer());
                prevMode = getMode();
                prevElroyMode = getElroyMode();
                frightMode();
                startFrightTimer();
                frightOn = true;
            }  
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
                System.out.println("CHANGING BACK");
                setMode();
                System.out.println(""+mode);
            }
        } 
    }
    
    public void resetLevel(){
        frightOn = false;
        elroyMode = 1;
        scatterMode();
        startScatChaseTimer();
    }
    
    public void changeLevel(){
        currentLevel++;
        updateSpecifications();
        resetLevel();
    }
    
    public void restartGame(){
        currentLevel = 1;
        updateSpecifications();
        resetLevel();
    }
    
    public int getMode(){
        return mode;
    }
    
    public int getElroyMode(){
        return elroyMode;
    }
    
    private boolean pacEatingEnergizer(){
        double [][] eCoords = maze.getEnergizerCoords();
        return ((TimerControl.positionCheck(characters.pacman.getX(),eCoords[0][0]) && TimerControl.positionCheck(characters.pacman.getY(),eCoords[0][1]))
                ||(TimerControl.positionCheck(characters.pacman.getX(),eCoords[1][0]) && TimerControl.positionCheck(characters.pacman.getY(),eCoords[1][1]))
                ||(TimerControl.positionCheck(characters.pacman.getX(),eCoords[2][0]) && TimerControl.positionCheck(characters.pacman.getY(),eCoords[2][1]))
                ||(TimerControl.positionCheck(characters.pacman.getX(),eCoords[3][0]) && TimerControl.positionCheck(characters.pacman.getY(),eCoords[3][1])));
    }
    
    private boolean isScatterTime(){
        return (TimerControl.timeCheck(getScatChaseTimer(),scatTimes[0]) 
                || TimerControl.timeCheck(getScatChaseTimer(),scatTimes[1]) 
                || TimerControl.timeCheck(getScatChaseTimer(),scatTimes[2]));
    }
    
    private boolean isChaseTime(){
        return (TimerControl.timeCheck(getScatChaseTimer(), chaseTimes[0]) 
                || TimerControl.timeCheck(getScatChaseTimer(), chaseTimes[1]) 
                || TimerControl.timeCheck(getScatChaseTimer(), chaseTimes[2]) 
                || TimerControl.timeCheck(getScatChaseTimer(), chaseTimes[3]));
    }
    
    private void scatterMode(){
        System.out.println("SCATTER MODE");
        mode = 1;
        reverseDirection();
        setMode();
        
    }
    
    private void chaseMode(){
        System.out.println("CHASE MODE");
        mode = 2;
        reverseDirection();
        setMode();
    }
    
    private void frightMode(){
        System.out.println("FRIGHTENED MODE");
        mode = 3;
        int i;
        for(i=0;i<characters.ghosts.size();i++){
            characters.ghosts.get(i).ghostControl.becomeBlue();
        }
        reverseDirection();
        cruiseElroyOff();
        setMode();
    }
    
    public void cruiseElroyOn(){
        System.out.println("Cruise Elroy ON");
        elroyOn = true;
        elroyMode = 4;
    }
    
    public void cruiseElroyOff(){
        System.out.println("Cruise Elroy OFF");
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

    public void reverseDirection(){
//        int i;
//        for(i=0;i<characters.ghosts.size();i++){
//            characters.ghosts.get(i).ghostControl.setReverseDirectionTrue();
//        }
    }
}
