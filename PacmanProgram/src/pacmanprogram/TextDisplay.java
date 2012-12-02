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
 * @author stavy92
 * 
 * TextDisplay class draws necessary strings on game play GUI panel
 * It enables to write number of lives left, level, 
 * and static strings like "Score", "Lives", etc
 * 
 */
public class TextDisplay {
    
    Font font = new Font ("Monospaced", Font.BOLD, 20);
    private Color color = Color.WHITE;
    Pacman pacman;
    Level level;
    
    /**
     * 
     * This public TextDisplay method is to get values of 
     * current lives left from Pacman class, and to get value of current level
     * from Level class.
     * 
     * @param level make level class as an object
     * @param pacman make pacman class as an object
     */
    public TextDisplay(Pacman pacman, Level level){
        this.pacman = pacman;
        this.level = level;
    }
    
    /**
     * 
     * This public drawText method is for displaying texts (strings)
     * It sets font type, and font color
     * This enables to display static texts, like "Score:", "Lives", etc
     * at specific coordinate on game play GUI
     * This public method is called by Game class.
     * 
     * @param g It is for the 2D-graphic drawings
     * 
     */
    public void drawText(Graphics g){
        Graphics2D text = (Graphics2D) g;
        text.setFont(font);
        text.setColor(color);
        text.drawString("Score: ", 10, 15);
        text.drawString("High Score", 165, 15);
        text.drawString("Lives: "+pacman.getLives(), 10, 565);
        text.drawString("Level: "+level.getLevel(), 165, 565);
    }
}
