 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khuselasecuritycompanysystem;
import java.awt.HeadlessException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
/**
 *
 * @author mahlobo
 */
public class frmVisitors extends javax.swing.JInternalFrame {

    /**
     * Creates new form frmVisitors
     */
    public frmVisitors() {
        initComponents();
        mLoadName();
        mLoadGUIControls();
    }
    Boolean boolRecordExists=false;
    Boolean boolEdit=false;
    Boolean boolCreate=false;
    int intVisitorID;
    String strDate;
    String strTimeIn;
    String strName;
    String strFromCompany;
    String strVehicleRegistrationNumber;
    String strTelephoneNumber;
    String strReasonForVisit;
    String strPersonToSee;
    
    
   
    
    private void mClearVariables()
    {
        intVisitorID=0;
        strDate="";
        strTimeIn="";
        strName="";
        strFromCompany="";
        strVehicleRegistrationNumber="";
        strTelephoneNumber="";
        strReasonForVisit="";
        strPersonToSee="";
    }
    private void mSetValuesToUpperCase()
    {
        strName = strName.toUpperCase();
        strFromCompany = strFromCompany.toUpperCase();
        strVehicleRegistrationNumber = strVehicleRegistrationNumber.toUpperCase();
        strReasonForVisit = strReasonForVisit.toUpperCase();
        strPersonToSee = strPersonToSee.toUpperCase();   
    strTelephoneNumber=strTelephoneNumber.toUpperCase();
    }
    
    private void mGetValuesFromGUI()
    {
        strName=txtName.getText();
        strFromCompany=txtFromCompany.getText();
        strVehicleRegistrationNumber=txtVehicleRegistrationNumber.getText();
        strReasonForVisit=txtReasonForVisit.getText();
        strPersonToSee=txtPersonToSee.getText();
        strDate=txtDate.getText();
        strTimeIn=txtTimeIn.getText();
        strTelephoneNumber=txtTelephoneNumber.getText();
       
              
                
    }
    
    private void mSetValuesInGUI()
    {
         txtDate.setText(strDate);
        txtTimeIn.setText(strTimeIn);
        txtName.setText(strName);
        txtFromCompany.setText(strFromCompany);
        txtVehicleRegistrationNumber.setText(strVehicleRegistrationNumber);
     txtTelephoneNumber.setText(strTelephoneNumber);
        txtReasonForVisit.setText(strReasonForVisit);
        txtPersonToSee.setText(strPersonToSee);
       
               
    }
    
    private void mClearTextFields()
    {
        txtName.setText("");
        txtFromCompany.setText("");
        txtVehicleRegistrationNumber.setText("");
        txtReasonForVisit.setText("");
        txtPersonToSee.setText("");
        txtTelephoneNumber.setText("");
        txtDate.setText("");
        txtTimeIn.setText("");
        
    }
    
    
    private void mcheckIfItemExistInTable()
    {
        String strDBConnectionString = "jdbc:mysql://localhost:3306/gateaccesscontrol";
        String strDBUser = "root";
        String strDBPassword = "Password";
        java.sql.Connection conMySQLConnectionString;
        Statement stStatement;
        ResultSet rs;
        
        try
    {
        conMySQLConnectionString = DriverManager.getConnection(strDBConnectionString,strDBUser,strDBPassword);
        stStatement = conMySQLConnectionString.createStatement();
        String strQuery = "SELECT* FROM Visitors"; 
        stStatement.execute(strQuery);
        
        rs=stStatement.getResultSet();
        boolRecordExists=rs.next();
        stStatement.close();
    }
        catch (Exception e)
    {
        JOptionPane.showMessageDialog(null, e);
    }
    }
   
    private void mCreateVisitors()
    {
        java.sql.Connection conMySQLConnectionString;
        String URL = "jdbc:mysql://localhost:3306/gateaccesscontrol";
        String User = "root";
        String Password = "Password";
        try
        {
            conMySQLConnectionString = DriverManager.getConnection(URL, User, Password);
            Statement stStatement = conMySQLConnectionString.createStatement();
            String sqlInsert = "INSERT INTO Visitors"+"(Date,TimeIn,Name,From Company,Vehicle Registration Number,Telephone Number,Reason For Visit,Person To See)" +"values('"+strDate+"','"+strTimeIn+"','"+strName+"','"+strFromCompany+"','"+strVehicleRegistrationNumber+"','"+strTelephoneNumber+"','"+strReasonForVisit+"','"+strPersonToSee+"')";
            stStatement.executeUpdate(sqlInsert);
            stStatement.close();
            JOptionPane.showMessageDialog(null, "Complete");
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    
    private void mLoadName()
    {
        String strDBConnectionString = "jdbc:mysql://localhost:3306/gateaccesscontrol";
        String strDBUser = "root";
        String strDBPassword = "Password";
        java.sql.Connection conMySQLConnectionString;
        Statement stStatement;
        ResultSet rs;
        try
        {
            conMySQLConnectionString = DriverManager.getConnection(strDBConnectionString, strDBUser, strDBPassword);
            stStatement = conMySQLConnectionString.createStatement();
            String strQuery ="SELECT Name FROM Visitors";
            stStatement.execute(strQuery);
            rs = stStatement.getResultSet();
            while(rs.next())
            {
                cboName.addItem(rs.getString(1));
            }
            stStatement.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
                
      
        }
    }
    
              
     private void mUpdateVisitors()
    {
        String strDBConnectionString = "jdbc:mysql://localhost:3306/gateaccesscontrol";
        String strDBUser = "root";
        String strDBPassword = "Password";
        java.sql.Connection conMySQLConnectionString;
        Statement stStatement;
        try
        {
            conMySQLConnectionString = DriverManager.getConnection(strDBConnectionString, strDBUser, strDBPassword);
            stStatement = conMySQLConnectionString.createStatement();
            String strQuery ="UPDATE Visitors SET Date ='"+strDate+"',TimeIn='"+strTimeIn+"',Name ='"+strName+"',From Company ='"+strFromCompany+"',Vehicle Registration Number ='"+strVehicleRegistrationNumber+"',Telephone Number ='"+strTelephoneNumber+"',Reason For Visit ='"+strReasonForVisit+"',Person_To_See ='"+strPersonToSee+"',WHERE VisitorID ='"+intVisitorID;
            stStatement.executeUpdate(strQuery);
            stStatement.close();
            JOptionPane.showMessageDialog(null, "Update Complete");
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
                
      
        }
    }
     
     
     private void mClearComboBox()
     {
         String[]arrArray = new String[0];
         javax.swing.DefaultComboBoxModel model = new javax.swing.DefaultComboBoxModel(arrArray);
         cboName.setModel(model);
     }
     
      private String mDefaultDate()
    {
        Date dt = new Date();
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
        String strVDate = sm.format(dt);
        return strDate = strVDate;
    }
     private String mDefaultTime()
     {
         Date dtTimeIn = new Date();
         strTimeIn = DateFormat.getTimeInstance().format(dtTimeIn).replace("AM","");
         return strTimeIn.replace("PM", "");          
    }
     
     
     private void mDeleteVisitors()
    {
        String strDBConncetionString = "jdbc:mysql://localhost:3306/gateaccesscontrol";
        String strDBUser = "root";
        String strDBPassword = "Password";
        java.sql.Connection conMySQLConnectionString;
        Statement stStatement;
       
        try
    {
        conMySQLConnectionString = DriverManager.getConnection(strDBConncetionString,strDBUser,strDBPassword);
      
        String strQuery = "Delete from Visitors where Date='"+strDate+"'and TimeIn ='"+strTimeIn+"'and Name='"+strName+"'and From Company='"+strFromCompany+"'and Vehicle Registration Number='"+strVehicleRegistrationNumber+"' and Telephone Number='"+strTelephoneNumber+"' and Reason For Visit='"+strReasonForVisit+"and Person To See='"+strPersonToSee+"'and VisitorID='"+intVisitorID;
        stStatement = conMySQLConnectionString.prepareStatement(strQuery);
        stStatement.execute(strQuery);
        stStatement.close();
        JOptionPane.showMessageDialog(null,"Connection String not closed");
        }
        catch (SQLException | HeadlessException e)
    {
        JOptionPane.showMessageDialog(null, e);
   
    }
    }


    private void mReadVisitorsDetails()
    {
        String strDBConnectionString = "jdbc:mysql://localhost:3306/gateaccesscontrol";
        String strDBUser = "root";
        String strDBPassword = "Password";
        java.sql.Connection conMySQLConnectionString;
        Statement stStatement;
        ResultSet rs;
        try
        {
            conMySQLConnectionString = DriverManager.getConnection(strDBConnectionString, strDBUser, strDBPassword);
            stStatement = conMySQLConnectionString.createStatement();
            String strQuery ="SELECT  VisitorID,Date,TimeIn,Name,From Company,Vehicle Registration Number,Telephone Number,Reason For Visit,Person To See FROM Visitors WHERE Name ='"+cboName.getSelectedItem().toString()+"'";
            stStatement.execute(strQuery);
            rs = stStatement.getResultSet();
            while(rs.next())
            {
                intVisitorID = rs.getInt(1);
                strDate = rs.getString(2);
                strTimeIn = rs.getString(3);
                strName = rs.getString(4);
                strFromCompany = rs.getString(5);
                strVehicleRegistrationNumber = rs.getString(6);
                strTelephoneNumber = rs.getString(7);
                strReasonForVisit = rs.getString(8);
                strPersonToSee = rs.getString(9);
            }
             stStatement.close();
        }
        catch(Exception eX)
        {
            JOptionPane.showMessageDialog(null, eX);
       
        }
    }
    
    
private void mLoadGUIControls()
{
txtTimeIn.setEnabled(false);
txtDate.setEnabled(false);
txtName.setEnabled(false);
txtFromCompany.setEnabled(false);
txtVehicleRegistrationNumber.setEnabled(false);
txtTelephoneNumber.setEnabled(false);
txtReasonForVisit.setEnabled(false);
txtPersonToSee.setEnabled(false);
cboName.setEnabled(true);
btnCreate.setEnabled(true);
btnLoad.setEnabled(true);
btnEdit.setEnabled(true);
btnSave.setEnabled(false);
btnDelete.setEnabled(true);
}

private void mEditGUIControls()
{
txtTimeIn.setEnabled(true);
txtDate.setEnabled(true);
txtName.setEnabled(true);
txtFromCompany.setEnabled(true);
txtVehicleRegistrationNumber.setEnabled(true);
txtTelephoneNumber.setEnabled(true);
txtReasonForVisit.setEnabled(true);
txtPersonToSee.setEnabled(true);
cboName.setEnabled(false);
btnCreate.setEnabled(false);
btnLoad.setEnabled(false);
btnEdit.setEnabled(false);
btnSave.setEnabled(true);
btnDelete.setEnabled(true);
}

private void mSaveGUIControls()
{
txtTimeIn.setEnabled(false);
txtDate.setEnabled(false);
txtName.setEnabled(false);
txtFromCompany.setEnabled(false);
txtVehicleRegistrationNumber.setEnabled(false);
txtTelephoneNumber.setEnabled(false);
txtReasonForVisit.setEnabled(false);
txtPersonToSee.setEnabled(false);
cboName.setEnabled(true);
btnCreate.setEnabled(true);
btnLoad.setEnabled(true);
btnEdit.setEnabled(true);
btnSave.setEnabled(false);
btnDelete.setEnabled(false);
}

private void mCreateGUIControls()
{
txtTimeIn.setEnabled(true);
txtDate.setEnabled(true);
txtName.setEnabled(true);
txtFromCompany.setEnabled(true);
txtVehicleRegistrationNumber.setEnabled(true);
txtTelephoneNumber.setEnabled(true);
txtReasonForVisit.setEnabled(true);
txtPersonToSee.setEnabled(true);
cboName.setEnabled(false);
btnCreate.setEnabled(false);
btnLoad.setEnabled(false);
btnEdit.setEnabled(false);
btnSave.setEnabled(true);
btnDelete.setEnabled(true);
}

private void mDeleteGUIControls()
{
txtTimeIn.setEnabled(false);
txtDate.setEnabled(false);
txtName.setEnabled(false);
txtFromCompany.setEnabled(false);
txtVehicleRegistrationNumber.setEnabled(false);
txtTelephoneNumber.setEnabled(false);
txtReasonForVisit.setEnabled(false);
txtPersonToSee.setEnabled(false);
cboName.setEnabled(true);
btnCreate.setEnabled(true);
btnLoad.setEnabled(true);
btnEdit.setEnabled(true);
btnSave.setEnabled(false);
btnDelete.setEnabled(false);
}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        cboName = new javax.swing.JComboBox<>();
        lblDate = new javax.swing.JLabel();
        lblTimeIn = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        lblFromCompany = new javax.swing.JLabel();
        lblVehicleRegistrationNumber = new javax.swing.JLabel();
        lblTelephoneNumber = new javax.swing.JLabel();
        lblReasonForVisit = new javax.swing.JLabel();
        lblPersonToSee = new javax.swing.JLabel();
        txtDate = new javax.swing.JTextField();
        txtTimeIn = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        txtFromCompany = new javax.swing.JTextField();
        txtVehicleRegistrationNumber = new javax.swing.JTextField();
        txtTelephoneNumber = new javax.swing.JTextField();
        txtReasonForVisit = new javax.swing.JTextField();
        txtPersonToSee = new javax.swing.JTextField();
        btnCreate = new javax.swing.JButton();
        btnLoad = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();

        jLabel4.setText("jLabel4");

        lblDate.setText("Date");

        lblTimeIn.setText("TimeIn");

        lblName.setText("Name");

        lblFromCompany.setText("From Company");

        lblVehicleRegistrationNumber.setText("Vehicle Registration Number");

        lblTelephoneNumber.setText("Telephone Number");

        lblReasonForVisit.setText("Reason For Visit");

        lblPersonToSee.setText("Person To See");

        txtFromCompany.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFromCompanyActionPerformed(evt);
            }
        });

        txtTelephoneNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelephoneNumberActionPerformed(evt);
            }
        });

        btnCreate.setText("CREATE");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        btnLoad.setText("LOAD");
        btnLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadActionPerformed(evt);
            }
        });

        btnEdit.setText("EDIT");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnSave.setText("SAVE");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnDelete.setText("DELETE");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTimeIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblFromCompany, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblVehicleRegistrationNumber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTelephoneNumber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblReasonForVisit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblPersonToSee, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtReasonForVisit, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                            .addComponent(txtTelephoneNumber, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtVehicleRegistrationNumber, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtFromCompany, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtName, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtTimeIn, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtDate, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtPersonToSee)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnCreate, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                            .addComponent(btnLoad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 134, Short.MAX_VALUE))
                    .addComponent(cboName, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(cboName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDate)
                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTimeIn)
                    .addComponent(txtTimeIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblName)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFromCompany)
                    .addComponent(txtFromCompany, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblVehicleRegistrationNumber)
                    .addComponent(txtVehicleRegistrationNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTelephoneNumber)
                    .addComponent(txtTelephoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblReasonForVisit, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtReasonForVisit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPersonToSee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPersonToSee))
                .addGap(14, 14, 14)
                .addComponent(btnCreate)
                .addGap(18, 18, 18)
                .addComponent(btnLoad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEdit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSave)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDelete)
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTelephoneNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelephoneNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelephoneNumberActionPerformed

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        mCreateGUIControls();
       
        txtDate.setText(mDefaultDate());
        txtTimeIn.setText(mDefaultTime());
        txtName.requestFocusInWindow();
        btnDelete.setText("Cancel");
        boolCreate = true;
    
    }//GEN-LAST:event_btnCreateActionPerformed

    private void btnLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadActionPerformed
        mReadVisitorsDetails();
    mSetValuesInGUI();
    mLoadGUIControls();
    }//GEN-LAST:event_btnLoadActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        mReadVisitorsDetails();
    mSetValuesInGUI();
    mEditGUIControls();
    txtName.requestFocusInWindow();
    btnDelete.setText("Cancel");
    boolEdit=true;
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
       if(boolCreate == true)
        {
            if(txtDate.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null, "The field cannot be left empty");
                txtDate.requestFocusInWindow();
            }
            else if(txtTimeIn.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null, "The field cannot be left empty");
                txtTimeIn.requestFocusInWindow();
            }
            else if(txtName.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null, "The field cannot be left empty");
                txtName.requestFocusInWindow();
            }
            else if(txtFromCompany.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null, "The field cannot be left empty");
                txtFromCompany.requestFocusInWindow();
            }
            else if(txtVehicleRegistrationNumber.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null, "The field cannot be left empty");
                txtVehicleRegistrationNumber.requestFocusInWindow();
            }
            else if(txtTelephoneNumber.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null, "The field cannot be left empty");
                txtTelephoneNumber.requestFocusInWindow();
            }
            else if(txtReasonForVisit.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null, "The field cannot be left empty");
                txtReasonForVisit.requestFocusInWindow();
            }
            else if(txtPersonToSee.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null, "The field cannot be left empty");
                txtPersonToSee.requestFocusInWindow();
            }
            else
            {
                mGetValuesFromGUI();
                mSetValuesToUpperCase();
                boolCreate = false;
                    mCreateVisitors();
                    mClearTextFields();
                    mClearVariables();
                    mClearComboBox();
                    mLoadName();
                    mLoadGUIControls();
                }
            
        }
        else if(boolEdit == true)
        {
            boolEdit = false;
            mGetValuesFromGUI();
            mSetValuesToUpperCase();
            
            mUpdateVisitors();
            mClearTextFields();
            mClearVariables();
            mClearComboBox();
            mLoadName();
            mLoadGUIControls();
        }
        btnDelete.setText("Delete");
    
        
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if("Delete".equals(btnDelete.getText()))
    {
    mReadVisitorsDetails();
    mDeleteVisitors();
    mClearComboBox();
    mClearVariables();
    mLoadName();
    }
    else if("Cancel".equals(btnDelete.getText()))
    {
    mClearTextFields();
    mClearVariables();
    mClearComboBox();
    mLoadName();
    mLoadGUIControls();
    btnDelete.setText("Delete");
    
    }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void txtFromCompanyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFromCompanyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFromCompanyActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnLoad;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> cboName;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblFromCompany;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblPersonToSee;
    private javax.swing.JLabel lblReasonForVisit;
    private javax.swing.JLabel lblTelephoneNumber;
    private javax.swing.JLabel lblTimeIn;
    private javax.swing.JLabel lblVehicleRegistrationNumber;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtFromCompany;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPersonToSee;
    private javax.swing.JTextField txtReasonForVisit;
    private javax.swing.JTextField txtTelephoneNumber;
    private javax.swing.JTextField txtTimeIn;
    private javax.swing.JTextField txtVehicleRegistrationNumber;
    // End of variables declaration//GEN-END:variables

    private String getText() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
