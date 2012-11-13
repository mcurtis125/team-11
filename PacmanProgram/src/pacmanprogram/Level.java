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
    private static final double TOLERANCE = 0.00815;

    
    private int mode;
    private int prevMode;
    private int currentLevel;
    private int dotCounter;
    private int energizerCounter;
    private int dotsEaten;
    private int energizersEaten;
    private double pacSpeedRatio;
    private double pacDotSpeedRatio;
    private double pacFrightSpeedRatio;
    private double pacFrightDotSpeedRatio;
    private double ghostSpeedRatio;
    private double ghostFrightSpeedRatio;
    private double ghostTunSpeedRatio;
    
    private double[] scatTimes;
    private double[] chaseTimes;
    private int frightTime;
    private int flashNumber;
    private boolean frightOn;
    private double pauseTime;
    private double scatChaseTimer;
    private double frightTimer;
    private double bonusTimer;
    private double timer;
    private boolean showBonus;
    private boolean bonusGone;
    private int bonusEaten;

    
//    private Timer lastDotTimer = new Timer();
//    private double timerLimit;
    
    
    Pacman pacman;
    Ghost blinky;
    Ghost pinky;
    Ghost inky;
    Ghost clyde;
    Walls walls;
    BonusSymbol bonus;
    
    
    public Level(Pacman pacman, Ghost blinky, Ghost inky, Ghost pinky, Ghost clyde, Walls walls) {
        currentLevel = 1;
        frightOn = false;
        showBonus = false;
        bonusGone = false;
        bonusEaten = 0;
        this.pacman = pacman;
        this.blinky = blinky;
        this.inky = inky;
        this.pinky = pinky;
        this.clyde = clyde;
        this.walls = walls;
        bonus = new BonusSymbol(levelSpecs.getBonusSymbol(currentLevel));
        updateSpecifications(); 
        assignSpeeds();
    }
    
    public void start() {
        startTimer();
        scatterMode();
        startScatChaseTimer();
    }
    
    public void drawBonus(Graphics g) {
        if(showBonus && !bonusGone){
            bonus.show(g);
        }      
    }


    public void refresh(ActionEvent ae) {
        //update dot count
        int[] map = walls.getMap();
        int dots = 0;
        int energizers = 0;
        int i;
        for(i=0; i<map.length; i++){
            if(map[i] == 3){
                dots++;
            }
            else if(map[i] == 4){
                energizers++;
            }
        }
        dotCounter = dots;
        energizerCounter = energizers;
        dotsEaten = 240-dotCounter;
        energizersEaten = 4-energizerCounter;
        
        //show bonus at the right time
        if(((dotsEaten+energizersEaten)==BonusSymbol.APPEAR_TIME_DOTS_EATEN[1]) || ((dotsEaten+energizersEaten)==BonusSymbol.APPEAR_TIME_DOTS_EATEN[0])){
            showBonus = true;
            startBonusTimer();
        }
        
        //erase bonus at the right time
        if(showBonus){
            if( (pacman.getX() > 216 && pacman.getX() < 232) && (pacman.getY() > 319 && pacman.getY() < 335)){
                eatBonus();
            }
            else if((getBonusTimer() > 9.5-TOLERANCE && getBonusTimer() < 9.5+TOLERANCE)){
                eraseBonus();
            }
        }
        
        
        //start fright mode when pacman eats energizers
        if(pacman.getCurrentTileIndex()==169||pacman.getCurrentTileIndex()==194||pacman.getCurrentTileIndex()==729||pacman.getCurrentTileIndex()==754){
            if(walls.getType(pacman.getCurrentTileIndex())==3){
                walls.changeType(pacman.getCurrentTileIndex(), 3, 1);
                System.out.println(getScatChaseTimer());
                pauseScatChaseTimer(frightTime);
                System.out.println(getScatChaseTimer());
                prevMode = getMode();
                frightMode();
                startFrightTimer();
                frightOn = true;
            }  
        }
        
        if(!frightOn){
            //scatter mode at appropriate times
            if((getScatChaseTimer() > scatTimes[0]-TOLERANCE && getScatChaseTimer() < scatTimes[0]+TOLERANCE) || (getScatChaseTimer() > scatTimes[1]-TOLERANCE && getScatChaseTimer() < scatTimes[1]+TOLERANCE) || (getScatChaseTimer() > scatTimes[2]-TOLERANCE && getScatChaseTimer() < scatTimes[2]+TOLERANCE))  {
                System.out.println(getScatChaseTimer());
                scatterMode();
            }
            //chase mode at appropriate times
            else if((getScatChaseTimer() > chaseTimes[0]-TOLERANCE && getScatChaseTimer() < chaseTimes[0]+TOLERANCE) || (getScatChaseTimer() > chaseTimes[1]-TOLERANCE && getScatChaseTimer() < chaseTimes[1]+TOLERANCE) || (getScatChaseTimer() > chaseTimes[2]-TOLERANCE && getScatChaseTimer() < chaseTimes[2]+TOLERANCE || (getScatChaseTimer() > chaseTimes[3]-TOLERANCE && getScatChaseTimer() < chaseTimes[3]+TOLERANCE)))  {
                System.out.println(getScatChaseTimer());
                chaseMode();
            }
        }
        else{
            if(getFrightTimer() > frightTime){
                frightOn = false;
                mode = prevMode;
                System.out.println("CHANGING BACK");
                setMode();
            }
        }        
    }
    
    public void eraseBonus(){
        bonusGone = true;
    }
    
    public void eatBonus(){
        bonusGone = true;
        bonusEaten++;
    }
    
    public void changeLevel(){
        currentLevel++;
        updateSpecifications();
        assignSpeeds();
        startTimer();
        resetLevel();
    }
    
    public void resetLevel(){
        frightOn = false;
        dotsEaten = 0;
        energizersEaten = 0;
        dotCounter = 240;
        energizerCounter = 4;
        pacman.reset();
        blinky.reset();
        pinky.reset();
        inky.reset();
        clyde.reset();
        scatterMode();
        startScatChaseTimer();
    }
    
    public int getDotsEaten(){
        return dotsEaten;
    }
    
    public int getDotsRemaining() {
        return dotCounter;
    }
    
    public int getEnergizersEaten(){
        return energizersEaten;
    }
    
    public int getEnergizersRemaining(){
        return energizerCounter;
    }
    
    public int getLevel(){
        return currentLevel;
    }
    
    private void updateSpecifications(){
        pacSpeedRatio = levelSpecs.getPacSpeedRatio(currentLevel);
        pacDotSpeedRatio = levelSpecs.getPacDotSpeedRatio(currentLevel);
        pacFrightSpeedRatio = levelSpecs.getPacFrightSpeedRatio(currentLevel);
        pacFrightDotSpeedRatio = levelSpecs.getPacFrightDotSpeedRatio(currentLevel);
        ghostSpeedRatio = levelSpecs.getGhostSpeedRatio(currentLevel);
        ghostFrightSpeedRatio = levelSpecs.getGhostFrightSpeedRatio(currentLevel);
        ghostTunSpeedRatio = levelSpecs.getGhostTunSpeedRatio(currentLevel);
        scatTimes = levelSpecs.getScatTimes(currentLevel);
        chaseTimes = levelSpecs.getChaseTimes(currentLevel);
        frightTime = levelSpecs.getFrightTime(currentLevel);
        flashNumber = levelSpecs.getFlashNumber(currentLevel);  
    }

    private void assignSpeeds() {
        pacman.assignSpeeds(pacSpeedRatio, pacDotSpeedRatio, pacFrightSpeedRatio, pacFrightDotSpeedRatio);
        blinky.assignSpeeds(ghostSpeedRatio, ghostTunSpeedRatio, ghostFrightSpeedRatio);
        pinky.assignSpeeds(ghostSpeedRatio, ghostTunSpeedRatio, ghostFrightSpeedRatio);
        inky.assignSpeeds(ghostSpeedRatio, ghostTunSpeedRatio, ghostFrightSpeedRatio);
        clyde.assignSpeeds(ghostSpeedRatio, ghostTunSpeedRatio, ghostFrightSpeedRatio);
    }
    
    private int getMode(){
        return mode;
    }
    
    private void scatterMode(){
        System.out.println("SCATTER MODE");
        mode = 1;
        setMode();
        
    }
    
    private void chaseMode(){
        System.out.println("CHASE MODE");
        mode = 2;
        setMode();
    }
    
    private void frightMode(){
        System.out.println("FRIGHTENED MODE");
        mode = 3;
        setMode();
    }
    
    private void setMode(){
        blinky.setMode(mode);
        inky.setMode(mode);
        pinky.setMode(mode);
        clyde.setMode(mode);
        pacman.setMode(mode);
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
    
    private void startBonusTimer() {
        bonusTimer = System.currentTimeMillis();
    }
    
    private double getBonusTimer() {
        if(bonusTimer == 0){
            return 0;
        }
        return ((System.currentTimeMillis()-bonusTimer)/1000);
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

    
   
}
