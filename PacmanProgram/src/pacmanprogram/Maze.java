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

/**
 *
 * @author stavy92
 */
public class Maze extends JPanel implements ActionListener, KeyListener {
    Timer t = new Timer(10, this);
    Walls walls = new Walls();
    Pacman pacman = new Pacman();
    Ghost blinky = new Ghost();
    Ghost pinky = new Ghost();
    Ghost inky = new Ghost();
    Ghost clyde = new Ghost();
    Dots dots = new Dots();
    Energizers energizers = new Energizers();
    Level level = new Level(/*pacman, blinky, inky, pinky, clyde, dots, energizers*/);
    
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
        dots.draw(g);
        energizers.draw(g);
        level.drawBonus(g);
        //walls.draw(g);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        pacman.refresh(ae);
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
