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

    PositionVelocityGetter getCharacterPositions;
    
    private static final double TIME_TOLERANCE = 0.00815;
    private static final double POSITION_TOLERANCE = 1.6;

    
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
    GhostControl ghostControl;
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
        
        getCharacterPositions= new PositionVelocityGetter(pacman.pacControl, blinky.ghostControl, pinky.ghostControl, 
                                                                    inky.ghostControl, clyde.ghostControl);
        startTimer();
        scatterMode();
        startScatChaseTimer();
        
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

    public void refresh(ActionEvent ae) {
        getCharacterPositions.refresh(ae);
        checkCollision();
        checkGameOver();
        updateDotCount();
        checkLevelChange();
        updateBonusSymbols();
        updateModes();           
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
                    ghostControl.goHome();
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
                pacman.newGame();
                currentLevel = 1;
                walls.resetMap();
                resetLevel();
                
            }
            catch(Exception e){}
        }  
    }
    
    private void updateDotCount(){
        int[] map = walls.getMap();
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
            walls.resetMap();
            System.out.println(""+currentLevel);
        }
    }
    
    public void changeLevel(){
        currentLevel++;
        walls.resetMap();
        updateSpecifications();
        assignSpeeds();
        startTimer();
        resetLevel();
    }
    
    private void updateBonusSymbols(){
        //show bonus at the right time
        if(!showBonus){
            if(((dotsEaten+energizersEaten)==BonusSymbol.APPEAR_TIME_DOTS_EATEN[1]) || ((dotsEaten+energizersEaten)==BonusSymbol.APPEAR_TIME_DOTS_EATEN[0])){
                showBonus = true;
                bonusGone = false;
                startBonusTimer();
            }
        }
        else{
            if(pacEatingBonus()){
                System.out.println("Eating bonus");
                eatBonus();
            }
            else if(timeCheck(getBonusTimer(), 9.5)){
                System.out.println("Bonus Time Up");
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
        return (positionCheck(pacman.getX(),224) && positionCheck(pacman.getY(), 320));
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
        setMode();
    }
    
    private void setMode(){
        blinky.setMode(mode);
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

    private boolean timeCheck(double num1, double num2){
        return ((num1 > (num2-TIME_TOLERANCE)) && (num1 < (num2+TIME_TOLERANCE)));
    }
   
    private boolean positionCheck(double pos1, double pos2){
        return ((pos1 > (pos2-POSITION_TOLERANCE)) && (pos1 < (pos2+POSITION_TOLERANCE)));
    }
}
