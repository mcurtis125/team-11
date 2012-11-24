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
public class Game extends JPanel implements ActionListener, KeyListener {
    Timer t = new Timer(10, this);
    int startLevel;
    Maze maze = new Maze();
    Pacman pacman = new Pacman(maze);
    Ghost blinky = new Ghost(Name.Blinky, maze);
    Ghost pinky = new Ghost(Name.Pinky, maze);
    Ghost inky = new Ghost(Name.Inky, maze);
    Ghost clyde = new Ghost(Name.Clyde, maze);
    Characters characters = new Characters(pacman,blinky,pinky,inky,clyde);
    boolean endGame=false;
    
    
    Level level = new Level(characters, maze, 1);
    
    TextDisplay text = new TextDisplay(pacman);
    ScoreDisplay score = new ScoreDisplay(level);
    
    public Game(/*int startLevel*/){
        t.setInitialDelay(1000);
        t.start();
//        this.startLevel = startLevel;
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }
    
    public void paintComponent(Graphics g){
       
            super.paintComponent(g);
            maze.draw(g);
            pacman.draw(g);
            blinky.draw(g);
            //blinky.ghostControl.targetAlgorithmTest(g);
            //blinky.ghostControl.movementAlgorithmTest(g);
            pinky.draw(g);
            //pinky.ghostControl.targetAlgorithmTest(g);
            //pinky.ghostControl.movementAlgorithmTest(g);
            inky.draw(g);
            //inky.ghostControl.targetAlgorithmTest(g);
            //inky.ghostControl.movementAlgorithmTest(g);
            clyde.draw(g);
            //clyde.ghostControl.targetAlgorithmTest(g);
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

    
    public void endGame(){
        t.stop();
    }
    
    public void startGame(){
        level.restartGame();
        t.start();
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
