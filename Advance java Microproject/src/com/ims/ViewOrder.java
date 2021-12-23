/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.ims;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.BorderFactory;                                                                
class ViewOrder
{
	JFrame frame;
	JPanel bottom,left,center;
	JTextField searchfield;
	JButton clear,insert,remove,search;
	DefaultTableModel model;
	JTable table;
	JScrollPane scrollpane;
                        JLabel titleLabel1, titleLabel2, searchLabel;

	ViewOrder()
	{
		frame=new JFrame("View Order Details");
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


                                                    

                                                titleLabel1=new JLabel("View");
                                                titleLabel1.setBounds(28,60,250,40);
                                                titleLabel1.setFont(new Font("Segoe Print",Font.PLAIN,36));
                                                titleLabel1.setForeground(Color.white);
                                                left.add(titleLabel1);

                                                titleLabel2=new JLabel("Orders -->");
                                                titleLabel2.setBounds(28,120,250,40);
                                                titleLabel2.setFont(new Font("Segoe Print",Font.PLAIN,36));
                                                titleLabel2.setForeground(Color.white);
                                                left.add(titleLabel2);
                
                                                    insert=new JButton("INSERT");
		insert.setBounds(28,200,250,40);
		insert.setFont(new Font("SansSerif",Font.PLAIN,18));
		insert.setBackground(new Color(40,154,103,255));
		insert.setForeground(Color.white);
		insert.setFocusable(false);
                                                    insert.addActionListener(new AddListener());
		left.add(insert);
                
                
                                                    clear=new JButton("REFRESH");
		clear.setBounds(28,280,250,40);
		clear.setFont(new Font("SansSerif",Font.PLAIN,18));
		clear.setBackground(new Color(241,97,66,255));
		clear.setForeground(Color.white);
		clear.setFocusable(false);
                clear.addActionListener(new RefreshListener());
		left.add(clear);
                
                                                    remove=new JButton("REMOVE");
		remove.setForeground(Color.white);
		remove.setFont(new Font("SansSerif",Font.PLAIN,18));
		remove.setBackground(new Color(255,79,105,255));
		remove.setBounds(28,360,250,40);
		remove.setFocusable(false);
                remove.addActionListener(new RemoveListener());
//		left.add(remove);
                
            


		bottom.setLayout(null);
                
                searchfield=new JTextField();
                searchfield.setBounds(490,24,380,40);
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
		search.setBounds(890,24,120,40);
		search.setFocusable(false);
                 search.addActionListener(new SearchListener());
		bottom.add(search);
                
                titleLabel2=new JLabel("Search :");
              titleLabel2.setBounds(350,24,100,40);
              titleLabel2.setFont(new Font("Tw Cen MT",Font.PLAIN,24));
              titleLabel2.setForeground(Color.white);
              bottom.add(titleLabel2);


		
                                                showOrder();



		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(1100,590);
                		 frame.setLocationRelativeTo(null);  


	}
	public static void main(String[] args) 
	{
		new ViewOrder();
	}
        
         public void searchOrder(String term){
                  ArrayList columnNames = new ArrayList();
                ArrayList data = new ArrayList();

                        //  Connect to an MySQL Database, run query, get result set
                        String url = "jdbc:mysql://localhost:3306/imc?zeroDateTimeBehavior=CONVERT_TO_NULL";
                        String userid = "root";
                        String password = "";
                        String sql = "SELECT * FROM ordertable WHERE ProductName = '" +term+ "'";

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

              center.setLayout(null);

		scrollpane=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollpane.setBounds(10,10,765,450);
		center.add(scrollpane);
        }
        
        
        public void showOrder(){
                  ArrayList columnNames = new ArrayList();
                ArrayList data = new ArrayList();

                        //  Connect to an MySQL Database, run query, get result set
                        String url = "jdbc:mysql://localhost:3306/imc?zeroDateTimeBehavior=CONVERT_TO_NULL";
                        String userid = "root";
                        String password = "";
                        String sql = "SELECT * FROM ordertable";

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

              center.setLayout(null);

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
                                                            showOrder();
                                                        }
                                                        else{
                                                            searchOrder(searchedTerm);
                                                        }
                                    }
                            
                        }
        
        class AddListener implements ActionListener{
                                 @Override
                        public void actionPerformed(ActionEvent e) {
                               new AddOrder();
                        }
                        }
    
                    class RemoveListener implements ActionListener{

                                                    @Override
                                                    public void actionPerformed(ActionEvent e) {
                                                                       
                                                    }

                                        }
                    
                     class RefreshListener implements ActionListener{

                                                    @Override
                                                    public void actionPerformed(ActionEvent e) {
                                                                        frame.invalidate();
                                                                        showOrder();
                                                    }

                                        }
                        
                        class EditListener implements ActionListener{

                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                                        // Edit Button Code
                                    }
                            
                        }
}
