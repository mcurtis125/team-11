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
    
    String totalScore;
    private int totalDotCount;
    private int currentLevelDotCount;
    private int energizerCount;
    private int currentLevelEnergizerCount;
    private int bonusCount;
    private int ghostCount;
    
    
    Font font = new Font ("Monospaced", Font.BOLD, 20);
    private Color color = Color.WHITE;
    
    Level level;
    
    public ScoreDisplay(Level level){
        this.level = level;
    }
    
    public void test(){
        bonusCount = level.bonusControl.getBonusesEaten();
        totalDotCount = level.getDots();
        totalScore = ""+bonusCount*100 + totalDotCount*10;
    }
    
    public void drawScore(Graphics g){
        test();
        Graphics2D score = (Graphics2D) g;
        score.drawString(totalScore, 10, 35);
    }
    
    public void refresh(ActionEvent ae){
        
    }
    
       
}
