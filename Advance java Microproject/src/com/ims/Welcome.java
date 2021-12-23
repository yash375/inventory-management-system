/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.ims;


import javax.swing.*;
import java.awt.*;
import javax.swing.BorderFactory;
import java.awt.event.*;

class Welcome extends JFrame implements ActionListener
{
	JPanel leftside;
	JLabel welcome;
	JLabel image;
	JLabel desc1,desc2; 
	JLabel fea,fea1,fea2;
	JButton next;
	Welcome()
	{
		setLayout(null);
		leftside=new JPanel();
		welcome=new JLabel("WELCOME");
		image = new JLabel(new ImageIcon(new ImageIcon("D:\\fifth semester microproject\\advance java\\project sample\\image.png").getImage().getScaledInstance(600,500, Image.SCALE_DEFAULT)));

		desc1=new JLabel("Inventory Management System");
		desc2=new JLabel("for College");

		fea=new JLabel("------------------FEATURES--------------------");
		fea1=new JLabel("Oraganise Material");
		fea2=new JLabel("Manage Goods");

		next=new JButton("NEXT");





		welcome.setFont(new Font("sanserif",Font.BOLD,50));
		desc1.setFont(new Font("sanserif",Font.BOLD,30));
		desc2.setFont(new Font("sanserif",Font.BOLD,30));
		fea.setFont(new Font("sanserif",Font.PLAIN,20));
		fea1.setFont(new Font("sanserif",Font.PLAIN,18));
		fea2.setFont(new Font("sanserif",Font.PLAIN,18));
		next.setFont(new Font("sanserif",Font.BOLD,20));



		leftside.setBackground(new Color(31,50,64,255));
		desc1.setForeground(Color.red);
		desc2.setForeground(Color.red);
		next.setBackground(new Color(31,50,64,255));
		next.setForeground(Color.white);

		next.addActionListener(this);



		leftside.setBounds(0,0,330,650);
		welcome.setBounds(445,30,280,40);

		desc1.setBounds(360,150,450,35);
		desc2.setBounds(490,200,160,35);

		fea.setBounds(390,280,600,40);
		fea1.setBounds(490,340,200,30);
		fea2.setBounds(500,390,200,30); 

		next.setBounds(680,530,100,40);
		next.setFocusable(false);



		getRootPane().setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, new Color(255,255,255)));

                
		add(leftside);
		add(welcome);
		leftside.add(image);
		add(desc1);
		add(desc2);
		add(fea);
		add(fea1);
		add(fea2);
		add(next);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
		
	}
	public static void main(String[] args) 
	{
		Welcome w1=new Welcome();
		w1.setVisible(true); 
		w1.setSize(850,650);
		w1.setTitle("Welcome");
	}
		public void actionPerformed(ActionEvent ae){
			new Login();
			this.dispose();
		}
}
