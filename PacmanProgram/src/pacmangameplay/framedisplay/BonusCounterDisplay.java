/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmangameplay.framedisplay;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import pacmangameplay.gamelogic.BonusSymbol;
import pacmangameplay.gamelogic.LevelLogic;

/**
 * Displays the symbols used for the last six levels completed and the current level at the bottom right corner of the screen.
 * @author stavy92
 */
public class BonusCounterDisplay {
    
    
    private static final double[] positionsX = {304, 322, 340, 358, 376, 394, 412};
    private static final double positionY = 544;
    boolean[] bonusesEatenLevels = new boolean[21];
    LevelLogic level;
    
    public BonusCounterDisplay(LevelLogic level){
        this.level = level;   
    }
    
    /**
     * Paints bonus symbols eaten in the last 7 levels (including current).
     * @param g 
     */
    public void drawSymbols(Graphics g){
        BonusSymbol bonus;
        int j = 0;
        for(int i = level.getLevel()-7;i<level.getLevel();i++){
            if(i>=0){
                if(bonusesEatenLevels[i]){
                    bonus = new BonusSymbol(level.levelSpecs.getBonusSymbol(i+1).getName());
                    bonus.setPosition(positionsX[j], positionY);
                    bonus.show(g);
                    j++;
                }
            }
        }
    }
    
    /**
     * Checks if Pacman ate bonus symbols in the current level.
     * @param ae 
     */
    public void refresh(ActionEvent ae){
        if(level.bonusControl.getBonusesEaten()>0){
            bonusesEatenLevels[level.getLevel()-1] = true;
        }
    }

    /**
     * Resets all levels to bonusEaten = false.
     */
    public void newGame(){
        for(int i=0;i<bonusesEatenLevels.length;i++){
            bonusesEatenLevels[i] = false;
        }
    }
}
