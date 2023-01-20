/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.awt.Color;
import java.awt.Dimension;


/**
 *
 * @author Seba
 */
abstract class JframeClass extends javax.swing.JFrame{
    
    public void config(){
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        this.getContentPane().setBackground(Color.getHSBColor(0,0,0.95f));
        //this.setMinimumSize(new Dimension(1280,720));
        //this.setMaximumSize(new Dimension(1280,720));
        //this.setResizable(true);
        //this.setSize(1280, 720);
        
      //  this.getContentPane().setBackground(Color.white);
       // this.setSize(1280, 1024);
    }
    
    public void Mostrar(javax.swing.JFrame f){ 
        if (f.isVisible()){
            f.toFront();
            f.repaint();
        }else{
            f.setVisible(true);
            f.toFront();
        }
        //f.setSize(1280, 720);
        f.setLocationRelativeTo(null);
    }
}
