/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.ims;

import javax.swing.*;
import java.awt.*;
import javax.swing.BorderFactory;
import java.awt.BorderLayout;
import	java.awt.event.*;

class Login extends JFrame implements ActionListener
{
	JPanel headpanel,footerpanel;
	JLabel heading,heading1;
	JLabel subtitle;
	JLabel user,pass;
	JLabel footername;
	JTextField userinput;
	JPasswordField passinput;
	JButton login;
	Login()                                                                                                               
	{
		setLayout(null);
		headpanel=new JPanel();
		footerpanel=new JPanel();




		heading=new JLabel("COLLEGE INVENTORY",JLabel.CENTER);
		heading1=new JLabel("MANAGEMENT SYSTEM",JLabel.CENTER);
		subtitle=new JLabel("LOGIN",JLabel.CENTER);
		user=new JLabel("USERNAME");
		pass=new JLabel("PASSWORD");
		userinput=new JTextField("");
		passinput=new JPasswordField("");
		footername=new JLabel("GOVERNMENT POLYTECHNIC AWASARI(KH)",JLabel.CENTER);
		login=new JButton("LOG IN");




		headpanel.setBackground(new Color(31,50,64,255));
		heading.setForeground(Color.white);
		heading1.setForeground(Color.white);
		subtitle.setForeground(Color.red);
		footerpanel.setBackground(new Color(31,50,64,255));
		login.setBackground(new Color(31,50,64,255));
		login.setForeground(Color.white);

		login.addActionListener(this);




		heading.setFont(new Font("Monospace",Font.BOLD,25));
		heading1.setFont(new Font("Monospace",Font.BOLD,25));
		subtitle.setFont(new Font("SansSerif",Font.BOLD,35));
		user.setFont(new Font("SansSerif",Font.BOLD,23));
		pass.setFont(new Font("SansSerif",Font.BOLD,23));
		userinput.setFont(new Font("SansSerif",Font.PLAIN,18));
		passinput.setFont(new Font("SansSerif",Font.PLAIN,14));
		footername.setFont(new Font("SansSerif",Font.PLAIN,15));
		login.setFont(new Font("SansSerif",Font.BOLD,18));





		headpanel.setBounds(0,0,440,80);
		heading.setBounds(5,100,500,30);
		subtitle.setBounds(145,100,120,40);
		user.setBounds(30,200,140,40);
		pass.setBounds(30,280,140,40);
		userinput.setBounds(220,200,180,40);
		passinput.setBounds(220,280,180,40);
		footerpanel.setBounds(0,470,440,30);
		footername.setBounds(30,550,300,15);
		login.setBounds(150,370,120,45);






		getRootPane().setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, new Color(31,50,64,255)));
		login.setFocusable(false);






		headpanel.add(heading);
		headpanel.add(heading1);
		footerpanel.add(footername);
		add(headpanel);
		add(subtitle);
		add(user);
		add(pass);
		add(userinput);
		add(passinput);
		add(footerpanel);
		add(login);
		setVisible(true);
		setSize(440,540);
		setTitle("LOGIN");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);   
	}

	public void actionPerformed(ActionEvent ae){
		if(ae.getSource() == login){
			String pwd = passinput.getText();
			String uname = userinput.getText();

			if(uname.equals("GPA") && pwd.equals("GovtPolyAwasari")){
				// new Home("User Logined Successfully..!!");
				new Index();
				this.dispose();
			}
			else if(!uname.equals("GPA")){
				JOptionPane.showMessageDialog(this, "Incorrect User Name..!!");
			}
			else if(!pwd.equals("123")){
				JOptionPane.showMessageDialog(this, "Incorrect Password..!!");
			}
		}
	}
	
}
