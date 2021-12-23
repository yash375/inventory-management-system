/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.ims;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Home extends JFrame implements ActionListener{

    JLabel label;
    JButton back;


	Home(String str){
        label = new JLabel(str);
        back = new JButton("Back");

        back.addActionListener(this);


        add(label, BorderLayout.NORTH);
        add(back, BorderLayout.SOUTH);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        setSize(500, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle("Home");
	}

    public void actionPerformed(ActionEvent ae){
            new Login();
            this.dispose();
    }

}
