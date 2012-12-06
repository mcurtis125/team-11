/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmangameplay.framedisplay;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.event.ActionEvent;
import pacmangameplay.gamelogic.LevelLogic;

/**
 * 
 * Calculates and displays the score of current user
 * on the game play GUI.
 * 
 * @author ChanWoo
 * @author stavy92
 * 
 */
public class ScoreDisplay {
    
    private static final int DOT_POINTS = 10;
    private static final int E_POINTS = 50;
    
    private int score;
    private int totalScore;
    private int dotCount;
    private int energizerCount;
    private int bonusScore;
    private int ghostScore;
    
    private int prevLevel;
    
    Font font = new Font ("Monospaced", Font.BOLD, 20);
    private Color color = Color.WHITE;
    
    LevelLogic level;
    
    public ScoreDisplay(LevelLogic level){
        this.level = level;
        this.totalScore = 0;
    }
    
    /**
     * This method calculates the score of the current level.
     */
    public void getScore(){
        score = getDotScore() + getEnergizerScore() + getBonusScore() + getGhostScore();
    }
    
    
    /**
     * 
     * This method displays the calculated scores on the game play GUI
     * The displayed score keeps being updated as pacman eats different
     * kind of score sources
     * 
     * @param g It is for the 2D-graphic drawings
     */
    public void drawScore(Graphics g){
        getScore();
        Graphics2D score = (Graphics2D) g;
        score.drawString(""+(this.score+totalScore), 10, 35);
    }
    
    /**
     * Updates dot count, bonuses eaten and ghosts eaten for the score calculation.
     * @param ae 
     */
    public void refresh(ActionEvent ae){
        dotCount = level.getDotsEaten();
        energizerCount = level.getEnergizersEaten();
        
        //calculates socre generated by pacman eating bonus symbols
        bonusScore = level.bonusControl.getBonusesEaten()*level.bonusControl.bonus1.getPoints();
        ghostScore = level.getGhostScore();
    }
    
    /**
     * Adds the score of the last level to the total score.
     */
    public void newLevel(){
        totalScore += score;
    }
    
    /**
     * Resets all scores to 0 for a new game.
     */
    public void newGame(){
        dotCount = 0;
        energizerCount = 0;
        bonusScore = 0;
        ghostScore = 0;
        score = 0;
        totalScore = 0;
    }  
    
    /**
     * Returns total score.
     * @return 
     */
    public int getTotalScore(){
        return (totalScore+score);
    }
    

    private int getDotScore(){
        return dotCount*DOT_POINTS;
    }
    
    private int getEnergizerScore(){
        return energizerCount*E_POINTS;
    }
    
    
    private int getBonusScore(){
        return bonusScore;
    }
    
    private int getGhostScore(){
        return ghostScore;
    }
}