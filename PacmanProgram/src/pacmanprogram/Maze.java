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
        blinky.ghostControl.targetAlgorithmTest(g);
        //blinky.ghostControl.movementAlgorithmTest(g);
        pinky.draw(g);
        pinky.ghostControl.targetAlgorithmTest(g);
        //pinky.ghostControl.movementAlgorithmTest(g);
        inky.draw(g);
        inky.ghostControl.targetAlgorithmTest(g);
        //inky.ghostControl.movementAlgorithmTest(g);
        clyde.draw(g);
        clyde.ghostControl.targetAlgorithmTest(g);
        //clyde.ghostControl.movementAlgorithmTest(g);
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
