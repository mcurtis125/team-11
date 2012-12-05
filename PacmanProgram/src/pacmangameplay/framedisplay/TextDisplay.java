/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmangameplay.framedisplay;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import pacmangameplay.gamelogic.LevelLogic;

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
    LevelLogic level;
    
    public TextDisplay(LevelLogic level){
        this.level = level;
    }
    
    /**
     * 
     * Paints all in-game text except the score.
     * 
     * @param g 
     * 
     */
    public void drawText(Graphics g){
        Graphics2D text = (Graphics2D) g;
        text.setFont(font);
        text.setColor(color);
        text.drawString("Score: ", 10, 15);
        text.drawString("High Score", 165, 15);
        text.drawString("Lives: "+level.characters.pacman.getLives(), 10, 560);
        text.drawString("Level: "+level.getLevel(), 165, 560);
    }
}
