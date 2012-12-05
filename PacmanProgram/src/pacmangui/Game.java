/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmangui;

import pacmangameplay.gamelogic.LevelLogic;
import pacmangameplay.mazedisplay.Maze;
import pacmangameplay.charactercontrol.Characters;
import pacmangameplay.framedisplay.TextDisplay;
import pacmangameplay.framedisplay.ScoreDisplay;
import pacmangameplay.framedisplay.BonusCounterDisplay;
import pacmanlogin.DataManagement;
import pacmanlogin.GetLoginInfo;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import pacmangameplay.charactercontrol.Ghost.Name;

/**
 * Game JPanel. Initiates all game components: Maze, Characters, Bonus Symbols, Timers, Displays.
 * @author stavy92
 */
public class Game extends JPanel implements ActionListener, KeyListener {
    Timer t = new Timer(10, this);
    Maze maze = new Maze();
    Characters characters = new Characters(maze);
    GetLoginInfo currentUser;
    DataManagement dataManage;
    String fileName = "UserData.txt";
    String guestUsername = "Guest";
    LevelLogic level = new LevelLogic(characters, maze);
    TextDisplay text = new TextDisplay(level);
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
            characters.draw(g);
            level.drawBonus(g);
            text.drawText(g);
            score.drawScore(g);
            bonusDisplay.drawSymbols(g);
    }

    /**
     * 
     * @param ae 
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        checkLevelChange();
        checkGameOver();
        characters.refresh(ae);
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
    public void startGame(int level,GetLoginInfo currentUser){
        this.currentUser=currentUser;
        this.level.startGame(level);
        t.start();
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        characters.pacman.controlPacman(ke);
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
            score.newLevel();
            level.changeLevel();
        }
    }
}
