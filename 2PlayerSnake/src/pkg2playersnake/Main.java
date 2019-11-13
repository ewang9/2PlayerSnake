/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2playersnake;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class Main extends JFrame {

    
    public Main(Snake s1, Snake s2){
        initUI(s1, s2);
    }
    //Constructor takes the two snakes that will be implemented into the game as parameters
    private void initUI(Snake s1, Snake s2){
        add (new Game_Board(s1, s2));
        //Create a game board with the snakes given
        setResizable(false);
        pack();
        
        setTitle("2 Player Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Window settings for optimal gameplay
    }
    public static void main(String[] args) {
//We planned to have a welcome screen to take player names, but it wasn't needed, 
//and more effort to implement than was worth it

//        String player1;
//        String player2;
//        EventQueue.invokeLater(() -> {
//            JFrame ex = new WelcomeScreen();
//            
//            ex.setVisible(true);
//        });
        
        Snake snake1 = new Snake(87,65,83,68);
        Snake snake2 = new Snake(38,37,40,39);
        //Creates snake objects with controls WASD, arrow keys
        EventQueue.invokeLater(() -> {
            JFrame main = new Main(snake1, snake2);
            main.setVisible(true);
        });
    //Opens the game window and runs the game using the snake objects from above
    }
    }
    

