/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khuselasecuritycompanysystem;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.sql.ResultSetMetaData;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


/**
 *
 * @author mahlobo
 */
public class frmViewVisitors extends javax.swing.JInternalFrame {

    /**
     * Creates new form frmViewVisitors
     */
    public frmViewVisitors() {
        initComponents();
         mLoadViewVisitors();
    }
     private void mLoadViewVisitors()
    {
        String URL = "jdbc:mysql://localhost:3306/gateaccesscontrol";
        String User = "root";
        String Password = "Password";
        String strSQLQuery;
        try
        {
            java.sql.Connection conMySQLConnectionString = DriverManager.getConnection(URL, User, Password);
            Statement stSQLQuery = conMySQLConnectionString.createStatement();
            strSQLQuery ="SELECT * FROM Visitors;";
            ResultSet rsVisitor = stSQLQuery.executeQuery(strSQLQuery);
            ResultSetMetaData rsmt = rsVisitor.getMetaData();
            
            int intColumnCount = rsmt.getColumnCount();
            Vector vColumn = new Vector(intColumnCount);
            for(int i = 1; i <= intColumnCount; i++)
            {
                vColumn.add(rsmt.getColumnName(i));
            }
            Vector vData = new Vector();
            Vector vRow = new Vector();
            while(rsVisitor.next())
            {
                vRow = new Vector(intColumnCount);
                for(int i = 1; i <= intColumnCount; i++)
                {
                    vRow.add(rsVisitor.getObject(i));
                }
                vData.add(vRow);
            }
            JPanel pnlTable = new JPanel();
            JTable tblVisitors = new JTable(vData, vColumn);
            JScrollPane jspUsersPane = new JScrollPane(tblVisitors);
            tblVisitors.setFillsViewportHeight(true);
            tblVisitors.setVisible(true);
            tblVisitors.validate();
            pnlTable.setLayout(new BorderLayout());
            pnlTable.add(jspUsersPane, BorderLayout.CENTER);
            this.setContentPane(pnlTable);
             stSQLQuery.close(); 
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 875, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 467, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
