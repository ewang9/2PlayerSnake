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
    private final int DOT_SIZE = 10;
    //private final int ALL_DOTS = 900;
    private final int RAND_POS = 29;
    private final int DELAY = 70;
    
    //private final int x[] = new int[ALL_DOTS];
    //private final int y[] = new int[ALL_DOTS];
    
    //private int dots;
    private int apple_x;
    private int apple_y;
    
//    private boolean leftDirection = false;
//    private boolean rightDirection = false;
//    private boolean upDirection = false;
//    private boolean downDirection = false;
    private boolean inGame = true;
    
    private Timer timer;
    private Image ball;
    private Image ball2;
    private Image apple;
    private Image head;
    private Image head2;
    
    Snake[] snakes = new Snake[2];
    
    public Game_Board(Snake player1, Snake player2){
        snakes[0] = player1;
        snakes[1] = player2;
        initGame_Board(player1, player2);
    }
    
    private void initGame_Board(Snake s1, Snake s2){
        
        addKeyListener(new TAdapter(s1, s2));
        setBackground(Color.black);
        setFocusable(true);
        
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        loadImages();
        initGame();
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
    private void initGame(){
        
        //dots = 2;
        
        for (Snake i: snakes) {
            for (int z = 0; z < i.length; z++){
                i.SnakeX[z] = 50 - z *10;
                i.SnakeY[z] = 50;
            }
        }
        locateApple();
        
        timer = new Timer(DELAY, this);
        timer.start();
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        doDrawing(g);
    }
    
    private void doDrawing(Graphics g){
        if (inGame){
            g.drawImage(apple, apple_x, apple_y, 20, 20, this);
            for (Snake i: snakes) {
                for (int z = 0; z<i.length; z++){
                    if (z==0){
                        g.drawImage(head, i.SnakeX[z], i.SnakeY[z],25, 25, this);
                    }else{
                        g.drawImage(ball, i.SnakeX[z], i.SnakeY[z], 15,15,  this);
                    }
                }
            }
            Toolkit.getDefaultToolkit().sync();
        }else{
            gameOver(g);
        }
    }
    
    private void gameOver(Graphics g){
        
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);
        
        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg))/2, B_HEIGHT / 2);
        
        String msg1 = "Player 1 Score =  "+((snakes[0].length-2)*10);
        Font small2 = new Font("Times New Roman", Font.BOLD, 12);
        FontMetrics metr1 = getFontMetrics(small);
        
        g.setColor(Color.red);
        g.setFont(small2);
        g.drawString(msg1, (B_WIDTH - metr1.stringWidth(msg1) + 30)/2, (B_HEIGHT/2) - 65);
       
        
        g.setColor(Color.blue);
        g.setFont(small2);
        g.drawString(msg1, (B_WIDTH - metr1.stringWidth(msg1))/2, (B_HEIGHT/2) - 50);
        
        String msg2 = "Player 2 Score =  "+((snakes[1].length-2)*10);
        
        g.setColor(Color.white);
        g.setFont(small2);
        g.drawString(msg2, (B_WIDTH - metr1.stringWidth(msg2))/2, (B_HEIGHT/2) - 100);
        
    }
    private void checkApple(){
        for (Snake i: snakes) {
        if ((i.SnakeX[0] == apple_x)&&(i.SnakeY[0] == apple_y)){
            
            i.length++;
            locateApple();
        }
        }
    }
    private void move(){
        for (Snake i: snakes) {
            for (int z = i.length; z>0; z--){
                i.SnakeX[z] = i.SnakeX[(z-1)];
                i.SnakeY[z] = i.SnakeY[(z-1)];
            }
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
        }
}
    private void checkCollision(){
        for (Snake i: snakes) {
            for (int z = i.length; z>0; z--){
                if ((z>4)&&(i.SnakeX[0] == i.SnakeX[z])&& (i.SnakeY[0] == i.SnakeY[z])){
                    inGame = false;
                }
            }
            if (i.SnakeY[0] >= B_HEIGHT){
                inGame = false;
            }
            if (i.SnakeY[0] < 0){
                inGame = false;
            }
            if (i.SnakeX[0] >= B_WIDTH){
                inGame = false;
            }
            if (i.SnakeX[0] < 0){
                inGame = false;
            }
            if (!inGame){
                timer.stop();
            }
        }
        
}
    private void locateApple(){
        int r = (int) (Math.random() * RAND_POS);
        apple_x = ((r*DOT_SIZE));
        
        r = (int) (Math.random()*RAND_POS);
        apple_y = ((r*DOT_SIZE));
}
    @Override
    public void actionPerformed(ActionEvent e){
        if (inGame){
            checkApple();
            checkCollision();
            move();
        }
            repaint();
    }
    
private class TAdapter extends KeyAdapter {
    
    Snake snake1;
    Snake snake2;
    
    public TAdapter(Snake s1, Snake s2){
        snake1 = s1;
        snake2 = s2;
    }
    
    @Override
    public void keyPressed(KeyEvent e){
            snake1.keyPressed(e);
            snake2.keyPressed(e);
        }
    }
}
            

