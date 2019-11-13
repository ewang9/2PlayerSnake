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
    //The location of each body part of the snake
    int length = 2;
    //Length of the snake
    boolean up = false;
    boolean down = false;
    boolean left = false;
    boolean right = false;
    //Which way the snake is moving
    int maxSize = 20;
    //Maximum size of the snake
    int upKey;
    int downKey;
    int leftKey;
    int rightKey;
    //KeyEvent keycodes for each button in the controls
    public Snake(int up, int left, int down, int right){
        upKey = up;
        downKey = down;
        leftKey = left;
        rightKey = right;
        SnakeX = new int[maxSize];
        SnakeY = new int[maxSize];
    }
    //Constructor initializes snake object with set controls and a max size for the length of the snake
    
    
    public void keyPressed(KeyEvent ev){
        
        int key = ev.getKeyCode();
        //When a key is pressed, this gets the keycode to tell what key was pressed
        
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
        //Set the direction of movement of the snake based on what key was pressed
        }
    }
