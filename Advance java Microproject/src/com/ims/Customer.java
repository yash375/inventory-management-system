/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.ims;

//import com.mysql.cj.xdevapi.Statement;
//import com.sun.jdi.connect.spi.Connection;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import javax.swing.BorderFactory;         
import java.sql.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Vector;
public class Customer
{
	JFrame frame;
	JPanel bottom,left,center;
	JLabel company,username,tel,email;
	JTextField companyfield,userfield,telfield,emailfield,searchfield;
	JButton clear,insert,edit,remove,search;
	JTable table;
	JScrollPane scrollpane;
        
        
//                        Databse Variables
                           String url = "jdbc:mysql://localhost:3306/imc?zeroDateTimeBehavior=CONVERT_TO_NULL";
                           String uname = "root";
                           String pwd = "";

	Customer()
	{
		frame=new JFrame("Customer");
		bottom=new JPanel();
		left=new JPanel();
		center=new JPanel();

		frame.setLayout(new BorderLayout(0,0));

		bottom.setPreferredSize(new Dimension(80,80));
		left.setPreferredSize(new Dimension(300,200));

		bottom.setBackground(new Color(31,50,64,255));
		left.setBackground(new Color(31,50,64,255));
		center.setBackground(new Color(255,255,255));


		frame.add(bottom,BorderLayout.SOUTH);
		frame.add(left,BorderLayout.WEST);
		frame.add(center,BorderLayout.CENTER);


		left.setLayout(null);

		company=new JLabel("COMPANY");
		company.setForeground(Color.white);
		company.setFont(new Font("SansSerif",Font.BOLD,19));
		company.setBounds(5,30,120,30);
		left.add(company);
		companyfield=new JTextField();
		companyfield.setBounds(120,30,170,35);
		companyfield.setFont(new Font("SansSerif",Font.PLAIN,20));
		companyfield.setBorder(BorderFactory.createLineBorder(new Color(31,50,64,255), 1));
		left.add(companyfield);


		username=new JLabel("USERNAME");
		username.setForeground(Color.white);
		username.setFont(new Font("SansSerif",Font.BOLD,19));
		username.setBounds(2,90,120,30);
		left.add(username);
		userfield=new JTextField();
		userfield.setBounds(120,90,170,35);
		userfield.setFont(new Font("SansSerif",Font.PLAIN,20));
		userfield.setBorder(BorderFactory.createLineBorder(new Color(31,50,64,255), 1));
		left.add(userfield);


		tel=new JLabel("TEL.");
		tel.setForeground(Color.white);
		tel.setFont(new Font("SansSerif",Font.BOLD,19));
		tel.setBounds(40,150,120,30);
		left.add(tel);
		telfield=new JTextField();
		telfield.setBounds(120,150,170,35);
		telfield.setFont(new Font("SansSerif",Font.PLAIN,20));
		telfield.setBorder(BorderFactory.createLineBorder(new Color(31,50,64,255), 1));
		left.add(telfield);


		email=new JLabel("EMAIL");
		email.setForeground(Color.white);
		email.setFont(new Font("SansSerif",Font.BOLD,19));
		email.setBounds(30,220,120,30);
		left.add(email);
		emailfield=new JTextField();
		emailfield.setBounds(120,220,170,35);
		emailfield.setFont(new Font("SansSerif",Font.PLAIN,20));
		emailfield.setBorder(BorderFactory.createLineBorder(new Color(31,50,64,255), 1));
		left.add(emailfield);

                
                
                insert=new JButton("INSERT");
		insert.setBounds(28,300,250,40);
		insert.setFont(new Font("SansSerif",Font.PLAIN,18));
		insert.setBackground(new Color(40,154,103,255));
		insert.setForeground(Color.white);
		insert.setFocusable(false);
                                                    insert.addActionListener(new AddListener());
		left.add(insert);
                
                
		clear=new JButton("CLEAR");
		clear.setBounds(28,380,250,40);
		clear.setFont(new Font("SansSerif",Font.PLAIN,18));
		clear.setBackground(new Color(241,97,66,255));
		clear.setForeground(Color.white);
		clear.setFocusable(false);
                                                    clear.addActionListener(new ClearListener());
		left.add(clear);


		bottom.setLayout(null);
                
                searchfield=new JTextField();
                searchfield.setBounds(300,24,380,40);
		searchfield.setFont(new Font("SansSerif",Font.PLAIN,20));
		searchfield.setBorder(BorderFactory.createLineBorder(new Color(31,50,64,255), 1));
                searchfield.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                if (searchfield.getText().equals("Search")) {
                searchfield.setText("");
                searchfield.setForeground(Color.BLACK);
            }
            }
                 @Override
                public void focusLost(FocusEvent e) {
                if (searchfield.getText().isEmpty()) {
                searchfield.setForeground(Color.GRAY);
                searchfield.setText("Search");
            }
        }
    });
		bottom.add(searchfield);
                
                search=new JButton("SEARCH");
                search.setForeground(Color.white);
		search.setFont(new Font("SansSerif",Font.PLAIN,18));
		search.setBackground(new Color(241,97,66,255));
		search.setBounds(690,24,120,40);
		search.setFocusable(false);
                                                    search.addActionListener(new SearchListener());
		bottom.add(search);
                
                edit=new JButton("EDIT");
		edit.setForeground(Color.white);
		edit.setFont(new Font("SansSerif",Font.PLAIN,18));
		edit.setBackground(new Color(98,163,241,255));
		edit.setBounds(825,24,120,40);
		edit.setFocusable(false);
                                                     edit.addActionListener(new EditListener());
//		bottom.add(edit);

		remove=new JButton("REMOVE");
		remove.setForeground(Color.white);
		remove.setFont(new Font("SansSerif",Font.PLAIN,18));
		remove.setBackground(new Color(255,79,105,255));
		remove.setBounds(960,24,120,40);
		remove.setFocusable(false);
                                                    remove.addActionListener(new RemoveListener());
		bottom.add(remove);
                

		center.setLayout(null);
                showCustomer();
		
		




		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.setLocationRelativeTo(null);  
		frame.setVisible(true);
		frame.setSize(1100,590);
                                		 frame.setLocationRelativeTo(null);  


	}
	public static void main(String[] args) 
	{
		new Customer();
	}
        
        public void addCustomer(){
            int idExistCustomer = checkCustomer();
            
            if(idExistCustomer == 0){
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection(url,uname, pwd);
                Statement state = conn.createStatement();
                
                state.execute("INSERT INTO `customer` (`custid`, `custname`, `username`, `telephone`, `mailid`) VALUES (NULL, '"+companyfield.getText()+"', '"+userfield.getText()+"', '"+telfield.getText()+"', '"+emailfield.getText()+"');");
                state.close();
                conn.close();
                                                                                                            JOptionPane.showMessageDialog(frame, "Customer Added Succesfully Please Refresh View Category..!!");

            }
            catch(Exception e){
                e.printStackTrace();
            }
            }
            else{
                                            JOptionPane.showMessageDialog(frame, "Customer Exist at Id : " + Integer.toString(idExistCustomer), "Alert",JOptionPane.ERROR_MESSAGE);

            }
        }
        
        public int checkCustomer(){
            //  Connect to an MySQL Database, run query, get result set
      
        String sql = "SELECT * FROM customer WHERE custname = '" + companyfield.getText() + "'";

        // Java SE 7 has try-with-resources
        // This will ensure that the sql objects are closed when the program 
        // is finished with them
        try (Connection connection = DriverManager.getConnection( url, uname, pwd );
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery( sql ))
        {
            
            if(rs.next()){
                System.out.println(rs.getInt("custid"));
                return rs.getInt("custid");
                
            }
        }
        catch (SQLException e)
        {
            System.out.println( e.getMessage() );
        }
        
         return 0;
        }
        
        public void searchCustomer(String term){
                   ArrayList columnNames = new ArrayList();
        ArrayList data = new ArrayList();

        //  Connect to an MySQL Database, run query, get result set
        String url = "jdbc:mysql://localhost:3306/imc?zeroDateTimeBehavior=CONVERT_TO_NULL";
        String userid = "root";
        String password = "";
        String sql = "SELECT * FROM customer WHERE custname='"+term+ "'";

        // Java SE 7 has try-with-resources
        // This will ensure that the sql objects are closed when the program 
        // is finished with them
        try (Connection connection = DriverManager.getConnection( url, userid, password );
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery( sql ))
        {
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();

            //  Get column names
            for (int i = 1; i <= columns; i++)
            {
                columnNames.add( md.getColumnName(i) );
            }

            //  Get row data
            while (rs.next())
            {
                ArrayList row = new ArrayList(columns);

                for (int i = 1; i <= columns; i++)
                {
                    row.add( rs.getObject(i) );
                }

                data.add( row );
            }
        }
        catch (SQLException e)
        {
            System.out.println( e.getMessage() );
        }

        // Create Vectors and copy over elements from ArrayLists to them
        // Vector is deprecated but I am using them in this example to keep 
        // things simple - the best practice would be to create a custom defined
        // class which inherits from the AbstractTableModel class
        Vector columnNamesVector = new Vector();
        Vector dataVector = new Vector();

        for (int i = 0; i < data.size(); i++)
        {
            ArrayList subArray = (ArrayList)data.get(i);
            Vector subVector = new Vector();
            for (int j = 0; j < subArray.size(); j++)
            {
                subVector.add(subArray.get(j));
            }
            dataVector.add(subVector);
        }

        for (int i = 0; i < columnNames.size(); i++ )
            columnNamesVector.add(columnNames.get(i));

        //  Create table with database data    
       table = new JTable(dataVector, columnNamesVector)
        {
            public Class getColumnClass(int column)
            {
                for (int row = 0; row < getRowCount(); row++)
                {
                    Object o = getValueAt(row, column);

                    if (o != null)
                    {
                        return o.getClass();
                    }
                }

                return Object.class;
            }
        };

      scrollpane=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    scrollpane.setBounds(10,10,765,450);
    center.add(scrollpane);
        }
        
    public void removeCustomer(Object id){
                 try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/imc?zeroDateTimeBehavior=CONVERT_TO_NULL";
        String uname = "root";
        String pwd = "";
                Connection conn = DriverManager.getConnection(url,uname, pwd);
                Statement state = conn.createStatement();
                
                state.execute("DELETE FROM `customer` WHERE `customer`.`custid` ='"+ id+"'");
                state.close();
                conn.close();
                                                                            JOptionPane.showMessageDialog(frame, "Category DeletedSuccesfully Please Refresh View Category..!!");
                                                                            
            }
            catch(Exception e){
                e.printStackTrace();
            }
}
        public void showCustomer(){
            ArrayList columnNames = new ArrayList();
        ArrayList data = new ArrayList();

        //  Connect to an MySQL Database, run query, get result set
        String url = "jdbc:mysql://localhost:3306/imc?zeroDateTimeBehavior=CONVERT_TO_NULL";
        String userid = "root";
        String password = "";
        String sql = "SELECT * FROM customer";

        // Java SE 7 has try-with-resources
        // This will ensure that the sql objects are closed when the program 
        // is finished with them
        try (Connection connection = DriverManager.getConnection( url, userid, password );
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery( sql ))
        {
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();

            //  Get column names
            for (int i = 1; i <= columns; i++)
            {
                columnNames.add( md.getColumnName(i) );
            }

            //  Get row data
            while (rs.next())
            {
                ArrayList row = new ArrayList(columns);

                for (int i = 1; i <= columns; i++)
                {
                    row.add( rs.getObject(i) );
                }

                data.add( row );
            }
        }
        catch (SQLException e)
        {
            System.out.println( e.getMessage() );
        }

        // Create Vectors and copy over elements from ArrayLists to them
        // Vector is deprecated but I am using them in this example to keep 
        // things simple - the best practice would be to create a custom defined
        // class which inherits from the AbstractTableModel class
        Vector columnNamesVector = new Vector();
        Vector dataVector = new Vector();

        for (int i = 0; i < data.size(); i++)
        {
            ArrayList subArray = (ArrayList)data.get(i);
            Vector subVector = new Vector();
            for (int j = 0; j < subArray.size(); j++)
            {
                subVector.add(subArray.get(j));
            }
            dataVector.add(subVector);
        }

        for (int i = 0; i < columnNames.size(); i++ )
            columnNamesVector.add(columnNames.get(i));

        //  Create table with database data    
        table = new JTable(dataVector, columnNamesVector)
        {
            public Class getColumnClass(int column)
            {
                for (int row = 0; row < getRowCount(); row++)
                {
                    Object o = getValueAt(row, column);

                    if (o != null)
                    {
                        return o.getClass();
                    }
                }

                return Object.class;
            }
        };

      scrollpane=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    scrollpane.setBounds(10,10,765,450);
    center.add(scrollpane);

       
       
        }
        
        
                        class SearchListener implements ActionListener{

                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                                        String searchedTerm = searchfield.getText();

                                                        if(searchedTerm.equals("")){
                                                          JOptionPane.showMessageDialog(frame, "No search Query", "Alert",JOptionPane.ERROR_MESSAGE);

                                                        }
                                                        else if(searchedTerm.equals("All")){
                                                            frame.invalidate();
                                                            showCustomer();
                                                        }
                                                        else{
                                                            searchCustomer(searchedTerm);
                                                        }
                                    }
                            
                        }
                        
                        class ClearListener implements ActionListener{

                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                                        // Clear Button Code
                                                        
                                                        companyfield.setText("");
                                                       userfield.setText("");
                                                        telfield.setText("");
                                                        emailfield.setText("");

                                                        }
                                    }
                            
               
                        
                         class RemoveListener implements ActionListener{

                                                    @Override
                                                    public void actionPerformed(ActionEvent e) {
                                                                        int col = 0;
                                                                        int row = table.getSelectedRow();
                                                                        removeCustomer(table.getValueAt(row, col));
                                                                       frame.invalidate();
                                                            showCustomer();
                                                    }

                                        }
                        
                        class EditListener implements ActionListener{

                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                                        // Edit Button Code
                                    }
                            
                        }
                        class AddListener implements ActionListener{

                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                                        String comp = companyfield.getText();
                                                         String use = userfield.getText();
                                                        String tele = telfield.getText();
                                                        String mail = emailfield.getText();


                                                        if(comp.equals("") || use.equals("") || tele.equals("") || mail.equals("")){
                                                          JOptionPane.showMessageDialog(frame, "One or more Items are missing", "Alert",JOptionPane.ERROR_MESSAGE);

                                                        }
                                                        else{
                                                            addCustomer();
                                                            frame.invalidate();
                                                            showCustomer();
                                                        }
                                      }
                            
                        }
}