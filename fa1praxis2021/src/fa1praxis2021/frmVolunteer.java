/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fa1praxis2021;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author HARDLEY PHETLO
 */
public class frmVolunteer extends javax.swing.JFrame {

    Connection conn=null;
    PreparedStatement pst=null;
    ResultSet rs=null;
    String user;
    /**
     * Creates new form frmVolunteer
     */
    public frmVolunteer() {
        initComponents();
        //LOADIND FORM WITH DATA
        
        //DISABLE UI FOR VIEW ONLY
        txtVDuration.setEnabled(false);
        txtVName.setEnabled(false);
        txtV_Username.setEnabled(false);
        txtV_Password.setEnabled(false);
        btnLOGOUT.setVisible(false);
        
        
    }
    public  frmVolunteer(String username){
        initComponents(); 
        
        
        lblUSERNAME.setText(username);
        user=lblUSERNAME.getText();
        
        
    }
    public void searchV()
     {
          try
        {
            txtSearch.setText(user);
            
             String query="SELECT * FROM `tbl_staff1` WHERE v_name=?";
              
            conn=DriverManager.getConnection("jdbc:mysql://localhost/db_fa1","root","");
            pst=conn.prepareStatement(query);
            pst.setString(1, txtSearch.getText());
            rs=pst.executeQuery();
            
             if(rs.next()==true)
               {
                String vID=rs.getString(1);
                String vName=rs.getString(2);
                String vSERVICE=rs.getString(3);
                String vDuration=rs.getString(4);
                String vCREATEDON=rs.getString(5);
                
                
                txtVName.setText(vName);
                lblTASK1.setText(vSERVICE);
                txtVDuration.setText(vDuration);
                lblCREATED.setText(vCREATEDON);
                
                
                
                JOptionPane.showMessageDialog(this,"DATA LOAD SUCCESS!");
                
               }
                 else
                 {
                JOptionPane.showMessageDialog(this,"NO records found!");
                txtVName.setText("");
                txtVName.setText("");
                lblCREATED.setText("");
                lblTASK1.setText("");
                txtV_Username.setText("");
                txtV_Password.setText("");
                
                 }
             
            String query1="SELECT * FROM `tbl_login1` WHERE username=?";
            conn=DriverManager.getConnection("jdbc:mysql://localhost/db_fa1","root","");
            pst=conn.prepareStatement(query1);
            pst.setString(1, txtSearch.getText());
            rs=pst.executeQuery();
            if(rs.next()==true)
            {
                String uNAME=rs.getString(1);
                String uPASS=rs.getString(2);
                
                txtV_Username.setText(uNAME);
                txtV_Password.setText(uPASS);
                
           
               btnUpdate.setVisible(true);
            }
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(this, ex.getMessage());
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

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblCREATED = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblTASK1 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        btn_LOADvolunteer = new javax.swing.JButton();
        lblUSERNAME = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnLOGOUT = new javax.swing.JButton();
        txtVDuration = new javax.swing.JTextField();
        txtV_Username = new javax.swing.JTextField();
        txtVName = new javax.swing.JTextField();
        txtV_Password = new javax.swing.JTextField();
        btnUpdate = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("WELCOME BACK VOLUNTEER");

        lblCREATED.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lblCREATED.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCREATED.setText(">>CREATED ON<<");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("LOGGED IN AS:");

        lblTASK1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lblTASK1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTASK1.setText(">>CURRENT TASKS<<");

        txtSearch.setEditable(false);
        txtSearch.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        btn_LOADvolunteer.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btn_LOADvolunteer.setText("LOAD MY DATA");
        btn_LOADvolunteer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LOADvolunteerActionPerformed(evt);
            }
        });

        lblUSERNAME.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lblUSERNAME.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUSERNAME.setText(">>USERNAME<<");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(656, Short.MAX_VALUE)
                .addComponent(lblCREATED)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_LOADvolunteer, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 566, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(lblUSERNAME)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(33, 33, 33)
                    .addComponent(lblTASK1)
                    .addContainerGap(593, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblUSERNAME))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_LOADvolunteer, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(lblCREATED)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(121, Short.MAX_VALUE)
                    .addComponent(lblTASK1)
                    .addContainerGap()))
        );

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PASSWORD:");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("DURATION");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("YOUR NAME:");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("USERNAME:");

        btnLOGOUT.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnLOGOUT.setText("LOGOUT");
        btnLOGOUT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLOGOUTActionPerformed(evt);
            }
        });

        txtVDuration.setEditable(false);
        txtVDuration.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtV_Username.setEditable(false);
        txtV_Username.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtVName.setEditable(false);
        txtVName.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtV_Password.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        btnUpdate.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnUpdate.setText("change password");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel1))
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtV_Password, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                    .addComponent(txtV_Username)
                    .addComponent(txtVName)
                    .addComponent(txtVDuration))
                .addGap(31, 31, 31)
                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(btnLOGOUT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtVDuration, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txtVName, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(txtV_Username, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtV_Password, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addComponent(btnLOGOUT, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_LOADvolunteerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LOADvolunteerActionPerformed
        // TODO add your handling code here:
        searchV();
    }//GEN-LAST:event_btn_LOADvolunteerActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
   txtV_Password.enable(true);
        
    try{
        
            String query="UPDATE `db_fa1`.`tbl_login1` SET `password` = '"+txtV_Password.getText()+"' WHERE (`username` = '"+txtV_Username.getText()+"')";
            conn=DriverManager.getConnection("jdbc:mysql://localhost/db_fa1","root","");
            pst=conn.prepareStatement(query);
         
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Login User Updated Sucessfully!");
            
            
            
        }catch(HeadlessException | SQLException ex)
        {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnLOGOUTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLOGOUTActionPerformed
        // TODO add your handling code here:
        frmLogin L = new frmLogin();
        L.show();
        this.hide();
    }//GEN-LAST:event_btnLOGOUTActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmVolunteer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmVolunteer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmVolunteer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmVolunteer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmVolunteer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLOGOUT;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btn_LOADvolunteer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblCREATED;
    private javax.swing.JLabel lblTASK1;
    private javax.swing.JLabel lblUSERNAME;
    public javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtVDuration;
    private javax.swing.JTextField txtVName;
    private javax.swing.JTextField txtV_Password;
    private javax.swing.JTextField txtV_Username;
    // End of variables declaration//GEN-END:variables
}
