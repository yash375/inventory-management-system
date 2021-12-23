/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ims;

/**
 *
 * @author swarup
 */
import javax.swing.*;
import javax.swing.BorderFactory;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

class AddCategory {



	JLabel l1, titleLabel;
	JTextField tx1;
	JButton b1,b2;
                        JFrame frame;
                        
                        //                        Databse Variables
                           String url = "jdbc:mysql://localhost:3306/imc?zeroDateTimeBehavior=CONVERT_TO_NULL";
                           String uname = "root";
                           String pwd = "";


	AddCategory()
	{
                                                    frame = new JFrame();
		frame.setLayout(null);

		l1 = new JLabel("Category Name");
		l1.setFont(new Font("SanSerif",Font.PLAIN,22));
		l1.setForeground(Color.white);
		l1.setBounds(70,130,200,50);
                
                                                     titleLabel = new JLabel("Add a Category");
		titleLabel.setFont(new Font("Segoe Print",Font.PLAIN,36));
		titleLabel.setForeground(Color.white);
		titleLabel.setBounds(110,40,400,50);


		tx1 =new JTextField(20);
		tx1.setFont(new Font("SansSerif",Font.PLAIN,20));
		tx1.setBounds(280,130,150,35);
		tx1.setBorder(BorderFactory.createLineBorder(new Color(31,50,64,255), 1));

		b1= new JButton("CLEAR");
		b1.setBackground(new Color(241,97,66,255));
		b2= new JButton("SUBMIT");
		b2.setBackground(new Color(40,154,103,255));
		b1.setBounds(90,250,150,40);
		b2.setBounds(270,250,150,40);
		b1.addActionListener(new ClearListener());
                		b2.addActionListener(new AddListener());


		frame.add(l1);frame.add(tx1);frame.add(b1);frame.add(b2);
                frame.add(titleLabel);
                
                frame.setTitle("catergory Name");
		frame.setVisible(true);
		frame.setSize(600,400);
		frame.getContentPane().setBackground(new Color(32,50,64,255));
                                		 frame.setLocationRelativeTo(null);  


	}


            

	public static void main(String[] args) {
		new AddCategory();
		
	}
        
        class ClearListener implements ActionListener{
            public void actionPerformed(ActionEvent ae){
		String cat = tx1.getText();
		
		if (ae.getSource()==b1) {
			tx1.setText("");
				
		}
                                                     
                                                   

	}
        }
        
        public void addCategory(){
            
            int idCateExist = checkCategory();
            
            if(idCateExist == 0){
               try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection(url,uname, pwd);
                Statement state = conn.createStatement();
                
                state.execute("INSERT INTO `category` (`Id`, `CategoryName`) VALUES (NULL, '"+tx1.getText()+"');");
                state.close();
                conn.close();
                                                                            JOptionPane.showMessageDialog(frame, "Category Added Succesfully Please Refresh View Category..!!");

            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
            else{
                                JOptionPane.showMessageDialog(frame, "Category Exist at Id : " + Integer.toString(idCateExist), "Alert",JOptionPane.ERROR_MESSAGE);

            }
        }
        
        
        public int checkCategory(){
        //  Connect to an MySQL Database, run query, get result set
      
        String sql = "SELECT * FROM category WHERE CategoryName = '" + tx1.getText() + "'";

        // Java SE 7 has try-with-resources
        // This will ensure that the sql objects are closed when the program 
        // is finished with them
        try (Connection connection = DriverManager.getConnection( url, uname, pwd );
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery( sql ))
        {
            
            if(rs.next()){
                System.out.println(rs.getInt("Id"));
                return rs.getInt("Id");
                
            }
        }
        catch (SQLException e)
        {
            System.out.println( e.getMessage() );
        }
        
         return 0;
        }
        
        class AddListener implements ActionListener{
            
           

        @Override
        public void actionPerformed(ActionEvent e) {
            String cat = tx1.getText();
             if(e.getSource() == b2){
                                                        if(cat.equals("")){
                                                            JOptionPane.showMessageDialog(frame, "Category is missing", "Alert",JOptionPane.ERROR_MESSAGE);
                                                           
                                                        }
                                                        else{
                                                            addCategory();
                                                            frame.dispose();
                                                        }
                                                    }
        }
        }
}
