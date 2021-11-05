/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khuseleka.securities;

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
 * @author user
 */
public class frmRegisterVisitor extends javax.swing.JInternalFrame {

    /**
     * Creates new form frmRegisterVisitor
     */
    public frmRegisterVisitor() {
        initComponents();
        mLoadName();
        mLoadGUIControls();
    }
    Boolean boolRecordExists = false;
    Boolean boolEdit = false;
    Boolean boolCreate = false;
    int intVisitorID;
    String strDate;
    String strTimeIn;
    String strName;
    String strFromCompany;
    String strVehicleRegNumber;
    String strContactNumber;
    String strReasonForVisit;
    String strPersonToSee;

    private void mClearVariables() {
        intVisitorID = 0;
        strDate = "";
        strTimeIn = "";
        strName = "";
        strFromCompany = "";
        strVehicleRegNumber = "";
        strContactNumber = "";
        strReasonForVisit = "";
        strPersonToSee = "";
    }

    private void mSetValuesToUpperCase() {
        strName = strName.toUpperCase();
        strFromCompany = strFromCompany.toUpperCase();
        strVehicleRegNumber = strVehicleRegNumber.toUpperCase();
        strReasonForVisit = strReasonForVisit.toUpperCase();
        strPersonToSee = strPersonToSee.toUpperCase();
        strContactNumber = strContactNumber.toUpperCase();
    }

    private void mGetValuesFromGUI() {
        strName = txtName.getText();
        strFromCompany = txtFrom.getText();
        strVehicleRegNumber = txtVehReg.getText();
        strReasonForVisit = txtReason.getText();
        strPersonToSee = txtPerson.getText();
        strDate = txtDate.getText();
        strTimeIn = txtTime.getText();
        strContactNumber = txtTel.getText();

    }

    private void mSetValuesInGUI() {
        txtDate.setText(strDate);
        txtTime.setText(strTimeIn);
        txtName.setText(strName);
        txtFrom.setText(strFromCompany);
        txtVehReg.setText(strVehicleRegNumber);
        txtTel.setText(strContactNumber);
        txtReason.setText(strReasonForVisit);
        txtPerson.setText(strPersonToSee);

    }

    private void mClearTextFields() {
        txtName.setText("");
        txtFrom.setText("");
        txtVehReg.setText("");
        txtReason.setText("");
        txtPerson.setText("");
        txtTel.setText("");
        txtDate.setText("");
        txtTime.setText("");

    }

    private void mCheckIfVisitorExists() {
        String strDBConnectionString = "jdbc:mysql://localhost:3306/gatecontrol";
        String strDBUser = "root";
        String strDBPassword = "Password";
        java.sql.Connection conMySQLConnectionString;
        Statement stStatement;
        ResultSet rs;

        try {
            conMySQLConnectionString = DriverManager.getConnection(strDBConnectionString, strDBUser, strDBPassword);
            stStatement = conMySQLConnectionString.createStatement();
            String strQuery = "SELECT* FROM Visitors";
            stStatement.execute(strQuery);

            rs = stStatement.getResultSet();
            boolRecordExists = rs.next();
            stStatement.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void mCreateVisitors() {
        java.sql.Connection conMySQLConnectionString;
        String URL = "jdbc:mysql://localhost:3306/gatecontrol";
        String User = "root";
        String Password = "Password";
        try {
            conMySQLConnectionString = DriverManager.getConnection(URL, User, Password);
            Statement stStatement = conMySQLConnectionString.createStatement();
            String sqlInsert = "INSERT INTO Visitors" + "(Date,TimeIn,Name,FromCompany,VehicleRegistrationNumber,TelephoneNumber,ReasonForVisit,PersonToSee)" + "values('" + strDate + "','" + strTimeIn + "','" + strName + "','" + strFromCompany + "','" + strVehicleRegNumber + "','" + strContactNumber + "','" + strReasonForVisit + "','" + strPersonToSee + "')";
            stStatement.executeUpdate(sqlInsert);
            stStatement.close();
            JOptionPane.showMessageDialog(null, "Complete");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void mLoadName() {
        String strDBConnectionString = "jdbc:mysql://localhost:3306/gatecontrol";
        String strDBUser = "root";
        String strDBPassword = "Password";
        java.sql.Connection conMySQLConnectionString;
        Statement stStatement;
        ResultSet rs;
        try {
            conMySQLConnectionString = DriverManager.getConnection(strDBConnectionString, strDBUser, strDBPassword);
            stStatement = conMySQLConnectionString.createStatement();
            String strQuery = "SELECT Name FROM Visitors";
            stStatement.execute(strQuery);
            rs = stStatement.getResultSet();
            while (rs.next()) {
                cboName.addItem(rs.getString(1));
            }
            stStatement.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }
    }

    private void mUpdateVisitors() {
        String strDBConnectionString = "jdbc:mysql://localhost:3306/gatecontrol";
        String strDBUser = "root";
        String strDBPassword = "Password";
        java.sql.Connection conMySQLConnectionString;
        Statement stStatement;
        try {
            conMySQLConnectionString = DriverManager.getConnection(strDBConnectionString, strDBUser, strDBPassword);
            stStatement = conMySQLConnectionString.createStatement();
            String strQuery = "UPDATE Visitors SET Date ='" + strDate + "',TimeIn='" + strTimeIn + "',Name ='" + strName + "',FromCompany ='" + strFromCompany + "',VehicleRegistrationNumber ='" + strVehicleRegNumber + "',TelephoneNumber ='" + strContactNumber + "',ReasonForVisit ='" + strReasonForVisit + "',PersonToSee ='" + strPersonToSee + "' WHERE VisitorID ='" + intVisitorID + "'";
            stStatement.executeUpdate(strQuery);
            stStatement.close();
            JOptionPane.showMessageDialog(null, "Update Complete");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }
    }

    private void mClearComboBox() {
        String[] arrArray = new String[0];
        javax.swing.DefaultComboBoxModel model = new javax.swing.DefaultComboBoxModel(arrArray);
        cboName.setModel(model);

    }

    private String mDefaultDate() {
        Date dt = new Date();
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
        String strVDate = sm.format(dt);
        return strDate = strVDate;
    }

    private String mDefaultTime() {
        Date dtTimeIn = new Date();
        strTimeIn = DateFormat.getTimeInstance().format(dtTimeIn).replace("AM", "");
        return strTimeIn.replace("PM", "");
    }

    private void mDeleteVisitors() {
        String strDBConncetionString = "jdbc:mysql://localhost:3306/gatecontrol";
        String strDBUser = "root";
        String strDBPassword = "Password";
        java.sql.Connection conMySQLConnectionString;
        Statement stStatement;

        try {
            conMySQLConnectionString = DriverManager.getConnection(strDBConncetionString, strDBUser, strDBPassword);

            String strQuery = "Delete from Visitors WHERE Date='" + strDate + "' AND TimeIn ='" + strTimeIn + "'AND Name='" + strName + "'AND FromCompany='" + strFromCompany + "'AND VehicleRegistrationNumber='" + strVehicleRegNumber + "' AND TelephoneNumber='" + strContactNumber + "' AND ReasonForVisit='" + strReasonForVisit + "'AND PersonToSee='" + strPersonToSee + "' AND VisitorID='" + intVisitorID + "'";
            stStatement = conMySQLConnectionString.prepareStatement(strQuery);
            stStatement.execute(strQuery);
            stStatement.close();
            JOptionPane.showMessageDialog(null, "Deleted");
        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, e);

        }
    }

    private void mReadVisitorsDetails() {
        String strDBConnectionString = "jdbc:mysql://localhost:3306/gatecontrol";
        String strDBUser = "root";
        String strDBPassword = "Password";
        java.sql.Connection conMySQLConnectionString;
        Statement stStatement;
        ResultSet rs;
        try {
            conMySQLConnectionString = DriverManager.getConnection(strDBConnectionString, strDBUser, strDBPassword);
            stStatement = conMySQLConnectionString.createStatement();
            String strQuery = "SELECT  VisitorID,Date,TimeIn,Name,FromCompany,VehicleRegistrationNumber,TelephoneNumber,ReasonForVisit,PersonToSee FROM Visitors WHERE Name ='" + cboName.getSelectedItem().toString() + "'";
            stStatement.execute(strQuery);
            rs = stStatement.getResultSet();
            while (rs.next()) {
                intVisitorID = rs.getInt(1);
                strDate = rs.getString(2);
                strTimeIn = rs.getString(3);
                strName = rs.getString(4);
                strFromCompany = rs.getString(5);
                strVehicleRegNumber = rs.getString(6);
                strContactNumber = rs.getString(7);
                strReasonForVisit = rs.getString(8);
                strPersonToSee = rs.getString(9);
            }
            stStatement.close();
        } catch (Exception eX) {
            JOptionPane.showMessageDialog(null, eX);

        }
    }

    private void mLoadGUIControls() {
        txtTime.setEnabled(false);
        txtDate.setEnabled(false);
        txtName.setEnabled(false);
        txtFrom.setEnabled(false);
        txtVehReg.setEnabled(false);
        txtTel.setEnabled(false);
        txtReason.setEnabled(false);
        txtPerson.setEnabled(false);
        cboName.setEnabled(true);
        btnCreate.setEnabled(true);
        btnRead.setEnabled(true);
        btnUpdate.setEnabled(true);
        btnSave.setEnabled(false);
        btnDelete.setEnabled(true);
    }

    private void mEditGUIControls() {
        txtTime.setEnabled(true);
        txtDate.setEnabled(true);
        txtName.setEnabled(true);
        txtFrom.setEnabled(true);
        txtVehReg.setEnabled(true);
        txtTel.setEnabled(true);
        txtReason.setEnabled(true);
        txtPerson.setEnabled(true);
        cboName.setEnabled(false);
        btnCreate.setEnabled(false);
        btnRead.setEnabled(false);
        btnUpdate.setEnabled(false);
        btnSave.setEnabled(true);
        btnDelete.setEnabled(true);
    }

    private void mSaveGUIControls() {
        txtTime.setEnabled(false);
        txtDate.setEnabled(false);
        txtName.setEnabled(false);
        txtFrom.setEnabled(false);
        txtVehReg.setEnabled(false);
        txtTel.setEnabled(false);
        txtReason.setEnabled(false);
        txtPerson.setEnabled(false);
        cboName.setEnabled(true);
        btnCreate.setEnabled(true);
        btnRead.setEnabled(true);
        btnUpdate.setEnabled(true);
        btnSave.setEnabled(false);
        btnDelete.setEnabled(false);
    }

    private void mCreateGUIControls() {
        txtTime.setEnabled(true);
        txtDate.setEnabled(true);
        txtName.setEnabled(true);
        txtFrom.setEnabled(true);
        txtVehReg.setEnabled(true);
        txtTel.setEnabled(true);
        txtReason.setEnabled(true);
        txtPerson.setEnabled(true);
        cboName.setEnabled(false);
        btnCreate.setEnabled(false);
        btnRead.setEnabled(false);
        btnUpdate.setEnabled(false);
        btnSave.setEnabled(true);
        btnDelete.setEnabled(true);
    }

    private void mDeleteGUIControls() {
        txtTime.setEnabled(false);
        txtDate.setEnabled(false);
        txtName.setEnabled(false);
        txtFrom.setEnabled(false);
        txtVehReg.setEnabled(false);
        txtTel.setEnabled(false);
        txtReason.setEnabled(false);
        txtPerson.setEnabled(false);
        cboName.setEnabled(true);
        btnCreate.setEnabled(true);
        btnRead.setEnabled(true);
        btnUpdate.setEnabled(true);
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

        lblHeading = new java.awt.Label();
        jLabel1 = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        lblVehReg = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        lblFrom = new javax.swing.JLabel();
        lblTel = new javax.swing.JLabel();
        lblPerson = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtDate = new javax.swing.JTextField();
        txtVehReg = new javax.swing.JTextField();
        txtReason = new javax.swing.JTextField();
        txtFrom = new javax.swing.JTextField();
        txtTel = new javax.swing.JTextField();
        txtPerson = new javax.swing.JTextField();
        txtTime = new javax.swing.JTextField();
        btnUpdate = new javax.swing.JButton();
        btnCreate = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnRead = new javax.swing.JButton();
        lblReason = new javax.swing.JLabel();
        btnDelete = new javax.swing.JButton();
        cboName = new javax.swing.JComboBox<String>();

        setBackground(new java.awt.Color(153, 153, 0));
        setClosable(true);

        lblHeading.setAlignment(java.awt.Label.CENTER);
        lblHeading.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        lblHeading.setText("Registration Page");

        lblName.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        lblName.setText("Name & Surname:");

        lblDate.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        lblDate.setText("Date:");

        lblVehReg.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        lblVehReg.setText("Vehicle Reg.No:");

        lblTime.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        lblTime.setText("TimeIn:");

        lblFrom.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        lblFrom.setText("From Company:");

        lblTel.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        lblTel.setText("Tel.No:");

        lblPerson.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        lblPerson.setText("Person to see:");

        txtName.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N

        txtDate.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N

        txtVehReg.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N

        txtReason.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N

        txtFrom.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        txtFrom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFromActionPerformed(evt);
            }
        });

        txtTel.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N

        txtPerson.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N

        txtTime.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N

        btnUpdate.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        btnUpdate.setText("UPDATE");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnCreate.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        btnCreate.setText("CREATE");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        btnSave.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        btnSave.setText("SAVE");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnRead.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        btnRead.setText("READ");
        btnRead.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReadActionPerformed(evt);
            }
        });

        lblReason.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        lblReason.setText("Reasons for visit:");

        btnDelete.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        btnDelete.setText("DELETE");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        cboName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNameActionPerformed(evt);
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblVehReg)
                            .addComponent(lblReason)
                            .addComponent(lblName)
                            .addComponent(lblDate)))
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVehReg, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(54, 54, 54))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(txtReason, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTime)
                        .addGap(31, 31, 31)
                        .addComponent(txtTime, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFrom)
                            .addComponent(lblTel)
                            .addComponent(lblPerson))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTel, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPerson, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(59, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cboName, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lblHeading, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(87, 87, 87))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnRead, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(217, 217, 217))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblHeading, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cboName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDate)
                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTime)
                    .addComponent(txtTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblName)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblFrom)
                        .addComponent(txtFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtReason, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblReason, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblTel)
                                    .addComponent(txtTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblPerson)
                                    .addComponent(txtPerson, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(27, 27, 27))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtVehReg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblVehReg, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(btnCreate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRead)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUpdate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDelete)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSave)
                .addGap(54, 54, 54))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtFromActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFromActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFromActionPerformed

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        // TODO add your handling code here:
        mCreateGUIControls();

        txtDate.setText(mDefaultDate());
        txtTime.setText(mDefaultTime());
        txtName.requestFocusInWindow();
        btnDelete.setText("Cancel");
        boolCreate = true;
    }//GEN-LAST:event_btnCreateActionPerformed

    private void btnReadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReadActionPerformed
        // TODO add your handling code here:
        mReadVisitorsDetails();
        mSetValuesInGUI();
        mLoadGUIControls();
    }//GEN-LAST:event_btnReadActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        mReadVisitorsDetails();
        mSetValuesInGUI();
        mEditGUIControls();
        // mSaveGUIControls();
        txtName.requestFocusInWindow();
        btnDelete.setText("Cancel");
        boolEdit = true;
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        mDeleteGUIControls();
        if ("Delete".equals(btnDelete.getText())) {
            mReadVisitorsDetails();
            mDeleteVisitors();
            mClearComboBox();
            mClearVariables();
            mLoadName();
        } else if ("Cancel".equals(btnDelete.getText())) {
            mClearTextFields();
            mClearVariables();
            mClearComboBox();
            mLoadName();
            mLoadGUIControls();
            btnDelete.setText("Delete");

        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        if (boolCreate == true) {
            if (txtDate.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "The field cannot be left empty");
                txtDate.requestFocusInWindow();
            } else if (txtTime.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "The field cannot be left empty");
                txtTime.requestFocusInWindow();
            } else if (txtName.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "The field cannot be left empty");
                txtName.requestFocusInWindow();
            } else if (txtFrom.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "The field cannot be left empty");
                txtFrom.requestFocusInWindow();
            } else if (txtVehReg.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "The field cannot be left empty");
                txtVehReg.requestFocusInWindow();
            } else if (txtTel.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "The field cannot be left empty");
                txtTel.requestFocusInWindow();
            } else if (txtReason.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "The field cannot be left empty");
                txtReason.requestFocusInWindow();
            } else if (txtPerson.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "The field cannot be left empty");
                txtPerson.requestFocusInWindow();
            } else {
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

        } else if (boolEdit == true) {
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

    private void cboNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboNameActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnRead;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cboName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblFrom;
    private java.awt.Label lblHeading;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblPerson;
    private javax.swing.JLabel lblReason;
    private javax.swing.JLabel lblTel;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel lblVehReg;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtFrom;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPerson;
    private javax.swing.JTextField txtReason;
    private javax.swing.JTextField txtTel;
    private javax.swing.JTextField txtTime;
    private javax.swing.JTextField txtVehReg;
    // End of variables declaration//GEN-END:variables
}
