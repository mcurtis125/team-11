/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanprogram;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import pacmanprogram.Ghost.Name;

/**
 *
 * @author stavy92
 */
public class Maze extends JPanel implements ActionListener, KeyListener {
    Timer t = new Timer(10, this);
    Walls walls = new Walls();
    Pacman pacman = new Pacman(walls);
    Ghost blinky = new Ghost(Name.Blinky);
    Ghost pinky = new Ghost(Name.Pinky);
    Ghost inky = new Ghost(Name.Inky);
    Ghost clyde = new Ghost(Name.Clyde);
    TextDisplay text = new TextDisplay();
    ScoreDisplay score = new ScoreDisplay();
    PositionVelocityGetter posVelGet = new PositionVelocityGetter(pacman.pacControl, blinky.ghostControl, pinky.ghostControl, 
                                                                                        inky.ghostControl, clyde.ghostControl);
    Level level = new Level(pacman, blinky, inky, pinky, clyde, walls);
    
    public Maze(){
        t.start();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        walls.draw(g);
        pacman.draw(g);
        blinky.draw(g);
        pinky.draw(g);
        inky.draw(g);
        clyde.draw(g);
        level.drawBonus(g);
        text.drawText(g);
        score.drawScore(g);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        pacman.refresh(ae);
        blinky.refresh(ae);
        pinky.refresh(ae);
        inky.refresh(ae);
        clyde.refresh(ae);
        level.refresh(ae);
        posVelGet.refresh(ae);
        if(posVelGet.pacmanGhostCollisionCheck()){
            try{
                Thread.sleep(1500);
            }
            catch(Exception e){}

            pacman.reset();
            blinky.reset();
            pinky.reset();
            inky.reset();
            clyde.reset();
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        pacman.controlPacman(ke);
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }
    
    
    
}
