/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanprogram;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;

/**
 *
 * @author ChanWoo
 */
public class ScoreDisplay {
    
    String totalScore;
    private int dotPoint;
    private int bonusPoint;
    
    Font font = new Font ("Monospaced", Font.BOLD, 20);
    private Color color = Color.WHITE;
    
    public void test(){
        dotPoint = 23*100;
        Integer i = new Integer(dotPoint);
        totalScore = i.toString();
    }
    
    public void drawScore(Graphics g){
        test();
        Graphics2D score = (Graphics2D) g;
        score.drawString(totalScore, 10, 35);
    }
    
}
