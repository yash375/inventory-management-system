/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.ims;



import javax.swing.*;
import java.awt.*;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BorderFactory;  



class AddProduct {

    JLabel l1,l2 ,l4,l5;
    JTextField tx1,tx3,tx4;
    JComboBox c1;
    JButton cancel,add;
    JFrame frame;
    
    //                        Databse Variables
                           String url = "jdbc:mysql://localhost:3306/imc?zeroDateTimeBehavior=CONVERT_TO_NULL";
                           String uname = "root";
                           String pwd = "";
    AddProduct()
    { 
        frame = new JFrame();
        frame.setLayout(null);

        l1=new JLabel("Category :");
        l2=new JLabel("Name :");
        l4=new JLabel("Price :");
        l5=new JLabel("Description :");
    

        c1= new JComboBox();
                    showCatName();




        l1.setFont(new Font("SanSerif",Font.BOLD,22));
        l2.setFont(new Font("SanSerif",Font.BOLD,22));
        l4.setFont(new Font("SanSerif",Font.BOLD,22));
        l5.setFont(new Font("SanSerif",Font.BOLD,22));
     
        
        
        l1.setForeground(Color.white);
        l2.setForeground(Color.white);
        l4.setForeground(Color.white);
        l5.setForeground(Color.white);
    
            
        


        tx1=new JTextField(20);
        tx3=new JTextField(20);
        tx4=new JTextField(20);



        cancel= new JButton("CLEAR");
        add= new JButton("ADD");
        cancel.setBackground(new Color(241,97,66,255));
        cancel.setFocusable(false);
        add.setBackground(new Color(40,154,103,255));
        add.setFocusable(false);
        cancel.setBounds(20,350,150,40);
        add.setBounds(220,350,150,40);
        
        cancel.addActionListener(new ClearListener());
         add.addActionListener(new AddListener());


        l1.setBounds(40,20,150,50);
        l2.setBounds(50,80,100,50);
        l4.setBounds(50,200,100,50);
        l5.setBounds(40,260,150,50);
   

        c1.setBounds(200,30,150,35);
        tx1.setBounds(200,90,150,35);
        tx3.setBounds(200,210,150,35);
        tx4.setBounds(200,270,150,35);

        c1.setBorder(BorderFactory.createLineBorder(new Color(31,50,64,255), 1));
        tx1.setBorder(BorderFactory.createLineBorder(new Color(31,50,64,255), 1));
        tx3.setBorder(BorderFactory.createLineBorder(new Color(31,50,64,255), 1));
        tx4.setBorder(BorderFactory.createLineBorder(new Color(31,50,64,255), 1));
        
        tx1.setFont(new Font("SansSerif",Font.PLAIN,20));
        tx3.setFont(new Font("SansSerif",Font.PLAIN,20));
        tx4.setFont(new Font("SansSerif",Font.PLAIN,20));


         frame.add(l1); frame.add(tx1);
         frame.add(l2); frame.add(tx3);
         frame.add(l4); frame.add(tx4);
         frame.add(l5);
        frame. add(cancel);
        frame. add(add);
         frame.add(c1);

         frame.setVisible(true);
      frame.setSize(400,500);
       frame.setTitle("Add Product");
       frame.getContentPane().setBackground(new Color(32,50,64,255));
                		 frame.setLocationRelativeTo(null);  

    }


    public static void main(String[] args) 
    {
        AddProduct pd = new AddProduct();
       

    }
    
    public void addProduct(){
        int idProductExist = checkProduct();
        
        if(idProductExist == 0){
         try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection(url,uname, pwd);
                Statement state = conn.createStatement();
                
                state.execute("INSERT INTO `product` (`Id`, `Category`, `ProductName`, `Price`, `Description`) VALUES (NULL, '"+c1.getSelectedItem()+"', '"+tx1.getText()+"', '"+Integer.parseInt(tx3.getText())+"', '"+tx4.getText()+"');");
                state.close();
                conn.close();
                                                                                            JOptionPane.showMessageDialog(frame, "Product Added Succesfully Please Refresh View Category..!!");

            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        else{
                                            JOptionPane.showMessageDialog(frame, "Product Exist at Id : " + Integer.toString(idProductExist), "Alert",JOptionPane.ERROR_MESSAGE);

        }
    }
    
    public void showCatName(){
            ArrayList columnNames = new ArrayList();
        ArrayList data = new ArrayList();

        //  Connect to an MySQL Database, run query, get result set
        String url = "jdbc:mysql://localhost:3306/imc?zeroDateTimeBehavior=CONVERT_TO_NULL";
        String userid = "root";
        String password = "";
        String sql = "SELECT CategoryName FROM category";

        // Java SE 7 has try-with-resources
        // This will ensure that the sql objects are closed when the program 
        // is finished with them
        try (Connection connection = DriverManager.getConnection( url, userid, password );
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery( sql ))
        {
     
      while(rs.next()) {
         c1.addItem(rs.getString("CategoryName"));
      }
        }
        catch (SQLException e)
        {
            System.out.println( e.getMessage() );
        }

    
    }
    
    public int checkProduct(){
        //  Connect to an MySQL Database, run query, get result set
      
        String sql = "SELECT * FROM product WHERE ProductName = '" + tx1.getText() + "'";

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
             String name = tx1.getText();
             String price = tx3.getText();
             String desc = tx4.getText();

            
                                                        if(name.equals("") || price.equals("") || desc.equals("")){
                                                            JOptionPane.showMessageDialog(frame, "One Or More Fields Are missing", "Alert",JOptionPane.ERROR_MESSAGE);
                                                           
                                                        }
                                                        else{
                                                            addProduct();
                                                            frame.dispose();
                                                        }
                                                    
        }
        
    }
    
    class ClearListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
             tx1.setText("");
                        tx3.setText("");
            tx4.setText("");
            

        }
        
    }
}


