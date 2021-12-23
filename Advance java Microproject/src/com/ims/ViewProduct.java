/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.ims;

/**
 *
 * @author Shiv
 */
import java.awt.*;
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
import javax.swing.*;
import javax.swing.BorderFactory.*;
class ViewProduct
{
	JFrame frame;
	JPanel left,bottom,upper;
	JButton add,edit,remove,search,refresh;
	JTextField searchfield;
	JTable table;
	JScrollPane scrollpane;
	ViewProduct()
	{
		frame=new JFrame("View Product");
		left=new JPanel();
		bottom=new JPanel();
		upper=new JPanel();

		frame.setLayout(new BorderLayout(3,3));

		left.setLayout(null);
		upper.setLayout(null);
		bottom.setLayout(null);

		left.setPreferredSize(new Dimension(270,270));
		upper.setPreferredSize(new Dimension(80,80));
		bottom.setPreferredSize(new Dimension(810,810));

		left.setBackground(new Color(31,50,64,255));
		upper.setBackground(new Color(31,50,64,255));
		bottom.setBackground(new Color(31,50,64,255));



		add=new JButton("Add New Product");
		add.setFont(new Font("SansSerif",Font.BOLD,17));
		add.setForeground(Color.white);
		add.setBackground(new Color(40,154,241,255));//blue
		add.setFocusable(false);
		add.setBounds(10,100,250,40);
                add.addActionListener(new AddListener());
		left.add(add);

		edit=new JButton("Edit Selected Product");
		edit.setFont(new Font("SansSerif",Font.BOLD,17));
		edit.setForeground(Color.white);
		edit.setBackground(new Color(255,79,105,255));//red
		edit.setFocusable(false);
		edit.setBounds(10,170,250,40);
                edit.addActionListener(new EditListener());
		left.add(edit);

		remove=new JButton("Remove Selected Product");
		remove.setFont(new Font("SansSerif",Font.BOLD,17));
		remove.setForeground(Color.white);
		remove.setBackground(new Color(120,79,105,255));//blue
		remove.setFocusable(false);
		remove.setBounds(10,240,250,40);
                remove.addActionListener(new RemoveListener());
		left.add(remove);


		searchfield=new JTextField();
		searchfield.setFont(new Font("SansSerif",Font.PLAIN,20));
		searchfield.setBounds(280,20,450,40);
		searchfield.setBorder(BorderFactory.createLineBorder(new Color(31,50,64,255),1));
                                                     
		upper.add(searchfield);

		search=new JButton("SEARCH");
		search.setFont(new Font("SansSerif",Font.BOLD,17));
		search.setForeground(Color.white);
		search.setBackground(new Color(120,79,105,255));
		search.setFocusable(false);
		search.setBounds(750,20,130,40);
                                                   search.addActionListener(new SearchListener());
		upper.add(search);

		refresh=new JButton("REFRESH");
		refresh.setFont(new Font("SansSerif",Font.BOLD,17));
		refresh.setForeground(Color.white);
		refresh.setBackground(new Color(255,79,105,255));
		refresh.setFocusable(false);
		refresh.setBounds(900,20,130,40);
                refresh.addActionListener(new RefreshListener());
		upper.add(refresh);

		
                                                    showProduct();
		

		frame.add(left,BorderLayout.WEST);
		frame.add(upper,BorderLayout.NORTH);
		
		
		frame.setVisible(true);
		frame.setSize(1100,650);
                                		 frame.setLocationRelativeTo(null);  

	}
	public static void main(String[] args) 
	{
		new ViewProduct();
	}
        
        public void searchProduct(String term){
                ArrayList columnNames = new ArrayList();
        ArrayList data = new ArrayList();

        //  Connect to an MySQL Database, run query, get result set
        String url = "jdbc:mysql://localhost:3306/imc?zeroDateTimeBehavior=CONVERT_TO_NULL";
        String userid = "root";
        String password = "";
        String sql = "SELECT * FROM product WHERE Category='"+term+"'";

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
		scrollpane.setBounds(10,10,790,510);
		bottom.add(scrollpane);
                frame.add(bottom,BorderLayout.EAST);
        }
        
         public void removeProduct(Object id){
                 try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/imc?zeroDateTimeBehavior=CONVERT_TO_NULL";
        String uname = "root";
        String pwd = "";
                Connection conn = DriverManager.getConnection(url,uname, pwd);
                Statement state = conn.createStatement();
                
                state.execute("DELETE FROM `product` WHERE `product`.`Id` = '"+id+"'");
                state.close();
                conn.close();
                                                                            JOptionPane.showMessageDialog(frame, "Category DeletedSuccesfully Please Refresh View Category..!!");
                                                                            
            }
            catch(Exception e){
                e.printStackTrace();
            }
}
        
        public void showProduct(){
                ArrayList columnNames = new ArrayList();
        ArrayList data = new ArrayList();

        //  Connect to an MySQL Database, run query, get result set
        String url = "jdbc:mysql://localhost:3306/imc?zeroDateTimeBehavior=CONVERT_TO_NULL";
        String userid = "root";
        String password = "";
        String sql = "SELECT * FROM product";

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
		scrollpane.setBounds(10,10,790,510);
		bottom.add(scrollpane);
                frame.add(bottom,BorderLayout.EAST);
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
                                                            showProduct();
                                                        }
                                                        else{
                                                            searchProduct(searchedTerm);
                                                        }
                                    }
                            
                        }
         
         class AddListener implements ActionListener{
                                 @Override
                        public void actionPerformed(ActionEvent e) {
                               new AddProduct();
                        }
                        }
    
                    class RemoveListener implements ActionListener{

                                                      @Override
                                                    public void actionPerformed(ActionEvent e) {
                                                                        int col = 0;
                                                                        int row = table.getSelectedRow();
                                                                        removeProduct(table.getValueAt(row, col));
                                                                       frame.invalidate();
                                                                        showProduct();
                                                    }

                                        }
                        
                        class EditListener implements ActionListener{

                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                                        // Edit Button Code
                                    }
                            
                        }
                        
                        class RefreshListener implements ActionListener{

                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                                        frame.invalidate();
                                                        showProduct();
                                    }
                            
                        }
}
