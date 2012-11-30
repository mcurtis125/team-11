/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanprogram;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.event.ActionEvent;

/**
 *
 * @author ChanWoo
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
    
    Level level;
    
    public ScoreDisplay(Level level){
        this.level = level;
        this.totalScore = 0;
    }
    
    public void getScore(){
        score = getDotScore() + getEnergizerScore() + getBonusScore() + getGhostScore();
    }
    
    public void drawScore(Graphics g){
        getScore();
        Graphics2D score = (Graphics2D) g;
        score.drawString(""+(this.score+totalScore), 10, 35);
    }
    
    public void refresh(ActionEvent ae){
        dotCount = level.getDotsEaten();
        energizerCount = level.getEnergizersEaten();
        bonusScore = level.bonusControl.getBonusesEaten()*level.bonusControl.bonus1.getPoints();
        ghostScore = level.getGhostScore();
    }
    
    public void newLevel(){
        totalScore += score;
    }
    
    public void newGame(){
        dotCount = 0;
        energizerCount = 0;
        bonusScore = 0;
        ghostScore = 0;
        score = 0;
        totalScore = 0;
    }  
    
    public int getTotalScore(){
        return totalScore;
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
