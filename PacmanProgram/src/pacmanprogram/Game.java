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
 * Game JPanel. Initiates all game components: Maze, Characters, Bonus Symbols, Timers, Displays.
 * @author stavy92
 */
public class Game extends JPanel implements ActionListener, KeyListener {
    Timer t = new Timer(10, this);
    Maze maze = new Maze();
    Pacman pacman = new Pacman(maze);
    Ghost blinky = new Ghost(Name.Blinky, maze);
    Ghost pinky = new Ghost(Name.Pinky, maze);
    Ghost inky = new Ghost(Name.Inky, maze);
    Ghost clyde = new Ghost(Name.Clyde, maze);
    Characters characters = new Characters(pacman,blinky,pinky,inky,clyde);
    New_GetLoginInfoClass currentUser;
    New_DataManagement dataManage;
    String fileName = "UserData.txt";
    String guestUsername = "Guest";
    Level level = new Level(characters, maze);
    TextDisplay text = new TextDisplay(pacman, level);
    ScoreDisplay score = new ScoreDisplay(level);
    BonusCounterDisplay bonusDisplay = new BonusCounterDisplay(level);
    
    public Game(){
        t.setInitialDelay(1000);
        t.start();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }
    
    /**
     * Paints the game.
     * @param g 
     */
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
            bonusDisplay.drawSymbols(g);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        checkLevelChange();
        checkGameOver();
        pacman.refresh(ae);
        blinky.refresh(ae);
        pinky.refresh(ae);
        inky.refresh(ae);
        clyde.refresh(ae);
        level.refresh(ae);
        score.refresh(ae);
        bonusDisplay.refresh(ae);
        repaint();
    }

    /**
     * Ends the current game and returns the final score.
     * @return Final score for logged in users, 0 for guests
     */
    public int endGame(){
        if(t.isRunning()){
            t.stop();
            int finalScore = score.getTotalScore();
            return finalScore;
        }
        
        return 0;

    }
    
    /**
     * Starts a game at any level for the current user.
     * @param level
     * @param currentUser 
     */
    public void startGame(int level,New_GetLoginInfoClass currentUser){
        this.currentUser=currentUser;
        this.level.startGame(level);
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

    private void checkGameOver() {
        if(level.checkGameOver()){
            if(currentUser.GetUserName().equals(guestUsername)){
            }
            else{
                dataManage.AddNewScore(currentUser.GetUserName(), score.getTotalScore(),fileName);
                dataManage.AddNewMaxLevel(currentUser.GetUserName(), level.getLevel(), fileName);
            }
            
            score.newGame();
            bonusDisplay.newGame();
            level.startGame(1);
        }
    }
    
    private void checkLevelChange() {
        if(level.checkLevelChange()){
            System.out.println("Next level");
            score.newLevel();
            level.changeLevel();
        }
    }
    
}
