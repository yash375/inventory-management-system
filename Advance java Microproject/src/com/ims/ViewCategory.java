/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ims;

/**
 *
 * @author swarup
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
class ViewCategory 
{
	JFrame frame;
	JPanel left,bottom,upper;
	JButton add,edit,remove,search,refresh;
	JTextField searchfield;
	JTable table;
	JScrollPane scrollpane;
                         JLabel titleLabel1, titleLabel2;
	ViewCategory()
	{
		frame=new JFrame("View Category");
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

                                                    titleLabel1=new JLabel("View");
                                                    titleLabel1.setBounds(28,60,250,40);
                                                    titleLabel1.setFont(new Font("Segoe Print",Font.PLAIN,36));
                                                    titleLabel1.setForeground(Color.white);
                                                    left.add(titleLabel1);

                                                    titleLabel2=new JLabel("Category -->");
                                                    titleLabel2.setBounds(28,120,250,60);
                                                    titleLabel2.setFont(new Font("Segoe Print",Font.PLAIN,36));
                                                    titleLabel2.setForeground(Color.white);
                                                    left.add(titleLabel2);

		add=new JButton("Add New Category");
		add.setFont(new Font("SansSerif",Font.BOLD,17));
		add.setForeground(Color.white);
		add.setBackground(new Color(40,154,241,255));//blue
		add.setFocusable(false);
		add.setBounds(10,200,250,40);
                                                    add.addActionListener(new AddListener());
		left.add(add);

		edit=new JButton("Edit Selected Product");
		edit.setFont(new Font("SansSerif",Font.BOLD,17));
		edit.setForeground(Color.white);
		edit.setBackground(new Color(255,79,105,255));//red
		edit.setFocusable(false);
		edit.setBounds(10,270,250,40);
                                                    edit.addActionListener(new EditListener());
		left.add(edit);
                
		remove=new JButton("Remove Selected Category");
		remove.setFont(new Font("SansSerif",Font.BOLD,17));
		remove.setForeground(Color.white);
		remove.setBackground(new Color(120,79,105,255));//blue
		remove.setFocusable(false);
		remove.setBounds(10,340,250,40);
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

		

		
                
                                                    showCategory();


		frame.add(left,BorderLayout.WEST);
		frame.add(upper,BorderLayout.NORTH);
		frame.add(bottom,BorderLayout.EAST);













		
		frame.setVisible(true);
		frame.setSize(1100,650);
                
                                                    // TO DO  : ADD THIS STATEMENT TO EVERY OTHER CLASS
                
                                                    frame.setLocationRelativeTo(null);
	}
	public static void main(String[] args) 
	{
		new ViewCategory();
	}
        
        public void removeCategory(Object id){
                 try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/imc?zeroDateTimeBehavior=CONVERT_TO_NULL";
        String uname = "root";
        String pwd = "";
                Connection conn = DriverManager.getConnection(url,uname, pwd);
                Statement state = conn.createStatement();
                
                state.execute("DELETE FROM `category` WHERE `category`.`Id` ='"+id+"'");
                state.close();
                conn.close();
                                                                            JOptionPane.showMessageDialog(frame, "Category DeletedSuccesfully Please Refresh View Category..!!");
                                                                            
            }
            catch(Exception e){
                e.printStackTrace();
            }
}
        
         public void searchCategory(String term){
                ArrayList columnNames = new ArrayList();
        ArrayList data = new ArrayList();

        //  Connect to an MySQL Database, run query, get result set
        String url = "jdbc:mysql://localhost:3306/imc?zeroDateTimeBehavior=CONVERT_TO_NULL";
        String userid = "root";
        String password = "";
        String sql = "SELECT * FROM category WHERE CategoryName = '"+term+"'";

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
        }
        
        public void showCategory(){
                ArrayList columnNames = new ArrayList();
        ArrayList data = new ArrayList();

        //  Connect to an MySQL Database, run query, get result set
        String url = "jdbc:mysql://localhost:3306/imc?zeroDateTimeBehavior=CONVERT_TO_NULL";
        String userid = "root";
        String password = "";
        String sql = "SELECT * FROM category";

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
                                                            showCategory();
                                                        }
                                                        else{
                                                            searchCategory(searchedTerm);
                                                        }
                                    }
                            
                        }

                        class AddListener implements ActionListener{
                                 @Override
                        public void actionPerformed(ActionEvent e) {
                               new AddCategory();
                        }
                        }
    
                    class RemoveListener implements ActionListener{

                                                    @Override
                                                    public void actionPerformed(ActionEvent e) {
                                                                        int col = 0;
                                                                        int row = table.getSelectedRow();
                                                                        removeCategory(table.getValueAt(row, col));
                                                                       
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
                                                        showCategory();
                                    }
                            
                        }
}