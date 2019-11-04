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
    
    private final int B_WIDTH = 300;
    private final int B_HEIGHT = 300;
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
        
        ImageIcon iid = new ImageIcon("src/resources/dot.png");
        ball = iid.getImage();
        
        ImageIcon iia = new ImageIcon("src/resources/apple.png");
        apple = iia.getImage();
        
        ImageIcon iih = new ImageIcon("src/resources/head.png");
        head = iih.getImage();            
}
    private void initGame(){
        
        dots = 3;
        
        for (int z = 0; z < dots; z++){
            x[z] = 50 - z *10;
            y[z] = 50;
        }
        
        locateApple();
        
        timer = new Timer(DELAY, this);
        timer.start();
    }
    
    @Override
    public void paintCompononent(Graphics g){
        super.paintComponent(g);
        
        doDrawing(g);
    }
    
    private void doDrawing(Graphics g){
        if (inGame){
            g.drawImage(apple, apple_x, apple_y, this);
            
            for (int z = 0; z<dots; z++){
                if (z==0){
                    g.drawImage(head, x[z], y[z], this);
                }else{
                    g.drawImage(ball, x[z], y[z], this);
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
    
    
}

    
    

