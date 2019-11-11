/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2playersnake;

/**
 *
 * @author S331460873
 */
import java.awt.event.KeyEvent;

public class Snake{
    int[] SnakeX;
    int[] SnakeY;
    int length = 2;
    boolean up = false;
    boolean down = false;
    boolean left = false;
    boolean right = false;
    int points; 
    int maxSize = 900;
    int upKey;
    int downKey;
    int leftKey;
    int rightKey;
    String name;
    public Snake(int up, int left, int down, int right){
        upKey = up;
        downKey = down;
        leftKey = left;
        rightKey = right;
        SnakeX = new int[maxSize];
        SnakeY = new int[maxSize];
    }
    
    
    public void keyPressed(KeyEvent ev){
        
        int key = ev.getKeyCode();
        
        if (key == leftKey){
            left = true;
            right = false;
            up = false;
            down = false;
        }
        if (key == rightKey){
            right = true;
            left = false;
            up = false;
            down = false;
        }
        if (key == upKey){
            up = true;
            down = false;
            right = false;
            left = false;
        }
        if (key == downKey){
            down = true;
            up = false;
            right = false;
            left = false;
            }
        }
    }