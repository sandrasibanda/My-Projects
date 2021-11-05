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
import javax.swing.JOptionPane;

/**
 *
 * @author mahlobo
 */
public class frmUsers extends javax.swing.JInternalFrame {

    /**
     * Creates new form frmUsers
     */
    public frmUsers() {
        initComponents();
        mLoadUsername();
        mLoadGUIControls();
    }
    CaesarsChiper clsCC = new CaesarsChiper();
    
    Boolean boolRecordExists=false;
    Boolean boolEdit=false;
    Boolean boolCreate=false;
    String strUsername;
    String strPassword;
    String strRole;
    int intUserID;
    
    private void mClearVariables()
    {
        strUsername ="";
        strPassword ="";
        strRole ="";
        intUserID = 0;
    }
    private void mGetValuesFromGUI()
    {
        strUsername = txtUsername.getText();
        strPassword = txtPassword.getText();
        strRole = txtRole.getText();
    }
    private void mSetValuesInGUI()
    {
        txtUsername.setText(strUsername);
        txtPassword.setText(strPassword);
        txtRole.setText(strRole);
    }
    private void mSetValuesToUpperCase()
    {
        strUsername = strUsername.toUpperCase();
        strPassword = strPassword.toUpperCase();
        strRole = strRole.toUpperCase();
    }
    private void mClearTextFields()
    {
        txtUsername.setText("");
        txtPassword.setText("");
        txtRole.setText("");
    }
    private void mCheckIfItemsExistInTable()
    {
        String strDBConnectionString = "jdbc:mysql://localhost:3306/gateaccesscontrol";
        String strDBUser = "root";
        String strDBPassword = "Password";
        
        
        
        try
        {
            java.sql.Connection conMySQLConnectionString = DriverManager.getConnection(strDBConnectionString, strDBUser, strDBPassword);
            Statement myStatement;
            myStatement = conMySQLConnectionString.createStatement();
            String strQuery = "SELECT * FROM users WHERE UserName='" + strUsername +"' AND Password ='" + strPassword +"' AND Role='"+strRole+"'";
            myStatement.execute(strQuery);
            ResultSet rs = myStatement.getResultSet();
            boolRecordExists = rs.next();
            myStatement.close();
        }
        catch(Exception e)
        {
           
                JOptionPane.showMessageDialog(null, "Connection String not closed"+e);
        }
    }
    private void mCreateUser()
    {
        
        String URL = "jdbc:mysql://localhost:3306/gateaccesscontrol";
        String User = "root";
        String Password = "Password";
        try
        {
            java.sql.Connection conMySQLConnectionString = DriverManager.getConnection(URL, User, Password);
            try (Statement myStatement = conMySQLConnectionString.createStatement()) {
                String sqlInsert = "INSERT INTO users "+"(UserName,Password,Role)" + "VALUES ('" + strUsername + "','" + strPassword + "',"
                        + "'" + strRole + "')";
                myStatement.executeUpdate(sqlInsert);
                myStatement.close();
                
            }
            JOptionPane.showMessageDialog(null, "Complete");
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"");
        }
    }
    private void mLoadUsername()
    {
        String strDBConnectionString = "jdbc:mysql://localhost:3306/gateaccesscontrol";
        String strDBUser = "root";
        String strDBPassword = "Password";
        
       
        try
        {
            java.sql.Connection conMySQLConnectionString = DriverManager.getConnection(strDBConnectionString, strDBUser, strDBPassword);
            Statement myStatement;
            myStatement = conMySQLConnectionString.createStatement();
            String strQuery = "SELECT Username FROM users";
            myStatement.execute(strQuery);
            ResultSet rs = myStatement.getResultSet();
            while(rs.next())
            {
                cboUsername.addItem(rs.getString(1));
            }
            myStatement.close();
        }
        catch(Exception e)
        {
                JOptionPane.showMessageDialog(null, "Connection String not closed"+e);
            }
        }
    
    private void mUpdateUser()
    {
        String strDBConnectionString = "jdbc:mysql://localhost:3306/gateaccesscontrol";
        String strDBUser = "root";
        String strDBPassword = "Password";
        
        
        try
        {
            java.sql.Connection conMySQLConnectionString = DriverManager.getConnection(strDBConnectionString, strDBUser, strDBPassword);
            Statement myStatement;
            myStatement = conMySQLConnectionString.createStatement();
            String strQuery ="UPDATE users SET UserName ='"+strUsername +"'Password = '"+ strPassword+"' Role ='" + strRole +"'WHERE UserID ="+intUserID;
            myStatement.executeUpdate(strQuery);
            myStatement.close(); 
        }
        catch(Exception e)
        {
           
                JOptionPane.showMessageDialog(null, "Connection string not closed"+" "+e);
            }
        }
    
    private void mClearComboBox()
    {
        String[] arrArray = new String[0];
        javax.swing.DefaultComboBoxModel model = new javax.swing.DefaultComboBoxModel(arrArray);
        cboUsername.setModel(model);
    }
    private void mDeleteUser()
    {
        String strDBConnectionString = "jdbc:mysql://localhost:3306/gateaccesscontrol";
        String strDBUser = "root";
        String strDBPassword = "Password";
        
        
        try
        {
            java.sql.Connection conMySQLConnectionString = DriverManager.getConnection(strDBConnectionString, strDBUser, strDBPassword);
            String strQuery ="DELETE FROM users WHERE UserName ='"+strUsername +"' AND Password = '"+ strPassword+"' AND Role ='"+ strRole +"'  AND UserID="+intUserID;
            Statement myStatement;
            myStatement = conMySQLConnectionString.prepareStatement(strQuery);
            myStatement.execute(strQuery);
            myStatement.close();
        }
        catch(Exception e)
        {
                JOptionPane.showMessageDialog(null, "Connection string not closed"+" "+e);
            }
        }
    
    private void mReadUserDetails()
    {
        String strDBConnectionString = "jdbc:mysql://localhost:3306/gateaccesscontrol";
        String strDBUser = "root";
        String strDBPassword = "Password";
        
        try
        {
            java.sql.Connection conMySQLConnectionString = DriverManager.getConnection(strDBConnectionString, strDBUser, strDBPassword);
            Statement myStatement;
            myStatement = conMySQLConnectionString.createStatement();
            String strQuery ="SELECT  UserID,UserName, Password, Role FROM users WHERE Username ='"+cboUsername.getSelectedItem().toString()+"'";
             myStatement.execute(strQuery);
            ResultSet rs = myStatement.getResultSet();
            while(rs.next())
            {
                intUserID = rs.getInt(1);
                strUsername = rs.getString(2);
                strPassword = rs.getString(3);
                strRole = rs.getString(4);
            }
             myStatement.close(); 
        }
        catch(Exception e)
        {
            
                JOptionPane.showMessageDialog(null, "Connection string not closed"+" "+e);
            }
        }
    
    private void mLoadGUIControls()
    {
        txtUsername.setEnabled(false);
        txtPassword.setEnabled(false);
        txtRole.setEnabled(false);
        cboUsername.setEnabled(true);
        btnCreate.setEnabled(true);
        btnLoad.setEnabled(true);
        btnEdit.setEnabled(true);
        btnSave.setEnabled(false);
        btnDelete.setEnabled(true);
    }
    private void mEditGUIControls()
    {
        txtUsername.setEnabled(true);
        txtPassword.setEnabled(true);
        txtRole.setEnabled(true);
        cboUsername.setEnabled(false);
        btnCreate.setEnabled(false);
        btnLoad.setEnabled(false);
        btnEdit.setEnabled(false);
        btnSave.setEnabled(true);
        btnDelete.setEnabled(true);
    }
    private void mSaveGUIControls()
    {
        txtUsername.setEnabled(false);
        txtPassword.setEnabled(false);
        txtRole.setEnabled(false);
        cboUsername.setEnabled(true);
        btnCreate.setEnabled(true);
        btnLoad.setEnabled(true);
        btnEdit.setEnabled(true);
        btnSave.setEnabled(false);
        btnDelete.setEnabled(false);
    }
    private void mCreateGUIControls()
    {
        txtUsername.setEnabled(true);
        txtPassword.setEnabled(true);
        txtRole.setEnabled(true);
        cboUsername.setEnabled(false);
        btnCreate.setEnabled(false);
        btnLoad.setEnabled(false);
        btnEdit.setEnabled(false);
        btnSave.setEnabled(true);
        btnDelete.setEnabled(true);
    }
    private void mDeleteGUIControls()
    {
        txtUsername.setEnabled(false);
        txtPassword.setEnabled(false);
        txtRole.setEnabled(false);
        cboUsername.setEnabled(true);
        btnCreate.setEnabled(true);
        btnLoad.setEnabled(true);
        btnEdit.setEnabled(true);
        btnSave.setEnabled(false);
        btnDelete.setEnabled(false);
    }
    private void mEncryptPassword()
    {
        strPassword = clsCC.mEncrypt(strPassword, 5);
    }
    private void mDecryptPassword()
    {
        strPassword = clsCC.mDecrypt(strPassword, 5);
    }
    

   
   
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cboUsername = new javax.swing.JComboBox<>();
        lblUsername = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        lblRole = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        txtPassword = new javax.swing.JTextField();
        txtRole = new javax.swing.JTextField();
        btnCreate = new javax.swing.JButton();
        btnLoad = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();

        lblUsername.setText("Username");

        lblPassword.setText("Password");

        lblRole.setText("Role");

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
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLoad, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cboUsername, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(lblRole, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPassword)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtRole))))
                .addGap(139, 139, 139))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(cboUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsername)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPassword)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRole)
                    .addComponent(txtRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addComponent(btnCreate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLoad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEdit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSave)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDelete)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        mCreateGUIControls();
        txtUsername.requestFocusInWindow();
        btnDelete.setText("Cancel");
        boolCreate = true;
    }//GEN-LAST:event_btnCreateActionPerformed

    private void btnLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadActionPerformed
        mReadUserDetails();
        mEncryptPassword();
        mSetValuesInGUI();
        mLoadGUIControls();
                   
    }//GEN-LAST:event_btnLoadActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        mReadUserDetails();
        mDecryptPassword();
        mSetValuesInGUI();
        mEditGUIControls();
        txtUsername.requestFocusInWindow();
        btnDelete.setText("Cancel");
        boolEdit = true;
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
      if(boolCreate == true)
        {
            if(txtUsername.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null, "The field cannot be left empty");
                txtUsername.requestFocusInWindow();
            }
            else if(txtPassword.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null, "The field cannot be left empty");
                txtPassword.requestFocusInWindow();
            }
            else if(txtRole.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null, "The field cannot be left empty");
                txtRole.requestFocusInWindow();
            }
            else
            {
                mGetValuesFromGUI(); 
                mSetValuesToUpperCase();
                mEncryptPassword();
                mCheckIfItemsExistInTable();
                if(boolRecordExists == true)
                {
                    boolRecordExists = false;
                    JOptionPane.showMessageDialog(null, "User already Exists");
                }
                else if(boolRecordExists == false)
                {
                    boolCreate = false;
                    mCreateUser();
                    mClearTextFields();
                    mClearVariables();
                    mClearComboBox();
                    mLoadUsername();
                    mLoadGUIControls();
                }
            }
        }
        else if(boolEdit == true)
        {
            boolEdit = false;
            mGetValuesFromGUI();
            mSetValuesToUpperCase();
            mEncryptPassword();
            mUpdateUser();
            mClearTextFields();
            mClearVariables();
            mClearComboBox();
            mLoadUsername();
            mLoadGUIControls();
        }
        btnDelete.setText("Delete");
    
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
       if("Delete".equals(btnDelete.getText()))
    {
    mReadUserDetails();
    mDeleteUser();
    mClearComboBox();
    mClearVariables();
    mLoadUsername();
    }
    else if("Cancel".equals(btnDelete.getText()))
    {
    mClearTextFields();
    mClearVariables();
    mClearComboBox();
    mLoadUsername();
    mLoadGUIControls();
    btnDelete.setText("Delete");
    }
    }//GEN-LAST:event_btnDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnLoad;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> cboUsername;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblRole;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtRole;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
