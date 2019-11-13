/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2playersnake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game_Board extends JPanel implements ActionListener{
    
    private final int B_WIDTH = 450;
    private final int B_HEIGHT = 450;
    //Dimensions of the game window
    private final int DOT_SIZE = 10;
    //Size of each part of the snake
    private final int RAND_POS = 29;
    //constant for randomly placing the apple
    private final int DELAY = 70;
    //time for one tick
    
    private int apple_x;
    private int apple_y;
    //location of the apple
    private boolean inGame = true;
    //If you're in game. Pretty self-explanatory
    private Timer timer;
    //A timer - still self-explanatory
    private Image ball;
    private Image ball2;
    private Image apple;
    private Image head;
    private Image head2;
    //Image variables
    
    Snake[] snakes = new Snake[2];
    //Array stores the snakes
    String winner = "";
    //Which snake is the winner
    
    public Game_Board(Snake player1, Snake player2){
        snakes[0] = player1;
        snakes[1] = player2;
        initGame_Board(player1, player2);
    }
    //Initializes the game board with snakes passed down from the main class,
    //and with preferred window settings
    
    private void initGame_Board(Snake s1, Snake s2){
        
        addKeyListener(new TAdapter(s1, s2));
        //Tracks when a key is pressed
        setBackground(Color.black);
        setFocusable(true);
        
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        //Preferred window settings
        loadImages();
        //Load the images from the images file
        initGame();
        //Start the actual game
        
    }
    
    private void loadImages(){

        ImageIcon iid = new ImageIcon("src/images/body.png");
        ball = iid.getImage();
        
        ImageIcon iia = new ImageIcon("src/images/apple.png");
        apple = iia.getImage();
        
        ImageIcon iih = new ImageIcon("src/images/head.png");
        head = iih.getImage();     
        
        ImageIcon iid2 = new ImageIcon("src/images/body2.png");
        ball2 = iid2.getImage();
        
        ImageIcon iih2 = new ImageIcon("src/images/head2.png");
        head2 = iih2.getImage();
}
    //Get all the images from the images file
    private void initGame(){
        
            for (int z = 0; z < snakes[0].length; z++){
                snakes[0].SnakeX[z] = 50 - z *10;
                snakes[0].SnakeY[z] = 50;
            }
            for (int z = 0; z < snakes[1].length; z++){
                snakes[1].SnakeX[z] = 400 - z *10;
                snakes[1].SnakeY[z] = 50;
            }
            //Set the starting points for the snakes
        locateApple();
        //Place the apple somewhere
        inGame = true;
        
        timer = new Timer(DELAY, this);
        timer.start();
        //Start the game and timer
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        doDrawing(g);
        //Draw the game screen
    }
    
    private void doDrawing(Graphics g){
        if (inGame){
            g.drawImage(apple, apple_x, apple_y, 20, 20, this);
            //Draw the apple
                for (int z = 0; z<snakes[0].length; z++){
                    if (z==0){
                        g.drawImage(head, snakes[0].SnakeX[z], snakes[0].SnakeY[z],25, 25, this);
                    }else{
                        g.drawImage(ball, snakes[0].SnakeX[z], snakes[0].SnakeY[z], 15,15,  this);
                    }
                }
                //Draw the first snake at its coordinates with its corresponding images
                for (int z = 0; z<snakes[1].length; z++){
                    if (z==0){
                        g.drawImage(head2, snakes[1].SnakeX[z], snakes[1].SnakeY[z],25, 25, this);
                    }else{
                        g.drawImage(ball2, snakes[1].SnakeX[z], snakes[1].SnakeY[z], 15,15,  this);
                    }
                }
                //Draw second snake in the same way
        String msg1 = "PLAYER 1: "+((snakes[0].length-2)*10);
        Font medium = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr1 = getFontMetrics(medium);
        
        g.setColor(Color.red);
        g.setFont(medium);
        g.drawString(msg1, (B_WIDTH - metr1.stringWidth(msg1) - 340)/2, (B_HEIGHT/2) - 200);
        //Keep player 1 score in the top left corner all the time
        
        String msg2 = "PLAYER 2: "+((snakes[1].length-2)*10);
        FontMetrics metr2 = getFontMetrics(medium);
        
        g.setColor(Color.blue);
        g.setFont(medium);
        g.drawString(msg2, (B_WIDTH - metr1.stringWidth(msg2) + 338)/2, (B_HEIGHT/2) - 200);
        //Same for player 2 score in top right corner
            Toolkit.getDefaultToolkit().sync();
            //Synchronizes graphics state - makes sure most recent screen of graphics is displayed
        }else{
            gameOver(g);
            //If not in game, go to game over screen
        }
    }
    
    private void gameOver(Graphics g){
        
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);
        
        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg))/2, B_HEIGHT / 2);
        
        //Says game over
        
        String msg1 = "Player 1 Score =  "+((snakes[0].length-2)*10);
        Font small1 = new Font("Times New Roman", Font.BOLD, 12);
        FontMetrics metr1 = getFontMetrics(small);
        
        g.setColor(Color.red);
        g.setFont(small1);
        g.drawString(msg1, (B_WIDTH - metr1.stringWidth(msg1) + 30)/2, (B_HEIGHT/2) - 65);
        
        String msg2 = "Player 2 Score =  "+((snakes[1].length-2)*10);
        FontMetrics metr2 = getFontMetrics(small);
        
        g.setColor(Color.blue);
        g.setFont(small1);
        g.drawString(msg2, (B_WIDTH - metr2.stringWidth(msg2) + 30)/2, (B_HEIGHT/2) - 50);
        
        //Displays both players's end scores
        
        String msg3 = "Press ENTER to play again.";
        Font large = new Font("Times New Roman", Font.BOLD, 18);
        FontMetrics metr3 = getFontMetrics(large);
        
        g.setColor(Color.yellow);
        g.setFont(large);
        g.drawString(msg3, (B_WIDTH - metr3.stringWidth(msg3) + 16)/2, (B_HEIGHT/2) + 75);

        //Tells player to press enter to play again
        
        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(winner + " wins!", (B_WIDTH - metr.stringWidth(msg) - 10)/2, (B_HEIGHT / 2) + 40);
        
        //Displays which player wins
        
        for (Snake i : snakes) {
        		i.up = false;
        		i.down = false;
        		i.left = false;
        		i.right = false;
        		i.length = 2;
        	}
            timer.stop();
        //Reset variables for next game, if players play again
        
    }
    private void checkApple(){
        for (Snake i: snakes) {
        if ((i.SnakeX[0] == apple_x)&&(i.SnakeY[0] == apple_y)){
            
            i.length++;
            locateApple();
        }
        }
    }
    //If a snake touches an apple, their length increases by 1 and a new apple is spawned elsewhere
    private void move(){
        for (Snake i: snakes) {
            for (int z = i.length; z>0; z--){
                i.SnakeX[z] = i.SnakeX[(z-1)];
                i.SnakeY[z] = i.SnakeY[(z-1)];
            }
            //Move the coordinates of the snake's tail along its movement path
            if (i.left){
                i.SnakeX[0] -= DOT_SIZE;
            }
            if (i.right){
                i.SnakeX[0] += DOT_SIZE;
            }
            if (i.up){
               i.SnakeY[0] -= DOT_SIZE;
            }
            if (i.down){
                i.SnakeY[0] += DOT_SIZE;
            }
            //Moves the coordinates of the head of the snake based on what control key was pressed
        }
}
    private void checkCollision(){

            if (snakes[0].SnakeY[0] >= B_HEIGHT){
                inGame = false;
                winner = "Player 2";
            }
            if (snakes[0].SnakeY[0] < 0){
                inGame = false;
                winner = "Player 2";
            }
            if (snakes[0].SnakeX[0] >= B_WIDTH){
                inGame = false;
                winner = "Player 2";
            }
            if (snakes[0].SnakeX[0] < 0){
                inGame = false;
                winner = "Player 2";
            }
            //if player 1 goes off screen, player 2 wins

            if (snakes[1].SnakeY[0] >= B_HEIGHT){
                inGame = false;
                winner = "Player 1";
            }
            if (snakes[1].SnakeY[0] < 0){
                inGame = false;
                winner = "Player 1";
            }
            if (snakes[1].SnakeX[0] >= B_WIDTH){
                inGame = false;
                winner = "Player 1";
            }
            if (snakes[1].SnakeX[0] < 0){
                inGame = false;
                winner = "Player 1";
            }
            //If player 2 goes off screen, player 1 wins
            
        for (int i = 0;i<snakes[1].length;i++){
            if (snakes[0].SnakeX[0]==snakes[1].SnakeX[i] && snakes[0].SnakeY[0]==snakes[1].SnakeY[i]){
                winner = "Player 2";
                inGame = false;
                break;
            }
        }
        //If player 1 head collides with player 2, player 2 wins
        for (int i = 0;i<snakes[0].length;i++){
            if (snakes[1].SnakeX[0]==snakes[0].SnakeX[i] && snakes[1].SnakeY[0]==snakes[0].SnakeY[i]){
                winner = "Player 1";
                inGame = false;
                break;
            }
        }
        //If player 2 head collides with player 1, player 1 wins
        for (int i = 0; i<snakes.length;i++) {
            if(snakes[i].length >= 12){
                winner = "Player " + (i+1);
                inGame = false;
                break;
            }
        }
        //If one snake reaches a certain length, they win
}
    private void locateApple(){
        int r = (int) (Math.random() * RAND_POS);
        apple_x = ((r*DOT_SIZE));
        
        r = (int) (Math.random()*RAND_POS);
        apple_y = ((r*DOT_SIZE));
}
    //Randomly set the location of the apple
    @Override
    public void actionPerformed(ActionEvent e){
        //Every tick of the timer: 
        //Also if the game is still running: 
        if (inGame){
            checkApple();
            //Check if a snake eats an apple
            checkCollision();
            //check if the snakes collide
            move();
            //Move the snakes
        }
            repaint();
            //Redraw the screen with current positions
    }
    

private class TAdapter extends KeyAdapter {
    
    Snake snake1;
    Snake snake2;
    
    public TAdapter(Snake s1, Snake s2){
        snake1 = s1;
        snake2 = s2;
    }
    //Constructor for the key tracking object takes the two snakes
    //This is so we can use the same method for 2 different snakes with 2 different sets of controls
    @Override
    public void keyPressed(KeyEvent e){
        //When a key is pressed:
        if (inGame) {
            snake1.keyPressed(e);
            snake2.keyPressed(e);
        }
        //If the game is running, the snake switches direction based on its controls
            if (!inGame) {
            	int key = e.getKeyCode();
            	if (key == KeyEvent.VK_ENTER) {
            		initGame();
            	}
            }
            //Or if game isn't running, track when enter is pressed, and restart the game when it is
        }
    }
}
           