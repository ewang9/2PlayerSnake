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
    
    private final int B_WIDTH = 600;
    private final int B_HEIGHT = 600;
    private final int DOT_SIZE = 10;
    private final int ALL_DOTS = 900;
    private final int RAND_POS = 29;
    private final int DELAY = 140;
    
    private final int x[] = new int[ALL_DOTS];
    private final int y[] = new int[ALL_DOTS];
    
    private int dots;
    private int apple_x;
    private int apple_y;
    
    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame = true;
    
    private Timer timer;
    private Image ball;
    private Image apple;
    private Image head;
    
    public Game_Board(){
        initGame_Board();
    }
    
    private void initGame_Board(){
        
        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);
        
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        loadImages();
        initGame();
    }
    
    private void loadImages(){
        
        ImageIcon iid = new ImageIcon("src/images/ball.png");
        ball = iid.getImage();
        
        ImageIcon iia = new ImageIcon("src/images/apple.png");
        apple = iia.getImage();
        
        ImageIcon iih = new ImageIcon("src/images/head.png");
        head = iih.getImage();            
}
    private void initGame(){
        
        dots = 2;
        
        for (int z = 0; z < dots; z++){
            x[z] = 50 - z *10;
            y[z] = 50;
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
            g.drawImage(apple, apple_x, apple_y, 50, 50, this);
            
            for (int z = 0; z<dots; z++){
                if (z==0){
                    g.drawImage(head, x[z], y[z],25, 25, this);
                }else{
                    g.drawImage(ball, x[z], y[z], 25,25,  this);
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
    }
    private void checkApple(){
        if ((x[0] == apple_x)&&(y[0] == apple_y)){
            
            dots++;
            locateApple();
        }
    }
    private void move(){
        for (int z = dots; z>0; z--){
            x[z] = x[(z-1)];
            y[z] = y[(z-1)];
        }
        if (leftDirection){
            x[0] -= DOT_SIZE;
        }
        if (rightDirection){
            x[0] += DOT_SIZE;
        }
        if (upDirection){
            x[0] -= DOT_SIZE;
        }
        if (downDirection){
            x[0] += DOT_SIZE;
        }
}
    private void checkCollision(){
        for (int z = dots; z>0; z--){
            if ((z>4)&&(x[0] == x[z])&& (y[0] == y[z])){
                inGame = false;
            }
        }
        if (y[0] >= B_HEIGHT){
            inGame = false;
        }
        if (y[0] < 0){
            inGame = false;
        }
        if (x[0] >= B_WIDTH){
            inGame = false;
        }
        if (x[0] < 0){
            inGame = false;
        }
        if (!inGame){
            timer.stop();
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
    @Override
    public void keyPressed(KeyEvent e){
        
        int key = e.getKeyCode();
        
        if ((key == KeyEvent.VK_LEFT) && (!rightDirection)){
            leftDirection = true;
            rightDirection = false;
            upDirection = false;
            downDirection = false;
        }
        if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)){
            rightDirection = true;
            leftDirection = false;
            upDirection = false;
            downDirection = false;
        }
        if ((key == KeyEvent.VK_UP) && (!downDirection)){
            upDirection = true;
            downDirection = false;
            rightDirection = false;
            leftDirection = false;
        }
        if ((key == KeyEvent.VK_DOWN) && (!upDirection)){
            downDirection = true;
            upDirection = false;
            rightDirection = false;
            leftDirection = false;
            }
        }
    }
}
            

