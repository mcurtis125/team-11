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

    
    //mode
    private int mode;
    private int elroyMode;
    private int prevMode;
    private boolean frightOn;

    
    //start
    private int currentLevel;
    
    //dot counters
    private int dotCounter;
    private int energizerCounter;
    private int dotsEaten;
    private int energizersEaten;
    private int dotCount;
    private int prevDotCount;
    private boolean dotEaten;
    private DotCounter globalDotCounter;
    private int elroyLimit1;
    private int elroyLimit2;
    private int[] personalCounterLimits;
    
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
    
    
    //timers
    private double[] scatTimes;
    private double[] chaseTimes;
    private int frightTime;
    private int flashNumber;
    private double pauseTime;
    private double scatChaseTimer;
    private double frightTimer;
    private double bonusTimer;
    private double timer;
    private double lastDotTimer;
    private double lastDotTimerLimit;
    private double[] lastDotTimerLimits = {4,4,4,4,3};

    //bonus
    private boolean showBonus;
    private boolean bonusGone;
    private int bonusEaten;
    BonusSymbol bonus;
    
    //characters    
    Pacman pacman;
    Ghost blinky;
    Ghost pinky;
    Ghost inky;
    Ghost clyde;
    ArrayList<Ghost> ghosts = new ArrayList<Ghost>();
    ArrayList<DotCounter> personalCounters= new ArrayList<DotCounter>();
    GhostControl ghostControl;
    PositionVelocityGetter getCharacterPositions;
    
    
    Maze maze;
    
    
   

    
    
    public Level(Pacman pacman, Ghost blinky, Ghost inky, Ghost pinky, Ghost clyde, Maze walls, int level) {
        currentLevel = level;
        
        //bonus
        showBonus = false;
        bonusGone = false;
        bonusEaten = 0;
        bonus = new BonusSymbol(levelSpecs.getBonusSymbol(currentLevel));
        
        // characters
        this.pacman = pacman;
        this.blinky = blinky;
        this.inky = inky;
        this.pinky = pinky;
        this.clyde = clyde;
        this.maze = walls;
        getCharacterPositions= new PositionVelocityGetter(pacman.pacControl, blinky.ghostControl, pinky.ghostControl, 
                                                                    inky.ghostControl, clyde.ghostControl);
        //counters
        globalDotCounter = new DotCounter(Type.global, maze);
        ghosts.add(this.pinky);
        ghosts.add(this.inky);
        ghosts.add(this.clyde);
        ghosts.add(this.blinky);
        personalCounters.add(pinky.dotCounter);
        personalCounters.add(inky.dotCounter);
        personalCounters.add(clyde.dotCounter);
        
        //speeds and timers
        updateSpecifications();
        setCounterLimits();
        assignSpeeds();
        startTimer();
        startScatChaseTimer();

        //modes
        elroyMode = 1;
        scatterMode();
        frightOn = false;
        
        
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
        
        scatTimes = levelSpecs.getScatTimes(currentLevel);
        chaseTimes = levelSpecs.getChaseTimes(currentLevel);
        frightTime = levelSpecs.getFrightTime(currentLevel);
        flashNumber = levelSpecs.getFlashNumber(currentLevel);
        lastDotTimerLimit = levelSpecs.getLastDotTimerLimit(currentLevel);
        
        elroyLimit1 = levelSpecs.getElroy1DotsLeft(currentLevel);
        elroyLimit2 = levelSpecs.getElroy2DotsLeft(currentLevel);
        personalCounterLimits = levelSpecs.getPersonalCounterLimits(currentLevel);
    }
    
    private void setCounterLimits(){
        blinky.dotCounter.setLimit(elroyLimit1);
        int i;
        for(i=0;i<personalCounters.size();i++){
            personalCounters.get(i).setLimit(personalCounterLimits[i]);
        }
    }
        
    private void assignSpeeds() {
        pacman.assignSpeeds(pacSpeedRatio, pacDotSpeedRatio, pacFrightSpeedRatio, pacFrightDotSpeedRatio);
        blinky.assignSpeeds(ghostSpeedRatio, elroy1SpeedRatio, elroy2SpeedRatio, ghostTunSpeedRatio, ghostFrightSpeedRatio);
        blinky.setElroySpeed(1);
        pinky.assignSpeeds(ghostSpeedRatio, elroy1SpeedRatio, elroy2SpeedRatio, ghostTunSpeedRatio, ghostFrightSpeedRatio);
        inky.assignSpeeds(ghostSpeedRatio, elroy1SpeedRatio, elroy2SpeedRatio, ghostTunSpeedRatio, ghostFrightSpeedRatio);
        clyde.assignSpeeds(ghostSpeedRatio, elroy1SpeedRatio, elroy2SpeedRatio, ghostTunSpeedRatio, ghostFrightSpeedRatio);
    }
    
    public void refresh(ActionEvent ae) {
        getCharacterPositions.refresh(ae);
        checkCollision();
        checkGameOver();
        updateDotCount();
        checkLevelChange();
        updateBonusSymbols();
        updateModes();
        updateDotCounters();
    }

    private void resetLevel(){
        frightOn = false;
        pacman.resetPosition();
        blinky.resetPosition();
        pinky.resetPosition();
        inky.resetPosition();
        clyde.resetPosition();
        scatterMode();
        startScatChaseTimer();
    }
    
    private void checkCollision(){
        if(getCharacterPositions.pacmanGhostCollisionCheck()!=null){
            try{ 
                if(mode==1||mode==2){
                    
                    Thread.sleep(1500);
                    System.out.println("LOSE LIFE");
                    resetLevel();
                    pacman.loseLife();
                }
                if(mode==3){
                    ghostControl=getCharacterPositions.pacmanGhostCollisionCheck();
                    ghostControl.becomeNonExistent();
                    ghostControl.frightenedAndCaught=true;
                }

            }
            catch(Exception e){}
        }
    }
    
    private void checkGameOver(){
        if(pacman.getLives() == 0){
            try{
                System.out.println("GAME OVER");
                Thread.sleep(5000);
                pacman.resetLives();
                currentLevel = 1;
                maze.resetMaze();
                updateSpecifications();
                blinky.dotCounter.resetCounter();
                resetPersonalCounters();
                globalDotCounter.resetCounter();
                setCounterLimits();
                assignSpeeds();
                startTimer();
                resetLevel();
            }
            catch(Exception e){}
        }  
    }
    
    private void updateDotCount(){
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
        dotCounter = dots;
        energizerCounter = energizers;
        dotsEaten = 240-dotCounter;
        energizersEaten = 4-energizerCounter;      
    }
    
    private void checkLevelChange(){
        if((getDotsRemaining()+getEnergizersRemaining()) == 0){
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
        maze.resetMaze();
        updateSpecifications();
        setCounterLimits();
//        resetCounters();
        assignSpeeds();
        startTimer();
        resetLevel();
    }
    
    private void updateBonusSymbols(){
        if(!showBonus){
            if(((dotsEaten+energizersEaten)==BonusSymbol.APPEAR_TIME_DOTS_EATEN[1]) || ((dotsEaten+energizersEaten)==BonusSymbol.APPEAR_TIME_DOTS_EATEN[0])){
                showBonus = true;
                bonusGone = false;
                startBonusTimer();
            }
        }
        else{
            if(pacEatingBonus()){
                eatBonus();
            }
            else if(timeCheck(getBonusTimer(), 9.5)){
                eraseBonus();
            }
        } 
    }
    
    public void drawBonus(Graphics g) {
        if(showBonus && !bonusGone){
            bonus.show(g);
        }      
    }
    
    public void eraseBonus(){
        showBonus = false;
        bonusGone = true;
    }
    
    public void eatBonus(){
        showBonus = false;
        bonusGone = true;
        bonusEaten++;
    }
    
    private void updateModes(){
        
        //start fright mode when pacman eats energizers
        if(pacEatingEnergizer()){
            if(maze.getType(pacman.getCurrentTileIndex())==3){
                maze.changeType(pacman.getCurrentTileIndex(), 3, 1);
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
                System.out.println(""+getScatChaseTimer());
                System.out.println("CHANGING BACK");
                setMode();
            }
        } 
    }
    
    private boolean pacEatingEnergizer(){
        return ((positionCheck(pacman.getX(),16) && positionCheck(pacman.getY(),96))
                ||(positionCheck(pacman.getX(),416) && positionCheck(pacman.getY(),96))
                ||(positionCheck(pacman.getX(),16) && positionCheck(pacman.getY(),416))
                ||(positionCheck(pacman.getX(),416) && positionCheck(pacman.getY(),416)));
    }
    
    private boolean pacEatingBonus(){
        return (positionCheck(pacman.getX(),220) && positionCheck(pacman.getY(), 320));
    }
    
    private boolean isScatterTime(){
        return (timeCheck(getScatChaseTimer(),scatTimes[0]) 
                || timeCheck(getScatChaseTimer(),scatTimes[1]) 
                || timeCheck(getScatChaseTimer(),scatTimes[2]));
    }
    
    private boolean isChaseTime(){
        return (timeCheck(getScatChaseTimer(), chaseTimes[0]) 
                || timeCheck(getScatChaseTimer(), chaseTimes[1]) 
                || timeCheck(getScatChaseTimer(), chaseTimes[2]) 
                || timeCheck(getScatChaseTimer(), chaseTimes[3]));
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
        elroyMode = 3;
        setMode();
    }
    
    private void cruiseElroyOn(){
        elroyMode = 4;
    }
    
    private void cruiseElroyOff(){
        elroyMode = mode;
    }
    
    private void setMode(){
        blinky.setMode(elroyMode);
        inky.setMode(mode);
        pinky.setMode(mode);
        clyde.setMode(mode);
        pacman.setMode(mode);
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
    
    private int getMode(){
        return mode;
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
    
    private void startLastDotTimer(){
        lastDotTimer = System.currentTimeMillis();
    }
    
    private double getLastDotTimer(){
        if(lastDotTimer == 0){
            return 0;
        }
        return (System.currentTimeMillis()-lastDotTimer)/1000;
    }

    private boolean timeCheck(double num1, double num2){
        return ((num1 > (num2-TIME_TOLERANCE)) && (num1 < (num2+TIME_TOLERANCE)));
    }
   
    private boolean positionCheck(double pos1, double pos2){
        return ((pos1 > (pos2-POSITION_TOLERANCE)) && (pos1 < (pos2+POSITION_TOLERANCE)));
    }
    
    private void resetPersonalCounters(){
        blinky.dotCounter.resetCounter();
        pinky.dotCounter.resetCounter();
        inky.dotCounter.resetCounter();
        clyde.dotCounter.resetCounter();
    }
    
    private void updateDotCounters(){
        countDots();
        updateLastDotTimer();
        if(dotEaten){
            startLastDotTimer();
            updateElroyCounter();
            updatePersonalCounters();
            updateGlobalCounter();
        }
    }
    
    private void enablePersonalCounters(){
        pinky.dotCounter.enableCounter();
        inky.dotCounter.enableCounter();
        clyde.dotCounter.enableCounter();
    }
    
    private void countDots() {
        prevDotCount = dotCount;
        int[] map = maze.getMap();
        int i;
        for(i=0; i<map.length; i++){
            if(map[i] > 1){
                dotCount++;
            }
        }
        if(prevDotCount<dotCount){
            dotEaten = true;
        }
        else{
            dotEaten = false;
        }
    }
    
    private void updateElroyCounter(){
        if(blinky.dotCounter.getStatus() == Status.activated){
            blinky.dotCounter.updateCounter();
        }
        if(blinky.dotCounter.checkIfLimitReached()){
            cruiseElroyOn();
            blinky.setElroySpeed(2);
            blinky.dotCounter.setLimit(elroyLimit2);
        }
    }
    
    private void updatePersonalCounters(){
        int i;
        for(i=0;i<personalCounters.size();i++){
            if(personalCounters.get(i).getStatus() == Status.enabled || personalCounters.get(i).getStatus() == Status.deactivated){
                if(ghosts.get(i).isInPen()) personalCounters.get(i).activateCounter();
            }
            else if(personalCounters.get(i).getStatus() == Status.activated){
                personalCounters.get(i).updateCounter();
                if(personalCounters.get(i).checkIfLimitReached()){
                    personalCounters.get(i).deactivateCounter();
                    //leave pen
                    if(i != (personalCounters.size()-1)){
                        personalCounters.get(i+1).activateCounter();
                    }
                }
            }
        }
    }
    
    private void updateGlobalCounter(){
        if(globalDotCounter.getStatus() == Status.activated){
            globalDotCounter.updateCounter();
            globalDotCounter.checkIfLimitReached();
            if(globalDotCounter.getCount() == 7){
                //pinky.leavePen();
            }
            else if(globalDotCounter.getCount() == 17){
                //inky.leavePen();
            }
            else if(globalDotCounter.getCount() == 32 && clyde.isInPen()){
                globalDotCounter.resetCounter();
                globalDotCounter.deactivateCounter();
                enablePersonalCounters();
            }
        }
    }
    
    private void updateLastDotTimer(){
        if(timeCheck(getLastDotTimer(),lastDotTimerLimit)){
            startLastDotTimer();
            //most preferred ghost in pen-> deactivateCounter(); leavePen();
        }
    }
}
