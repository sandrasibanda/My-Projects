/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khuseleka.securities;

import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class frmMain extends javax.swing.JFrame {

    /**
     * Creates new form frmMain
     */
    public frmMain() {
        initComponents();
    }
    public String strRole;

    public void mSetRoleRights() {
        if (null != strRole) {
            switch (strRole) {
                case "SUPERVISOR":
                    mnuLogout.setEnabled(true);
                    mnuExit.setEnabled(true);
                    mnuRegVisitor.setEnabled(true);
                    mnuRegSecurity.setEnabled(true);
                    mnuViewSecurity.setEnabled(true);
                    mnuViewVisitors.setEnabled(true);

                    break;
                case "SECURITY":
                    mnuLogout.setEnabled(true);
                    mnuExit.setEnabled(true);
                    mnuRegVisitor.setEnabled(true);
                    mnuRegSecurity.setEnabled(false);
                    mnuViewSecurity.setEnabled(false);
                    mnuViewVisitors.setEnabled(true);
                    break;
                case "STANDARD":
                    mnuLogout.setEnabled(true);
                    mnuExit.setEnabled(true);
                    mnuRegVisitor.setEnabled(false);
                    mnuRegSecurity.setEnabled(true);
                    mnuViewSecurity.setEnabled(true);
                    mnuViewVisitors.setEnabled(false);
                    break;
                default:
                    break;
            }
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

        DpMain = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnuFile = new javax.swing.JMenu();
        mnuLogout = new javax.swing.JMenuItem();
        mnuExit = new javax.swing.JMenuItem();
        mnuRegistrations = new javax.swing.JMenu();
        mnuRegSecurity = new javax.swing.JMenuItem();
        mnuRegVisitor = new javax.swing.JMenuItem();
        mnuReports = new javax.swing.JMenu();
        mnuViewSecurity = new javax.swing.JMenuItem();
        mnuViewVisitors = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        DpMain.setBackground(new java.awt.Color(204, 204, 0));

        javax.swing.GroupLayout DpMainLayout = new javax.swing.GroupLayout(DpMain);
        DpMain.setLayout(DpMainLayout);
        DpMainLayout.setHorizontalGroup(
            DpMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        DpMainLayout.setVerticalGroup(
            DpMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 279, Short.MAX_VALUE)
        );

        mnuFile.setText("File");

        mnuLogout.setText("LogOut");
        mnuLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuLogoutActionPerformed(evt);
            }
        });
        mnuFile.add(mnuLogout);

        mnuExit.setText("Exit");
        mnuExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuExitActionPerformed(evt);
            }
        });
        mnuFile.add(mnuExit);

        jMenuBar1.add(mnuFile);

        mnuRegistrations.setText("Registrations");

        mnuRegSecurity.setText("Register Security");
        mnuRegSecurity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuRegSecurityActionPerformed(evt);
            }
        });
        mnuRegistrations.add(mnuRegSecurity);

        mnuRegVisitor.setText("Register Visitor");
        mnuRegVisitor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuRegVisitorActionPerformed(evt);
            }
        });
        mnuRegistrations.add(mnuRegVisitor);

        jMenuBar1.add(mnuRegistrations);

        mnuReports.setText("Reports");

        mnuViewSecurity.setText("View Security Details");
        mnuViewSecurity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuViewSecurityActionPerformed(evt);
            }
        });
        mnuReports.add(mnuViewSecurity);

        mnuViewVisitors.setText("View Visitors Details");
        mnuViewVisitors.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuViewVisitorsActionPerformed(evt);
            }
        });
        mnuReports.add(mnuViewVisitors);

        jMenuBar1.add(mnuReports);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(DpMain, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(DpMain)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mnuRegSecurityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuRegSecurityActionPerformed
        // TODO add your handling code here:
        frmRegisterSecurity Register = new frmRegisterSecurity();
        DpMain.add(Register);
        Register.setVisible(true);
    }//GEN-LAST:event_mnuRegSecurityActionPerformed

    private void mnuRegVisitorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuRegVisitorActionPerformed
        // TODO add your handling code here:
        frmRegisterVisitor Register = new frmRegisterVisitor();
        DpMain.add(Register);
        Register.setVisible(true);
    }//GEN-LAST:event_mnuRegVisitorActionPerformed

    private void mnuViewSecurityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuViewSecurityActionPerformed
        // TODO add your handling code here:
        frmViewSecurityDetails Details = new frmViewSecurityDetails();
        DpMain.add(Details);
        Details.setVisible(true);
    }//GEN-LAST:event_mnuViewSecurityActionPerformed

    private void mnuViewVisitorsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuViewVisitorsActionPerformed
        // TODO add your handling code here:
        frmViewVisitorsDetails Details = new frmViewVisitorsDetails();
        DpMain.add(Details);
        Details.setVisible(true);
    }//GEN-LAST:event_mnuViewVisitorsActionPerformed

    private void mnuLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuLogoutActionPerformed
        // TODO add your handling code here:

        frmLogin frmL = new frmLogin();
        frmL.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_mnuLogoutActionPerformed

    private void mnuExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuExitActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_mnuExitActionPerformed

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
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane DpMain;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem mnuExit;
    private javax.swing.JMenu mnuFile;
    private javax.swing.JMenuItem mnuLogout;
    private javax.swing.JMenuItem mnuRegSecurity;
    private javax.swing.JMenuItem mnuRegVisitor;
    private javax.swing.JMenu mnuRegistrations;
    private javax.swing.JMenu mnuReports;
    private javax.swing.JMenuItem mnuViewSecurity;
    private javax.swing.JMenuItem mnuViewVisitors;
    // End of variables declaration//GEN-END:variables
}
