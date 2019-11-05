/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2playersnake;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class Main extends JFrame {
    
    public Main(){
        initUI();
    }
    private void initUI(){
        add (new Game_Board());
        
        setResizable(false);
        pack();
        
        setTitle("2 Player Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        
        EventQueue.invokeLater(() -> {
            JFrame ex = new Main();
            ex.setVisible(true);
            
        });
    
    }
    }
    

