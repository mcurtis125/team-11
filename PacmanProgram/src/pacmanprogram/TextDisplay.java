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
public class TextDisplay {
    
    Font font = new Font ("Monospaced", Font.BOLD, 20);
    private Color color = Color.WHITE;
    Pacman pacman;
    
    public TextDisplay(Pacman pacman){
        this.pacman = pacman;
    }
    
    public void drawText(Graphics g){
        Graphics2D text = (Graphics2D) g;
        text.setFont(font);
        text.setColor(color);
        text.drawString("Score: ", 10, 15);
        text.drawString("High Score", 165, 15);
        text.drawString("Lives: "+pacman.getLives(), 10, 565);
    }
}
