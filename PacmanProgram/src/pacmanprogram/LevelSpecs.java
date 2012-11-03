/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanprogram;

import java.util.ArrayList;
import pacmanprogram.BonusSymbols.Name;

/**
 *
 * @author stavy92
 */
class LevelSpecs {
    private double[] pacSpeedRatios = {0.8,0.9,0.9,0.9,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0.9};
    private double[] pacDotSpeedRatios = {0.71,0.79,0.79,0.79,0.87,0.87,0.87,0.87,0.87,0.87,0.87,0.87,0.87,0.87,0.87,0.87,0.87,0.87,0.87,0.87,0.79};
    private double[] pacFrightSpeedRatios = {0.9,0.95,0.95,0.95,1};
    private double[] pacFrightDotSpeedRatios = {0.79,0.83,0.83,0.83,0.87};
    private double[] ghostSpeedRatios = {0.75,0.85,0.85,0.85,0.95};
    private double[] ghostFrightSpeedRatios = {0.5,0.55,0.55,0.55,0.6};
    private double[] ghostTunSpeedRatios = {0.4,0.45,0.45,0.45,0.5};
    private int[] frightTimes = {6,5,4,3,2,5,2,2,1,5,2,1,1,3,1,1,0,1,0,0,0};
    private int[] flashNumbers = {5,5,5,5,5,5,5,5,3,5,5,3,3,5,3,3,0,3,0,0,0};
    private double[] lastDotTimerLimits = {4,4,4,4,3};
    private double[] elroy1DotsLeft = {20,30,40,40,40,50,50,50,60,60,60,80,80,80,100,100,100,100,120,120,120};
    private double[] elroy2DotsLeft = {10,15,20,20,20,25,25,25,30,30,30,40,40,40,50,50,50,50,60,60,60};
    private double[] elroy1SpeedRatios = {0.8,0.9,0.9,0.9,1};
    private double[] elroy2SpeedRatios = {0.85,0.95,0.95,0.95,1.05};
    ArrayList<BonusSymbols> bonusSymbols = new ArrayList<BonusSymbols>();
    
    public LevelSpecs(){
        bonusSymbols.add(0,new BonusSymbols(Name.cherry));
        bonusSymbols.add(new BonusSymbols(Name.strawberry));
        bonusSymbols.add(new BonusSymbols(Name.peach));
        bonusSymbols.add(new BonusSymbols(Name.peach));
        bonusSymbols.add(new BonusSymbols(Name.apple));
        bonusSymbols.add(new BonusSymbols(Name.apple));
        bonusSymbols.add(new BonusSymbols(Name.grape));
        bonusSymbols.add(new BonusSymbols(Name.grape));
        bonusSymbols.add(new BonusSymbols(Name.galaxian));
        bonusSymbols.add(new BonusSymbols(Name.galaxian));
        bonusSymbols.add(new BonusSymbols(Name.bell));
        bonusSymbols.add(new BonusSymbols(Name.bell));
        bonusSymbols.add(new BonusSymbols(Name.key));
    }
    
    public double getPacSpeedRatio(int level){
        if(level>21){
            return pacSpeedRatios[20];
        }
        return pacSpeedRatios[level-1];
    }
    public double getPacDotSpeedRatio(int level){
        if(level>21){
            return pacDotSpeedRatios[20];
        }
        return pacDotSpeedRatios[level-1];
    }
    public double getPacFrightSpeedRatio(int level){
        if(level>5){
            return pacFrightSpeedRatios[4];
        }
        return pacFrightSpeedRatios[level-1];
    }
    public double getPacFrightDotSpeedRatio(int level){
        if(level>5){
            return pacFrightDotSpeedRatios[4];
        }
        return pacFrightDotSpeedRatios[level-1];
    }
    public double getGhostSpeedRatio(int level){
        if(level>5){
            return ghostSpeedRatios[4];
        }
        return ghostSpeedRatios[level-1];
    }
    public double getGhostFrightSpeedRatio(int level){
        if(level>5){
            return ghostFrightSpeedRatios[4];
        }
        return ghostFrightSpeedRatios[level-1];
    }
    public double getGhostTunSpeedRatio(int level){
        if(level>5){
            return ghostTunSpeedRatios[4];
        }
        return ghostTunSpeedRatios[level-1];
    }
    public int getFrightTime(int level){
        if(level>21){
            return frightTimes[20];
        }
        return frightTimes[level-1];
    }
    public int getFlashNumber(int level){
        if(level>21){
            return flashNumbers[20];
        }
        return flashNumbers[level-1];
    }
    public double getLastDotTimerLimit(int level){
        if(level>5){
            return lastDotTimerLimits[4];
        }
        return lastDotTimerLimits[level-1];
    }
    public double getElroy1DotsLeft(int level){
        if(level>21){
            return elroy1DotsLeft[20];
        }
        return elroy1DotsLeft[level-1];
    }
    public double getElroy2DotsLeft(int level){
        if(level>21){
            return elroy2DotsLeft[20];
        }
        return elroy2DotsLeft[level-1];
    }
    public double getElroy1SpeedRatio(int level){
        if(level>5){
            return elroy1SpeedRatios[4];
        }
        return elroy1SpeedRatios[level-1];
    }
    public double getElroy2SpeedRatio(int level){
        if(level>5){
            return elroy2SpeedRatios[4];
        }
        return elroy2SpeedRatios[level-1];
    }
    public BonusSymbols getBonusSymbol(int level){
        if(level>13){
            return bonusSymbols.get(12);
        }
        return bonusSymbols.get(level-1);
    }
}
