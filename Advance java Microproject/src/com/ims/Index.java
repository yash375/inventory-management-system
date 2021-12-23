/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.ims;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*; 
import javax.swing.BorderFactory;
class Index implements ActionListener
{
	JFrame frame;
	JPanel colorpanel,footer,navbar;
	JLabel imagelabel;
	JLabel footername;
	JLabel desc1,desc2;
	JButton product,category,customer,order; 

	Index()
	{
		frame=new JFrame("HOME");
		frame.setLayout(null);
		
		navbar=new JPanel();
		navbar.setLayout(null);
		navbar.setBounds(10,5,1350,50);
		navbar.setBackground(new Color(204, 204, 255));
		navbar.setBorder(BorderFactory.createLineBorder(new Color(0, 128, 128), 1));
		frame.add(navbar);

		product=new JButton("PRODUCT");
		product.setBounds(10,7,130,35);
		product.setBackground(Color.orange);
		product.setForeground(Color.white);
		product.setFont(new Font("SansSerif",Font.BOLD,16));
		product.setBorder(null);
		product.setFocusable(false);
                                                   product.addActionListener(this);
		navbar.add(product);
                
                
                
		category=new JButton("CATEGORY");
		category.setBounds(150,7,130,35);
		category.setBackground(Color.orange);
		category.setForeground(Color.white);
		category.setFont(new Font("SansSerif",Font.BOLD,16));
		category.setBorder(null);
		category.setFocusable(false);
                                                    category.addActionListener(this);                               
		navbar.add(category);
                
                
                
		customer=new JButton("CUSTOMER");
		customer.setBounds(290,7,130,35);
		customer.setBackground(Color.orange);
		customer.setForeground(Color.white);
		customer.setFont(new Font("SansSerif",Font.BOLD,16));
		customer.setBorder(null);
		customer.setFocusable(false);
                                                    customer.addActionListener(this);
		navbar.add(customer);
                
                
                
		order=new JButton("ORDER");
		order.setBounds(430,7,130,35);
		order.setBackground(Color.orange);
		order.setForeground(Color.white);
		order.setFont(new Font("SansSerif",Font.BOLD,16));
		order.setBorder(null);
		order.setFocusable(false);
                                                    order.addActionListener(this);
		navbar.add(order);




		colorpanel=new JPanel();
		colorpanel.setLayout(null);


		desc1=new JLabel("ORDER",JLabel.CENTER);
		desc2=new JLabel("MANAGER",JLabel.CENTER);

		desc1.setFont(new Font("Ink Free",Font.BOLD,80));
		desc2.setFont(new Font("Ink Free",Font.BOLD,80));
		colorpanel.setBackground(new Color(31,50,64,255));
		desc1.setForeground(new Color(225,255,255));
		desc2.setForeground(new Color(255,255,255));
		colorpanel.setBounds(820,60,540,580);
		desc1.setBounds(50,150,500,90);
		desc2.setBounds(50,250,500,90);
		colorpanel.add(desc1);
		colorpanel.add(desc2);
		frame.add(colorpanel);


		imagelabel = new JLabel(new ImageIcon(new ImageIcon("D:\\fifth semester microproject\\advance java\\project sample\\clgimage1.jpg").getImage().getScaledInstance(1600,500, Image.SCALE_DEFAULT)));

		imagelabel.setBounds(10,140,800,1100);
		imagelabel.setVerticalAlignment(JLabel.TOP);
		frame.add(imagelabel);

		footer=new JPanel();
		footer.setLayout(null);
		footername=new JLabel("G o v e r n m e n t  |  P o l y t e c h n i c  |  A w a s a r i (K h u r d)   ||   T a l. A m b e g a o n    D i s. P u n e",JLabel.CENTER);
		footer.setBackground(new Color(31,50,64,255));
		footername.setForeground(new Color(255,255,255));
		footername.setFont(new Font("Eras Light ITC",Font.PLAIN,20));
		footer.setBounds(10,650,1350,80);
		footername.setBounds(190,25,900,20);
		footer.add(footername);
		frame.add(footer);

		frame.setVisible(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setTitle("Inventory Management System");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                                                    		 frame.setLocationRelativeTo(null);  


	}
	public static void main(String[] args)  
	{
	     new Index(); 
	}
        public void actionPerformed(ActionEvent ae)
        {
            
            if(ae.getSource() == customer){
                    new Customer();
             }
        if(ae.getSource() == product){
            new ViewProduct();
             }
if(ae.getSource() == order){
            new ViewOrder();
             }
if(ae.getSource() == category){
            new ViewCategory();
             }
}
}