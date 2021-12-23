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

class AddOrder{

                           JFrame frame;
	JLabel j1,j2,j4, titleLabel;
	JTextField tx1,tx2,tx4;
	JButton b1,b2;
                          int price;
                           //                        Databse Variables
                           String url = "jdbc:mysql://localhost:3306/imc?zeroDateTimeBehavior=CONVERT_TO_NULL";
                           String uname = "root";
                           String pwd = "";
                          


	AddOrder()
	{
                                                    frame = new JFrame();
		frame.setLayout(null);
		j1 = new JLabel("Seller Mail : ");
		j2 = new JLabel("Name : ");
		j4 = new JLabel("Quantity : ");
                                                    titleLabel = new JLabel("Add an Order..!!");

		j1.setFont(new Font("SanSerif",Font.PLAIN,22));
		j2.setFont(new Font("SanSerif",Font.PLAIN,22));
		j4.setFont(new Font("SanSerif",Font.PLAIN,22));
                                                    titleLabel.setFont(new Font("Segoe Print",Font.PLAIN,36));


		j1.setForeground(Color.white);
		j2.setForeground(Color.white);
		j4.setForeground(Color.white);
                		titleLabel.setForeground(Color.white);


		j1.setBounds(40,130,150,50);
		j2.setBounds(40,70,100,50);
		j4.setBounds(40,190,150,50);
                		titleLabel.setBounds(40,10,300,50);

		tx1=new JTextField(20);
		tx2=new JTextField(20);
		tx4=new JTextField(20);

		tx1.setFont(new Font("SansSerif",Font.PLAIN,20));
		tx2.setFont(new Font("SansSerif",Font.PLAIN,20));
		tx4.setFont(new Font("SansSerif",Font.PLAIN,20));


		tx1.setBounds(200,140,150,35);
		tx2.setBounds(200,80,150,35);
		tx4.setBounds(200,200,150,35);


		b1= new JButton("CLEAR");
		b2= new JButton("SUBMIT");
		b1.setBackground(new Color(241,97,66,255));
		b2.setBackground(new Color(40,154,103,255));
		b1.setBounds(20,350,150,40);
		b2.setBounds(220,350,150,40);
		b1.addActionListener(new ClearListener());
                		b2.addActionListener(new AddListener());






		tx1.setBorder(BorderFactory.createLineBorder(new Color(31,50,64,255), 1));
		tx2.setBorder(BorderFactory.createLineBorder(new Color(31,50,64,255), 1));
		tx4.setBorder(BorderFactory.createLineBorder(new Color(31,50,64,255), 1));




                                                    frame.add(j1);
		frame.add(j2);
                                                    frame.add(j4);
                                                    frame.add(tx1);
		frame.add(tx2);
                                                    frame.add(tx4);
		frame.add(b1);
                                                    frame. add(b2);
                                                    frame. add(titleLabel);
                                                   
                                                   frame.setTitle("Add an Order");
		frame.setVisible(true);
		frame.setSize(400,500);
		frame.getContentPane().setBackground(new Color(32,50,64,255));

                		 frame.setLocationRelativeTo(null);  


	}




	public static void main(String[] args) {
		AddOrder ad = new AddOrder();
		
	}
        
        public void addOrder(){
            
            price = getPrice();
            
            if(price!=0){

                  try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                      try (Connection conn = DriverManager.getConnection(url,uname, pwd)) {
                          Statement state = conn.createStatement();
                          
                          // Get All Input Value ;;;
                          
                          int qty = Integer.parseInt(tx4.getText());
                          
                          
                          
                          state.execute("INSERT INTO `ordertable` (`ID`, `ProductName`, `Quantity`, `Price`, `Rate`) VALUES (NULL, '"+tx2.getText()+"', '"+qty+"', '"+price+"', '"+qty*price+"');");
                          state.close();
                          JOptionPane.showMessageDialog(frame, "Order Added Succesfully Please Refresh View Order..!!");

                      }
            }
            catch(Exception e){
                e.printStackTrace();
            }
            }
            else{
                JOptionPane.showMessageDialog(frame, "No such Product", "Alert",JOptionPane.ERROR_MESSAGE);
            }
            
        }
        
        public int getPrice(){
            //            System.out.println("Thought you clicked me!");

 ArrayList columnNames = new ArrayList();
        ArrayList data = new ArrayList();

        //  Connect to an MySQL Database, run query, get result set
      
        String sql = "SELECT Price FROM product WHERE ProductName = '" + tx2.getText() + "'";

        // Java SE 7 has try-with-resources
        // This will ensure that the sql objects are closed when the program 
        // is finished with them
        try (Connection connection = DriverManager.getConnection( url, uname, pwd );
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery( sql ))
        {
            
            if(rs.next()){
                
               return rs.getInt("Price");
                
            }
        }
        catch (SQLException e)
        {
            System.out.println( e.getMessage() );
        }
        
         return 0;

        }
        
        class ClearListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
          

		
			tx1.setText("");
			tx2.setText("");
			tx4.setText("");


		
        }
            
        }
        
        public void callMail()  throws Exception{
            try{
            String name = tx2.getText();
             String qty = tx4.getText();
             String mail = tx1.getText();
            Mailer m = new Mailer();
               m.senMail(mail, name, qty);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        
        
        class AddListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e){
             String name = tx2.getText();
             String qty = tx4.getText();
             String mail = tx1.getText();
             if(name.equals("")  || qty.equals("") || mail.equals("")){
                    JOptionPane.showMessageDialog(frame, "One or more fields are empty", "Alert",JOptionPane.ERROR_MESSAGE);
                   System.out.print("I am here");
            }
            else{
               addOrder();
               try{
              callMail();
               }
               catch(Exception ae){
                   ae.printStackTrace();
               }
               frame.dispose();
               
            }
        }
            
        }
}