/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmangameplay.gamelogic;

import java.util.ArrayList;
import pacmangameplay.gamelogic.BonusSymbol.Name;

/**
 * Contains all the level specifications for the game.
 * @author stavy92
 */
public class LevelSpecs {
    private double[] pacSpeedRatios = {0.8,0.9,0.9,0.9,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0.9};
    private double[] pacDotSpeedRatios = {0.71,0.79,0.79,0.79,0.87,0.87,0.87,0.87,0.87,0.87,0.87,0.87,0.87,0.87,0.87,0.87,0.87,0.87,0.87,0.87,0.79};
    private double[] pacFrightSpeedRatios = {0.9,0.95,0.95,0.95,1};
    private double[] pacFrightDotSpeedRatios = {0.79,0.83,0.83,0.83,0.87};
    private double[] ghostSpeedRatios = {0.75,0.85,0.85,0.85,0.95};
    private double[] ghostFrightSpeedRatios = {0.5,0.55,0.55,0.55,0.6};
    private double[] ghostTunSpeedRatios = {0.4,0.45,0.45,0.45,0.5};
    private double[][] chaseTimes = {{7,34,59,84},{7,34,59,1033.167},{5,30,55,1037.167}};
    private double [][] scatTimes = {{27,54,79},{27,54,1033},{25,50,1037}};
    private int[] frightTimes = {6,5,4,3,2,5,2,2,1,5,2,1,1,3,1,1,0,1,0,0,0};
    private double[] lastDotTimerLimits = {4,4,4,4,3};
    private int[][] elroyDotsLeft = {{20,10,10},{30,15,15},{40,20,20},{40,20,20},{40,20,20},{50,25,25},{50,25,25},{50,25,25},{60,30,30},{60,30,30},{60,30,30},{80,40,40},{80,40,40},{80,40,40},{100,50,50},{100,50,50},{100,50,0},{100,50,50},{120,60,0},{120,60,60},{120,60,60}};
    private double[] elroy1SpeedRatios = {0.8,0.9,0.9,0.9,1};
    private double[] elroy2SpeedRatios = {0.85,0.95,0.95,0.95,1.05}; 
    private int[][] personalCounterLimits = {{0,30,60},{0,0,50},{0,0,0}};
    ArrayList<BonusSymbol> bonusSymbols = new ArrayList<BonusSymbol>();
    
    public LevelSpecs(){
        bonusSymbols.add(new BonusSymbol(Name.cherry));
        bonusSymbols.add(new BonusSymbol(Name.strawberry));
        bonusSymbols.add(new BonusSymbol(Name.orange));
        bonusSymbols.add(new BonusSymbol(Name.orange));
        bonusSymbols.add(new BonusSymbol(Name.apple));
        bonusSymbols.add(new BonusSymbol(Name.apple));
        bonusSymbols.add(new BonusSymbol(Name.melon));
        bonusSymbols.add(new BonusSymbol(Name.melon));
        bonusSymbols.add(new BonusSymbol(Name.tulip));
        bonusSymbols.add(new BonusSymbol(Name.tulip));
        bonusSymbols.add(new BonusSymbol(Name.bell));
        bonusSymbols.add(new BonusSymbol(Name.bell));
        bonusSymbols.add(new BonusSymbol(Name.key));
    }

    /**
     * Returns pacman's normal speed.
     * @param level
     * @return ratio of max speed
     */
    public double getPacSpeedRatio(int level){
        if(level>21){
            return pacSpeedRatios[20];
        }
        return pacSpeedRatios[level-1];
    }
    /**
     * Returns pacman's dot speed.
     * @param level
     * @return ratio of max speed
     */
    public double getPacDotSpeedRatio(int level){
        if(level>21){
            return pacDotSpeedRatios[20];
        }
        return pacDotSpeedRatios[level-1];
    }
    /**
     * Returns pacman's fright speed.
     * @param level
     * @return ratio of max speed
     */
    public double getPacFrightSpeedRatio(int level){
        if(level>5){
            return pacFrightSpeedRatios[4];
        }
        return pacFrightSpeedRatios[level-1];
    }
    /**
     * Returns pacman's fright dot speed.
     * @param level
     * @return ratio of max speed
     */
    public double getPacFrightDotSpeedRatio(int level){
        if(level>5){
            return pacFrightDotSpeedRatios[4];
        }
        return pacFrightDotSpeedRatios[level-1];
    }
    /**
     * Returns ghosts' normal speed.
     * @param level
     * @return ratio of max speed
     */
    public double getGhostSpeedRatio(int level){
        if(level>5){
            return ghostSpeedRatios[4];
        }
        return ghostSpeedRatios[level-1];
    }
    /**
     * Returns ghosts' fright speed.
     * @param level
     * @return ratio of max speed
     */
    public double getGhostFrightSpeedRatio(int level){
        if(level>5){
            return ghostFrightSpeedRatios[4];
        }
        return ghostFrightSpeedRatios[level-1];
    }
    /**
     * Returns ghosts' tunnel speed.
     * @param level
     * @return ratio of max speed
     */
    public double getGhostTunSpeedRatio(int level){
        if(level>5){
            return ghostTunSpeedRatios[4];
        }
        return ghostTunSpeedRatios[level-1];
    }
    /**
     * Returns the times at which to change to scatter mode.
     * @param level
     * @return array of times
     */
    public double[] getScatTimes(int level){
        if(level==1){
            return scatTimes[0];
        }
        else if(level>1 && level<5){
            return scatTimes[1];
        }
        else{
            return scatTimes[2];
        }
    }
    /**
     * Returns the times at which to change to chase mode.
     * @param level
     * @return array of times
     */
    public double[] getChaseTimes(int level){
        if(level==1){
            return chaseTimes[0];
        }
        else if(level>1 && level<5){
            return chaseTimes[1];
        }
        else{
            return chaseTimes[2];
        }
    }
    /**
     * Returns the duration of frightened mode.
     * @param level
     * @return 
     */
    public int getFrightTime(int level){
        if(level>21){
            return frightTimes[20];
        }
        return frightTimes[level-1];
    }
    /**
     * Returns the timer limit of the last dot timer.
     * @param level
     * @return 
     */
    public double getLastDotTimerLimit(int level){
        if(level>5){
            return lastDotTimerLimits[4];
        }
        return lastDotTimerLimits[level-1];
    }
    /**
     * Returns the dot limit of the 2 elroy modes.
     * @param level
     * @return 
     */
    public int[] getElroyDotsLeft(int level){
        if(level>21){
            return elroyDotsLeft[20];
        }
        return elroyDotsLeft[level-1];
    }
    /**
     * Returns Elroy1's speed.
     * @param level
     * @return ratio of max speed
     */
    public double getElroy1SpeedRatio(int level){
        if(level>5){
            return elroy1SpeedRatios[4];
        }
        return elroy1SpeedRatios[level-1];
    }
    /**
     * Returns Elroy2's speed.
     * @param level
     * @return ratio of max speed
     */
    public double getElroy2SpeedRatio(int level){
        if(level>5){
            return elroy2SpeedRatios[4];
        }
        return elroy2SpeedRatios[level-1];
    }
    /**
     * Returns the dot limits of the personal counters.
     * @param level
     * @return 
     */
    public int[] getPersonalCounterLimits(int level){
        if(level>3){
            return personalCounterLimits[2];
        }
        return personalCounterLimits[level-1];
    }
    /**
     * Returns the Bonus Symbol of the current level.
     * @param level
     * @return 
     */
    public BonusSymbol getBonusSymbol(int level){
        if(level>13){
            return bonusSymbols.get(12);
        }
        return bonusSymbols.get(level-1);
    }
}