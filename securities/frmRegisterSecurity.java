/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khuseleka.securities;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class frmRegisterSecurity extends javax.swing.JInternalFrame {

    /**
     * Creates new form frmRegSecurity
     */
    public frmRegisterSecurity() {
        initComponents();
        mLoadUsername();
        mLoadGUIControls();
    }
    CaesarsChiper clsCC = new CaesarsChiper();

    Boolean boolRecordExists = false;
    Boolean boolEdit = false;
    Boolean boolCreate = false;
    String strName, strLastName, strContactNumber, strAppPass, strShifts;

    String strRole;
    int intSecurityID;

    private void mClearVariables() {
        strName = "";
        strLastName = "";
        strContactNumber = "";
        strAppPass = "";
        strShifts = "";
        strRole = "";
        intSecurityID = 0;
    }

    private void mGetValuesFromGUI() {
        strName = txtName.getText();
        strLastName = txtLastName.getText();
        strShifts = cboShifts.getSelectedItem().toString();
        strContactNumber = txtContact.getText();
        strAppPass = txtPassword.getText();
        strRole = txtRole.getText();
    }

    private void mSetValuesInGUI() {
        txtName.setText(strName);
        txtLastName.setText(strLastName);
        cboShifts.setSelectedItem(strShifts);
        txtContact.setText(strContactNumber);
        txtPassword.setText(strAppPass);
        txtRole.setText(strRole);
    }

    private void mSetValuesToUpperCase() {
        strName = strName.toUpperCase();
        strLastName = strLastName.toUpperCase();
        strRole = strRole.toUpperCase();
        //strContactNumber = strContactNumber.toUpperCase();
    }

    private void mClearTextFields() {
        txtName.setText("");
        txtLastName.setText("");
        txtRole.setText("");
        txtContact.setText("");
        txtPassword.setText("");
    }

    private void mCheckIfItemsExistInTable() {
        String strDBConnectionString = "jdbc:mysql://localhost:3306/gatecontrol";
        String strDBUser = "root";
        String strDBPassword = "Password";

        try {
            java.sql.Connection conMySQLConnectionString = DriverManager.getConnection(strDBConnectionString, strDBUser, strDBPassword);
            Statement myStatement;
            myStatement = conMySQLConnectionString.createStatement();
            String strQuery = "SELECT * FROM securityguard WHERE Name='" + strName + "' AND LastName ='" + strLastName + "' AND Shift='" + strShifts + "'AND ContactNumber='" + strContactNumber + "'AND AppPassword='" + strAppPass + "'AND Role='" + strRole + "'";
            myStatement.execute(strQuery);
            ResultSet rs = myStatement.getResultSet();
            boolRecordExists = rs.next();
            myStatement.close();
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Connection String not closed" + e);
        }
    }

    private void mCreateSecurity() {

        String URL = "jdbc:mysql://localhost:3306/gatecontrol";
        String User = "root";
        String Password = "Password";
        try {
            java.sql.Connection conMySQLConnectionString = DriverManager.getConnection(URL, User, Password);
            try (Statement myStatement = conMySQLConnectionString.createStatement()) {
                String sqlInsert = "INSERT INTO securityguard " + "(Name,LastName,Shift,ContactNumber,AppPassword,Role)" + "VALUES ('" + strName + "','" + strLastName + "','" + strShifts + "','" + strContactNumber + "','" + strAppPass + "','" + strRole + "')";
                myStatement.executeUpdate(sqlInsert);
                myStatement.close();

            }
            JOptionPane.showMessageDialog(null, "Complete");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "");
        }
    }

    private void mLoadUsername() {
        String strDBConnectionString = "jdbc:mysql://localhost:3306/gatecontrol";
        String strDBUser = "root";
        String strDBPassword = "Password";

        try {
            java.sql.Connection conMySQLConnectionString = DriverManager.getConnection(strDBConnectionString, strDBUser, strDBPassword);
            Statement myStatement;
            myStatement = conMySQLConnectionString.createStatement();
            String strQuery = "SELECT Name FROM securityguard";
            myStatement.execute(strQuery);
            ResultSet rs = myStatement.getResultSet();
            while (rs.next()) {
                cboName.addItem(rs.getString(1));
            }
            myStatement.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Connection String not closed" + e);
        }
    }

    private void mUpdateUser() {
        String strDBConnectionString = "jdbc:mysql://localhost:3306/gatecontrol";
        String strDBUser = "root";
        String strDBPassword = "Password";

        try {
            java.sql.Connection conMySQLConnectionString = DriverManager.getConnection(strDBConnectionString, strDBUser, strDBPassword);
            Statement myStatement;
            myStatement = conMySQLConnectionString.createStatement();
            String strQuery = "UPDATE securityguard SET Name ='" + strName + "',LastName = '" + strLastName + "', Shift ='" + strShifts + "' ,ContactNumber ='" + strContactNumber + "', AppPassword ='" + strAppPass + "',Role='" + strRole + "' WHERE SecurityID =" + intSecurityID;
            myStatement.executeUpdate(strQuery);
            myStatement.close();
            JOptionPane.showMessageDialog(null, "Update Complete");
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Connection string not closed" + " " + e);
        }
    }

    private void mClearComboBox() {
        String[] arrArray = new String[0];
        javax.swing.DefaultComboBoxModel model = new javax.swing.DefaultComboBoxModel(arrArray);
        cboName.setModel(model);
    }

    private void mDeleteUser() {
        String strDBConnectionString = "jdbc:mysql://localhost:3306/gatecontrol";
        String strDBUser = "root";
        String strDBPassword = "Password";

        try {
            java.sql.Connection conMySQLConnectionString = DriverManager.getConnection(strDBConnectionString, strDBUser, strDBPassword);
            String strQuery = "DELETE FROM securityguard WHERE Name ='" + strName + "' AND LastName = '" + strLastName + "' AND Shift ='" + strShifts + "'  AND ContactNumber ='" + strContactNumber + "'  AND AppPassword ='" + strAppPass + "' AND Role ='" + strRole + "' AND SecurityID='" + intSecurityID + "'";
            Statement myStatement;
            myStatement = conMySQLConnectionString.prepareStatement(strQuery);
            myStatement.execute(strQuery);
            myStatement.close();
            JOptionPane.showMessageDialog(null, "Deleted");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Connection string not closed" + " " + e);
        }
    }

    private void mReadUserDetails() {
        String strDBConnectionString = "jdbc:mysql://localhost:3306/gatecontrol";
        String strDBUser = "root";
        String strDBPassword = "Password";

        try {
            java.sql.Connection conMySQLConnectionString = DriverManager.getConnection(strDBConnectionString, strDBUser, strDBPassword);
            Statement myStatement;
            myStatement = conMySQLConnectionString.createStatement();
            String strQuery = "SELECT  SecurityID,Name, LastName, Shift,ContactNumber,AppPassword, Role FROM securityguard WHERE Name ='" + cboName.getSelectedItem().toString() + "'";
            myStatement.execute(strQuery);
            ResultSet rs = myStatement.getResultSet();
            while (rs.next()) {
                intSecurityID = rs.getInt(1);
                strName = rs.getString(2);
                strLastName = rs.getString(3);
                strShifts = rs.getString(4);
                strContactNumber = rs.getString(5);
                strAppPass = rs.getString(6);
                strRole = rs.getString(7);
            }
            myStatement.close();
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Connection string not closed" + " " + e);
        }
    }

    private void mLoadGUIControls() {
        txtName.setEnabled(false);
        txtLastName.setEnabled(false);
        cboShifts.setEnabled(false);
        txtContact.setEnabled(false);
        txtPassword.setEnabled(false);
        txtRole.setEnabled(false);
        cboName.setEnabled(true);
        btnCreate.setEnabled(true);
        btnRead.setEnabled(true);
        btnUpdate.setEnabled(true);
        btnDelete.setEnabled(true);
        btnSave.setEnabled(true);
    }

    private void mEditGUIControls() {
        txtName.setEnabled(true);
        txtLastName.setEnabled(true);
        txtContact.setEnabled(true);
        txtPassword.setEnabled(true);
        txtRole.setEnabled(true);
        cboName.setEnabled(false);
        cboShifts.setEnabled(true);
        btnCreate.setEnabled(false);
        btnRead.setEnabled(false);
        btnUpdate.setEnabled(false);
        btnSave.setEnabled(true);
        btnDelete.setEnabled(true);
    }

    private void mSaveGUIControls() {

        txtName.setEnabled(false);
        txtLastName.setEnabled(false);
        txtContact.setEnabled(false);
        txtPassword.setEnabled(false);
        txtRole.setEnabled(false);
        cboShifts.setEnabled(false);
        cboName.setEnabled(true);
        btnCreate.setEnabled(true);
        btnRead.setEnabled(true);
        btnUpdate.setEnabled(true);
        btnSave.setEnabled(false);
        btnDelete.setEnabled(false);
    }

    private void mCreateGUIControls() {
        txtName.setEnabled(true);
        txtLastName.setEnabled(true);
        txtContact.setEnabled(true);
        txtPassword.setEnabled(true);
        txtRole.setEnabled(true);
        cboShifts.setEnabled(true);
        cboName.setEnabled(false);
        btnCreate.setEnabled(false);
        btnRead.setEnabled(false);
        btnUpdate.setEnabled(false);
        btnSave.setEnabled(true);
        btnDelete.setEnabled(true);
    }

    private void mDeleteGUIControls() {
        txtName.setEnabled(false);
        txtName.setEnabled(false);
        txtLastName.setEnabled(false);
        txtContact.setEnabled(false);
        txtPassword.setEnabled(false);
        txtRole.setEnabled(false);
        cboName.setEnabled(true);
        cboShifts.setEnabled(false);
        btnCreate.setEnabled(true);
        btnRead.setEnabled(true);
        btnUpdate.setEnabled(true);
        btnSave.setEnabled(false);
        btnDelete.setEnabled(false);
    }

    private void mEncryptPassword() {
        strAppPass = clsCC.mEncrypt(strAppPass, 5);
    }

    private void mDecryptPassword() {
        strAppPass = clsCC.mDecrypt(strAppPass, 5);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbLastName = new javax.swing.JLabel();
        btnUpdate = new javax.swing.JButton();
        lblShift = new javax.swing.JLabel();
        btnDelete = new javax.swing.JButton();
        lblRole = new javax.swing.JLabel();
        lblAppPassword = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtLastName = new javax.swing.JTextField();
        cboShifts = new javax.swing.JComboBox<>();
        txtContact = new javax.swing.JTextField();
        txtPassword = new javax.swing.JTextField();
        lblHeading = new java.awt.Label();
        btnCreate = new javax.swing.JButton();
        lblName = new javax.swing.JLabel();
        btnRead = new javax.swing.JButton();
        cboName = new javax.swing.JComboBox<>();
        btnSave = new javax.swing.JButton();
        lblContact1 = new javax.swing.JLabel();
        txtRole = new javax.swing.JTextField();

        setBackground(new java.awt.Color(204, 204, 0));
        setClosable(true);

        lbLastName.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        lbLastName.setText("Last Name:");

        btnUpdate.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        btnUpdate.setText("UPDATE");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        lblShift.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        lblShift.setText("Shift:");

        btnDelete.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        btnDelete.setText("DELETE");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        lblRole.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        lblRole.setText("Role:");

        lblAppPassword.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        lblAppPassword.setText("App Password:");

        txtName.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N

        txtLastName.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N

        cboShifts.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        cboShifts.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "Morning", "Afternoon", "Evening" }));

        txtContact.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N

        txtPassword.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });

        lblHeading.setAlignment(java.awt.Label.CENTER);
        lblHeading.setBackground(new java.awt.Color(204, 204, 0));
        lblHeading.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        lblHeading.setText("Registration Page");

        btnCreate.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        btnCreate.setText("CREATE");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        lblName.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        lblName.setText("Name:");

        btnRead.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        btnRead.setText("READ");
        btnRead.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReadActionPerformed(evt);
            }
        });

        btnSave.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        btnSave.setText("SAVE");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        lblContact1.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        lblContact1.setText("Contact Number:");

        txtRole.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(207, Short.MAX_VALUE)
                .addComponent(lblHeading, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93))
            .addGroup(layout.createSequentialGroup()
                .addGap(177, 177, 177)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnRead, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                    .addComponent(btnCreate, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                    .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(cboName, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblAppPassword)
                                .addGap(21, 21, 21)
                                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblRole, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblShift)
                                .addGap(18, 18, 18)
                                .addComponent(cboShifts, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblContact1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblName)
                        .addGap(20, 20, 20)
                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbLastName)
                        .addGap(39, 39, 39)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtContact, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLastName)
                    .addComponent(txtRole, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lblHeading, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(cboName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbLastName)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblName))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblShift)
                    .addComponent(cboShifts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblContact1))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAppPassword)
                    .addComponent(lblRole)
                    .addComponent(txtRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addComponent(btnCreate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSave)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRead)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnUpdate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDelete)
                .addContainerGap(73, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPasswordActionPerformed

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        // TODO add your handling code here:
        mCreateGUIControls();
        txtName.requestFocusInWindow();
        btnDelete.setText("Cancel");
        boolCreate = true;
    }//GEN-LAST:event_btnCreateActionPerformed

    private void btnReadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReadActionPerformed
        // TODO add your handling code here:
        mReadUserDetails();
        mEncryptPassword();
        mSetValuesInGUI();
        mLoadGUIControls();
    }//GEN-LAST:event_btnReadActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        mReadUserDetails();
        mDecryptPassword();
        mSetValuesInGUI();
        mEditGUIControls();
        txtName.requestFocusInWindow();
        btnDelete.setText("Cancel");
        boolEdit = true;
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        mDeleteGUIControls();
        if ("Delete".equals(btnDelete.getText())) {
            mReadUserDetails();
            mDeleteUser();
            mClearComboBox();
            mClearVariables();
            mLoadUsername();
        } else if ("Cancel".equals(btnDelete.getText())) {
            mClearTextFields();
            mClearVariables();
            mClearComboBox();
            mLoadUsername();
            mLoadGUIControls();
            btnDelete.setText("Delete");

        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        if (boolCreate == true) {
            if (txtName.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "The field cannot be left empty");
                txtName.requestFocusInWindow();
            } else if (txtLastName.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "The field cannot be left empty");
                txtLastName.requestFocusInWindow();
            } else if (txtContact.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "The field cannot be left empty");
                txtContact.requestFocusInWindow();
            } else if (txtPassword.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "The field cannot be left empty");
                txtPassword.requestFocusInWindow();
            } else {
                mGetValuesFromGUI();
                mSetValuesToUpperCase();
                mEncryptPassword();
                mCheckIfItemsExistInTable();
                if (boolRecordExists == true) {
                    boolRecordExists = false;
                    JOptionPane.showMessageDialog(null, "User already Exists");
                } else if (boolRecordExists == false) {
                    boolCreate = false;
                    mCreateSecurity();
                    mClearTextFields();
                    mClearVariables();
                    mClearComboBox();
                    mLoadUsername();
                    mLoadGUIControls();
                }
            }
        } else if (boolEdit == true) {
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnRead;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cboName;
    private javax.swing.JComboBox<String> cboShifts;
    private javax.swing.JLabel lbLastName;
    private javax.swing.JLabel lblAppPassword;
    private javax.swing.JLabel lblContact1;
    private java.awt.Label lblHeading;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblRole;
    private javax.swing.JLabel lblShift;
    private javax.swing.JTextField txtContact;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtRole;
    // End of variables declaration//GEN-END:variables
}
